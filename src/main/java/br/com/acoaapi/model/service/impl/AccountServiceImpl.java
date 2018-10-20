package br.com.acoaapi.model.service.impl;

import br.com.acoaapi.exception.ApplicationException;
import br.com.acoaapi.model.dao.AccountDao;
import br.com.acoaapi.model.entity.Account;
import br.com.acoaapi.model.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public Account create(Account entity) {
        return accountDao.create(entity);
    }

    @Override
    public Account findOne(Long entityCode) {
        return null;
    }

    @Override
    public List<Account> findAll() {
        List<Account> all = accountDao.findAll();
        if (null != all && all.size() > 0) {
            return all;
        }
        throw new ApplicationException("response.error.entity.notfound",
                HttpStatus.NOT_FOUND.value());
    }

    @Override
    public Account update(Account entity) {
        return null;
    }

    @Override
    public Account delete(Long entityCode) {
        return null;
    }
}
