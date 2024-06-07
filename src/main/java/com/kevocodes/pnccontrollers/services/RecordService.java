package com.kevocodes.pnccontrollers.services;

import com.kevocodes.pnccontrollers.domain.dtos.RecordDTO;
import com.kevocodes.pnccontrollers.domain.entities.Record;
import com.kevocodes.pnccontrollers.domain.entities.User;

import java.util.List;

public interface RecordService {
    List<Record> getAll();
    void create(RecordDTO record, User user);
    void update(Record record, String info);
    void delete(Record record);

}
