package student.course.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "soul")
@Entity(name = "soul")
@NoArgsConstructor
@AllArgsConstructor
@Data
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

}
