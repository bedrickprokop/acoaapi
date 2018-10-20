package br.com.acoaapi.model.service.impl;

import br.com.acoaapi.exception.ApplicationException;
import br.com.acoaapi.model.dao.LitersPerHourDao;
import br.com.acoaapi.model.entity.LitersPerHour;
import br.com.acoaapi.model.entity.User;
import br.com.acoaapi.model.service.LitersPerHourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LitersPerHourServiceImpl implements LitersPerHourService {

    @Autowired
    private LitersPerHourDao litersPerHourDao;

    @Override
    public LitersPerHour create(LitersPerHour litersPerHour, User user) {
        try {
            litersPerHour.setAccount(user.getAccount());
            return litersPerHourDao.create(litersPerHour);
        } catch (Exception e) {
            throw new ApplicationException("internal.error.server",
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
