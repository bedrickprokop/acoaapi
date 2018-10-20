package br.com.acoaapi.model.service;

import br.com.acoaapi.model.entity.Account;
import br.com.acoaapi.model.entity.User;

public interface UserService extends GenericService<User> {

    User findByToken(String token);

    Integer loadTotalUsersByAccount(Account account);
}
