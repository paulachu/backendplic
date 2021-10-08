package com.example.domain.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@With
@EqualsAndHashCode
public class LightEntity {
    private long id;
    private int red;
    private int green;
    private int blue;
    private int alpha;
    private int level;
}
