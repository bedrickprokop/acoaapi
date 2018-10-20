package br.com.acoaapi.model.service;

import java.util.List;

public interface GenericService<T> {

    T create(T entity);

    T findOne(Long entityCode);

    List<T> findAll();

    T update(T entity);

    T delete(Long entityCode);
}
