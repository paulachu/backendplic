package com.example.data.repository;

import com.example.data.model.LevelModel;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LevelRepository implements PanacheRepositoryBase<LevelModel, Long> {
}
