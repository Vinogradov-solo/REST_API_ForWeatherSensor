package ru.vinogradov.springsensor.ApiForWeatherSensor.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vinogradov.springsensor.ApiForWeatherSensor.dto.SensorDTO;
import ru.vinogradov.springsensor.ApiForWeatherSensor.models.Sensor;
import ru.vinogradov.springsensor.ApiForWeatherSensor.services.SensorService;
import ru.vinogradov.springsensor.ApiForWeatherSensor.util.MeasurementErrorResponse;
import ru.vinogradov.springsensor.ApiForWeatherSensor.util.MeasurementException;
import ru.vinogradov.springsensor.ApiForWeatherSensor.util.SensorValidator;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static ru.vinogradov.springsensor.ApiForWeatherSensor.util.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorValidator sensorValidator;
    private final ModelMapper mapper;
    private final SensorService sensorService;

    @Autowired
    public SensorController(SensorValidator sensorValidator, ModelMapper mapper, SensorService sensorService) {
        this.sensorValidator = sensorValidator;
        this.mapper = mapper;
        this.sensorService = sensorService;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> createSensor(
                                                    @RequestBody @Valid SensorDTO sensorDTO,
                                                    @NotNull BindingResult bindingResult) {
        Sensor sensor = convertToSensor(sensorDTO);

        sensorValidator.validate(sensor, bindingResult);
        if(bindingResult.hasErrors())
            returnErrorsToClient(bindingResult);

        sensorService.save(sensor);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(e.getMessage(),
                                                                         System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return mapper.map(sensorDTO, Sensor.class);
    }
}
