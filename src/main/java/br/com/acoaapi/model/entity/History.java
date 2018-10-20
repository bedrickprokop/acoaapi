package br.com.acoaapi.model.entity;

import java.util.Map;

public class History {

    private Map<String, Double> totalMonthConsumption;
    private Map<Integer, Double> totalYearsConsumption;

    public Map<String, Double> getTotalMonthConsumption() {
        return totalMonthConsumption;
    }

    public void setTotalMonthConsumption(Map<String, Double> totalMonthConsumption) {
        this.totalMonthConsumption = totalMonthConsumption;
    }

    public Map<Integer, Double> getTotalYearsConsumption() {
        return totalYearsConsumption;
    }

    public void setTotalYearsConsumption(Map<Integer, Double> totalYearsConsumption) {
        this.totalYearsConsumption = totalYearsConsumption;
    }
}
