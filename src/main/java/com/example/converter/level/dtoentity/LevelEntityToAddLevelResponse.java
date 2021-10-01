package com.example.converter.level.dtoentity;

import com.example.converter.Converter;
import com.example.domain.entity.LevelEntity;
import com.example.presentation.level.AddLevelResponse;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LevelEntityToAddLevelResponse implements Converter<LevelEntity, AddLevelResponse> {
    @Override
    public AddLevelResponse convertNotNull(LevelEntity from) {
        return new AddLevelResponse()
                .withId(from.getId())
                .withLight(from.getLight())
                .withMeshs(from.getMeshs())
                .withMusic(from.getMusic())
                .withSkybox(from.getSkybox())
                .withTexture(from.getTexture());
    }
}
