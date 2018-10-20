package br.com.acoaapi.model.dao;

import java.util.List;

public interface GenericDao<T> {

    T create(T entity);

    T findOne(Long entityId);

    List<T> findAll();

    T update(T entity);

    T delete(T entity);
}
