import dao.MeasurementDTO;
import dao.MeasurementResponse;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MeasurementsData {
    public static void main(String[] args) {
        List<Double> measurements = getMeasurements();
        for (int i = 0; i < measurements.size(); i++) {
            System.out.println(i + " - " + measurements.get(i));
        }

        Long rainyDaysCount = getRainingDaysCount();
        System.out.println("Количество дождливых дней - " + rainyDaysCount);
    }

    public static List<Double> getMeasurements() {
        final RestTemplate restTemplate = new RestTemplate();
        final String url = "http://localhost:8080/measurements/";

        MeasurementResponse jsonResponse = restTemplate.getForObject(url, MeasurementResponse.class);
        if (jsonResponse == null || jsonResponse.getMeasurements() == null) {
            return Collections.emptyList();
        }
        return jsonResponse.getMeasurements().stream().map(MeasurementDTO::getValue).collect(Collectors.toList());
    }

    public static Long getRainingDaysCount() {
        final RestTemplate restTemplate = new RestTemplate();
        final String url = "http://localhost:8080/measurements/rainyDaysCount";

        MeasurementResponse jsonResponse = restTemplate.getForObject(url, MeasurementResponse.class);
        if (jsonResponse == null || jsonResponse.getMeasurements() == null) {
            return null;
        }
        return jsonResponse.getMeasurements().stream().filter(MeasurementDTO::isRaining).count();
    }
}
