package com.example.presentation.light;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@With
@EqualsAndHashCode
public class AddLightRequest {
    private int red;
    private int green;
    private int blue;
    private int alpha;
}
