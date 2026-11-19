package com.test.meetroomBooking.repository;

import com.test.meetroomBooking.model.persistence.MeetRoomBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface MeetRoomBookingRepository extends JpaRepository<MeetRoomBooking, Integer> {
    @Query("Select res from MeetRoomBooking res inner join res.meetRoom sala where sala.id=:meetRoomId")
    List<MeetRoomBooking> findByMeetRoomBookingByMeetRoomId(Integer meetRoomId);

    //@Query("Select res from MeetRoomBooking res inner join res.meetRoom sala where sala.nombre=:meetRoomName")
    Page<MeetRoomBooking> findMeetRoomBookingByNombreSala(String meetRoomName, Pageable pageable);

    @Query("Select res from MeetRoomBooking res where lower(nombreSala)=lower(:roomName) and :fecha between res.fechaInicio and res.fechaFin")
    Page<MeetRoomBooking> findMeetRoomBookingByFechaNombreSala(Date fecha, String roomName, Pageable pageable);

    @Query("Select res from MeetRoomBooking res where :fecha between res.fechaInicio and res.fechaFin")
    Page<MeetRoomBooking> findMeetRoomBookingByFecha(Date fecha, Pageable pageable);


    @Query("Select res from MeetRoomBooking res where lower(nombreSala)=lower(:roomName) and ((:start between res.fechaInicio and res.fechaFin) or (:end between res.fechaInicio and res.fechaFin))")
    List<MeetRoomBooking> findMeetRoomBookingByPeriod(Date start, Date end, String roomName);

    @Override
    List<MeetRoomBooking> findAll();

    @Override
    Optional<MeetRoomBooking> findById(Integer id);
}