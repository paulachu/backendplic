package com.example.presentation.music;

import lombok.*;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;
import java.io.File;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
@With
@EqualsAndHashCode
@ToString
public class AddMusicRequest
{
    @NotNull
    @FormParam("file")
    @Schema(type = SchemaType.STRING, format = "binary", description = "file")
    private File file;
    @NotNull
    @FormParam("filename")
    private String filename;
}
