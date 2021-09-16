package com.example.presentation.resource;

import com.example.converter.Converter;
import com.example.domain.entity.MeshEntity;
import com.example.domain.service.MeshServiceInterface;
import com.example.presentation.mesh.AddMeshResponse;
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

@Path("mesh")
@Produces(MediaType.APPLICATION_JSON)
public class MeshResource {
    @Inject
    MeshServiceInterface meshService;

    @Inject
    Converter<MeshEntity, AddMeshResponse> entityToAddResponse;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Transactional
    public Response addMesh(MultipartFormDataInput input)
    {
        try {
            File file = input.getFormDataPart("file", File.class, null);
            String name = input.getFormDataPart("filename", String.class, null);;
            MeshEntity meshEntity = new MeshEntity().withFilename(name).withFile(file);
            MeshEntity meshEntityAdded = meshService.addMesh(meshEntity);
            return Response.status(Response.Status.CREATED).entity(entityToAddResponse.convert(meshEntityAdded))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
