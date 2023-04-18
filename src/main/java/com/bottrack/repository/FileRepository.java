package com.bottrack.repository;

import com.bottrack.repositorymodel.FileDetail;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@EnableAutoConfiguration
public interface FileRepository extends JpaRepository<FileDetail, Integer> {
    @Query("select f from FileDetail f where f.userId = :userId and f.fileName like :fileName")
    public FileDetail filterByName(@Param("userId") long userId, @Param("fileName")  String fileName);
}
