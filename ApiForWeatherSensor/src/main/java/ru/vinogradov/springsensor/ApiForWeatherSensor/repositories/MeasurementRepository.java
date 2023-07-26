package ru.vinogradov.springsensor.ApiForWeatherSensor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vinogradov.springsensor.ApiForWeatherSensor.models.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement, String> {
}
