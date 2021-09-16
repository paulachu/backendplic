package com.example.presentation.mesh;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@With
@EqualsAndHashCode
public class AddMeshResponse {
    private long id;
    private String filename;
    private String presignedUrl;
}
