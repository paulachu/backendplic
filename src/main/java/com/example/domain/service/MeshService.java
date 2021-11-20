package com.example.domain.service;

import com.example.converter.Converter;
import com.example.data.model.LightModel;
import com.example.data.model.MeshModel;
import com.example.domain.entity.LightEntity;
import com.example.domain.entity.MeshEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class MeshService implements MeshServiceInterface {
    @Inject
    PanacheRepositoryBase<MeshModel, Long> meshRepository;

    @Inject
    Converter<MeshModel, MeshEntity> modelToEntity;
    @Inject
    Converter<MeshEntity, MeshModel> entityToModel;

    @Inject
    StorageServiceInterface storageService;

    @Override
    @Transactional
    public MeshEntity addMesh(MeshEntity toAdd)
    {
        MeshModel meshModel = entityToModel.convert(toAdd);
        try {
            String url;
            try {
                url = storageService.storeFile(toAdd.getFilename(), toAdd.getFile(), FileType.Mesh);
            }
            catch (Exception e)
            {
                return null;
            }
            meshRepository.persist(meshModel);
            MeshEntity addedMesh = modelToEntity.convert(meshModel);
            addedMesh.setPresignedUrl(url);
            return addedMesh;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public MeshEntity getByIdMesh(Long id) throws Exception {
        MeshModel meshModel = meshRepository.findById(id);
        if (meshModel != null){
            String url = storageService.getUrlFile(meshModel.getFilename(), FileType.Mesh);
            return modelToEntity.convert(meshModel).withPresignedUrl(url);
        }
        return null;
    }
}
