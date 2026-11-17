package com.test.meetroomBooking.model.persistence;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "meetroom_booking", schema="public")

public class MeetRoomBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private Integer id;

    @Column(unique = true, length = 100, nullable = false)
    private String responsable;

    @Column(name="FECHA_INICIO")
    private Date fechaInicio;

    @Column(name="FECHA_FIN")
    private Date fechaFin;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="MEETROOM_ID")
    private MeetRoom meetRoom;

    public MeetRoomBooking(MeetRoom salon, String incharge, Date dateInit, Date dateFin){
        super();
        this.setResponsable(incharge);
        this.setMeetRoom(salon);
        this.setFechaInicio(dateInit);
        this.setFechaFin(dateFin);
    }

}
