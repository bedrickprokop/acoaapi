package br.com.acoaapi.endpoint;

import br.com.acoaapi.model.entity.User;
import br.com.acoaapi.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserEndpoint {

    //TODO Refatorar tratamento de entidades e erros para retornar via protocolo http
    // usando rest seguindo os padrões definidos pelo protocolo http.

    //SERVER    - http://www.codingpedia.org/ama/error-handling-in-rest-api-with-jersey/
    //ANDROID   - https://futurestud.io/tutorials/retrofit-2-simple-error-handling

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<User> create(@RequestBody @Valid User user) {
        user = userService.create(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{userCode}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<User> findOne(@PathVariable("userCode") Long userCode) {
        User user = userService.findOne(userCode);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<User>> findAll() {
        List<User> all = userService.findAll();
        if (all.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public ResponseEntity<User> update(@RequestBody @Valid User user) {
        user = userService.update(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<User> delete(@PathVariable("userCode") Long userCode) {
        User deleted = userService.delete(userCode);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }
}
