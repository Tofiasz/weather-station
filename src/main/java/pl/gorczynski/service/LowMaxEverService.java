package pl.gorczynski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.gorczynski.model.Weather;
import pl.gorczynski.repository.WeatherRepository;

@Service
public class LowMaxEverService {

    private WeatherRepository weatherRepository;

    @Autowired
    public LowMaxEverService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public Weather getHighestHumidityEver() {
         return weatherRepository.findFirstByOrderByHumidityDesc();
    }

    public Weather getLowestHumidityEver() {
        return weatherRepository.findFirstByOrderByHumidity();
    }

    public Weather getHighestTemperatureEver() {
        return weatherRepository.findFirstByOrderByTemperatureDesc();
    }

    public Weather getLowestTemperatureEver() {
        return weatherRepository.findFirstByOrderByTemperature();
    }

    public Weather getHighestPressureEver() {
        return weatherRepository.findFirstByOrderByAtmosphericPressureDesc();
    }

    public Weather getLowestPressureEver() {
        return weatherRepository.findFirstByOrderByAtmosphericPressure();
    }
}
