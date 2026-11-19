package com.test.meetroomBooking.integrationTest.repositories;

import com.test.meetroomBooking.model.persistence.MeetRoom;
import com.test.meetroomBooking.repository.MeetRoomRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(properties = {
    "spring.datasource.url=jdbc:h2:mem:meetroom_test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
    "spring.jpa.hibernate.ddl-auto=create-drop"
})
class MeetRoomRepositoryIntegrationTest {
    @Autowired
    private MeetRoomRepository meetRoomRepository;

    @Test
    @DisplayName("Debe crear un nuevo auto diesel")
    void should_save_a_room() {
        MeetRoom room = new MeetRoom("salon-11");
        MeetRoom roomDB = this.meetRoomRepository.save(room);

        assertNotNull(roomDB.getId());
        assertEquals(room.getNombre(), roomDB.getNombre());
    }

}