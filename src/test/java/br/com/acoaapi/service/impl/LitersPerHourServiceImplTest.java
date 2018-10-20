package br.com.acoaapi.service.impl;

import br.com.acoaapi.Constants;
import br.com.acoaapi.exception.ApplicationException;
import br.com.acoaapi.model.entity.Account;
import br.com.acoaapi.model.entity.LitersPerHour;
import br.com.acoaapi.model.entity.User;
import br.com.acoaapi.model.service.LitersPerHourService;
import br.com.acoaapi.model.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class LitersPerHourServiceImplTest extends BaseService {

    @Autowired
    private LitersPerHourService litersPerHourService;

    @Autowired
    private UserService userService;

    @Test
    public void testCreate() {
        User user = userService.findByToken(Constants.TOKEN);
        Account account = user.getAccount();

        LitersPerHour litersPerHour = new LitersPerHour();
        litersPerHour.setAccount(account);
        litersPerHour.setDateCollection(new Date());
        litersPerHour.setFlowRate(19.3);
        litersPerHour = litersPerHourService.create(litersPerHour, user);

        Assert.assertNotNull(litersPerHour.getId());
    }

    @Test(expected = ApplicationException.class)
    public void testCreateUserAndAccountNull() {
        LitersPerHour litersPerHour = new LitersPerHour();
        litersPerHour.setAccount(null);
        litersPerHour.setDateCollection(new Date());
        litersPerHour.setFlowRate(19.3);
        litersPerHourService.create(litersPerHour, null);
    }

    @Test(expected = ApplicationException.class)
    public void testCreateLitersPerHourNull(){
        User user = userService.findByToken(Constants.TOKEN);
        Account account = user.getAccount();

        litersPerHourService.create(null, user);
    }
}
