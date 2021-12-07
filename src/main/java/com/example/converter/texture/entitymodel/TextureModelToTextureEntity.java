package com.example.converter.texture.entitymodel;

import com.example.converter.Converter;
import com.example.data.model.TextureModel;
import com.example.domain.entity.TextureEntity;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TextureModelToTextureEntity implements Converter<TextureModel, TextureEntity> {
    @Override
    public TextureEntity convertNotNull(TextureModel from) {
        return new TextureEntity().withId(from.getId()).withFilename(from.getFilename());
    }
}
