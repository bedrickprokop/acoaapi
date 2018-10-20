package br.com.acoaapi.model.service.impl;

import br.com.acoaapi.model.dao.HistoryDao;
import br.com.acoaapi.model.dao.StatisticsDao;
import br.com.acoaapi.model.entity.DateFlow;
import br.com.acoaapi.model.entity.History;
import br.com.acoaapi.model.entity.LitersPerMonth;
import br.com.acoaapi.model.entity.User;
import br.com.acoaapi.model.service.HistoryService;
import br.com.acoaapi.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private UserService userService;

    @Autowired
    private StatisticsDao statisticsDao;

    @Autowired
    private HistoryDao historyDao;

    @Override
    public History loadConsumptionHistory(User user) {
        Integer accountId = user.getAccount().getId();

        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);

        //buscar o consumo mensal do ano atual
        List<LitersPerMonth> litersPerMonthList = statisticsDao.loadConsumptionPerMonthInYear(currentYear, accountId);
        Map<String, Double> monthConsumption = setupMonthConsumption(litersPerMonthList);

        //busca o consumo anual dos últimos 4 anos
        Integer years[] = new Integer[]{currentYear, currentYear - 1, currentYear - 2, currentYear - 3};
        List<LitersPerMonth> litersPerMonthInYears = historyDao.loadConsumptionPerYears(years, accountId);
        Map<Integer, Double> yearsConsumption = setupYearsConsumption(litersPerMonthInYears, currentYear);

        History history = new History();
        history.setTotalMonthConsumption(monthConsumption);
        history.setTotalYearsConsumption(yearsConsumption);
        return history;
    }

    private Map<String, Double> setupMonthConsumption(List<LitersPerMonth> litersPerMonthList) {
        List<DateFlow> monthlyConsumptionList = new ArrayList<>();

        Calendar calStartCollection = initializeCalendar(litersPerMonthList.get(0).getDateCollection());
        Calendar calEndDateCollection = initializeCalendar(litersPerMonthList
                .get(litersPerMonthList.size() - 1).getDateCollection());

        Calendar calBeginingYear = initializeCalendar(Calendar.JANUARY, true);
        Calendar calEndingYear = initializeCalendar(Calendar.DECEMBER, false);
        int resultStart = calStartCollection.compareTo(calBeginingYear);

        if (resultStart > 0) {
            while (calBeginingYear.compareTo(calStartCollection) < 0) {
                monthlyConsumptionList.add(constructMonthlyConsumptionObject(
                        calBeginingYear.getTime(), 0.0));
                calBeginingYear.add(Calendar.DAY_OF_MONTH, 1);
            }
        }

        for (LitersPerMonth litersPerMonth : litersPerMonthList) {
            Date dateCollection = new Date(litersPerMonth.getDateCollection().getTime());
            monthlyConsumptionList.add(constructMonthlyConsumptionObject(dateCollection,
                    litersPerMonth.getFlowRate()));
        }

        int resultEnd = calEndDateCollection.compareTo(calEndingYear);
        if (resultEnd < 0) {
            while (calEndingYear.compareTo(calEndDateCollection) >= 0) {
                monthlyConsumptionList.add(constructMonthlyConsumptionObject(calEndDateCollection.getTime(), 0.0));
                calEndDateCollection.add(Calendar.DAY_OF_MONTH, 1);
            }
        }

        Map<String, Double> monthConsumption = new LinkedHashMap<>();
        for (DateFlow dateFlow : monthlyConsumptionList) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateFlow.getDate());
            String displayName = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
            monthConsumption.put(displayName, dateFlow.getFlowRate());
        }
        return monthConsumption;
    }

    private Map<Integer, Double> setupYearsConsumption(List<LitersPerMonth> litersPerMonthList, Integer currentYear) {
        List<DateFlow> monthlyConsumptionList = new ArrayList<>();

        Calendar calStartCollection = initializeCalendar(litersPerMonthList.get(0).getDateCollection());
        Calendar calEndDateCollection = initializeCalendar(litersPerMonthList.get(litersPerMonthList.size() - 1)
                .getDateCollection());

        Calendar calBeginingYear = Calendar.getInstance();
        calBeginingYear.set(Calendar.YEAR, currentYear - 3);
        calBeginingYear.set(Calendar.MONTH, Calendar.JANUARY);
        calBeginingYear.set(Calendar.DAY_OF_MONTH, calBeginingYear.getActualMinimum(Calendar.DAY_OF_MONTH));
        calBeginingYear = initializeCalendar(calBeginingYear.getTime());

        Calendar calEndingYear = Calendar.getInstance();
        calEndingYear.set(Calendar.YEAR, currentYear);
        calEndingYear.set(Calendar.MONTH, Calendar.DECEMBER);
        calEndingYear.set(Calendar.DAY_OF_MONTH, calEndingYear.getActualMaximum(Calendar.DAY_OF_MONTH));
        calEndingYear = initializeCalendar(calEndingYear.getTime());

        //Retorna valor MENOR que 0 se o tempo de 'calStartCollection' for inferior do que 'calBeginingYear'
        //Retorna valor MAIOR que 0 se o tempo de 'calStartCollection' for superior do que 'calBeginingYear'
        int resultStart = calStartCollection.compareTo(calBeginingYear);
        if (resultStart > 0) {
            while (calBeginingYear.compareTo(calStartCollection) < 0) {
                monthlyConsumptionList.add(constructMonthlyConsumptionObject(
                        calBeginingYear.getTime(), 0.0));
                calBeginingYear.add(Calendar.DAY_OF_MONTH, 1);
            }
        }

        for (LitersPerMonth litersPerMonth : litersPerMonthList) {
            Date dateCollection = new Date(litersPerMonth.getDateCollection().getTime());
            monthlyConsumptionList.add(constructMonthlyConsumptionObject(dateCollection,
                    litersPerMonth.getFlowRate()));
        }

        int resultEnd = calEndDateCollection.compareTo(calEndingYear);
        if (resultEnd < 0) {
            while (calEndingYear.compareTo(calEndDateCollection) >= 0) {
                monthlyConsumptionList.add(constructMonthlyConsumptionObject(calEndDateCollection.getTime(), 0.0));
                calEndDateCollection.add(Calendar.DAY_OF_MONTH, 1);
            }
        }

        Map<Integer, Double> yearsConsumption = new LinkedHashMap<>();
        Double[] totalSum = new Double[]{0.0, 0.0, 0.0, 0.0};
        for (DateFlow dateFlow : monthlyConsumptionList) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateFlow.getDate());
            int calYear = calendar.get(Calendar.YEAR);

            for (int i = currentYear - 3; i <= currentYear; i++) {
                if (calYear == i) {
                    totalSum[i - (currentYear - 3)] += dateFlow.getFlowRate();
                }
            }
        }
        yearsConsumption.put(currentYear, totalSum[3]);
        yearsConsumption.put(currentYear - 1, totalSum[2]);
        yearsConsumption.put(currentYear - 2, totalSum[1]);
        yearsConsumption.put(currentYear - 3, totalSum[0]);
        return yearsConsumption;
    }

    private Calendar initializeCalendar(Integer month, Boolean isFirstDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month);
        if (isFirstDay)
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        else
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return setDefaultValues(calendar);
    }

    private Calendar initializeCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return setDefaultValues(calendar);
    }

    private Calendar setDefaultValues(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    private DateFlow constructMonthlyConsumptionObject(Date date, Double value) {
        DateFlow dateFlow = new DateFlow();
        dateFlow.setDate(date);
        dateFlow.setFlowRate(value);
        return dateFlow;
    }
}
