package com.test.meetroomBooking.service;


import java.util.List;

public interface IComunService<T, Y> {
    List<T> getAll();

    T getOne(Long id);

    String delete(Long id);

    String create(Y create);

    String update(Y update);

    String update(Long id,Y update);
}
