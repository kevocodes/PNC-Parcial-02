package com.kevocodes.pnccontrollers.services.implementations;

import com.kevocodes.pnccontrollers.domain.entities.Record;
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
    public void create(Record record) {
        repository.save(record);
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
