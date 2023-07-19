# REST API For Weather Sensor

A REST API service designed to receive data from a “Weather sensor”

## Functionality

- The weather sensor measures the air temperature, and determines whether it’s raining or not
- My REST API receives data from the sensor, and stores it into the database

### Technology

- Spring Boot 2.7
- REST Template
- Jackson
- Data JPA (+ Hibernate)

#### Details

- Since we do not have a real weather sensor, we will use our own Java client, which uses the RestTemplate to make HTTP requests to our service
– Standard layers include controllers, services, config, DAO, models, repos, utils (validation)
– Uses PostgreSQL database
- Uses Jackson to work witn requests from my Java Client to REST API service
