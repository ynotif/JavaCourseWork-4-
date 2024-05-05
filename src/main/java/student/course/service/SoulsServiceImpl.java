package student.course.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import student.course.model.Souls;
import student.course.repository.SoulsRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SoulsServiceImpl implements SoulsService {

    private final SoulsRepository soulsRepository;

    @Override
    public Souls createSoul(Souls souls) {
        return soulsRepository.save(souls);
    }

    @Override
    public List<Souls> getAllSouls() {
        return soulsRepository.findAll();
    }

    @Override
    public Optional<Souls> getSoulById(Long id) {
        return soulsRepository.findById(id);
    }

    @Override
    public void updateSoul(Souls souls) {
        Optional<Souls> optionalSouls = getSoulById(souls.getSoulId());
        if (optionalSouls.isPresent()) {
            soulsRepository.save(souls);
        }
    }

    @Override
    public void deleteSoulById(Long id) {
        Optional<Souls> optionalSouls = getSoulById(id);
        optionalSouls.ifPresent(soulsRepository::delete);
    }
}
