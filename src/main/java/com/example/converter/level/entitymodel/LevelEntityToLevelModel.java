package com.example.converter.level.entitymodel;

import com.example.converter.Converter;
import com.example.data.model.*;
import com.example.domain.entity.LevelEntity;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LevelEntityToLevelModel implements Converter<LevelEntity, LevelModel> {
    @Override
    public LevelModel convertNotNull(LevelEntity from) {
        List<MeshModel> meshModelList = new ArrayList<MeshModel>();
        for (long meshId: from.getMeshs())
        {
            meshModelList.add(new MeshModel().withId(meshId));
        }
        return new LevelModel()
                .withId(from.getId())
                .withLight(new LightModel().withId(from.getLight()))
                .withMeshs(meshModelList)
                .withMusic(new MusicModel().withId(from.getMusic()))
                .withSkybox(new SkyboxModel().withId(from.getSkybox()))
                .withTexture(new TextureModel().withId(from.getTexture()));
    }
}
