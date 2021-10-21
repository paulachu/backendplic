package com.example.presentation.resource;

import com.example.converter.Converter;
import com.example.domain.entity.LightEntity;
import com.example.domain.entity.MusicEntity;
import com.example.domain.service.MusicServiceInterface;
import com.example.presentation.light.AddLightResponse;
import com.example.presentation.music.AddMusicResponse;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;

@Path("music")
@Produces(MediaType.APPLICATION_JSON)
public class MusicResource {
    @Inject
    MusicServiceInterface musicService;

    @Inject
    Converter<MusicEntity, AddMusicResponse> entityToAddResponse;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Transactional
    public Response addMusic(MultipartFormDataInput input)
    {
        try {
            File file = input.getFormDataPart("file", File.class, null);
            String name = input.getFormDataPart("filename", String.class, null);;
            MusicEntity musicEntity = new MusicEntity().withFilename(name).withFile(file);
            MusicEntity musicEntityAdded = musicService.addMusic(musicEntity);
            return Response.status(Response.Status.CREATED).entity(entityToAddResponse.convert(musicEntityAdded))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public AddMusicResponse getByIdMusic(@PathParam("id") Long id){
        MusicEntity musicEntity = musicService.getByIdMusic(id);
        return entityToAddResponse.convert(musicEntity);
    }
}
