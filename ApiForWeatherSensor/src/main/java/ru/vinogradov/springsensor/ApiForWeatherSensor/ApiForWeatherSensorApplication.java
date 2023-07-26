package ru.vinogradov.springsensor.ApiForWeatherSensor;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiForWeatherSensorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiForWeatherSensorApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {  // Специальный маппер (mvnDependency) чтобы не устанавливать для модели поля вручную, так как их может быть много
		return new ModelMapper();
	}

}
