package pl.gorczynski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.gorczynski.dto.SensorDataDTO;
import pl.gorczynski.mapper.WeatherMapper;
import pl.gorczynski.model.Weather;
import pl.gorczynski.repository.WeatherRepository;

import java.util.Optional;

@Service
public class WeatherService {

    private final StationService stationService;
    private final WeatherRepository weatherRepository;
    private final WeatherMapper weatherMapper;

    @Autowired
    public WeatherService(final WeatherRepository weatherRepository,
                          final StationService stationService, final WeatherMapper weatherMapper) {
        this.weatherRepository = weatherRepository;
        this.stationService = stationService;
        this.weatherMapper = weatherMapper;
    }

    public Weather getCurrentWeather() {
        return weatherRepository.findFirstByOrderByIdDesc();
    }

    @Scheduled(fixedDelay = 30_000)
    public void saveNewData() {
        Optional<SensorDataDTO> optional = stationService.read();

        if (optional.isPresent()) {
            SensorDataDTO sensorDataDTO = optional.get();
            Weather weather = weatherMapper.toWeather(sensorDataDTO);
            weatherRepository.save(weather);
        } else {
            System.out.println("Problem z odczytem sensora");
        }
    }
}
