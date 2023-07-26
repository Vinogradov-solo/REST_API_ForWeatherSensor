package ru.vinogradov.springsensor.ApiForWeatherSensor.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.vinogradov.springsensor.ApiForWeatherSensor.models.Measurement;
import ru.vinogradov.springsensor.ApiForWeatherSensor.services.SensorService;

@Component
public class MeasurementValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public MeasurementValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Measurement measurement = (Measurement) o;

        if (measurement.getSensor() == null)
            return;
        if (sensorService.findByName(measurement.getSensor().getName()).isEmpty())
            errors.rejectValue("sensor", "No registered sensor with this name!");
    }
}
