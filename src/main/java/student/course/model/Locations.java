package student.course.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "locations")
@Entity(name = "locations")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Locations {
    @Id
    @Column(name = "locationId")
    @GeneratedValue(generator = "location_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "location_id_seq", sequenceName = "location_id_seq", allocationSize = 1, initialValue = 1)
    private Long locationId;

    @Column(name = "locationName")
    private String locationName; // Название локи

    @Column(name = "locationEstusQuantity")
    private int locationEstusQuantity; // Количество эстуса

    @Column(name = "locationUniqueUnitsQuantity")
    private int locationUniqueUnitsQuantity; // Количество уникальных юнитов

    @ManyToOne
    @JoinColumn(name = "unitId")
    private Units unit;

    @Column(name = "locationNewMagic")
    private int locationNewMagic; // Количество новой магии

    @ManyToOne
    @JoinColumn(name = "magicId")
    private Magics magic; // Сама магия

    @Column(name = "locationUniqueWeaponsQuantity")
    private int locationUniqueWeaponsQuantity; // Количество уникального оружия

    @ManyToOne
    @JoinColumn(name = "weaponId")
    private Weapons weapon;

    @Column(name = "locationsUniqueBossesQuantity")
    private int locationsUniqueBossesQuantity;
    @ManyToOne
    @JoinColumn(name = "bossId")
    private Bosses boss;

    @Column(name = "locationUniqueArmorsQuantity")
    private int locationUniqueArmorsQuantity;

    @ManyToOne
    @JoinColumn(name = "armorId")
    private Armors armor;

    @Column(name = "locationBosseQuantity")
    private int locationBosseQuantity;

    @Column(name = "locationNPCQuantity")
    private int locationNPCQuantity;

    @ManyToOne
    @JoinColumn(name = "npcId")
    private NPC npc;

    @Column(name = "locationLizardsQuantity")
    private int locationLizardsQuantity;

    @Column(name = "locationSomeInformation")
    private String locationSomeInformation; // Некоторая информация о локе
}

