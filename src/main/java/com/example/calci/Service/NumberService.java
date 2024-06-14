package com.example.calci.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.example.calci.Response.*;
@Service
public class NumberService {

    private final RestTemplate restTemplate = new RestTemplate();

    private List<Integer> numberWindow = new ArrayList<>();
    private int windowSize = 10;

    public NumberResponse processRequest(String numberId) {
        List<Integer> fetchedNumbers = fetchNumbersFromTestServer(numberId);

        updateNumberWindow(fetchedNumbers);

        return createResponse(fetchedNumbers);
    }

    private List<Integer> fetchNumbersFromTestServer(String numberId) {
        String apiUrl = buildApiUrl(numberId);
        try {
            Integer[] response = restTemplate.getForObject(apiUrl, Integer[].class);
            if (response != null) {
                return List.of(response);
            }
        } catch (Exception e) {
            System.err.println("Error fetching numbers from test server: " + e.getMessage());
        }
        return Collections.emptyList();
    }

    private void updateNumberWindow(List<Integer> fetchedNumbers) {
        numberWindow.addAll(fetchedNumbers);
        if (numberWindow.size() > windowSize) {
            numberWindow = numberWindow.subList(numberWindow.size() - windowSize, numberWindow.size());
        }
    }

    private NumberResponse createResponse(List<Integer> fetchedNumbers) {
        List<Integer> prevWindow = new ArrayList<>(numberWindow);
        List<Integer> currWindow = new ArrayList<>(numberWindow);
        currWindow.removeAll(prevWindow);
        currWindow.addAll(fetchedNumbers);

        double average = calculateAverage(currWindow);

        return new NumberResponse(fetchedNumbers, prevWindow, currWindow, average);
    }

    private double calculateAverage(List<Integer> numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        int sum = numbers.stream().mapToInt(Integer::intValue).sum();
        return (double) sum / numbers.size();
    }

    private String buildApiUrl(String numberId) {
        return "http://localhost:9876/numbers/e" + numberId; // Example URL
    }
}
