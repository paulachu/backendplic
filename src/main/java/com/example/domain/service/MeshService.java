package com.example.domain.service;

import com.example.converter.Converter;
import com.example.data.model.LevelModel;
import com.example.data.model.LightModel;
import com.example.data.model.MeshModel;
import com.example.domain.entity.LevelEntity;
import com.example.domain.entity.LightEntity;
import com.example.domain.entity.MeshEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<MeshEntity> getMeshs() throws Exception {
        List<MeshModel> meshRepo = meshRepository.listAll();
        if (meshRepo == null) {
            return null;
        }
        List<MeshEntity> res = new ArrayList<>();
        for (MeshModel i  : meshRepo) {
            String url = storageService.getUrlFile(i.getFilename(), FileType.Mesh);
            res.add(modelToEntity.convert(i).withPresignedUrl(url));
        }
        return res;
    }
}
