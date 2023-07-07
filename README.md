# REST_API_ForWeatherSensor
REST API service that will receive data from the "sensor"
The weather sensor, as a customer, measures the air temperature and determines if it is raining or not. 
My REST API accepts data from the sensor and stores it in the database. 
Since we don't have a real sensor, it will be our own Java Client, which will use RestTemplate to send HTTP requests to my service.
