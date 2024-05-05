package student.course.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.course.model.Armors;
import student.course.model.Bosses;
import student.course.model.Souls;
import student.course.model.Weapons;
import student.course.repository.ArmorsRepository;
import student.course.repository.BossesRepository;
import student.course.repository.SoulsRepository;
import student.course.repository.WeaponsRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BossesServiceImpl implements BossesService {

    @Autowired
    private BossesRepository bossesRepository;
    @Autowired
    private ArmorsRepository armorsRepository;
    @Autowired
    private WeaponsRepository weaponsRepository;
    @Autowired
    private SoulsRepository soulsRepository;

    @Override
    public Bosses createBosse(Bosses bosses) {
        return bossesRepository.save(bosses);
    }

    @Override
    public List<Bosses> getAllBosses() {
        return bossesRepository.findAll();
    }

    @Override
    public Optional<Bosses> getBosseById(Long id) {
        return bossesRepository.findById(id);
    }

    @Override
    public void updateBosse(Bosses bosses) {
        Optional<Bosses> optionalBosses = getBosseById(bosses.getBossId());
        if (optionalBosses.isPresent()) {
            bossesRepository.save(bosses);
        }
    }


    @Override
    public void deleteBosseById(Long id) {
        Optional<Bosses> bosses = bossesRepository.findById(id);
        bosses.ifPresent(bossesRepository::delete);
    }

    @Override
    public Bosses addArmorToBoss(Long bossId, Long armorId) {
        Bosses bosses = bossesRepository.findById(bossId)
                .orElseThrow(() -> new RuntimeException("Bosses not found"));
        Armors armors = armorsRepository.findById(armorId)
                .orElseThrow(() -> new RuntimeException("Armors not found"));
        bosses.getArmor().add(armors);
        return bossesRepository.save(bosses);
    }

    @Override
    public Bosses removeArmorFromBoss(Long bossId, Long armorId) {
        Bosses bosses = bossesRepository.findById(bossId)
                .orElseThrow(() -> new RuntimeException("Bosses not found"));
        Armors armors = armorsRepository.findById(armorId)
                .orElseThrow(() -> new RuntimeException("Armors not found"));

        bosses.getArmor().remove(armors);
        return bossesRepository.save(bosses);
    }

    @Override
    public Bosses addWeaponToBoss(Long bossId, Long weaponId) {
        Bosses bosses = bossesRepository.findById(bossId)
                .orElseThrow(() -> new RuntimeException("Bosses not found"));
        Weapons weapons = weaponsRepository.findById(weaponId)
                .orElseThrow(() -> new RuntimeException("Weapons not found"));

        bosses.getWeapon().add(weapons);
        return bossesRepository.save(bosses);
    }

    @Override
    public Bosses removeWeaponFromBoss(Long bossId, Long weaponId) {
        Bosses bosses = bossesRepository.findById(bossId)
                .orElseThrow(() -> new RuntimeException("Bosses not found"));
        Weapons weapons = weaponsRepository.findById(weaponId)
                .orElseThrow(() -> new RuntimeException("Weapons not found"));

        bosses.getWeapon().remove(weapons);
        return bossesRepository.save(bosses);
    }

    public Bosses addSoulToBoss(Long bossId, Long soulId){
        Bosses bosses = bossesRepository.findById(bossId)
                .orElseThrow(() -> new RuntimeException("Bosses not found"));
        Souls souls = soulsRepository.findById(soulId)
                .orElseThrow(() -> new RuntimeException("Souls not found"));

        bosses.getSoul().add(souls);
        return bossesRepository.save(bosses);
    }

    public Bosses removeSoulFromBoss(Long bossId, Long soulId){
        Bosses bosses = bossesRepository.findById(bossId)
                .orElseThrow(() -> new RuntimeException("Bosses not found"));
        Souls souls = soulsRepository.findById(soulId)
                .orElseThrow(() -> new RuntimeException("Souls not found"));

        bosses.getSoul().remove(souls);
        return bossesRepository.save(bosses);
    }

}
