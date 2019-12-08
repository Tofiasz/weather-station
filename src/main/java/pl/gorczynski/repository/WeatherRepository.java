package pl.gorczynski.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.gorczynski.model.Weather;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface WeatherRepository extends CrudRepository<Weather, Integer> {

    Weather findFirstByOrderByIdDesc();

    Weather findFirstByOrderByAtmosphericPressure();

    Weather findFirstByOrderByAtmosphericPressureDesc();

    Weather findFirstByOrderByTemperatureDesc();

    Weather findFirstByOrderByTemperature();

    Weather findFirstByOrderByHumidityDesc();

    Weather findFirstByOrderByHumidity();

    @Query(value = "SELECT AVG(humidity) from weather WHERE measured_at>?1 AND measured_at<?2", nativeQuery = true)
    Double getAvgHumidityByDate(ZonedDateTime measuredAt, ZonedDateTime measuredAt2);

    @Query(value = "SELECT AVG(temperature) from weather WHERE measured_at>?1 AND measured_at<?2", nativeQuery = true)
    Double getAvgTemperatureByDate(ZonedDateTime measuredAt, ZonedDateTime measuredAt2);

    @Query(value = "SELECT AVG(atmospheric_pressure) from weather WHERE measured_at>?1 AND measured_at<?2", nativeQuery = true)
    Double getAvgPressureByDate(ZonedDateTime measuredAt, ZonedDateTime measuredAt2);

    Weather findFirstById(Integer id);

    Weather findFirstByMeasuredAtBetweenOrderByAtmosphericPressure(ZonedDateTime zonedDateTime, ZonedDateTime zonedDateTime2);

    Weather findFirstByMeasuredAtBetweenOrderByAtmosphericPressureDesc(ZonedDateTime zonedDateTime, ZonedDateTime zonedDateTime2);

    Weather findFirstByMeasuredAtBetweenOrderByHumidity(ZonedDateTime zonedDateTime, ZonedDateTime zonedDateTime2);

    Weather findFirstByMeasuredAtBetweenOrderByHumidityDesc(ZonedDateTime zonedDateTime, ZonedDateTime zonedDateTime2);

    Weather findFirstByMeasuredAtBetweenOrderByTemperature(ZonedDateTime zonedDateTime, ZonedDateTime zonedDateTime2);

    Weather findFirstByMeasuredAtBetweenOrderByTemperatureDesc(ZonedDateTime zonedDateTime, ZonedDateTime zonedDateTime2);

    Weather findFirstByMeasuredAtBetween(ZonedDateTime measuredAt, ZonedDateTime measuredAt2);
}
