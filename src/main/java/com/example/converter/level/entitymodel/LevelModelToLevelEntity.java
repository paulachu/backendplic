package com.example.converter.level.entitymodel;

import com.example.converter.Converter;
import com.example.data.model.LevelModel;
import com.example.domain.entity.LevelEntity;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LevelModelToLevelEntity implements Converter<LevelModel, LevelEntity> {
    @Override
    public LevelEntity convertNotNull(LevelModel from) {
        return new LevelEntity()
                .withId(from.getId())
                .withLight(from.getLight())
                .withMeshs(from.getMeshs())
                .withMusic(from.getMusic())
                .withSkybox(from.getSkybox())
                .withTexture(from.getTexture());
    }
}
