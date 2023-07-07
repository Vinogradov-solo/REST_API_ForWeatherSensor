package dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SensorDTO {

    @Column(name = "name")
    @NotEmpty(message = "Name should be not empty")
    @Size(min = 3, max = 30, message = "Name should be between 3 and 30")
    private String name;
}
