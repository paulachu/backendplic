package com.example.presentation.resource;

import com.example.converter.Converter;
import com.example.domain.entity.LevelEntity;
import com.example.domain.entity.LightEntity;
import com.example.domain.entity.MusicEntity;
import com.example.domain.service.MusicServiceInterface;
import com.example.presentation.level.AddLevelResponse;
import com.example.presentation.light.AddLightResponse;
import com.example.presentation.music.AddMusicRequest;
import com.example.presentation.music.AddMusicResponse;
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
    public Response addMusic(@Valid @Parameter(hidden = true) @MultipartForm AddMusicRequest addMusicRequest)
    {
        try {
            MusicEntity musicEntity = new MusicEntity().withFilename(addMusicRequest.getFilename()).withFile(addMusicRequest.getFile());
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
    public AddMusicResponse getByIdMusic(@PathParam("id") Long id) throws Exception {
        MusicEntity musicEntity = musicService.getByIdMusic(id);
        return entityToAddResponse.convert(musicEntity);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AddMusicResponse> getMusics(){
        List<MusicEntity> musicEntityList = musicService.getMusics();
        return entityToAddResponse.convertList(musicEntityList);
    }
}
