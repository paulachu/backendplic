package com.example.converter.level.entitymodel;

import com.example.converter.Converter;
import com.example.data.model.LevelModel;
import com.example.domain.entity.LevelEntity;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LevelEntityToLevelModel implements Converter<LevelEntity, LevelModel> {
    @Override
    public LevelModel convertNotNull(LevelEntity from) {
        return new LevelModel()
                .withId(from.getId())
                .withLight(from.getLight())
                .withMeshs(from.getMeshs())
                .withMusic(from.getMusic())
                .withSkybox(from.getSkybox())
                .withTexture(from.getTexture());
    }
}
