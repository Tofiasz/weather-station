package pl.gorczynski.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.gorczynski.service.LowMaxEverService;

import static pl.gorczynski.controller.MainController.TEXT;
import static pl.gorczynski.controller.MainController.WEATHER_MODEL_NAME;

@Controller
public class LowMaxEverController {

    private final LowMaxEverService lowMaxEverService;

    @Autowired
    public LowMaxEverController(final LowMaxEverService lowMaxEverService) {
        this.lowMaxEverService = lowMaxEverService;
    }

    @RequestMapping(path = "/low-max-ever")
    public String getHome() {
        return "indexLowMaxEver";
    }

    @GetMapping(path = "/highest-humidity-in-ever")
    public String maxHumidity(Model model) {
        model.addAttribute(WEATHER_MODEL_NAME, lowMaxEverService.getHighestHumidityEver());
        model.addAttribute(TEXT, "HIGHEST HUMIDITY EVER ");
        return "Ever";
    }

    @GetMapping(path = "/lowest-humidity-in-ever")
    public String minHumidity(Model model) {
        model.addAttribute(WEATHER_MODEL_NAME, lowMaxEverService.getLowestHumidityEver());
        model.addAttribute(TEXT, "LOWEST HUMIDITY EVER ");
        return "Ever";
    }

    @GetMapping(path = "/highest-temp-in-ever")
    public String maxTemp(Model model) {
        model.addAttribute(WEATHER_MODEL_NAME, lowMaxEverService.getHighestTemperatureEver());
        model.addAttribute(TEXT, "HIGHEST TEMPERATURE EVER ");
        return "Ever";
    }

    @GetMapping(path = "/lowest-temp-in-ever")
    public String minTemp(Model model) {
        model.addAttribute(WEATHER_MODEL_NAME, lowMaxEverService.getLowestTemperatureEver());
        model.addAttribute(TEXT, "LOWEST TEMPERATURE EVER ");
        return "Ever";
    }

    @GetMapping(path = "/highest-pressure-in-ever")
    public String maxPressure(Model model) {
        model.addAttribute(WEATHER_MODEL_NAME, lowMaxEverService.getHighestPressureEver());
        model.addAttribute(TEXT, "HIGHEST PRESSURE EVER ");
        return "Ever";
    }

    @GetMapping(path = "/lowest-pressure-in-ever")
    public String minPressure(Model model) {
        model.addAttribute(WEATHER_MODEL_NAME, lowMaxEverService.getLowestPressureEver());
        model.addAttribute(TEXT, "LOWEST PRESSURE EVER ");
        return "Ever";
    }
}
