package com.example.converter.texture.dtoentity;

import com.example.converter.Converter;
import com.example.domain.entity.TextureEntity;
import com.example.presentation.texture.AddTextureResponse;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TextureEntityToAddTextureResponse implements Converter<TextureEntity, AddTextureResponse> {
    @Override
    public AddTextureResponse convertNotNull(TextureEntity from) {
        return new AddTextureResponse().withId(from.getId()).withFilename(from.getFilename()).withPresignedUrl(from.getPresignedUrl());
    }
}
