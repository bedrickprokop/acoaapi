package br.com.acoaapi.endpoint;

import br.com.acoaapi.Constants;
import br.com.acoaapi.model.entity.Statistics;
import br.com.acoaapi.model.entity.User;
import br.com.acoaapi.model.service.StatisticsService;
import br.com.acoaapi.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsEndpoint {

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Statistics> loadConsumptionStatistics() {
        User user = userService.findByToken(Constants.TOKEN);
        Statistics statistics = statisticsService.loadConsumptionStatistics(user);
        return new ResponseEntity<>(statistics, HttpStatus.OK);
    }

    @RequestMapping(value = "/day/{date}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Statistics> loadConsumptionPerDay(@PathVariable("date") String date) {
        User user = userService.findByToken(Constants.TOKEN);
        Date parsedDate;
        try {
            parsedDate = Constants.FORMAT.parse(date);
            Statistics statistics = statisticsService.loadConsumptionPerDay(user, parsedDate);
            return new ResponseEntity<>(statistics, HttpStatus.OK);
        } catch (ParseException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/month/{date}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Statistics> loadConsumptionPerMonth(@PathVariable("date") String date) {
        User user = userService.findByToken(Constants.TOKEN);
        Date parsedDate;
        try {
            parsedDate = Constants.FORMAT.parse(date);
            Statistics statistics = statisticsService.loadConsumptionPerMonth(user, parsedDate);
            return new ResponseEntity<>(statistics, HttpStatus.OK);
        } catch (ParseException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/month/year/{year}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Statistics> loadConsumptionPerMonthInYear(@PathVariable("year") Integer year) {
        User user = userService.findByToken(Constants.TOKEN);
        Statistics statistics = statisticsService.loadConsumptionPerMonthInYear(user, year);
        return new ResponseEntity<>(statistics, HttpStatus.OK);
    }

    @RequestMapping(value = "/year/{year}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Statistics> loadConsumptionPerYear(@PathVariable("year") Integer year) {
        User user = userService.findByToken(Constants.TOKEN);
        Statistics statistics = statisticsService.loadConsumptionPerYear(user, year);
        return new ResponseEntity<>(statistics, HttpStatus.OK);
    }
}
