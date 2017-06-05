package com.map.mapmaxv1.dto;

public class UserDTO {

    private String username; // Логин пользователя

    private String password; // Пароль пользователя

    private String FIO; // Его ФИО, так ка его видят другие

    private String role; // Его тип работы, кто он по профилю

    public UserDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}


