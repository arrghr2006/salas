package com.test.meetroomBooking.repository;

import com.test.meetroomBooking.model.persistence.MeetRoom;
import com.test.meetroomBooking.model.persistence.MeetRoomBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface MeetRoomBookingRepository extends JpaRepository<MeetRoomBooking, String> {
    @Query("Select res from MeetRoomBooking res inner join res.meetRoom sala where sala.id=:meetRoomId")
    List<MeetRoomBooking> findByMeetRoomId(Integer meetRoomId);

    @Query("Select res from MeetRoomBooking res inner join res.meetRoom sala where sala.nombre=:meetRoomName")
    List<MeetRoomBooking> findByMeetRoomId(String meetRoomName);

    @Query("Select res from MeetRoomBooking res where :fecha between res.fechaInicio and res.fechaFin")
    Optional<MeetRoom> findMeetRoomBookingByFecha(Date fecha);

}
