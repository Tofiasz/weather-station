package pl.gorczynski.mapper;

import org.springframework.stereotype.Component;
import pl.gorczynski.dto.SensorDataDTO;
import pl.gorczynski.model.Weather;

@Component
public class WeatherMapper {

    public Weather toWeather(final SensorDataDTO sensorDataDTO) {
        return new Weather(sensorDataDTO.getTemperature(), sensorDataDTO.getHumidity(), sensorDataDTO.getPressure());
    }
}
