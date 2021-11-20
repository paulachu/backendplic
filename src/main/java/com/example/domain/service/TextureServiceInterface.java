package com.example.domain.service;

import com.example.domain.entity.TextureEntity;

public interface TextureServiceInterface {
    TextureEntity addTexture(TextureEntity toAdd);

    TextureEntity getByIdTexture(Long id) throws Exception;
}
