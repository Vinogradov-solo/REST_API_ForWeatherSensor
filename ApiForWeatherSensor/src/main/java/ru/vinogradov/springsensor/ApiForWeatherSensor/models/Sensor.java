package ru.vinogradov.springsensor.ApiForWeatherSensor.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "Sensor")
@Getter
@Setter
@NoArgsConstructor
public class Sensor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sensor_id")
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Name should be not empty")
    @Size(min = 3, max = 30, message = "Name should be between 3 and 30")
    private String name;
}
