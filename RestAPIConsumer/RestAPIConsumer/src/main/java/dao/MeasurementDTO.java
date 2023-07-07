package dao;

import lombok.Getter;
import lombok.Setter;
import ru.vinogradov.springsensor.ApiForWeatherSensor.models.Sensor;

@Getter
@Setter
public class MeasurementDTO {
    private double value;
    private boolean raining;
    private Sensor sensor;
}
