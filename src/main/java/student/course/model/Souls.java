package student.course.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Table(name = "soul")
@Entity(name = "soul")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Souls {
    @Id
    @Column(name = "soulId")
    @GeneratedValue(generator = "soul_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "soul_id_seq", sequenceName = "soul_id_seq", initialValue = 1, allocationSize = 1)
    private Long soulId;

    @Column(name = "soulName")
    private String soulName;

    @Column(name = "soulEqualSoulCurrency")
    private int soulEqualSoulCurrency; // Эквивалентность душам волютам, за сколько можно продать одно и то же

    @Column(name = "soulSomeInformation")
    private String soulSomeInformation;

    @ManyToMany
    @JsonIgnore
    private Set<Units> unit;

    @ManyToMany
    @JsonIgnore
    private Set<Bosses> bosses;

    @ManyToMany
    @JsonIgnore
    private Set<NPC> npc;
}
