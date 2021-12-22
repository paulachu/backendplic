package com.example.data.repository;

import com.example.data.model.SkyboxModel;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SkyboxRepository implements PanacheRepositoryBase<SkyboxModel, Long> {
}
