package com.example.data.repository;

import com.example.data.model.LightModel;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LightRepository implements PanacheRepositoryBase<LightModel, Long> {
}
