package com.kevocodes.pnccontrollers.repositories;

import com.kevocodes.pnccontrollers.domain.entities.AppointmentXUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AppointmentXUserRepository extends JpaRepository<AppointmentXUser, UUID> {
    /*@Query(value = "SELECT * FROM AppointmentXUser AU WHERE AU.doctor = :doctorId")
    List<AppointmentXUser> findAllByDoctorId(@Param("doctorId") UUID id);*/
}
