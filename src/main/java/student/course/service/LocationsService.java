package student.course.service;

import student.course.model.Locations;

import java.util.List;
import java.util.Optional;

public interface LocationsService {

    Locations createLocation(Locations location);

    List<Locations> getAllLocations();

    Optional<Locations> getLocationById(Long id);

}
