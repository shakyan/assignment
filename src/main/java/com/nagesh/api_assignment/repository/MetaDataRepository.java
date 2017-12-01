package com.nagesh.api_assignment.repository;

import com.nagesh.api_assignment.model.MetaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetaDataRepository extends JpaRepository<MetaData, Integer> {

    public MetaData findByName(final String name);
}
