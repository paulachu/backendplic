package com.example.converter.light.entitymodel;

import com.example.converter.Converter;
import com.example.data.model.LightModel;
import com.example.domain.entity.LightEntity;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LightModelToLightEntity implements Converter<LightModel, LightEntity> {
    @Override
    public LightEntity convertNotNull(LightModel from) {
        return new LightEntity()
                .withId(from.getId())
                .withRed(from.getRed())
                .withGreen(from.getGreen())
                .withBlue(from.getBlue())
                .withAlpha(from.getAlpha());
    }
}
