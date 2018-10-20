package br.com.acoaapi.model.dao;

import br.com.acoaapi.model.entity.LitersPerDay;
import br.com.acoaapi.model.entity.LitersPerMonth;

import java.util.Date;
import java.util.List;

public interface StatisticsDao {

    LitersPerDay loadConsumptionPerDay(Date date, Integer accountId);

    LitersPerMonth loadConsumptionPerMonth(Integer month, Integer accountId);

    List<LitersPerMonth> loadConsumptionPerMonthInYear(Integer year, Integer accountId);

    List<LitersPerDay> loadConsumptionPerDayInYear(Integer year, Integer accountId);
}
