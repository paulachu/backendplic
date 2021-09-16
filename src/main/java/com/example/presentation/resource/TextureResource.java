package com.example.presentation.resource;

import com.example.converter.Converter;
import com.example.domain.entity.LightEntity;
import com.example.domain.entity.TextureEntity;
import com.example.domain.service.LightServiceInterface;
import com.example.domain.service.TextureServiceInterface;
import com.example.presentation.light.AddLightRequest;
import com.example.presentation.light.AddLightResponse;
import com.example.presentation.texture.AddTextureResponse;
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
    public Response addTexture(MultipartFormDataInput input)
    {
        try {
            File file = input.getFormDataPart("file", File.class, null);
            String name = input.getFormDataPart("filename", String.class, null);;
            TextureEntity textureEntity = new TextureEntity().withFilename(name).withFile(file);
            TextureEntity textureEntityAdded = textureService.addTexture(textureEntity);
            return Response.status(Response.Status.CREATED).entity(entityToAddResponse.convert(textureEntityAdded))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
