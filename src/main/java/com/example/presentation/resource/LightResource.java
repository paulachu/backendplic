package com.example.presentation.resource;

import com.example.domain.service.LightServiceInterface;

import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
public class LightResource {
    @Inject
    LightServiceInterface lightService;
}
