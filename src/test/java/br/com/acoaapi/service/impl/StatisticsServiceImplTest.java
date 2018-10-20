package br.com.acoaapi.service.impl;

import br.com.acoaapi.Constants;
import br.com.acoaapi.model.entity.Statistics;
import br.com.acoaapi.model.entity.User;
import br.com.acoaapi.model.service.StatisticsService;
import br.com.acoaapi.model.service.UserService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.Date;

public class StatisticsServiceImplTest extends BaseService {

    @Autowired
    private StatisticsService statisticsService;
    @Autowired
    private UserService userService;

    private User user;

    @Before
    public void setup() {
        user = userService.findOne(1l);
    }

    @After
    public void tearDown() {
        user = null;
    }

    @Test
    public void testLoadConsumptionStatistics() {
        Statistics statistics = statisticsService.loadConsumptionStatistics(user);
        Assert.assertNotNull(statistics);
        Assert.assertTrue(!statistics.getDailyConsumptionList().isEmpty());
    }

    @Test
    public void testLoadConsumptionPerDay() {
        Date parsedDate;
        try {
            parsedDate = Constants.FORMAT.parse("2018-09-29");
        } catch (ParseException e) {
            parsedDate = null;
        }

        Statistics statistics = statisticsService.loadConsumptionPerDay(user, parsedDate);
        Assert.assertNotNull(statistics);
    }

    @Test
    public void testLoadConsumptionPerMonth() {
        Date parsedDate;
        try {
            parsedDate = Constants.FORMAT.parse("2018-09-30");
        } catch (ParseException e) {
            parsedDate = null;
        }
        Statistics statistics = statisticsService.loadConsumptionPerMonth(user, parsedDate);
        Assert.assertNotNull(statistics);
    }

    @Test
    public void testLoadConsumptionPerMonthInYear() {
        Statistics statistics = statisticsService.loadConsumptionPerMonthInYear(user, 2018);
        Assert.assertNotNull(statistics);
        Assert.assertTrue(!statistics.getMonthlyConsumptionList().isEmpty());
    }

    @Test
    public void testLoadConsumptionPerYear() {
        Statistics statistics = statisticsService.loadConsumptionPerYear(user, 2018);
        Assert.assertNotNull(statistics);
    }
}
