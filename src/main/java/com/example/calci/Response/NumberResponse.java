package com.example.calci.Response;

import java.util.List;

public class NumberResponse {
    private List<Integer> numbers;
    private List<Integer> windowPrevState;
    private List<Integer> windowCurrState;
    private double avg;

    public NumberResponse(List<Integer> numbers, List<Integer> windowPrevState, List<Integer> windowCurrState, double avg) {
        this.numbers = numbers;
        this.windowPrevState = windowPrevState;
        this.windowCurrState = windowCurrState;
        this.avg = avg;
    }
}

