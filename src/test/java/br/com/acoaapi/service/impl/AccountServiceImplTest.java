package br.com.acoaapi.service.impl;

import br.com.acoaapi.model.entity.Account;
import br.com.acoaapi.model.service.AccountService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AccountServiceImplTest extends BaseService{

    @Autowired
    private AccountService accountService;

    @Test
    public void testFindAll(){
        List<Account> all = accountService.findAll();
        Assert.assertNotNull(all);
        Assert.assertTrue(!all.isEmpty());
    }
}
