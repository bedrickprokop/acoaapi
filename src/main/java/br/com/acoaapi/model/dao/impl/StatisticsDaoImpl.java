package br.com.acoaapi.model.dao.impl;

import br.com.acoaapi.model.dao.StatisticsDao;
import br.com.acoaapi.model.entity.LitersPerDay;
import br.com.acoaapi.model.entity.LitersPerMonth;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Repository
public class StatisticsDaoImpl implements StatisticsDao {

    @PersistenceContext
    protected EntityManager entityManager;

    public LitersPerDay loadConsumptionPerDay(Date date, Integer accountId) {
        String strQuery = "SELECT lpd FROM LitersPerDay lpd WHERE lpd.dateCollection = :date " +
                "AND lpd.account.id = :accountId";
        Query query = entityManager.createQuery(strQuery, LitersPerDay.class);
        query.setParameter("accountId", accountId);
        query.setParameter("date", date);
        return (LitersPerDay) query.getSingleResult();
    }

    @Override
    public LitersPerMonth loadConsumptionPerMonth(Integer month, Integer accountId) {
        String strQuery = "SELECT lpm FROM LitersPerMonth lpm WHERE " +
                "MONTH(lpm.dateCollection) = :month AND lpm.account.id = :accountId";
        Query query = entityManager.createQuery(strQuery, LitersPerMonth.class);
        query.setParameter("accountId", accountId);
        query.setParameter("month", month);
        return (LitersPerMonth) query.getSingleResult();
    }

    public List<LitersPerDay> loadConsumptionPerDayInYear(Integer year, Integer accountId) {
        String strQuery = "SELECT lpd FROM LitersPerDay lpd WHERE YEAR(lpd.dateCollection) = :year " +
                "AND lpd.account.id = :accountId";
        Query query = entityManager.createQuery(strQuery, LitersPerDay.class);
        query.setParameter("accountId", accountId);
        query.setParameter("year", year);
        return query.getResultList();
    }

    @Override
    public List<LitersPerMonth> loadConsumptionPerMonthInYear(Integer year, Integer accountId) {
        String strQuery = "SELECT lpm FROM LitersPerMonth lpm WHERE " +
                "YEAR(lpm.dateCollection) = :year AND lpm.account.id = :accountId";
        Query query = entityManager.createQuery(strQuery, LitersPerMonth.class);
        query.setParameter("accountId", accountId);
        query.setParameter("year", year);
        return query.getResultList();
    }
}
