package student.course.service;


import student.course.exceptions.BosseNotFoundException;
import student.course.model.Bosses;

import java.util.List;
import java.util.Optional;

public interface BossesService {

    Bosses createBosse(Bosses bosses);

    List<Bosses> getAllBosses();

    Optional<Bosses> getBosseById(Long id) throws BosseNotFoundException;

    void updateBosse(Bosses bosses, Long id) throws BosseNotFoundException;

    void deleteBosseById(Long id) throws BosseNotFoundException;

    Bosses addArmorToBoss(Long bossId, Long armorId);

    Bosses removeArmorFromBoss(Long bossId, Long armorId);

    Bosses addWeaponToBoss(Long bossId, Long weaponId);

    Bosses removeWeaponFromBoss(Long bossId, Long weaponId);

    Bosses addSoulToBoss(Long bossId, Long soulId);

    Bosses removeSoulFromBoss(Long bossId, Long soulId);
}
