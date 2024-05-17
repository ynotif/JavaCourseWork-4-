package student.course.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import student.course.bdsetters.BosseSetter;
import student.course.exceptions.BosseNotFoundException;
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


    private final BossesRepository bossesRepository;

    private final ArmorsRepository armorsRepository;

    private final WeaponsRepository weaponsRepository;

    private final SoulsRepository soulsRepository;

    private final BosseSetter bosseSetter = new BosseSetter();

    @CacheEvict(cacheNames = "Boss", allEntries = true)
    @Override
    public Bosses createBosse(Bosses bosses) {
        return bossesRepository.save(bosses);
    }

    @Cacheable(cacheNames = "Boss")
    @Override
    public List<Bosses> getAllBosses() {
        return bossesRepository.findAll();
    }

    @Override
    public Optional<Bosses> getBosseById(Long id) throws BosseNotFoundException {
        Optional<Bosses> bosses = bossesRepository.findById(id);
        if (bosses.isPresent()) {
            return bosses;
        }
        else{
            throw new BosseNotFoundException(id);
        }
    }

    @CacheEvict(cacheNames = "Boss", allEntries = true)
    @Override
    public void updateBosse(Bosses updateBosse, Long id) throws BosseNotFoundException {
        Bosses bosses = bossesRepository.findById(id)
                .orElseThrow(() -> new BosseNotFoundException(id));

        bosseSetter.update(bosses, updateBosse, id);

        bossesRepository.save(bosses);

    }

    @CacheEvict(cacheNames = "Boss", allEntries = true)
    @Override
    public void deleteBosseById(Long id){
        Optional<Bosses> bosses = bossesRepository.findById(id);
        bosses.ifPresent(bossesRepository::delete);
    }

    @CacheEvict(cacheNames = "Boss", allEntries = true)
    @Override
    public Bosses addArmorToBoss(Long bossId, Long armorId) {
            Bosses bosses = bossesRepository.findById(bossId)
                    .orElseThrow(() -> new RuntimeException("Bosses not found"));
            Armors armors = armorsRepository.findById(armorId)
                    .orElseThrow(() -> new RuntimeException("Armors not found"));
            bosses.getArmor().add(armors);
            return bossesRepository.save(bosses);
    }

    @CacheEvict(cacheNames = "Boss", allEntries = true)
    @Override
        public Bosses removeArmorFromBoss(Long bossId, Long armorId) {
            Bosses bosses = bossesRepository.findById(bossId)
                    .orElseThrow(() -> new RuntimeException("Bosses not found"));
            Armors armors = armorsRepository.findById(armorId)
                    .orElseThrow(() -> new RuntimeException("Armors not found"));

            bosses.getArmor().remove(armors);
            return bossesRepository.save(bosses);
    }

    @CacheEvict(cacheNames = "Boss", allEntries = true)
    @Override
    public Bosses addWeaponToBoss(Long bossId, Long weaponId) {
            Bosses bosses = bossesRepository.findById(bossId)
                    .orElseThrow(() -> new RuntimeException("Bosses not found"));
            Weapons weapons = weaponsRepository.findById(weaponId)
                    .orElseThrow(() -> new RuntimeException("Weapons not found"));

            bosses.getWeapon().add(weapons);
            return bossesRepository.save(bosses);
    }

    @CacheEvict(cacheNames = "Boss", allEntries = true)
    @Override
    public Bosses removeWeaponFromBoss(Long bossId, Long weaponId) {
            Bosses bosses = bossesRepository.findById(bossId)
                    .orElseThrow(() -> new RuntimeException("Bosses not found"));
            Weapons weapons = weaponsRepository.findById(weaponId)
                    .orElseThrow(() -> new RuntimeException("Weapons not found"));

            bosses.getWeapon().remove(weapons);
            return bossesRepository.save(bosses);
    }

    @CacheEvict(cacheNames = "Boss", allEntries = true)
    public Bosses addSoulToBoss(Long bossId, Long soulId){
            Bosses bosses = bossesRepository.findById(bossId)
                    .orElseThrow(() -> new RuntimeException("Bosses not found"));
            Souls souls = soulsRepository.findById(soulId)
                    .orElseThrow(() -> new RuntimeException("Souls not found"));

            bosses.getSoul().add(souls);
            return bossesRepository.save(bosses);
    }

    @CacheEvict(cacheNames = "Boss", allEntries = true)
    public Bosses removeSoulFromBoss(Long bossId, Long soulId){
            Bosses bosses = bossesRepository.findById(bossId)
                    .orElseThrow(() -> new RuntimeException("Bosses not found"));
            Souls souls = soulsRepository.findById(soulId)
                    .orElseThrow(() -> new RuntimeException("Souls not found"));

            bosses.getSoul().remove(souls);
            return bossesRepository.save(bosses);
    }

}
