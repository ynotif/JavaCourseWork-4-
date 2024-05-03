package student.course.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Table(name = "armor")
@Entity(name = "armor")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Armors {
    @Id
    @Column(name = "armorId")
    @GeneratedValue(generator = "armor_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "armor_id_seq", sequenceName = "armor_id_seq", initialValue = 1, allocationSize = 1)
    private Long armorId;

    @Column(name = "armorName")
    private String armorName;

    @Column(name = "armorPhysicalNormalDamageResistance")
    private double armorPhysicalNormalDamageResistance;

    @Column(name = "armorPhysicalCrushingDamageResistance")
    private double armorPhysicalCrushingDamageResistance;

    @Column(name = "armorPhysicalSlashingDamageResistance")
    private double armorPhysicalSlashingDamageResistance;

    @Column(name = "armorPhysicalPiercingDamageResistance")
    private double armorPhysicalPiercingDamageResistance;

    @Column(name = "armorMagicalDamageResistance")
    private double armorMagicalDamageResistance;

    @Column(name = "armorFireDamageResistance")
    private double armorFireDamageResistance;

    @Column(name = "armorLightningDamageResistance")
    private double armorLightningDamageResistance;

    @Column(name = "armorDarknessDamageResistance")
    private double unitDarknessDamageResistance;

    @Column(name = "armorType")
    private String armorType;

    @Column(name = "armorBalance")
    private int armorBalance;

    @Column(name = "armorWeight")
    private int armorWeight;

    @Column(name = "armorEndurance")
    private int armorEndurance;

    @Column(name = "armorSaleCost")
    private int armorSaleCost;

    @Column(name = "armorBuyCost")
    private int armorBuyCost;

    @Column(name = "armorSomeInformation")
    private String armorSomeInformation;

    @JsonIgnore
    @ManyToMany
    private Set<Locations> locations;

    @JsonIgnore
    @ManyToMany
    private Set<Units> units;

    @JsonIgnore
    @ManyToMany
    private Set<Bosses> bosses;

    @ManyToMany
    @JsonIgnore
    private Set<NPC> npc;
}
