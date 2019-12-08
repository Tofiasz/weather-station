package pl.gorczynski.service;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gorczynski.model.Weather;
import pl.gorczynski.repository.WeatherRepository;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class LowMaxByDateService {

    private WeatherRepository weatherRepository;

    @Autowired
    public LowMaxByDateService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public Weather getHighestTemperatureByDate(HttpServletRequest request) {
        return weatherRepository.findFirstByMeasuredAtBetweenOrderByTemperatureDesc(LocalDate.parse(request.getParameter("name")).atStartOfDay(ZoneId.systemDefault()),
                LocalDate.parse(request.getParameter("name")).atStartOfDay(ZoneId.systemDefault()).plusDays(1));
    }

    public Weather getLowestTemperatureByDate(HttpServletRequest request) {
        return weatherRepository.findFirstByMeasuredAtBetweenOrderByTemperature(LocalDate.parse(request.getParameter("name")).atStartOfDay(ZoneId.systemDefault()),
                LocalDate.parse(request.getParameter("name")).atStartOfDay(ZoneId.systemDefault()).plusDays(1));
    }

    public Weather getHighestHumidityByDate(HttpServletRequest request) {
        return weatherRepository.findFirstByMeasuredAtBetweenOrderByHumidityDesc(LocalDate.parse(request.getParameter("name")).atStartOfDay(ZoneId.systemDefault()),
                LocalDate.parse(request.getParameter("name")).atStartOfDay(ZoneId.systemDefault()).plusDays(1));
    }

    public Weather getLowestHumidityByDate(HttpServletRequest request) {
        return weatherRepository.findFirstByMeasuredAtBetweenOrderByHumidity(LocalDate.parse(request.getParameter("name")).atStartOfDay(ZoneId.systemDefault()),
                LocalDate.parse(request.getParameter("name")).atStartOfDay(ZoneId.systemDefault()).plusDays(1));
    }

    public Weather getHighestPressureByDate(HttpServletRequest request) {
        return weatherRepository.findFirstByMeasuredAtBetweenOrderByAtmosphericPressureDesc(LocalDate.parse(request.getParameter("name")).atStartOfDay(ZoneId.systemDefault()),
                LocalDate.parse(request.getParameter("name")).atStartOfDay(ZoneId.systemDefault()).plusDays(1));
    }

    public Weather getLowestPressureByDate(HttpServletRequest request) {
        return weatherRepository.findFirstByMeasuredAtBetweenOrderByAtmosphericPressure(LocalDate.parse(request.getParameter("name")).atStartOfDay(ZoneId.systemDefault()),
                LocalDate.parse(request.getParameter("name")).atStartOfDay(ZoneId.systemDefault()).plusDays(1));
    }
}
