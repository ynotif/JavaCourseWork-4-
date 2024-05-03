package student.course.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import student.course.model.Weapons;
import student.course.repository.WeaponsRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WeaponsServiceImpl implements WeaponsService{

    private final WeaponsRepository weaponsRepository;

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
        weaponsRepository.save(weapons);
    }

    @Override
    public void deleteWeaponById(Long id) {
        weaponsRepository.deleteById(id);
    }
}
