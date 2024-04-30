package student.course.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import student.course.model.Locations;
import student.course.repository.LocationsRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationsServiceImpl implements LocationsService {

    private final LocationsRepository locationsRepository;

    @Override
    public Locations createLocation(Locations location) {
        return locationsRepository.save(location);
    }

    @Override
    public List<Locations> getAllLocations() {
        return locationsRepository.findAll();
    }

    @Override
    public Optional<Locations> getLocationById(Long id) {
        return locationsRepository.findById(id);
    }
}
