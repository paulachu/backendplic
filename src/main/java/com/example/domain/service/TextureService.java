package com.example.domain.service;

import com.example.data.model.TextureModel;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class TextureService implements TextureServiceInterface {
    @Inject
    PanacheRepositoryBase<TextureModel, Long> textureRepository;
}
