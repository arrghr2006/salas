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
public interface MeetRoomBookingRepository extends JpaRepository<MeetRoomBooking, Integer> {
    @Query("Select res from MeetRoomBooking res inner join res.meetRoom sala where sala.id=:meetRoomId")
    List<MeetRoomBooking> findByMeetRoomId(Integer meetRoomId);

    @Query("Select res from MeetRoomBooking res inner join res.meetRoom sala where sala.nombre=:meetRoomName")
    List<MeetRoomBooking> findByMeetRoomId(String meetRoomName);

    @Query("Select res from MeetRoomBooking res where :fecha between res.fechaInicio and res.fechaFin")
    Optional<MeetRoomBooking> findMeetRoomBookingByFecha(Date fecha);

    @Query("Select res from MeetRoomBooking res where (:start between res.fechaInicio and res.fechaFin) or (:end between res.fechaInicio and res.fechaFin)")
    List<MeetRoomBooking> findMeetRoomBookingByPeriod(Date start, Date end);

    List<MeetRoomBooking> findAll();

    Optional<MeetRoomBooking> findById(Integer id);
}