package student.course.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import student.course.exceptions.SoulNotFoundException;
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
    public Optional<Souls> getSoulById(Long id) throws SoulNotFoundException {
        Optional<Souls> optionalSouls = soulsRepository.findById(id);
        if(optionalSouls.isPresent()) {
            return optionalSouls;
        }
        else{
            throw new SoulNotFoundException(id);
        }
    }

    @Override
    public void updateSoul(Souls souls) throws SoulNotFoundException {
        Optional<Souls> optionalSouls = getSoulById(souls.getSoulId());
        if (optionalSouls.isPresent()) {
            soulsRepository.save(souls);
        }
    }

    @Override
    public void deleteSoulById(Long id) throws SoulNotFoundException {
        Optional<Souls> optionalSouls = getSoulById(id);
        optionalSouls.ifPresent(soulsRepository::delete);
    }
}
