package com.example.domain.service;

import com.example.data.model.MusicModel;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MusicService implements MusicServiceInterface {
    @Inject
    PanacheRepositoryBase<MusicModel, Long> musicRepository;
}
