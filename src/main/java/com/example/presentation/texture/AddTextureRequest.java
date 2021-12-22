package com.example.presentation.texture;

import lombok.*;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;
import java.io.File;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@With
@EqualsAndHashCode
public class AddTextureRequest {
    @NotNull
    @FormParam("file")
    @Schema(type = SchemaType.STRING, format = "binary", description = "file")
    private File file;
    @NotNull
    @FormParam("filename")
    private String filename;
}
