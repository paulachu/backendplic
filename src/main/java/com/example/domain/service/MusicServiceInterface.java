package com.example.domain.service;

import com.example.domain.entity.MusicEntity;

public interface MusicServiceInterface {
    MusicEntity addMusic(MusicEntity toAdd);

    MusicEntity getByIdMusic(Long id) throws Exception;
}
