import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Hello world!
 *
 */
public class Client {
    public static void main( String[] args ) {
        final String sensorName = "Sensor1";

        addSensor(sensorName);

        Random random = new Random();

        double maxTemperature = 45.0;
        for (int i = 0; i < 500; i++) {
            System.out.println(i);
            sendMeasurement(random.nextDouble() * maxTemperature, random.nextBoolean(), sensorName);
        }
    }

    public static void addSensor(String sensorName) {
        final String registrationUrl = "http://localhost:8080/sensors/registration";

        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("name", sensorName);

        makePostRequestWithJSONData(registrationUrl, jsonData);
    }

    public static void sendMeasurement(Double value, boolean isRaining, String sensorName) {
        final String measurementUrl = "http://localhost:8080/measurements/add";

        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("value", value);
        jsonData.put("raining", isRaining);
        jsonData.put("sensor", Map.of("name", sensorName));

        makePostRequestWithJSONData(measurementUrl, jsonData);
    }

    private static void makePostRequestWithJSONData(String url, Map<String, Object> jsonData) {
        final RestTemplate restTemplate = new RestTemplate();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> request = new HttpEntity<>(jsonData, headers);

        try {
            restTemplate.postForObject(url, request, String.class);
            System.out.println("Measurement successfully has been sent to the server!");
        } catch (HttpClientErrorException e) {
            System.err.println("ERROR!");
            System.err.println(e.getMessage());
        }
    }
}
