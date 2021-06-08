package com.dolbunmark.dto;

import com.dolbunmark.enums.Role;

import java.io.Serializable;

public class UserDto implements Serializable {

    private Long id;
    private String first_name;
    private String last_name;
    private Role role;
    private String email;
    private String password;

    public UserDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
//    private int id;
//    private String firstname;
//    private String lastname;
//
//    public UserDto(int id, String firstname, String lastname) {
//        this.id = id;
//        this.firstname = firstname;
//        this.lastname = lastname;
//    }
//
//    public UserDto(UserDto newTicketDto) {
//        this.id = newTicketDto.id;
//        this.firstname = newTicketDto.firstname;
//        this.lastname = newTicketDto.lastname;
//        System.out.println(newTicketDto.firstname);
//    }
//
//
//
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getFirstname() {
//        return firstname;
//    }
//
//    public void setFirstname(String firstname) {
//        this.firstname = firstname;
//    }
//
//    public String getLastname() {
//        return lastname;
//    }
//
//    public void setLastname(String lastname) {
//        this.lastname = lastname;
//    }