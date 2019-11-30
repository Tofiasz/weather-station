package pl.gorczynski.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.gorczynski.dto.SensorDataDTO;

import java.util.Optional;
import java.util.Random;

@Service
@Profile("dev")
public class MockStationService implements StationService {

    @Override
    public Optional<SensorDataDTO> read() {
        Random random = new Random();
        SensorDataDTO sensorDataDTO = new SensorDataDTO(random.nextDouble() + 3,
                random.nextDouble() + 2, random.nextDouble() + 1);

        return Optional.of(sensorDataDTO);
    }
}
