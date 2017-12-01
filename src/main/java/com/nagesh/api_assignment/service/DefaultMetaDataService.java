package com.nagesh.api_assignment.service;

import com.nagesh.api_assignment.model.MetaData;
import com.nagesh.api_assignment.repository.MetaDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultMetaDataService implements MetaDataService {

    private final MetaDataRepository metaDataRepository;

    @Autowired
    public DefaultMetaDataService(final MetaDataRepository metaDataRepository) {
        this.metaDataRepository = metaDataRepository;
    }

    @Override
    public void saveMetaData(final MetaData metaData) {
        metaDataRepository.save(metaData);
    }

    @Override
    public MetaData findMetaDataByFile(final String fileName) {
        return metaDataRepository.findByName(fileName);
    }
}
