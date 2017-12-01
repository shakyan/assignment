package com.nagesh.api_assignment.service;

import com.nagesh.api_assignment.model.MetaData;

public interface MetaDataService {

    public void saveMetaData(MetaData metaData);

    public MetaData findMetaDataByFile(String fileName);
}
