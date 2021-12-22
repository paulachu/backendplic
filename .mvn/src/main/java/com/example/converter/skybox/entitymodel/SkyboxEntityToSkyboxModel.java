package com.example.converter.skybox.entitymodel;

import com.example.converter.Converter;
import com.example.data.model.SkyboxModel;
import com.example.domain.entity.SkyboxEntity;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SkyboxEntityToSkyboxModel implements Converter<SkyboxEntity, SkyboxModel> {
    @Override
    public SkyboxModel convertNotNull(SkyboxEntity from) {
        return new SkyboxModel().withId(from.getId()).withFilename(from.getFilename());
    }
}
