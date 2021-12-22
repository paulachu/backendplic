package com.example.domain.entity;

import lombok.*;

import java.io.File;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@With
@EqualsAndHashCode
public class TextureEntity {
    private long id;
    private String filename;
    private File file;
    private String presignedUrl;
    private long level;
}
