package com.example.presentation.resource;

import com.example.converter.Converter;
import com.example.domain.entity.LevelEntity;
import com.example.domain.entity.LightEntity;
import com.example.domain.service.LevelServiceInterface;
import com.example.presentation.level.AddLevelRequest;
import com.example.presentation.level.AddLevelResponse;
import com.example.presentation.light.AddLightRequest;
import com.example.presentation.light.AddLightResponse;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("level")
@Produces(MediaType.APPLICATION_JSON)
public class LevelResource {
    @Inject
    LevelServiceInterface levelService;
    @Inject
    Converter<LevelEntity, AddLevelResponse> entityToAddResponse;
    @Inject
    Converter<AddLevelRequest, LevelEntity> addRequestToEntity;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public AddLevelResponse addLevel(AddLevelRequest addLevelRequest)
    {
        LevelEntity levelEntityToAdd = addRequestToEntity.convert(addLevelRequest);
        LevelEntity levelEntity = levelService.addLevel(levelEntityToAdd);
        return entityToAddResponse.convert(levelEntity);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AddLevelResponse> getLevels(){
        List<LevelEntity> levelEntityList = levelService.getLevels();
        return entityToAddResponse.convertList(levelEntityList);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AddLevelResponse putLevel(@PathParam("id") Long id, AddLevelRequest addLevelRequest){
        LevelEntity levelEntityToAdd = addRequestToEntity.convert(addLevelRequest);
        LevelEntity levelEntity = levelService.putLevel(levelEntityToAdd, id);
        return entityToAddResponse.convert(levelEntity);
    }
}
