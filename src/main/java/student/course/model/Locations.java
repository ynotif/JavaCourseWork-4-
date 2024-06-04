package student.course.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Table(name = "locations")
@Entity(name = "locations")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
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

    @ManyToMany
    @JoinTable(
            name = "locations_units",
            joinColumns = @JoinColumn(name = "locationId"),
            inverseJoinColumns = @JoinColumn(name = "unitId")
    )
    private Set<Units> unit; //Сами юниты

    @Column(name = "locationNewMagicQuantity")
    private int locationNewMagicQuantity; // Количество новой магии

    @ManyToMany
    @JoinTable(
            name = "locations_magics",
            joinColumns = @JoinColumn(name = "locationId"),
            inverseJoinColumns = @JoinColumn(name = "magicId")
    )
    private Set<Magics> magic; // Сама магия

    @Column(name = "locationUniqueWeaponsQuantity")
    private int locationUniqueWeaponsQuantity; // Количество уникального оружия

    @ManyToMany
    @JoinTable(
            name = "locations_weapons",
            joinColumns = @JoinColumn(name = "locationId"),
            inverseJoinColumns = @JoinColumn(name = "weaponId")
    )
    private Set<Weapons> weapon; // Само оружие

    @Column(name = "locationsUniqueBossesQuantity")
    private int locationsUniqueBossesQuantity; // Количество уникальный боссов

    @ManyToMany
    @JoinTable(
            name = "locations_bosses",
            joinColumns = @JoinColumn(name = "locationId"),
            inverseJoinColumns = @JoinColumn(name = "bossId")
    )
    private Set<Bosses> boss; // Сами боссы

    @Column(name = "locationUniqueArmorsQuantity")
    private int locationUniqueArmorsQuantity; // Количество уникальной брони

    @ManyToMany
    @JoinTable(
            name = "locations_armors",
            joinColumns = @JoinColumn(name = "locationId"),
            inverseJoinColumns = @JoinColumn(name = "armorId")
    )
    private Set<Armors> armor; // Сама броня

    @Column(name = "locationBosseQuantity")
    private int locationBosseQuantity; // Количество всего боссов на локе

    @Column(name = "locationNpcQuantity")
    private int locationNPCQuantity; // Количество уникальный НПС

    @ManyToMany
    @JoinTable(
            name = "locations_NPC",
            joinColumns = @JoinColumn(name = "locationId"),
            inverseJoinColumns = @JoinColumn(name = "npcId")
    )
    private Set<NPC> npc; // Сами НПС

    @Column(name = "locationLizardsQuantity")
    private int locationLizardsQuantity; // Количество ящерок

    @Column(name = "locationSomeInformation")
    private String locationSomeInformation; // Некоторая информация о локе
}

