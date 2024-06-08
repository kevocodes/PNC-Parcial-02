package com.kevocodes.pnccontrollers.services;

import com.kevocodes.pnccontrollers.domain.entities.Record;

import java.util.List;

public interface RecordService {
    List<Record> getAll();
    void create(Record info) throws Exception;
    void update(Record record, String info) throws Exception;
    void delete(Record record) throws Exception;

}
