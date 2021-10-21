package com.example.presentation.light;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@With
@EqualsAndHashCode
public class AddLightResponse {
    private long id;
    private int red;
    private int green;
    private int blue;
    private int alpha;
}
