package com.example.converter.level.dtoentity;

import com.example.converter.Converter;
import com.example.domain.entity.LevelEntity;
import com.example.presentation.level.AddLevelRequest;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AddLevelRequestToLevelEntity implements Converter<AddLevelRequest, LevelEntity> {
    @Override
    public LevelEntity convertNotNull(AddLevelRequest from) {
        return new LevelEntity()
                .withLevelNumber(from.getLevelNumber())
                .withLight(from.getLight())
                .withMeshs(from.getMeshs())
                .withMusic(from.getMusic())
                .withSkybox(from.getSkybox())
                .withTexture(from.getTexture());
    }
}
