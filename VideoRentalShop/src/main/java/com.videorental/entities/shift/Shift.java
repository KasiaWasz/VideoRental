package com.videorental.entities.shift;

import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Shift implements com.videorental.entities.Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private Long employeeId;
    @Column
    private LocalDate date;
    @Column
    private Long hours;


    public Shift(Long employeeId, LocalDate date, Long hours) {

        Assert.notNull(employeeId, "employeeId must not be null");
        Assert.notNull(date, "date must not be null");
        Assert.notNull(hours, "hours must not be null");

        this.employeeId = employeeId;
        this.date = date;
        this.hours = hours;
    }


    public Shift() {
    }


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getHours() {
        return hours;
    }

    public void setHours(Long hours) {
        this.hours = hours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shift shift = (Shift) o;
        return Objects.equals(id, shift.id) && Objects.equals(employeeId, shift.employeeId) && Objects.equals(date, shift.date) && Objects.equals(hours, shift.hours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeId, date, hours);
    }

    @Override
    public String toString() {
        return "Shift{" +
                "id=" + id +
                ", employeeId=" + employeeId +
                ", date=" + date +
                ", hours=" + hours +
                '}';
    }
}
