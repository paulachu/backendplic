package com.example.domain.service;

import com.example.converter.Converter;
import com.example.data.model.LightModel;
import com.example.data.model.SkyboxModel;
import com.example.domain.entity.LightEntity;
import com.example.domain.entity.SkyboxEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class SkyboxService implements SkyboxServiceInterface {
    @Inject
    PanacheRepositoryBase<SkyboxModel, Long> skyboxRepository;

    @Inject
    Converter<SkyboxModel, SkyboxEntity> modelToEntity;
    @Inject
    Converter<SkyboxEntity, SkyboxModel> entityToModel;

    @Inject
    StorageServiceInterface storageService;

    @Override
    @Transactional
    public SkyboxEntity addSkybox(SkyboxEntity toAdd)
    {
        SkyboxModel skyboxModel = entityToModel.convert(toAdd);
        try {
            String url;
            try {
                url = storageService.storeFile(toAdd.getFilename(), toAdd.getFile(), FileType.Skybox);
            }
            catch (Exception e)
            {
                return null;
            }
            skyboxRepository.persist(skyboxModel);
            SkyboxEntity addedSkybox = modelToEntity.convert(skyboxModel);
            addedSkybox.setPresignedUrl(url);
            return addedSkybox;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public SkyboxEntity getByIdSkybox(Long id){
        SkyboxModel skyboxModel = skyboxRepository.findById(id);
        if (skyboxModel != null){
            return modelToEntity.convert(skyboxModel);
        }
        return null;
    }
}
