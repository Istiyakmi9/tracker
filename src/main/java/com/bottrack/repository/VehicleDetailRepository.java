package com.bottrack.repository;


import com.bottrack.model.VehicleDetail;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@EnableAutoConfiguration
public interface VehicleDetailRepository extends JpaRepository<VehicleDetail, Long> {

    @Query(nativeQuery = true, value = "select v.* from vehicledetail v order by v.VehicleId desc limit 1")
    VehicleDetail getLastVehicle();

    VehicleDetail findByUserId(long userId);
}
