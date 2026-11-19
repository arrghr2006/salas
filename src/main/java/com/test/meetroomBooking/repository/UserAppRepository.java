package com.test.meetroomBooking.repository;

import com.test.meetroomBooking.model.persistence.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAppRepository extends JpaRepository<UserApp, Integer> {
    Optional<UserApp> findUserAppByUsername(String username);

    Boolean existsUserAppByUsername(String username);

}
