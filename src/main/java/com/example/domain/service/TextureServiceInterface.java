package com.example.domain.service;

import com.example.domain.entity.LevelEntity;
import com.example.domain.entity.TextureEntity;

import java.util.List;

public interface TextureServiceInterface {
    TextureEntity addTexture(TextureEntity toAdd);
    List<TextureEntity> getTextures() throws Exception;
    TextureEntity getByIdTexture(Long id) throws Exception;
}
