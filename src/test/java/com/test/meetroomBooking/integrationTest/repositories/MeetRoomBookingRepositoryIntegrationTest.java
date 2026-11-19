package com.test.meetroomBooking.integrationTest.repositories;

import com.test.meetroomBooking.model.persistence.MeetRoomBooking;
import com.test.meetroomBooking.repository.MeetRoomBookingRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(properties = {
    "spring.datasource.url=jdbc:h2:mem:booking_test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
    "spring.jpa.hibernate.ddl-auto=create-drop"
})
class MeetRoomBookingRepositoryIntegrationTest {
    @Autowired
    private MeetRoomBookingRepository meetRoomBookingRepository;

    @Test
    @DisplayName("Encontrando reserva por fecha")
    void should_find_a_book_by_date() {


    }

}