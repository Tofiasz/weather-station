package pl.gorczynski.service;

import pl.gorczynski.dto.SensorDataDTO;

import java.util.Optional;

public interface StationService {
    Optional<SensorDataDTO> read();
}
