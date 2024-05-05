package student.course.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

    @Override
    public Weapons createWeapon(Weapons weapons) {
        return weaponsRepository.save(weapons);
    }

    @Override
    public List<Weapons> getAllWeapons() {
        return weaponsRepository.findAll();
    }

    @Override
    public Optional<Weapons> getWeaponById(Long id) {
        return weaponsRepository.findById(id);
    }

    @Override
    public void updateWeapon(Weapons weapons) {
        Optional<Weapons> optionalWeapons = getWeaponById(weapons.getWeaponId());
        if (optionalWeapons.isPresent()) {
            weaponsRepository.save(weapons);
        }
    }

    @Override
    public void deleteWeaponById(Long weaponId) {
        Optional<Weapons> optionalWeapons = getWeaponById(weaponId);
        optionalWeapons.ifPresent(weaponsRepository::delete);
    }

    @Override
    public Weapons addSoulToWeapon(Long weaponId, Long soulId) {
        Weapons weapons = weaponsRepository.findById(weaponId)
                .orElseThrow(() -> new RuntimeException("Weapon not found"));

        Souls souls = soulsRepository.findById(soulId)
                .orElseThrow(() -> new RuntimeException("Soul not found"));

        weapons.getSoul().add(souls);
        return weaponsRepository.save(weapons);
    }

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
