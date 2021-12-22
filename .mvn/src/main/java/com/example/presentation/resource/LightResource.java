package com.example.presentation.resource;

import com.example.converter.Converter;
import com.example.domain.entity.LightEntity;
import com.example.domain.service.LightServiceInterface;
import com.example.presentation.light.AddLightRequest;
import com.example.presentation.light.AddLightResponse;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AddLightResponse putLight(@PathParam("id") Long id, AddLightRequest addLightRequest){
        LightEntity lightEntityToAdd = addRequestToEntity.convert(addLightRequest);
        LightEntity lightEntity = lightService.putLight(lightEntityToAdd, id);
        return entityToAddResponse.convert(lightEntity);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public AddLightResponse getByIdLight(@PathParam("id") Long id){
        LightEntity lightEntity = lightService.getByIdLight(id);
        return entityToAddResponse.convert(lightEntity);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AddLightResponse> getLights(){
        List<LightEntity> lightEntityList = lightService.getLights();
        return entityToAddResponse.convertList(lightEntityList);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteLight(@PathParam("id") Long id) {
        if (lightService.deleteLight(id)){
            return Response.status(Response.Status.ACCEPTED).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
