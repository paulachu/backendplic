package com.example.domain.service;

import com.example.converter.Converter;
import com.example.data.model.LightModel;
import com.example.domain.entity.LightEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

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
}
