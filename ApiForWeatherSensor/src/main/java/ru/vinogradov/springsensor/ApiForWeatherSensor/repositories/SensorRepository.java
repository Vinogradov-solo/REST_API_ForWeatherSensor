package ru.vinogradov.springsensor.ApiForWeatherSensor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vinogradov.springsensor.ApiForWeatherSensor.models.Sensor;

import java.util.Optional;

public interface SensorRepository extends JpaRepository<Sensor, String> {

    Optional<Sensor> findByName(String name);
}
