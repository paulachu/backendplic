package com.example.domain.service;

import com.example.data.model.SkyboxModel;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class SkyboxService implements SkyboxServiceInterface {
    @Inject
    PanacheRepositoryBase<SkyboxModel, Long> skyboxRepository;
}
