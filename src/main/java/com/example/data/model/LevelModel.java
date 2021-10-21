package com.example.data.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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
    @ManyToOne()
    private TextureModel texture;
    @ManyToOne()
    private MusicModel music;
    @ManyToOne()
    private SkyboxModel skybox;
    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(name = "level_mesh", joinColumns = @JoinColumn(name = "level_id", referencedColumnName = "id"),
                    inverseJoinColumns = @JoinColumn(name = "mesh_id", referencedColumnName = "id"))
    private List<MeshModel> meshs;
    @OneToOne()
    private LightModel light;
}
