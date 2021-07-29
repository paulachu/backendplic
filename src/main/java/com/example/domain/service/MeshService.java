package com.example.domain.service;

import com.example.data.model.MeshModel;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MeshService implements MeshServiceInterface {
    @Inject
    PanacheRepositoryBase<MeshModel, Long> meshRepository;
}
