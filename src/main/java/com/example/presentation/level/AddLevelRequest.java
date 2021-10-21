package com.example.presentation.level;

import lombok.*;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@With
@EqualsAndHashCode
public class AddLevelRequest {
    private long texture;
    private long music;
    private long skybox;
    private ArrayList<Long> meshs;
    private long light;
}
