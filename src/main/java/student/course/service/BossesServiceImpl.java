package student.course.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.course.model.Armors;
import student.course.model.Bosses;
import student.course.repository.ArmorsRepository;
import student.course.repository.BossesRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BossesServiceImpl implements BossesService {

    @Autowired
    private BossesRepository bossesRepository;
    @Autowired
    private ArmorsRepository armorsRepository;

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
    public Bosses addArmorToBosses(Long bossesId, Long armorId) {
        Bosses bosses = bossesRepository.findById(bossesId)
                .orElseThrow(() -> new RuntimeException("Bosses not found"));
        Armors armors = armorsRepository.findById(armorId)
                .orElseThrow(() -> new RuntimeException("Armors not found"));
        bosses.getArmor().add(armors);
        return bossesRepository.save(bosses);
    }

    @Override
    public Bosses removeArmorFromBosses(Long bossesId, Long armorId) {
        Bosses bosses = bossesRepository.findById(bossesId)
                .orElseThrow(() -> new RuntimeException("Bosses not found"));
        Armors armors = armorsRepository.findById(armorId)
                .orElseThrow(() -> new RuntimeException("Armors not found"));

        bosses.getArmor().remove(armors);
        return bossesRepository.save(bosses);
    }

}
