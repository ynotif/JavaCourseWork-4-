package student.course.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import student.course.exceptions.ArmorNotFoundException;
import student.course.model.Armors;
import student.course.bdsetters.ArmorSetter;
import student.course.model.Locations;
import student.course.repository.ArmorsRepository;
import student.course.repository.LocationsRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArmorsServiceImpl implements ArmorsService {

    private final ArmorsRepository armorsRepository;
    private final ArmorSetter armorsSetter = new ArmorSetter();

    @CacheEvict(cacheNames = "armors", allEntries = true)
    @Override
    public Armors createArmor(Armors armors) {
        return armorsRepository.save(armors);
    }

    @Cacheable(cacheNames = "armors") // Обязательно включаем кэш в CourseApplication аннотацие EnableCaching
    @Override
    public List<Armors> getAllArmors() {
        return armorsRepository.findAll();
    }

    @Override
    public Optional<Armors> getArmorById(Long id) throws ArmorNotFoundException {
        Optional<Armors> armor = armorsRepository.findById(id);
        if (armor.isPresent()) {
            return armor;
        }
        else {
            throw new ArmorNotFoundException(id);
        }
    }

    @CacheEvict(cacheNames = "armors", allEntries = true)
    @Override
    public void updateArmor(Armors updateArmor, Long id) throws ArmorNotFoundException {
        Armors armor = armorsRepository.findById(id)
                .orElseThrow(() -> new ArmorNotFoundException(id));
        armorsSetter.update(armor, updateArmor, id);
        armorsRepository.save(armor);
    }

    @CacheEvict(cacheNames = "armors", allEntries = true)
    @Override
    public void deleteArmorById(Long id) throws ArmorNotFoundException {
        Optional<Armors> existingArmor = getArmorById(id);
        existingArmor.ifPresent(armorsRepository::delete);
    }
}
