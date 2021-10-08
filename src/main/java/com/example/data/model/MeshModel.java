package com.example.data.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "mesh")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@With
@EqualsAndHashCode
public class MeshModel extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String filename;
}
