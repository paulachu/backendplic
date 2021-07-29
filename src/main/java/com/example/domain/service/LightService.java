package com.example.domain.service;

import com.example.data.model.LightModel;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class LightService implements LightServiceInterface {
    @Inject
    PanacheRepositoryBase<LightModel, Long> lightRepository;
}
