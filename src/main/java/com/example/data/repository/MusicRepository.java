package com.example.data.repository;

import com.example.data.model.MusicModel;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MusicRepository implements PanacheRepositoryBase<MusicModel, Long> {
}
