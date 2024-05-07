package student.course.service;

import lombok.RequiredArgsConstructor;
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

    @Override
    public Armors createArmor(Armors armors) {
        return armorsRepository.save(armors);
    }

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

    @Override
    public void updateArmor(Armors armor) throws ArmorNotFoundException {
        Optional<Armors> optionalArmors = getArmorById(armor.getArmorId());
        if (optionalArmors.isPresent()) {
            armorsRepository.save(armor);
        }
    }

    @Override
    public void deleteArmorById(Long id) throws ArmorNotFoundException {
        Optional<Armors> existingArmor = getArmorById(id);
        existingArmor.ifPresent(armorsRepository::delete);
    }
}
