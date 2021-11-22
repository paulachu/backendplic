package com.example.domain.service;

import com.example.converter.Converter;
import com.example.data.model.LevelModel;
import com.example.data.model.LightModel;
import com.example.domain.entity.LevelEntity;
import com.example.domain.entity.LightEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class LevelService implements LevelServiceInterface {
    @Inject
    PanacheRepositoryBase<LevelModel, Long> levelRepository;
    @Inject
    Converter<LevelModel, LevelEntity> modelToEntity;
    @Inject
    Converter<LevelEntity, LevelModel> entityToModel;
    @Inject
    Converter<LightModel, LightEntity> lightModelLightEntityConverter;
    @Override
    @Transactional
    public LevelEntity addLevel(LevelEntity toAdd)
    {
        LevelModel levelModel = entityToModel.convert(toAdd);
        levelRepository.persist(levelModel);
        return modelToEntity.convert(levelModel);
    }

    @Override
    public List<LevelEntity> getLevels(){
        List<LevelModel> levelRepo = levelRepository.listAll();
        List<LevelEntity> res = modelToEntity.convertList(levelRepo);
        return res;
    }

    @Override
    @Transactional
    public LevelEntity putLevel(LevelEntity toUpdate, long id){
        LevelModel levelModel = entityToModel.convert(toUpdate);
        LevelModel row = levelRepository.findById(id, LockModeType.PESSIMISTIC_WRITE);
        if (row != null) {
            row.setLevelNumber(levelModel.getLevelNumber());
            row.setLight(levelModel.getLight());
            row.setMeshs(levelModel.getMeshs());
            row.setMusic(levelModel.getMusic());
            row.setSkybox(levelModel.getSkybox());
            row.setTexture(levelModel.getTexture());
            return modelToEntity.convert(row);
        }
        return null;
    }
}
