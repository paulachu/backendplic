package com.example.converter.music.entitymodel;

import com.example.converter.Converter;
import com.example.data.model.MusicModel;
import com.example.domain.entity.MusicEntity;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MusicModelToMusicEntity implements Converter<MusicModel, MusicEntity> {
    @Override
    public MusicEntity convertNotNull(MusicModel from) {
        return new MusicEntity().withId(from.getId()).withFilename(from.getFilename());
    }
}
