package com.example.domain.service;

import com.example.converter.Converter;
import com.example.data.model.TextureModel;
import com.example.domain.entity.TextureEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TextureService implements TextureServiceInterface {
    @Inject
    PanacheRepositoryBase<TextureModel, Long> textureRepository;
    @Inject
    Converter<TextureModel, TextureEntity> modelToEntity;
    @Inject
    Converter<TextureEntity, TextureModel> entityToModel;

    @Inject
    StorageServiceInterface storageService;

    @Override
    @Transactional
    public TextureEntity addTexture(TextureEntity toAdd)
    {
        TextureModel textureModel = entityToModel.convert(toAdd);
        try {
            String url;
            try {
                url = storageService.storeFile(toAdd.getFilename(), toAdd.getFile(), FileType.Texture);
            }
            catch (Exception e)
            {
                return null;
            }
            textureRepository.persist(textureModel);
            TextureEntity addedTexture = modelToEntity.convert(textureModel);
            addedTexture.setPresignedUrl(url);
            return addedTexture;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public TextureEntity getByIdTexture(Long id) throws Exception {
        TextureModel textureModel = textureRepository.findById(id);
        if (textureModel != null){
            String url = storageService.getUrlFile(textureModel.getFilename(), FileType.Texture);
            return modelToEntity.convert(textureModel).withPresignedUrl(url);
        }
        return null;
    }

    @Override
    public List<TextureEntity> getTextures() throws Exception {
        List<TextureModel> textureRepo = textureRepository.listAll();
        if (textureRepo == null) {
            return null;
        }
        List<TextureEntity> res = new ArrayList<>();
        for (TextureModel i  : textureRepo) {
            String url = storageService.getUrlFile(i.getFilename(), FileType.Texture);
            res.add(modelToEntity.convert(i).withPresignedUrl(url));
        }
        return res;
    }

    @Override
    @Transactional
    public boolean deleteTexture(Long id)
    {
        return textureRepository.deleteById(id);
    }

    @Override
    public TextureEntity putTexture(TextureEntity textureEntityToAdd, Long id) {
        TextureModel row = textureRepository.findById(id, LockModeType.PESSIMISTIC_WRITE);
        try {
            String url;
            try {
                url = storageService.storeFile(textureEntityToAdd.getFilename(), textureEntityToAdd.getFile(), FileType.Texture);
            }
            catch (Exception e)
            {
                return null;
            }
            row.setFilename(textureEntityToAdd.getFilename());
            TextureEntity addedTexture = modelToEntity.convert(row);
            addedTexture.setPresignedUrl(url);
            return addedTexture;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
