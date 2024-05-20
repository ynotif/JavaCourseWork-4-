package student.course.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Table(name = "units")
@Entity(name = "units")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Units {
    @Id
    @Column(name = "unitId")
    @GeneratedValue(generator = "unit_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "unit_id_seq", sequenceName = "unit_id_seq", allocationSize = 1, initialValue = 1)
    private Long unitId;

    @Column(name = "unitName")
    private String unitName;

    @Column(name = "unitHitPoints")
    private double unitHitPoints;

    @Column(name = "unitPhysicalNormalDamageResistance")
    private double unitPhysicalNormalDamageResistance;

    @Column(name = "unitPhysicalCrushingDamageResistance")
    private double unitPhysicalCrushingDamageResistance;

    @Column(name = "unitPhysicalSlashingDamageResistance")
    private double unitPhysicalSlashingDamageResistance;

    @Column(name = "unitPhysicalPiercingDamageResistance")
    private double unitPhysicalPiercingDamageResistance;

    @Column(name = "unitMagicalDamageResistance")
    private double unitMagicalDamageResistance;

    @Column(name = "unitFireDamageResistance")
    private double unitFireDamageResistance;

    @Column(name = "unitLightningDamageResistance")
    private double unitLightningDamageResistance;

    @Column(name = "unitDarknessDamageResistance")
    private double unitDarknessDamageResistance;

    @Column(name = "unitBleedingResistance")
    private double unitBleedingResistance;

    @Column(name = "unitPoisonResistance")
    private double unitPoisonResistance;

    @Column(name = "unitFrostResistance")
    private double unitFrostResistance;

    @Column(name = "unitPhysicalNormalDamage")
    private double unitPhysicalNormalDamage;

    @Column(name = "unitPhysicalCrushingDamage")
    private double unitPhysicalCrushingDamage;

    @Column(name = "unitPhysicalSlashingDamage")
    private double unitPhysicalSlashingDamage;

    @Column(name = "unitPhysicalPiercingDamage")
    private double unitPhysicalPiercingDamage;

    @Column(name = "unitMagicalDamage")
    private double unitMagicalDamage;

    @Column(name = "unitFireDamage")
    private double unitFireDamage;

    @Column(name = "unitLightningDamage")
    private double unitLightningDamage;

    @Column(name = "unitDarknessDamage")
    private double unitDarknessDamage;

    @Column(name = "unitBleedingEffect")
    private double unitBleedingEffect;

    @Column(name = "unitPoisonEffect")
    private double unitPoisonEffect;

    @Column(name = "unitFrostEffect")
    private double unitFrostEffect;

    @Column(name = "unitCurseEffect")
    private double unitCurseEffect;

    @Column(name = "unitSoulsQuantity")
    private int unitSoulsQuantity;

    @Column(name = "unitSomeInformation")
    private String unitSomeInformation;

    //Для дропа
    @ManyToMany
    @JoinTable(
            name = "units_armors",
            joinColumns = @JoinColumn(name = "unitId"),
            inverseJoinColumns = @JoinColumn(name = "armorId")
    )
    private Set<Armors> armor;

    @ManyToMany
    @JoinTable(
            name = "units_weapons",
            joinColumns = @JoinColumn(name = "unitId"),
            inverseJoinColumns = @JoinColumn(name = "weaponId")
    )
    private Set<Weapons> weapon;

    @ManyToMany
    @JoinTable(
            name = "units_souls",
            joinColumns = @JoinColumn(name = "unitId"),
            inverseJoinColumns = @JoinColumn(name = "soulId")
    )
    private Set<Souls> soul;

    @ManyToMany
    @JsonIgnore
    private Set<Locations> locations;
}
