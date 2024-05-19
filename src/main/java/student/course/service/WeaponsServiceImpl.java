package student.course.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import student.course.bdsetters.WeaponSetter;
import student.course.exceptions.WeaponNotFoundException;
import student.course.model.Souls;
import student.course.model.Weapons;
import student.course.repository.SoulsRepository;
import student.course.repository.WeaponsRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WeaponsServiceImpl implements WeaponsService{

    private final WeaponsRepository weaponsRepository;
    private final SoulsRepository soulsRepository;

    @CacheEvict(cacheNames = "Weapons", allEntries = true)
    @Override
    public Weapons createWeapon(Weapons weapons) {
        return weaponsRepository.save(weapons);
    }

    @Cacheable(cacheNames = "Weapons")
    @Override
    public List<Weapons> getAllWeapons() {
        return weaponsRepository.findAll();
    }

    @Override
    public Optional<Weapons> getWeaponById(Long id) throws WeaponNotFoundException {
        Optional<Weapons> weaponsOptional = weaponsRepository.findById(id);
        if(weaponsOptional.isPresent()) {
            return weaponsOptional;
        }
        else{
            throw new WeaponNotFoundException(id);
        }
    }

    @CacheEvict(cacheNames = "Weapons", allEntries = true)
    @Override
    public void updateWeapon(Weapons updateWeapon, Long id) throws WeaponNotFoundException {
        Weapons weapon = weaponsRepository.findById(id)
                .orElseThrow(() -> new WeaponNotFoundException(id));

        WeaponSetter.update(weapon, updateWeapon, id);

        weaponsRepository.save(weapon);
    }

    @CacheEvict(cacheNames = "Weapons", allEntries = true)
    @Override
    public void deleteWeaponById(Long weaponId) throws WeaponNotFoundException {
        Optional<Weapons> optionalWeapons = getWeaponById(weaponId);
        optionalWeapons.ifPresent(weaponsRepository::delete);
    }

    @CacheEvict(cacheNames = "Weapons", allEntries = true)
    @Override
    public Weapons addSoulToWeapon(Long weaponId, Long soulId) {
        Weapons weapons = weaponsRepository.findById(weaponId)
                .orElseThrow(() -> new RuntimeException("Weapon not found"));

        Souls souls = soulsRepository.findById(soulId)
                .orElseThrow(() -> new RuntimeException("Soul not found"));

        weapons.getSoul().add(souls);
        return weaponsRepository.save(weapons);
    }

    @CacheEvict(cacheNames = "Weapons", allEntries = true)
    @Override
    public Weapons removeSoulFromWeapon(Long weaponId, Long soulId) {
        Weapons weapons = weaponsRepository.findById(weaponId)
                .orElseThrow(() -> new RuntimeException("Weapon not found"));

        Souls souls = soulsRepository.findById(soulId)
                .orElseThrow(() -> new RuntimeException("Soul not found"));

        weapons.getSoul().remove(souls);
        return weaponsRepository.save(weapons);
    }
}
