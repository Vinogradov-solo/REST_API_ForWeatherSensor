package ru.vinogradov.springsensor.ApiForWeatherSensor.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class MeasurementDTO {

    @NotNull
    @Min(-100)
    @Max(100)
    private Double value;

    @NotNull
    private Boolean raining;

    @NotNull
    private SensorDTO sensor;
}
