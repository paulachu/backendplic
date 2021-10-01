package com.example.domain.service;

import com.example.domain.entity.LevelEntity;

import java.util.List;

public interface LevelServiceInterface {
    LevelEntity addLevel(LevelEntity toAdd);
    List<LevelEntity> getLevels();
    LevelEntity putLevel(LevelEntity toUpdate, long id);
}
