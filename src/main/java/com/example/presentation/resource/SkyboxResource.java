package com.example.presentation.resource;

import com.example.converter.Converter;
import com.example.domain.entity.SkyboxEntity;
import com.example.domain.service.SkyboxServiceInterface;
import com.example.presentation.skybox.AddSkyboxResponse;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;

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
    public Response addSkybox(MultipartFormDataInput input)
    {
        try {
            File file = input.getFormDataPart("file", File.class, null);
            String name = input.getFormDataPart("filename", String.class, null);;
            SkyboxEntity skyboxEntity = new SkyboxEntity().withFilename(name).withFile(file);
            SkyboxEntity skyboxEntityAdded = skyboxService.addSkybox(skyboxEntity);
            return Response.status(Response.Status.CREATED).entity(entityToAddResponse.convert(skyboxEntityAdded))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
