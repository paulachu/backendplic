package com.example.domain.service;

import com.example.converter.Converter;
import com.example.data.model.LightModel;
import com.example.domain.entity.LightEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class LightService implements LightServiceInterface {
    @Inject
    PanacheRepositoryBase<LightModel, Long> lightRepository;
    @Inject
    Converter<LightModel, LightEntity> modelToEntity;
    @Inject
    Converter<LightEntity, LightModel> entityToModel;


    @Override
    @Transactional
    public LightEntity addLight(LightEntity toAdd)
    {
        LightModel lightModel = entityToModel.convert(toAdd);
        lightRepository.persist(lightModel);
        return modelToEntity.convert(lightModel);
    }

    @Override
    @Transactional
    public LightEntity putLight(LightEntity toUpdate, Long id){
        LightModel lightModel = entityToModel.convert(toUpdate);
        LightModel row = lightRepository.findById(id, LockModeType.PESSIMISTIC_WRITE);
        if (row != null) {
            row.setAlpha(lightModel.getAlpha());
            row.setBlue(lightModel.getBlue());
            row.setGreen(lightModel.getGreen());
            row.setRed(lightModel.getRed());
            return modelToEntity.convert(row);
        }
        return null;
    }

    @Override
    @Transactional
    public LightEntity getByIdLight(Long id){
        LightModel lightModel = lightRepository.findById(id);
        if (lightModel != null){
            return modelToEntity.convert(lightModel);
        }
        return null;
    }

    @Override
    public List<LightEntity> getLights(){
        List<LightModel> lightRepo = lightRepository.listAll();
        List<LightEntity> res = modelToEntity.convertList(lightRepo);
        return res;
    }

    @Override
    @Transactional
    public boolean deleteLight(Long id)
    {
        return lightRepository.deleteById(id);
    }
}
