package com.kevocodes.pnccontrollers.services.implementations;

import com.kevocodes.pnccontrollers.domain.dtos.RecordDTO;
import com.kevocodes.pnccontrollers.domain.entities.Record;
import com.kevocodes.pnccontrollers.domain.entities.User;
import com.kevocodes.pnccontrollers.repositories.RecordRepository;
import com.kevocodes.pnccontrollers.services.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordServiceImplementation implements RecordService {
    
    private final RecordRepository repository;
    
    @Override
    public List<Record> getAll() {
        return repository.findAll();
    }

    @Override
    public void create(RecordDTO record, User user) {

        Record newRecord = new Record();

        newRecord.setIdRecord(record.getIdRecord());
        newRecord.setPatient(user);
        newRecord.setComments(record.getComments());

        repository.save(newRecord);
    }

    @Override
    public void update(Record record, String info) {

        record.setComments(info);

        repository.save(record);

    }

    @Override
    public void delete(Record record) {
        repository.delete(record);
    }
}
