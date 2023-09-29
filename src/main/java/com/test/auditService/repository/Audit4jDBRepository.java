package com.test.auditService.repository;

import org.springframework.stereotype.Repository;

import com.test.auditService.model.Audit4jDB;

@Repository
public interface Audit4jDBRepository extends ReadOnlyRepository<Audit4jDB, String> {

}
