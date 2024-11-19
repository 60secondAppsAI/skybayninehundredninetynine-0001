package com.skybayninehundredninetynine.service;

import com.skybayninehundredninetynine.dao.GenericDAO;

public interface GenericService<T, ID> {

    abstract GenericDAO<T, ID> getDAO();

    T getById(Integer id) ;

}