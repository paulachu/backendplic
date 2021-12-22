package com.example.converter.skybox.entitymodel;

import com.example.converter.Converter;
import com.example.data.model.SkyboxModel;
import com.example.domain.entity.SkyboxEntity;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SkyboxModelToSkyboxEntity implements Converter<SkyboxModel, SkyboxEntity> {
    @Override
    public SkyboxEntity convertNotNull(SkyboxModel from) {
        return new SkyboxEntity().withId(from.getId()).withFilename(from.getFilename());
    }
}
