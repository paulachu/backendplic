package com.example.presentation.level;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@With
@EqualsAndHashCode
public class AddLevelResponse {
    private long id;
    private long levelNumber;
    private long texture;
    private long music;
    private long skybox;
    private List<Long> meshs;
    private long light;
}
