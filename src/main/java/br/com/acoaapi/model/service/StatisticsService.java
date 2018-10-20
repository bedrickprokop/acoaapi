package br.com.acoaapi.model.service;

import br.com.acoaapi.model.entity.Statistics;
import br.com.acoaapi.model.entity.User;

import java.util.Date;

public interface StatisticsService {

    Statistics loadConsumptionStatistics(User user);

    Statistics loadConsumptionPerDay(User user, Date date);

    Statistics loadConsumptionPerMonth(User user, Date date);

    Statistics loadConsumptionPerMonthInYear(User user, Integer year);

    Statistics loadConsumptionPerYear(User user, Integer year);
}
