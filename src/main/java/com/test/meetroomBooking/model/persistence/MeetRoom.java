package com.test.meetroomBooking.model.persistence;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "meetroom", schema="public")

public class MeetRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private Integer id;

    @Column(unique = true, length = 100, nullable = false)
    private String nombre;

    public MeetRoom(String name){
        super();
        this.setNombre(name);
    }

}
