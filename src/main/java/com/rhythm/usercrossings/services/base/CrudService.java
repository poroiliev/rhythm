package com.rhythm.usercrossings.services.base;

import java.util.List;

public interface CrudService<E, ID> {
    E save(E entity);

    List<E> save(List<E> entity);

    E findById(ID id);

    void delete(E entity);
}
