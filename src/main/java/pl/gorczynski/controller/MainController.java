package pl.gorczynski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.gorczynski.model.Weather;
import pl.gorczynski.service.WeatherService;

@Controller
public class MainController {
    public static final String WEATHER_MODEL_NAME = "weather";
    public static final String TEXT = "text";
    private final WeatherService weatherService;

    @Autowired
    public MainController(final WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @RequestMapping(path = {"/", "/home"})
    public String getHome(Model model) {
        Weather weather = weatherService.getCurrentWeather();
        model.addAttribute(WEATHER_MODEL_NAME, weather);

        return "index";
    }
}