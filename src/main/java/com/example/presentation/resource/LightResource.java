package com.example.presentation.resource;

import com.example.converter.Converter;
import com.example.domain.entity.LightEntity;
import com.example.domain.service.LightServiceInterface;
import com.example.presentation.light.AddLightRequest;
import com.example.presentation.light.AddLightResponse;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("light")
@Produces(MediaType.APPLICATION_JSON)
public class LightResource {
    @Inject
    LightServiceInterface lightService;
    @Inject
    Converter<LightEntity, AddLightResponse> entityToAddResponse;
    @Inject
    Converter<AddLightRequest, LightEntity> addRequestToEntity;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public AddLightResponse addLight(AddLightRequest addLightRequest)
    {
        LightEntity lightEntityToAdd = addRequestToEntity.convert(addLightRequest);
        LightEntity lightEntity = lightService.addLight(lightEntityToAdd);
        return entityToAddResponse.convert(lightEntity);
    }
}
