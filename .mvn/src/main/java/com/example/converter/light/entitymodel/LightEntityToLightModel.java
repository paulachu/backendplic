package com.example.converter.light.entitymodel;

import com.example.converter.Converter;
import com.example.data.model.LightModel;
import com.example.domain.entity.LightEntity;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LightEntityToLightModel implements Converter<LightEntity, LightModel> {
    @Override
    public LightModel convertNotNull(LightEntity from) {
        return new LightModel()
                .withId(from.getId())
                .withRed(from.getRed())
                .withGreen(from.getGreen())
                .withBlue(from.getBlue())
                .withAlpha(from.getAlpha());
    }
}
