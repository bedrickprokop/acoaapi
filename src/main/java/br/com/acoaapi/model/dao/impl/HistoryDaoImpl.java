package br.com.acoaapi.model.dao.impl;

import br.com.acoaapi.model.dao.HistoryDao;
import br.com.acoaapi.model.entity.LitersPerMonth;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class HistoryDaoImpl implements HistoryDao {

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public List<LitersPerMonth> loadConsumptionPerYears(Integer years[], Integer accountId) {
        String strQuery = "SELECT lpm FROM LitersPerMonth lpm WHERE " +
                "YEAR(lpm.dateCollection) IN (:year1, :year2, :year3, :year4) " +
                "AND lpm.account.id = :accountId";
        Query query = entityManager.createQuery(strQuery, LitersPerMonth.class);
        query.setParameter("accountId", accountId);
        for (int i = 0; i < 4; i++) {
            query.setParameter("year" + (i + 1), years[0]);
        }
        return query.getResultList();
    }
}
