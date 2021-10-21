package com.example.converter.music.dtoentity;

import com.example.converter.Converter;
import com.example.domain.entity.MusicEntity;
import com.example.presentation.music.AddMusicResponse;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MusicEntityToAddMusicResponse implements Converter<MusicEntity, AddMusicResponse> {
    @Override
    public AddMusicResponse convertNotNull(MusicEntity from) {
        return new AddMusicResponse().withId(from.getId()).withFilename(from.getFilename()).withPresignedUrl(from.getPresignedUrl());
    }
}
