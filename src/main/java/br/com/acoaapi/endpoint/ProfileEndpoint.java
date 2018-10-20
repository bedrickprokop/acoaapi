package br.com.acoaapi.endpoint;

import br.com.acoaapi.config.security.model.UserContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/me")
public class ProfileEndpoint {

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<UserContext> get(/*@RequestBody JwtAuthenticationToken token*/) {

        //TODO implement
        //return (UserContext) token.getPrincipal();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
