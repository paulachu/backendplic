package com.example.data.repository;

import com.example.data.model.TextureModel;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TextureRepository implements PanacheRepositoryBase<TextureModel, Long> {
}
