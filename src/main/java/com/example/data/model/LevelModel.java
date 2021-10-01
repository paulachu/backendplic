package com.example.data.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.List;
import java.util.Collection;

@Entity
@Table(name = "level")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@With
@EqualsAndHashCode
public class LevelModel extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long texture;
    private long music;
    private long skybox;
    @ElementCollection
    private List<Long> meshs;
    private long light;
}
