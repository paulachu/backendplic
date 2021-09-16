package com.example.presentation.skybox;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@With
@EqualsAndHashCode
public class AddSkyboxResponse {
    private long id;
    private String filename;
    private String presignedUrl;
}
