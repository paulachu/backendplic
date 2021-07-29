package com.example.converter.light.dtoentity;

import com.example.converter.Converter;
import com.example.domain.entity.LightEntity;
import com.example.presentation.light.AddLightRequest;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AddLightRequestToLightEntity implements Converter<AddLightRequest, LightEntity> {
    @Override
    public LightEntity convertNotNull(AddLightRequest from) {
        return new LightEntity()
                .withRed(from.getRed())
                .withGreen(from.getGreen())
                .withBlue(from.getBlue())
                .withAlpha(from.getAlpha());
    }
}
