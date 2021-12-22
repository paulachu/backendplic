package com.example.converter.skybox.dtoentity;

import com.example.converter.Converter;
import com.example.domain.entity.SkyboxEntity;
import com.example.presentation.skybox.AddSkyboxResponse;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SkyboxEntityToAddSkyboxResponse implements Converter<SkyboxEntity, AddSkyboxResponse> {
    @Override
    public AddSkyboxResponse convertNotNull(SkyboxEntity from) {
        return new AddSkyboxResponse().withId(from.getId()).withFilename(from.getFilename()).withPresignedUrl(from.getPresignedUrl());
    }
}
