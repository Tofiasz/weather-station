package pl.gorczynski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.gorczynski.service.LowMaxByDateService;
import javax.servlet.http.HttpServletRequest;
import static pl.gorczynski.controller.MainController.TEXT;
import static pl.gorczynski.controller.MainController.WEATHER_MODEL_NAME;

@Controller
public class LowMaxByDateController {

    private final LowMaxByDateService lowMaxByDateService;

    @Autowired
    public LowMaxByDateController(final LowMaxByDateService lowMaxByDateService) {
        this.lowMaxByDateService = lowMaxByDateService;
    }

    @RequestMapping(path = "/low-max-by-date")
    public String getHome() {
        return "indexLowMaxByDate";
    }

    @PostMapping(path = "/lowest-temp")
    public String minTempByDate(Model model, HttpServletRequest request) {
        model.addAttribute(WEATHER_MODEL_NAME, lowMaxByDateService.getLowestTemperatureByDate(request));
        model.addAttribute(TEXT, "LOWEST TEMPERATURE RECORDED IN ");
        return "ByDate";
    }

    @RequestMapping(value = "/lowest-temp", method = RequestMethod.GET)
    public String lowTempByDate() {
        return "Date";
    }

    @PostMapping(path = "/highest-temp")
    public String maxTempByDate(Model model, HttpServletRequest request) {
        model.addAttribute(WEATHER_MODEL_NAME, lowMaxByDateService.getHighestTemperatureByDate(request));
        model.addAttribute(TEXT, "HIGHEST PRESSURE RECORDED IN ");
        return "ByDate";
    }

    @RequestMapping(value = "/highest-temp", method = RequestMethod.GET)
    public String maxTempByDate() {
        return "Date";
    }

    @PostMapping(path = "/lowest-pressure")
    public String minPressureByDate(Model model, HttpServletRequest request) {
        model.addAttribute(WEATHER_MODEL_NAME, lowMaxByDateService.getLowestPressureByDate(request));
        model.addAttribute(TEXT, "LOWEST PRESSURE RECORDED IN ");
        return "ByDate";
    }

    @RequestMapping(value = "/lowest-pressure", method = RequestMethod.GET)
    public String lowPressureByDate() {
        return "Date";
    }

    @PostMapping(path = "/highest-humidity")
    public String maxHumidityByDate(Model model, HttpServletRequest request) {
        model.addAttribute(WEATHER_MODEL_NAME, lowMaxByDateService.getHighestHumidityByDate(request));
        model.addAttribute(TEXT, "HIGHEST HUMIDITY RECORDED IN ");
        return "ByDate";
    }

    @RequestMapping(value = "/highest-humidity", method = RequestMethod.GET)
    public String maxHumByDate() {
        return "Date";
    }

    @PostMapping(path = "/lowest-humidity")
    public String minHumidityByDate(Model model, HttpServletRequest request) {
        model.addAttribute(WEATHER_MODEL_NAME, lowMaxByDateService.getLowestHumidityByDate(request));
        model.addAttribute(TEXT, "LOWEST HUMIDITY RECORDED IN ");
        return "ByDate";
    }

    @RequestMapping(value = "/lowest-humidity", method = RequestMethod.GET)
    public String lowHumByDate() {
        return "Date";
    }

    @PostMapping(path = "/highest-pressure")
    public String maxPressureByDate(Model model, HttpServletRequest request) {
        model.addAttribute(WEATHER_MODEL_NAME, lowMaxByDateService.getHighestPressureByDate(request));
        model.addAttribute(TEXT, "LOWEST HUMIDITY RECORDED IN ");
        return "ByDate";
    }

    @RequestMapping(value = "/highest-pressure", method = RequestMethod.GET)
    public String maxPressureByDate() {
        return "Date";
    }
}
