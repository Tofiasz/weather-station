package pl.gorczynski.model;

import com.sun.istack.NotNull;
import javax.persistence.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
@Table(name = "weather")

public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", unique = true, nullable = false)
    @NotNull
    private Integer id;
    @Column(name = "temperature")
    @NotNull
    private Double temperature;
    @Column(name = "humidity")
    @NotNull
    private Double humidity;
    @Column(name = "atmospheric_pressure")
    @NotNull
    private Double atmosphericPressure;
    @Column(name = "measured_at")
    @NotNull
    private ZonedDateTime measuredAt;

    public Weather() {
    }

    public Weather(Double temperature, ZonedDateTime measuredAt, Double humidity, Double atmosphericPressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.atmosphericPressure = atmosphericPressure;
        this.measuredAt = measuredAt;
    }

    public Weather(Double temperature, Double humidity, Double atmosphericPressure) {
        this(temperature, ZonedDateTime.now(ZoneId.of("Europe/Warsaw")), humidity, atmosphericPressure);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer Id) {
        this.id = Id;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getAtmosphericPressure() {
        return atmosphericPressure;
    }

    public void setAtmosphericPressure(Double atmosphericPressure) {
        this.atmosphericPressure = atmosphericPressure;
    }

    public ZonedDateTime getMeasuredAt() {
        return measuredAt;
    }

    public void setMeasuredAt(ZonedDateTime measuredAt) {
        this.measuredAt = measuredAt;
    }
}