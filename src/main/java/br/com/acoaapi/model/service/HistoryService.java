package br.com.acoaapi.model.service;

import br.com.acoaapi.model.entity.History;
import br.com.acoaapi.model.entity.User;

public interface HistoryService {

    History loadConsumptionHistory(User user);
}
