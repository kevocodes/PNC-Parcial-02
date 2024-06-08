package com.kevocodes.pnccontrollers.repositories;
import java.util.UUID;
import java.util.List;

import com.kevocodes.pnccontrollers.domain.entities.AppointmentXUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AppointmentXUserRepository extends JpaRepository<AppointmentXUser, UUID>{

    @Query("SELECT axu FROM AppointmentXUser axu WHERE axu.user.id = :userId")
    List<AppointmentXUser> findAllByDoctorId(@Param("userId")UUID doctorId);
}
