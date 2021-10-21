package com.example.presentation.music;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@With
@EqualsAndHashCode
public class AddMusicResponse {
    private long id;
    private String filename;
    private String presignedUrl;
}
