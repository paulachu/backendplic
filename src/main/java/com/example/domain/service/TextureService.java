package com.example.domain.service;

import com.example.ConfigurationProperties;
import com.example.converter.Converter;
import com.example.data.model.LightModel;
import com.example.data.model.TextureModel;
import com.example.domain.entity.LightEntity;
import com.example.domain.entity.TextureEntity;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import io.minio.http.Method;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

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
    public TextureEntity getByIdTexture(Long id){
        TextureModel textureModel = textureRepository.findById(id);
        if (textureModel != null){
            return modelToEntity.convert(textureModel);
        }
        return null;
    }
}
