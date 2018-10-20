package br.com.acoaapi.model.service.impl;

import br.com.acoaapi.exception.ApplicationException;
import br.com.acoaapi.model.dao.StatisticsDao;
import br.com.acoaapi.model.entity.*;
import br.com.acoaapi.model.service.StatisticsService;
import br.com.acoaapi.model.service.UserService;
import br.com.acoaapi.utils.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private UserService userService;
    @Autowired
    private StatisticsDao statisticsDao;

    @Override
    public Statistics loadConsumptionStatistics(User user) {
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        int year = calendar.get(Calendar.YEAR);

        Statistics statistics = loadConsumptionPerDay(user, currentDate);
        Statistics statisticsPerMonth = loadConsumptionPerMonth(user, currentDate);
        Statistics statisticsPerYear = loadConsumptionPerYear(user, year);

        statistics.setAverageConsumptionPerDay(statisticsPerMonth.getAverageConsumptionPerDay());
        statistics.setTotalConsumptionPerMonth(statisticsPerMonth.getTotalConsumptionPerMonth());
        statistics.setAverageConsumptionPerMonth(statisticsPerYear.getAverageConsumptionPerMonth());
        statistics.setTotalConsumptionPerYear(statisticsPerYear.getTotalConsumptionPerYear());

        List<LitersPerDay> litersPerDayInYearList = statisticsDao.loadConsumptionPerDayInYear(
                year, user.getAccount().getId());
        statistics.setDailyConsumptionList(setupDailyConsumptionList(litersPerDayInYearList));

        Integer totalAccountUsers = userService.loadTotalUsersByAccount(user.getAccount());
        statistics.setTotalAccountUsers(totalAccountUsers);
        return statistics;
    }

    @Override
    public Statistics loadConsumptionPerDay(User user, Date date) {
        try {
            Integer accountId = user.getAccount().getId();

            LitersPerDay litersPerDay = statisticsDao.loadConsumptionPerDay(date, accountId);
            Statistics statistics = new Statistics();
            if (null != litersPerDay) {
                statistics.setAverageConsumptionPerHour(NumberUtils.roundWithTwoDecimals(
                        litersPerDay.getFlowRate() / 24));
                statistics.setTotalConsumptionPerDay(NumberUtils.roundWithTwoDecimals(litersPerDay
                        .getFlowRate()));
            }
            return statistics;
        } catch (Exception e) {
            if (e instanceof EmptyResultDataAccessException) {
                throw new ApplicationException("response.error.entity.notfound",
                        HttpStatus.NOT_FOUND.value());
            }
            throw new ApplicationException("internal.error.server",
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public Statistics loadConsumptionPerMonth(User user, Date date) {
        try {
            Integer accountId = user.getAccount().getId();

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int actualMaximum = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH) + 1;

            LitersPerMonth litersPerMonth = statisticsDao.loadConsumptionPerMonth(month, accountId);
            Statistics statistics = new Statistics();
            if (null != litersPerMonth) {
                statistics.setAverageConsumptionPerDay(
                        NumberUtils.roundWithTwoDecimals(litersPerMonth.getFlowRate() / actualMaximum));
                statistics.setTotalConsumptionPerMonth(
                        NumberUtils.roundWithTwoDecimals(litersPerMonth.getFlowRate()));
            }
            return statistics;
        } catch (Exception e) {
            if (e instanceof EmptyResultDataAccessException) {
                throw new ApplicationException("response.error.entity.notfound",
                        HttpStatus.NOT_FOUND.value());
            }
            throw new ApplicationException("internal.error.server",
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public Statistics loadConsumptionPerMonthInYear(User user, Integer year) {
        try {
            Integer accountId = user.getAccount().getId();
            List<LitersPerMonth> litersPerMonthList = statisticsDao
                    .loadConsumptionPerMonthInYear(year, accountId);
            List<DateFlow> monthlyConsumptionList = new ArrayList<>();
            litersPerMonthList.forEach(litersPerMonth -> {
                DateFlow dateFlow = new DateFlow();
                dateFlow.setDate(litersPerMonth.getDateCollection());
                dateFlow.setFlowRate(litersPerMonth.getFlowRate());
                monthlyConsumptionList.add(dateFlow);
            });
            Statistics statistics = new Statistics();
            statistics.setMonthlyConsumptionList(monthlyConsumptionList);
            return statistics;
        } catch (Exception e) {
            if (e instanceof EmptyResultDataAccessException) {
                throw new ApplicationException("response.error.entity.notfound",
                        HttpStatus.NOT_FOUND.value());
            }
            throw new ApplicationException("internal.error.server",
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public Statistics loadConsumptionPerYear(User user, Integer year) {
        try {
            Integer accountId = user.getAccount().getId();

            List<LitersPerMonth> litersPerMonthList = statisticsDao.loadConsumptionPerMonthInYear(year, accountId);
            Double totalConsumption = 0.0;
            for (LitersPerMonth litersPerMonth : litersPerMonthList) {
                totalConsumption += litersPerMonth.getFlowRate();
            }
            Statistics statistics = new Statistics();
            statistics.setAverageConsumptionPerMonth(
                    NumberUtils.roundWithTwoDecimals(totalConsumption / litersPerMonthList.size()));
            statistics.setTotalConsumptionPerYear(
                    NumberUtils.roundWithTwoDecimals(totalConsumption));
            return statistics;
        } catch (Exception e) {
            if (e instanceof EmptyResultDataAccessException) {
                throw new ApplicationException("response.error.entity.notfound",
                        HttpStatus.NOT_FOUND.value());
            }
            throw new ApplicationException("internal.error.server",
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    private List<DateFlow> setupDailyConsumptionList(List<LitersPerDay> litersPerDayInYearList) {
        List<DateFlow> dailyConsumptionList = new ArrayList<>();

        Calendar calStartCollection = initializeCalendar(litersPerDayInYearList.get(0).getDateCollection());
        Calendar calEndDateCollection = initializeCalendar(litersPerDayInYearList.get(litersPerDayInYearList.size() - 1)
                .getDateCollection());

        Calendar calBeginingYear = initializeCalendar(Calendar.JANUARY, true);
        Calendar calEndingYear = initializeCalendar(Calendar.DECEMBER, false);

        int resultStart = calStartCollection.compareTo(calBeginingYear);
        if (resultStart > 0) {
            while (calBeginingYear.compareTo(calStartCollection) < 0) {
                dailyConsumptionList.add(constructDailyConsumptionObject(
                        calBeginingYear.getTime(),
                        0.0));
                calBeginingYear.add(Calendar.DAY_OF_MONTH, 1);
            }
        }

        for (LitersPerDay litersPerDay : litersPerDayInYearList) {
            Date dateCollection = new Date(litersPerDay.getDateCollection().getTime());
            dailyConsumptionList.add(constructDailyConsumptionObject(dateCollection,
                    litersPerDay.getFlowRate()));
        }

        int resultEnd = calEndDateCollection.compareTo(calEndingYear);
        if (resultEnd < 0) {
            while (calEndingYear.compareTo(calEndDateCollection) >= 0) {
                dailyConsumptionList.add(constructDailyConsumptionObject(calEndDateCollection.getTime(), 0.0));
                calEndDateCollection.add(Calendar.DAY_OF_MONTH, 1);
            }
        }
        return dailyConsumptionList;
    }

    private Calendar initializeCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return setDefaultValues(calendar);
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

    private Calendar setDefaultValues(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    private DateFlow constructDailyConsumptionObject(Date date, Double value) {
        DateFlow dateFlow = new DateFlow();
        dateFlow.setDate(date);
        dateFlow.setFlowRate(value);
        return dateFlow;
    }
}
