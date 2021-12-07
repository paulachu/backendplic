package com.example.presentation.resource;

import com.example.converter.Converter;
import com.example.domain.entity.LevelEntity;
import com.example.domain.entity.LightEntity;
import com.example.domain.entity.SkyboxEntity;
import com.example.domain.service.SkyboxServiceInterface;
import com.example.presentation.level.AddLevelResponse;
import com.example.presentation.light.AddLightRequest;
import com.example.presentation.light.AddLightResponse;
import com.example.presentation.skybox.AddSkyboxRequest;
import com.example.presentation.skybox.AddSkyboxResponse;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.util.List;

@Path("skybox")
@Produces(MediaType.APPLICATION_JSON)
public class SkyboxResource {
    @Inject
    SkyboxServiceInterface skyboxService;

    @Inject
    Converter<SkyboxEntity, AddSkyboxResponse> entityToAddResponse;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Transactional
    public Response addSkybox(@Valid @Parameter(hidden = true) @MultipartForm AddSkyboxRequest addSkyboxRequest)
    {
        try {
            SkyboxEntity skyboxEntity = new SkyboxEntity().withFilename(addSkyboxRequest.getFilename()).withFile(addSkyboxRequest.getFile());
            SkyboxEntity skyboxEntityAdded = skyboxService.addSkybox(skyboxEntity);
            return Response.status(Response.Status.CREATED).entity(entityToAddResponse.convert(skyboxEntityAdded))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public AddSkyboxResponse getByIdSkybox(@PathParam("id") Long id) throws Exception {
        SkyboxEntity skyboxEntity = skyboxService.getByIdSkybox(id);
        return entityToAddResponse.convert(skyboxEntity);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AddSkyboxResponse> getSkyboxs() throws Exception {
        List<SkyboxEntity> skyboxEntityList = skyboxService.getSkyboxs();
        return entityToAddResponse.convertList(skyboxEntityList);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSkybox(@PathParam("id") Long id) {
        if (skyboxService.deleteSkybox(id)){
            return Response.status(Response.Status.ACCEPTED).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
