package br.com.acoaapi.model.service.impl;

import br.com.acoaapi.exception.ApplicationException;
import br.com.acoaapi.model.dao.UserDao;
import br.com.acoaapi.model.entity.Account;
import br.com.acoaapi.model.entity.User;
import br.com.acoaapi.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//TODO analisar anotações de transação e add para cada método exceto os de busca
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User create(User user) {
        try {
            return userDao.create(user);
        } catch (Exception e) {
            throw new ApplicationException("internal.error.server",
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public User findOne(Long userCode) {
        User user = userDao.findOne(userCode);
        if (null != user) {
            return user;
        }
        throw new ApplicationException("response.error.entity.notfound",
                HttpStatus.NOT_FOUND.value());
    }

    @Override
    public List<User> findAll() {
        List<User> all = userDao.findAll();
        if (null != all && all.size() > 0) {
            return all;
        }
        throw new ApplicationException("response.error.entity.notfound",
                HttpStatus.NOT_FOUND.value());
    }

    @Override
    public User update(User user) {
        if (null != user.getId()) {
            return userDao.update(user);
        }
        throw new ApplicationException("response.error.missing.attribute",
                HttpStatus.BAD_REQUEST.value());
    }

    @Override
    public User delete(Long userCode) {
        User user = userDao.findOne(userCode);
        if (null != user) {
            return userDao.delete(user);
        }
        throw new ApplicationException("response.error.entity.notfound",
                HttpStatus.NOT_FOUND.value());
    }

    @Override
    public User findByToken(String token) {
        User user = userDao.findByToken(token);
        if (null != user) {
            return user;
        }
        throw new ApplicationException("response.error.entity.notfound",
                HttpStatus.NOT_FOUND.value());
    }

    @Override
    public Integer loadTotalUsersByAccount(Account account) {
        return userDao.loadTotalUsersByAccount(account.getId());
    }
}
