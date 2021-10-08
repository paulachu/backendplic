package com.example.converter.level.entitymodel;

import com.example.converter.Converter;
import com.example.data.model.LevelModel;
import com.example.data.model.LightModel;
import com.example.domain.entity.LevelEntity;
import com.example.domain.entity.LightEntity;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.stream.Collectors;

@ApplicationScoped
public class LevelModelToLevelEntity implements Converter<LevelModel, LevelEntity> {
    @Override
    public LevelEntity convertNotNull(LevelModel from) {
        return new LevelEntity()
                .withId(from.getId())
                .withLight(from.getLight().getId())
                .withMeshs(from.getMeshs().stream().map(meshModel -> meshModel.getId()).collect(Collectors.toList()))
                .withMusic(from.getMusic().getId())
                .withSkybox(from.getSkybox().getId())
                .withTexture(from.getTexture().getId());
    }
}
