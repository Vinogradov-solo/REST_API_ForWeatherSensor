package ru.vinogradov.springsensor.ApiForWeatherSensor.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "measuring")
@Getter
@Setter
@NoArgsConstructor
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "value")
    @NotEmpty
    @Min(-100)
    @Max(100)
    private Double value;

    @Column(name = "raining")
    @NotNull
    private Boolean raining;

    @Column(name = "measuring_time")
    @NotNull
    private LocalDateTime measuringTime;

    @ManyToOne()
    @JoinColumn(name = "sensor", referencedColumnName = "name")
    @NotNull
    private Sensor sensor;

    public Measurement(Double value, Boolean raining, LocalDateTime measuringTime, Sensor sensor) {
        this.value = value;
        this.raining = raining;
        this.measuringTime = measuringTime;
        this.sensor = sensor;
    }

    public boolean isRaining() {
        return raining;
    }
}
