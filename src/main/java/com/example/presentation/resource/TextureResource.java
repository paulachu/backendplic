package com.example.presentation.resource;

import com.example.converter.Converter;
import com.example.domain.entity.LevelEntity;
import com.example.domain.entity.LightEntity;
import com.example.domain.entity.TextureEntity;
import com.example.domain.service.LightServiceInterface;
import com.example.domain.service.TextureServiceInterface;
import com.example.presentation.level.AddLevelResponse;
import com.example.presentation.light.AddLightRequest;
import com.example.presentation.light.AddLightResponse;
import com.example.presentation.texture.AddTextureRequest;
import com.example.presentation.texture.AddTextureResponse;
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

@Path("texture")
@Produces(MediaType.APPLICATION_JSON)
public class TextureResource {
    @Inject
    TextureServiceInterface textureService;

    @Inject
    Converter<TextureEntity, AddTextureResponse> entityToAddResponse;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Transactional
    public Response addTexture(@Valid @Parameter(hidden = true) @MultipartForm AddTextureRequest addTextureRequest)
    {
        try {
            TextureEntity textureEntity = new TextureEntity().withFilename(addTextureRequest.getFilename()).withFile(addTextureRequest.getFile());
            TextureEntity textureEntityAdded = textureService.addTexture(textureEntity);
            return Response.status(Response.Status.CREATED).entity(entityToAddResponse.convert(textureEntityAdded))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public AddTextureResponse getByIdLight(@PathParam("id") Long id) throws Exception {
        TextureEntity textureEntity = textureService.getByIdTexture(id);
        return entityToAddResponse.convert(textureEntity);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AddTextureResponse> getTextures() throws Exception {
        List<TextureEntity> textureEntityList = textureService.getTextures();
        return entityToAddResponse.convertList(textureEntityList);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTexture(@PathParam("id") Long id) {
        if (textureService.deleteTexture(id)){
            return Response.status(Response.Status.ACCEPTED).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
