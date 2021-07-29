package com.example.presentation.resource;

import com.example.domain.service.SkyboxServiceInterface;

import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
public class SkyboxResource {
    @Inject
    SkyboxServiceInterface skyboxService;
}
