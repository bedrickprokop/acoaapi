package br.com.acoaapi.endpoint;

import br.com.acoaapi.Constants;
import br.com.acoaapi.model.entity.LitersPerHour;
import br.com.acoaapi.model.entity.User;
import br.com.acoaapi.model.service.LitersPerHourService;
import br.com.acoaapi.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/liters")
public class LitersPerHourEndpoint {

    @Autowired
    private LitersPerHourService litersPerHourService;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<LitersPerHour> create(@RequestBody LitersPerHour litersPerHour) {
        User user = userService.findByToken(Constants.TOKEN);
        litersPerHour = litersPerHourService.create(litersPerHour, user);
        return new ResponseEntity<>(litersPerHour, HttpStatus.CREATED);
    }
}
