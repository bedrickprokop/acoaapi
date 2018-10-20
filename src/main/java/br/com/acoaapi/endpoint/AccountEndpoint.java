package br.com.acoaapi.endpoint;

import br.com.acoaapi.model.entity.Account;
import br.com.acoaapi.model.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountEndpoint {

    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Account>> findAll() {
        List<Account> all = accountService.findAll();
        if(all.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(all, HttpStatus.OK);
    }
}
