package br.com.acoaapi.endpoint;

import br.com.acoaapi.Constants;
import br.com.acoaapi.model.entity.History;
import br.com.acoaapi.model.entity.User;
import br.com.acoaapi.model.service.HistoryService;
import br.com.acoaapi.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/history")
public class HistoryEndpoint {

    @Autowired
    private HistoryService historyService;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<History> loadConsumptionHistory() {
        User user = userService.findByToken(Constants.TOKEN);

        History history = historyService.loadConsumptionHistory(user);
        return new ResponseEntity<>(history, HttpStatus.OK);
    }
}
