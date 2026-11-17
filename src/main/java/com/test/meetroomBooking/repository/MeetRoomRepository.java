package com.test.meetroomBooking.repository;

import com.test.meetroomBooking.model.persistence.MeetRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MeetRoomRepository extends JpaRepository<MeetRoom, Integer> {

    @Query("Select v from MeetRoom v where v.nombre=:name")
    Optional<MeetRoom> findMeetRoomByName(String name);
}
