package ru.vinogradov.springsensor.ApiForWeatherSensor.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vinogradov.springsensor.ApiForWeatherSensor.dto.MeasurementDTO;
import ru.vinogradov.springsensor.ApiForWeatherSensor.dto.MeasurementResponse;
import ru.vinogradov.springsensor.ApiForWeatherSensor.models.Measurement;
import ru.vinogradov.springsensor.ApiForWeatherSensor.services.MeasurementService;
import ru.vinogradov.springsensor.ApiForWeatherSensor.util.MeasurementErrorResponse;
import ru.vinogradov.springsensor.ApiForWeatherSensor.util.MeasurementException;
import ru.vinogradov.springsensor.ApiForWeatherSensor.util.MeasurementValidator;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.stream.Collectors;

import static ru.vinogradov.springsensor.ApiForWeatherSensor.util.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementValidator measurementValidator;
    private final ModelMapper mapper;
    private final MeasurementService measurementService;

    @Autowired
    public MeasurementController(MeasurementValidator measurementValidator, ModelMapper mapper, MeasurementService measurementService) {
        this.measurementValidator = measurementValidator;
        this.mapper = mapper;
        this.measurementService = measurementService;
    }

    @GetMapping
    public MeasurementResponse getMeasurements() {
        return new MeasurementResponse(measurementService.findAll()
                .stream().map(this::convertToMeasurementDTO).collect(Collectors.toList()));
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO,
                                                     @NotNull BindingResult bindingResult) {
        Measurement measurementToAdd = convertToMeasurement(measurementDTO);

        measurementValidator.validate(measurementToAdd, bindingResult);
        if (bindingResult.hasErrors())
            returnErrorsToClient(bindingResult);

        measurementService.addMeasurement(measurementToAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/rainyDaysCount")
    public Long getRainyDaysCount() {
        return measurementService.findAll().stream().filter(Measurement::isRaining).count();
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(e.getMessage(),
                                                                        System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return mapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return mapper.map(measurement, MeasurementDTO.class);
    }
}
