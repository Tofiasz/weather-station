package pl.gorczynski.dto;

public class SensorDataDTO {
    private double humidity;
    private double pressure;
    private double temperature;

    public SensorDataDTO(final double humidity, final double pressure, final double temperature) {
        this.humidity = humidity;
        this.pressure = pressure;
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public double getTemperature() {
        return temperature;
    }
}

