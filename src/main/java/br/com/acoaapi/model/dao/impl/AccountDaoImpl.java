package br.com.acoaapi.model.dao.impl;

import br.com.acoaapi.model.dao.AccountDao;
import br.com.acoaapi.model.entity.Account;
import br.com.acoaapi.model.entity.LitersPerDay;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

@Repository
public class AccountDaoImpl extends BaseDao<Account> implements AccountDao {

    @Override
    public List<Account> findAll() {
        return super.findAll();
    }
}
