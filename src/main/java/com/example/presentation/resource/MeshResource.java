package com.example.presentation.resource;

import com.example.converter.Converter;
import com.example.domain.entity.MeshEntity;
import com.example.domain.service.MeshServiceInterface;
import com.example.presentation.mesh.AddMeshRequest;
import com.example.presentation.mesh.AddMeshResponse;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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
    public Response addMesh(@Valid @Parameter(hidden = true) @MultipartForm AddMeshRequest addMeshRequest)
    {
        try {
            MeshEntity meshEntity = new MeshEntity().withFilename(addMeshRequest.getFilename()).withFile(addMeshRequest.getFile());
            MeshEntity meshEntityAdded = meshService.addMesh(meshEntity);
            return Response.status(Response.Status.CREATED).entity(entityToAddResponse.convert(meshEntityAdded))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Transactional
    public Response putMesh(@PathParam("id") Long id, @Valid @Parameter(hidden = true) @MultipartForm AddMeshRequest addMeshRequest){
        try {
            MeshEntity meshEntityToAdd = new MeshEntity().withFilename(addMeshRequest.getFilename()).withFile(addMeshRequest.getFile());
            MeshEntity meshEntityAdded = meshService.putMesh(meshEntityToAdd, id);
            return Response.status(Response.Status.CREATED).entity(entityToAddResponse.convert(meshEntityAdded))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public AddMeshResponse getByIdMesh(@PathParam("id") Long id) throws Exception {
        MeshEntity meshEntity = meshService.getByIdMesh(id);
        return entityToAddResponse.convert(meshEntity);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AddMeshResponse> getMeshs() throws Exception {
        List<MeshEntity> meshEntityList = meshService.getMeshs();
        return entityToAddResponse.convertList(meshEntityList);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMesh(@PathParam("id") Long id) {
        if (meshService.deleteMesh(id)){
            return Response.status(Response.Status.ACCEPTED).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }


}
