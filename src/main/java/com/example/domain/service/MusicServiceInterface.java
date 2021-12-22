package com.example.domain.service;

import com.example.domain.entity.MusicEntity;

import java.util.List;

public interface MusicServiceInterface {
    MusicEntity addMusic(MusicEntity toAdd);
    List<MusicEntity> getMusics() throws Exception;
    MusicEntity getByIdMusic(Long id) throws Exception;
    boolean deleteMusic(Long id);
    MusicEntity putMusic(MusicEntity musicEntityToAdd, Long id);
}
