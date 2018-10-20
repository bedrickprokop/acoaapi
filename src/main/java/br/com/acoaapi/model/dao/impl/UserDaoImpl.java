package br.com.acoaapi.model.dao.impl;

import br.com.acoaapi.model.dao.UserDao;
import br.com.acoaapi.model.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.math.BigInteger;

@Repository
public class UserDaoImpl extends BaseDao<User> implements UserDao {

    @Override
    public User findByToken(String token) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.token = :token");
        query.setParameter("token", token);
        return (User) query.getSingleResult();
    }

    @Override
    public Integer loadTotalUsersByAccount(Integer accountId) {
        String strQuery = "SELECT COUNT(account_id) AS total FROM user WHERE account_id = :accountId";
        Query query = entityManager.createNativeQuery(strQuery);
        query.setParameter("accountId", accountId);
        BigInteger bigInteger = (BigInteger) query.getSingleResult();
        return bigInteger.intValue();
    }
}
