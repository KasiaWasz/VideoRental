package com.videorental.repositories.shift;

import com.videorental.entities.shift.Shift;

public interface ShiftRepository {

    void saveOrUpdate(Shift shift);

    void deleteById(Long id);
}
