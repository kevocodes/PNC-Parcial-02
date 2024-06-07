package com.kevocodes.pnccontrollers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kevocodes.pnccontrollers.domain.entities.Record;

import java.util.UUID;

public interface RecordRepository extends JpaRepository<Record, UUID> {
}
