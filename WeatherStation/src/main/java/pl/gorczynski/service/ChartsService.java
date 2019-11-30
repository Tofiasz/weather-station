package pl.gorczynski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.gorczynski.model.Weather;
import pl.gorczynski.repository.*;

import javax.servlet.http.HttpServletRequest;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;

@Service
public class ChartsService {
    private final WeatherRepository weatherRepository;

    @Autowired
    public ChartsService(final WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public Map<Integer, Double> getEveryHourPressure(HttpServletRequest request) {
        Map<Integer, Double> surveyMapPressure = new LinkedHashMap<>();
        LocalDate localDate = LocalDate.parse(request.getParameter("name"));
        for (int i = 0; i <= 23; i++) {
            Weather weather = weatherRepository.findFirstByMeasuredAtBetween(
                    (localDate.atTime(LocalTime.of(i, 0, 0))).atZone(ZoneId.of("Europe/Warsaw")),
                    (localDate.atTime(LocalTime.of(i, 0, 30))).atZone(ZoneId.of("Europe/Warsaw")));
            if (weather != null) {
                surveyMapPressure.put(i, weather.getAtmosphericPressure());
            }
            else{
                surveyMapPressure.put(i, null);
            }
        }
        return surveyMapPressure;
    }

    public Map<Integer, Double> getEveryHourHumidity(HttpServletRequest request) {
        Map<Integer, Double> surveyMapHumidity = new LinkedHashMap<>();
        LocalDate localDate = LocalDate.parse(request.getParameter("name"));
        for (int i = 0; i <= 23; i++) {
            Weather weather = weatherRepository.findFirstByMeasuredAtBetween(
                    (localDate.atTime(LocalTime.of(i, 0, 0))).atZone(ZoneId.of("Europe/Warsaw")),
                    (localDate.atTime(LocalTime.of(i, 0, 30))).atZone(ZoneId.of("Europe/Warsaw")));
            if (weather != null) {
                surveyMapHumidity.put(i, weather.getHumidity());
            }
            else{
                surveyMapHumidity.put(i, null);
            }
        }
        return surveyMapHumidity;
    }

    public Map<Integer, Double> getEveryHourTemperature(HttpServletRequest request) {
        Map<Integer, Double> surveyMapTemperature = new LinkedHashMap<>();
        LocalDate localDate = LocalDate.parse(request.getParameter("name"));
        for (int i = 0; i <= 23; i++) {
            Weather weather = weatherRepository.findFirstByMeasuredAtBetween(
                    (localDate.atTime(LocalTime.of(i, 0, 0))).atZone(ZoneId.of("Europe/Warsaw")),
                    (localDate.atTime(LocalTime.of(i, 0, 30))).atZone(ZoneId.of("Europe/Warsaw")));
            if (weather != null) {
                surveyMapTemperature.put(i, weather.getTemperature());
            }
            else{
                surveyMapTemperature.put(i, null);
            }
        }
        return surveyMapTemperature;
    }

    public Map<Integer, Double> getDataOfAverageDailyPressure() {
        Map<Integer, Double> surveyAvgDailyPressure = new LinkedHashMap<>();
        for (int i = 0; i <= ChronoUnit.DAYS.between(getFirstDate().getMeasuredAt().toLocalDate(), LocalDate.now()); i++) {
            surveyAvgDailyPressure.put(i, weatherRepository.getAvgPressureByDate(getFirstDate().getMeasuredAt().toLocalDateTime().atZone(ZoneId.systemDefault()),
                    getFirstDate().getMeasuredAt().toLocalDateTime().atZone(ZoneId.systemDefault()).plusDays(1)));
        }
        return surveyAvgDailyPressure;
    }

    public Map<Integer, Double> getDataOfAverageDailyHumidity() {
        Map<Integer, Double> surveyAvgDailyHumidity = new LinkedHashMap<>();
        for (int i = 0; i <= ChronoUnit.DAYS.between(getFirstDate().getMeasuredAt().toLocalDate(), LocalDate.now()); i++) {
            surveyAvgDailyHumidity.put(i, weatherRepository.getAvgHumidityByDate(getFirstDate().getMeasuredAt().toLocalDateTime().atZone(ZoneId.systemDefault()),
                    getFirstDate().getMeasuredAt().toLocalDateTime().atZone(ZoneId.systemDefault()).plusDays(1)));
        }
        return surveyAvgDailyHumidity;
    }

    public Map<Integer, Double> getDataOfAverageDailyTemperature() {
        Map<Integer, Double> surveyAvgDailyTemperature = new LinkedHashMap<>();
        for (int i = 0; i <= ChronoUnit.DAYS.between(getFirstDate().getMeasuredAt().toLocalDate(), LocalDate.now()); i++) {
            surveyAvgDailyTemperature.put(i, weatherRepository.getAvgTemperatureByDate(getFirstDate().getMeasuredAt().toLocalDateTime().atZone(ZoneId.systemDefault()),
                    getFirstDate().getMeasuredAt().toLocalDateTime().atZone(ZoneId.systemDefault()).plusDays(1)));
        }
        return surveyAvgDailyTemperature;
    }

    public Map<Integer, Double> getDataForLineChartTemperature() {
        Map<Integer, Double> surveyChart = new LinkedHashMap<>();
        for (Integer i = 1; i <= weatherRepository.findFirstByOrderByIdDesc().getId(); i++) {
                        surveyChart.put(i, weatherRepository.findFirstById(i).getTemperature());
        }
        return surveyChart;
    }

    public Map<Integer, Double> getDataForLineChartHumidity() {
        Map<Integer, Double> surveyChart = new LinkedHashMap<>();
        for (Integer i = 1; i <= weatherRepository.findFirstByOrderByIdDesc().getId(); i++) {
            surveyChart.put(i, weatherRepository.findFirstById(i).getHumidity());
        }
        return surveyChart;
    }

    public Map<Integer, Double> getDataForLineChartPressure() {
        Map<Integer, Double> surveyChart = new LinkedHashMap<>();
        for (Integer i = 1; i <= weatherRepository.findFirstByOrderByIdDesc().getId(); i++) {
            surveyChart.put(i, weatherRepository.findFirstById(i).getAtmosphericPressure());
        }
        return surveyChart;
    }

    public Weather getFirstDate() {
        return weatherRepository.findFirstById(1);
    }
}
