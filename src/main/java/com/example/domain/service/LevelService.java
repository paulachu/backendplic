package com.example.domain.service;

import com.example.data.model.LevelModel;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class LevelService implements LevelServiceInterface {
    @Inject
    PanacheRepositoryBase<LevelModel, Long> levelRepository;
}
