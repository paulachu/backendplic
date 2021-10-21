package com.example.domain.service;

import com.example.converter.Converter;
import com.example.data.model.LightModel;
import com.example.data.model.MusicModel;
import com.example.domain.entity.LightEntity;
import com.example.domain.entity.MusicEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

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
    public MusicEntity getByIdMusic(Long id){
        MusicModel musicModel = musicRepository.findById(id);
        if (musicModel != null){
            return modelToEntity.convert(musicModel);
        }
        return null;
    }
}
