package br.com.acoaapi.model.service;

import br.com.acoaapi.model.entity.LitersPerHour;
import br.com.acoaapi.model.entity.User;

public interface LitersPerHourService {

    LitersPerHour create(LitersPerHour litersPerHour, User user);
}
