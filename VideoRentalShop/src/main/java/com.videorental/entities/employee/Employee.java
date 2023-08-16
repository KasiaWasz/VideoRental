package com.videorental.entities.employee;

import org.springframework.util.Assert;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Employee implements com.videorental.entities.Entity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private LocalDate joinDate;

    @Column
    private String phoneNumber;

    @Column
    private BigDecimal hourSalary;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private Role role;

    public Employee(
        String firstName,
        String lastName,
        LocalDate joinDate,
        String phoneNumber,
        BigDecimal hourSalary,
        Role role) {

        Assert.notNull(firstName, "firstName must not be null");
        Assert.notNull(lastName, "lastName must not be null");
        Assert.notNull(joinDate, "joinDate must not be null");
        Assert.notNull(phoneNumber, "phoneNumber must not be null");
        Assert.notNull(hourSalary, "hourSalary must not be null");
        Assert.notNull(role, "role must not be null");

        this.firstName = firstName;
        this.lastName = lastName;
        this.joinDate = joinDate;
        this.phoneNumber = phoneNumber;
        this.hourSalary = hourSalary;
        this.role = role;
    }

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public BigDecimal getHourSalary() {
        return hourSalary;
    }

    public void setHourSalary(BigDecimal hourSalary) {
        this.hourSalary = hourSalary;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName) && Objects.equals(joinDate, employee.joinDate) && Objects.equals(phoneNumber, employee.phoneNumber) && Objects.equals(hourSalary, employee.hourSalary) && role == employee.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, joinDate, phoneNumber, hourSalary, role);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", joinDate=" + joinDate +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", hourSalary=" + hourSalary +
                ", role=" + role +
                '}';
    }
}
