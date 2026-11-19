package com.test.meetroomBooking.model.persistence;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "user_app", schema = "public" )
public class UserApp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private Integer id;

    @Column(unique = true, length = 100, nullable = false)
    private String username;

    @JsonIgnore
    @Column( nullable = false)
    private String password;

    public UserApp(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
