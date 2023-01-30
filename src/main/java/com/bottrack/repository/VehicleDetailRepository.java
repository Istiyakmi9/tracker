package com.bottrack.repository;


import com.bottrack.model.Roles;
import com.bottrack.model.VehicleDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.query.Query;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.ParameterMode;
import java.util.ArrayList;
import java.util.List;

@Repository
@EnableAutoConfiguration
public interface VehicleDetailRepository extends JpaRepository<VehicleDetail, Long> {


}
