package com.example.converter.light.dtoentity;

import com.example.converter.Converter;
import com.example.domain.entity.LightEntity;
import com.example.presentation.light.AddLightResponse;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LightEntityToAddLightResponse implements Converter<LightEntity, AddLightResponse> {
    @Override
    public AddLightResponse convertNotNull(LightEntity from) {
        return new AddLightResponse()
                .withId(from.getId())
                .withRed(from.getRed())
                .withGreen(from.getGreen())
                .withBlue(from.getBlue())
                .withAlpha(from.getAlpha());
    }
}
