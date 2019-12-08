package pl.gorczynski.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.gorczynski.service.ChartsService;
import javax.servlet.http.HttpServletRequest;
import static pl.gorczynski.controller.MainController.TEXT;

@Controller
public class ChartsController {
    private final String year="year";
    private final String month="month";
    private final String day="day";
    private final String minute="minute";
    private final String hour="hour";
    private final String surveyChart="surveyChart";
    private final String surveyMapTemperature="surveyMapTemperature";
    private final String surveyMapHumidity="surveyMapHumidity";
    private final String surveyMapPressure="surveyMapPressure";
    private final ChartsService chartsService;

    @Autowired
    public ChartsController(final ChartsService chartsService) {
        this.chartsService = chartsService;
    }

    @RequestMapping(path = "/charts")
    public String getHome() {
        return "indexCharts";
    }

    @PostMapping(path = "/chart-all")
    public String chartAllThree(Model model, HttpServletRequest request) {
        model.addAttribute(this.surveyMapTemperature, chartsService.getEveryHourTemperature(request));
        model.addAttribute(this.surveyMapHumidity, chartsService.getEveryHourHumidity(request));
        model.addAttribute(this.surveyMapPressure, chartsService.getEveryHourPressure(request));
        return "chartAllThree";
    }

    @RequestMapping(value = "/chart-all", method = RequestMethod.GET)
    public String chartAllThree() {
        return "Date";
    }

    @GetMapping(path = "/chart-average-daily")
    public String chartAvgDaily(Model model) {
        model.addAttribute(year, chartsService.getFirstDate().getMeasuredAt().getYear());
        model.addAttribute(month, chartsService.getFirstDate().getMeasuredAt().getMonthValue());
        model.addAttribute(day, chartsService.getFirstDate().getMeasuredAt().getDayOfMonth());
        model.addAttribute(surveyMapTemperature, chartsService.getDataOfAverageDailyTemperature());
        model.addAttribute(surveyMapHumidity, chartsService.getDataOfAverageDailyHumidity());
        model.addAttribute(surveyMapPressure, chartsService.getDataOfAverageDailyPressure());
        return "chartAvgDaily";
    }

    @GetMapping(path = "/line-temperature-chart")
    public String lineTemperatureChart(Model model) {
        getModelOfDateAndTime(model);
        model.addAttribute(this.surveyChart, chartsService.getDataForLineChartTemperature());
        model.addAttribute(TEXT, "Temperature Measurements");
        return "chartOneLine";
    }

    @GetMapping(path = "/line-humidity-chart")
    public String lineHumidityChart(Model model) {
        getModelOfDateAndTime(model);
        model.addAttribute(this.surveyChart, chartsService.getDataForLineChartHumidity());
        model.addAttribute(TEXT, "Humidity Measurements");
        return "chartOneLine";
    }

    @GetMapping(path = "/line-pressure-chart")
    public String linePressureChart(Model model) {
        getModelOfDateAndTime(model);
        model.addAttribute(this.surveyChart, chartsService.getDataForLineChartPressure());
        model.addAttribute(TEXT, "Atmospheric Pressure Measurements");
        return "chartOneLine";
    }

    private void getModelOfDateAndTime(Model model) {
        model.addAttribute(year, chartsService.getFirstDate().getMeasuredAt().getYear());
        model.addAttribute(month, chartsService.getFirstDate().getMeasuredAt().getMonthValue());
        model.addAttribute(day, chartsService.getFirstDate().getMeasuredAt().getDayOfMonth());
        model.addAttribute(hour, chartsService.getFirstDate().getMeasuredAt().getHour());
        model.addAttribute(minute, chartsService.getFirstDate().getMeasuredAt().getMinute());
    }
}
