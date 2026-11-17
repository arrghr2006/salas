package com.test.meetroomBooking.model.persistence;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(columnNames = "username") })
public class UserApp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private Long id;

    @NotBlank
    private String username;

    @JsonIgnore
    @NotBlank
    @Size(max = 120)
    private String password;

    public UserApp() {
    }

    public UserApp(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
