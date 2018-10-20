package br.com.acoaapi.model.dao;

import br.com.acoaapi.model.entity.LitersPerMonth;

import java.util.List;

public interface HistoryDao {

    List<LitersPerMonth> loadConsumptionPerYears(Integer years[], Integer accountId);
}
