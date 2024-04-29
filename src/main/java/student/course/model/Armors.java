package student.course.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "armor")
@Entity(name = "armor")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Armors {
    @Id
    @Column(name = "armorId")
    @GeneratedValue(generator = "armor_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "armor_id_seq", sequenceName = "armor_id_seq", initialValue = 1, allocationSize = 1)
    private Long armorId;

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


}
