package com.example.domain.service;

import com.example.converter.Converter;
import com.example.data.model.LevelModel;
import com.example.data.model.LightModel;
import com.example.data.model.MeshModel;
import com.example.data.model.MusicModel;
import com.example.domain.entity.LevelEntity;
import com.example.domain.entity.LightEntity;
import com.example.domain.entity.MeshEntity;
import com.example.domain.entity.MusicEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MusicService implements MusicServiceInterface {
    @Inject
    PanacheRepositoryBase<MusicModel, Long> musicRepository;
    @Inject
    Converter<MusicModel, MusicEntity> modelToEntity;
    @Inject
    Converter<MusicEntity, MusicModel> entityToModel;

    @Inject
    StorageServiceInterface storageService;

    @Override
    @Transactional
    public MusicEntity addMusic(MusicEntity toAdd)
    {
        MusicModel musicModel = entityToModel.convert(toAdd);
        try {
            String url;
            try {
                url = storageService.storeFile(toAdd.getFilename(), toAdd.getFile(), FileType.Music);
                //musicModel.setPresignedUrl(url);
            }
            catch (Exception e)
            {
                return null;
            }
            musicRepository.persist(musicModel);
            MusicEntity addedMusic = modelToEntity.convert(musicModel);
            addedMusic.setPresignedUrl(url);
            return addedMusic;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public MusicEntity getByIdMusic(Long id) throws Exception {
        MusicModel musicModel = musicRepository.findById(id);
        if (musicModel != null){
            String url = storageService.getUrlFile(musicModel.getFilename(), FileType.Music);
            return modelToEntity.convert(musicModel).withPresignedUrl(url);
        }
        return null;
    }

    @Override
    public List<MusicEntity> getMusics() throws Exception {
        List<MusicModel> musicRepo = musicRepository.listAll();
        if (musicRepo == null) {
            return null;
        }
        List<MusicEntity> res = new ArrayList<>();
        for (MusicModel i  : musicRepo) {
            String url = storageService.getUrlFile(i.getFilename(), FileType.Music);
            res.add(modelToEntity.convert(i).withPresignedUrl(url));
        }
        return res;
    }

    @Override
    @Transactional
    public boolean deleteMusic(Long id)
    {
        return musicRepository.deleteById(id);
    }
}
