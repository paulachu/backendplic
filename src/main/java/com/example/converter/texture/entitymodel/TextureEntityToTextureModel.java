package com.example.converter.texture.entitymodel;

import com.example.converter.Converter;
import com.example.data.model.TextureModel;
import com.example.domain.entity.TextureEntity;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TextureEntityToTextureModel implements Converter<TextureEntity, TextureModel> {
    @Override
    public TextureModel convertNotNull(TextureEntity from) {
        return new TextureModel().withId(from.getId()).withFilename(from.getFilename());
    }

}
