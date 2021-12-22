package com.example.converter.music.entitymodel;

import com.example.converter.Converter;
import com.example.data.model.MusicModel;
import com.example.domain.entity.MusicEntity;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MusicEntityToMusicModel implements Converter<MusicEntity, MusicModel> {
    @Override
    public MusicModel convertNotNull(MusicEntity from) {
        return new MusicModel().withId(from.getId()).withFilename(from.getFilename());
    }
}
