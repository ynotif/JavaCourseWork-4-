package student.course.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "magic")
@Table(name = "magic")
public class Magics {

    @Id
    @Column(name = "magic")
    @GeneratedValue(generator = "magic_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "magic_id_seq", sequenceName = "magic_id_seq", initialValue = 1, allocationSize = 1)
    private Long magicId;

    @Column(name = "magicName")
    private String magicName;

    @Column(name = "magicManaCost")
    private int magicManaCost;

    @Column(name = "magicMagicalDamage")
    private double magicDamage;

    @Column(name = "magicPhysicalDamage")
    private double magicPhysicalDamage;

    @Column(name = "magicFireDamage")
    private double magicFireDamage;

    @Column(name = "magicLightningDamage")
    private double magicLightningDamage;

    @Column(name = "magicDarknessDamage")
    private double magicDarknessDamage;

    @Column(name = "magicHeal")
    private double magicHeal;

    @Column(name = "magicCastTime")
    private double magicCastTime;

    @Column(name = "magicCastRange")
    private double magicCastRange;

    @Column(name = "magicIntellectRequired")
    private int magicIntellectRequired; //Требуемый интеллект

    @Column(name = "magicFaithRequired")
    private int magicFaithRequired; //Трбуемая вера

    @Column(name = "magicNumberCells")
    private int magicNumberCells; // Количество ячеек

    @Column(name = "magicBuyCost")
    private int magicBuyCost;

    @Column(name = "magicSoulCraft")
    private String magicSoulCraft; //Душа для крафта магии

    @Column(name = "magicSomeInformation")
    private String magicSomeInformation;

}
