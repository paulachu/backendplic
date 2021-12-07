package com.example.presentation.resource;

import com.example.converter.Converter;
import com.example.domain.entity.MusicEntity;
import com.example.domain.service.MusicServiceInterface;
import com.example.presentation.music.AddMusicRequest;
import com.example.presentation.music.AddMusicResponse;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    public List<AddMusicResponse> getMusics() throws Exception {
        List<MusicEntity> musicEntityList = musicService.getMusics();
        return entityToAddResponse.convertList(musicEntityList);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMusic(@PathParam("id") Long id) {
        if (musicService.deleteMusic(id)){
            return Response.status(Response.Status.ACCEPTED).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Transactional
    public Response putMusic(@PathParam("id") Long id, @Valid @Parameter(hidden = true) @MultipartForm AddMusicRequest addMusicRequest){
        try {
            MusicEntity musicEntityToAdd = new MusicEntity().withFilename(addMusicRequest.getFilename()).withFile(addMusicRequest.getFile());
            MusicEntity musicEntityAdded = musicService.putMusic(musicEntityToAdd, id);
            return Response.status(Response.Status.CREATED).entity(entityToAddResponse.convert(musicEntityAdded))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
