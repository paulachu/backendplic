package com.example.data.repository;

import com.example.data.model.MeshModel;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MeshRepository implements PanacheRepositoryBase<MeshModel, Long> {
}
