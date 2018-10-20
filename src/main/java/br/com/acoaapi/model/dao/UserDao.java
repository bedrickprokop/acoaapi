package br.com.acoaapi.model.dao;

import br.com.acoaapi.model.entity.User;

public interface UserDao extends GenericDao<User> {

    User findByToken(String token);

    Integer loadTotalUsersByAccount(Integer accountId);
}
