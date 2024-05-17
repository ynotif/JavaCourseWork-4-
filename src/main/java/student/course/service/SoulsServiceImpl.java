package student.course.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import student.course.bdsetters.SoulSetter;
import student.course.exceptions.SoulNotFoundException;
import student.course.model.Souls;
import student.course.repository.SoulsRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SoulsServiceImpl implements SoulsService {

    private final SoulsRepository soulsRepository;

    private final SoulSetter soulSetter = new SoulSetter();

    @CacheEvict(cacheNames = "Souls", allEntries = true)
    @Override
    public Souls createSoul(Souls souls) {
        return soulsRepository.save(souls);
    }

    @Cacheable(cacheNames = "Souls")
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

    @CacheEvict(cacheNames = "Souls", allEntries = true)
    @Override
    public void updateSoul(Souls updateSoul, Long id) throws SoulNotFoundException {
        Souls soul = soulsRepository.findById(id)
                .orElseThrow(() -> new SoulNotFoundException(id));

        soulSetter.update(soul, updateSoul, id);

        soulsRepository.save(soul);
    }

    @CacheEvict(cacheNames = "Souls", allEntries = true)
    @Override
    public void deleteSoulById(Long id) throws SoulNotFoundException {
        Optional<Souls> optionalSouls = getSoulById(id);
        optionalSouls.ifPresent(soulsRepository::delete);
    }
}
