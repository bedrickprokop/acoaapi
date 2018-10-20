package br.com.acoaapi.service.impl;

import br.com.acoaapi.Constants;
import br.com.acoaapi.exception.ApplicationException;
import br.com.acoaapi.model.entity.Account;
import br.com.acoaapi.model.entity.User;
import br.com.acoaapi.model.service.AccountService;
import br.com.acoaapi.model.service.UserService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImplTest extends BaseService {

    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;

    @Before
    public void setup() {
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testCreate() {
        Account account = new Account();
        account.setPrice(23.3);
        account = accountService.create(account);

        Assert.assertNotNull(account.getId());

        User user = new User();
        user.setPassword("11112323");
        user.setUsername("username");
        user.setToken("asdfasdfasdfasdfa");
        user.setAccount(account);

        user = userService.create(user);

        Assert.assertNotNull(user.getId());
    }

    @Test(expected = ApplicationException.class)
    public void testCreateUserNull() {
        userService.create(null);
    }

    @Test
    public void testFindOne() {
        User one = userService.findOne(1l);
        Assert.assertNotNull(one);
    }

    @Test(expected = ApplicationException.class)
    public void testFindOneNoFound() {
        userService.findOne(10000l);
    }

    @Test
    public void testFindAll() {
        List<User> all = userService.findAll();
        Assert.assertTrue(!all.isEmpty());
    }

    @Test
    public void testUpdate() {
        String oldUsername = "username1";
        String newUsername = "username2";

        Account account = new Account();
        account.setPrice(23.3);
        account = accountService.create(account);

        Assert.assertNotNull(account.getId());

        User user = new User();
        user.setPassword("11112323");
        user.setUsername(oldUsername);
        user.setToken("asdfasdfasdfasdfa");
        user.setAccount(account);
        user = userService.create(user);

        Assert.assertNotNull(user.getId());

        user.setUsername(newUsername);
        user = userService.update(user);
        Assert.assertNotEquals(user.getUsername(), oldUsername);
    }

    @Test
    public void testDelete() {
        Account account = new Account();
        account.setPrice(23.3);
        account = accountService.create(account);

        Assert.assertNotNull(account.getId());

        User user = new User();
        user.setPassword("11112323");
        user.setUsername("username");
        user.setToken("asdfasdfasdfasdfa");
        user.setAccount(account);
        user = userService.create(user);

        Assert.assertNotNull(user.getId());

        userService.delete(user.getId());
        userService.findOne(user.getId());
    }

    @Test
    public void testFindByToken() {
        User byToken = userService.findByToken(Constants.TOKEN);
        Assert.assertNotNull(byToken);
    }

    @Test
    public void testLoadTotalUsersByAccount() {
        User byToken = userService.findByToken(Constants.TOKEN);
        Account account = byToken.getAccount();

        Integer integer = userService.loadTotalUsersByAccount(account);
        Assert.assertTrue(integer > 0);
    }
}
