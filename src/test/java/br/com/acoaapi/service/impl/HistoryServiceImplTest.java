package br.com.acoaapi.service.impl;

import br.com.acoaapi.Constants;
import br.com.acoaapi.model.entity.History;
import br.com.acoaapi.model.entity.User;
import br.com.acoaapi.model.service.HistoryService;
import br.com.acoaapi.model.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class HistoryServiceImplTest extends BaseService{

    @Autowired
    private HistoryService historyService;

    @Autowired
    private UserService userService;


    @Test
    public void testLoadConsumptionHistory() {
        User user = userService.findByToken(Constants.TOKEN);
        History history = historyService.loadConsumptionHistory(user);

        Assert.assertNotNull(history);
        Assert.assertNotNull(history.getTotalMonthConsumption());
        Assert.assertNotNull(history.getTotalYearsConsumption());
        Assert.assertEquals(12, history.getTotalMonthConsumption().size());
        Assert.assertEquals(4, history.getTotalYearsConsumption().size());
    }
}
