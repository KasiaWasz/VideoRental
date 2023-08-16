package com.videorental.entities.client;

import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Client implements com.videorental.entities.Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private LocalDate registrationDate;

    @Column
    private Long phoneNumber;

    @Column
    private Long email;

    @Column
    private Long movieId;

    public Client(String firstName, String lastName, LocalDate registrationDate, Long phoneNumber, Long email, Long movieId) {

        Assert.notNull(firstName, "firstName must not be null");
        Assert.notNull(lastName, "lastName must not be null");
        Assert.notNull(registrationDate, "registrationDate must not be null");
        Assert.notNull(phoneNumber, "phoneNumber must not be null");
        Assert.notNull(email, "email must not be null");
        Assert.notNull(movieId, "movieId must not be null");

        this.firstName = firstName;
        this.lastName = lastName;
        this.registrationDate = registrationDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.movieId = movieId;
    }

    public Client() {
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

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getEmail() {
        return email;
    }

    public void setEmail(Long email) {
        this.email = email;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(firstName, client.firstName) && Objects.equals(lastName, client.lastName) && Objects.equals(registrationDate, client.registrationDate) && Objects.equals(phoneNumber, client.phoneNumber) && Objects.equals(email, client.email) && Objects.equals(movieId, client.movieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, registrationDate, phoneNumber, email, movieId);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", registrationDate=" + registrationDate +
                ", phoneNumber=" + phoneNumber +
                ", email=" + email +
                ", movieId=" + movieId +
                '}';
    }
}
