package com.example.presentation.texture;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@With
@EqualsAndHashCode
public class AddTextureResponse {
    private long id;
    private String filename;
    private String presignedUrl;
}
