package com.skarpushin.releasemanagerapp.repository;

import com.skarpushin.releasemanagerapp.entities.SystemVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface SystemVersionRepository extends JpaRepository<SystemVersion, Integer> {

    @Query(value = "select current value for system_version_seq", nativeQuery = true)
    BigInteger getCurrentSystemVersion();

    @Query(value = "select * from system_version s " +
            "join (select service_id, max(service_version) ver from system_version where version_id <= ?1 group by service_id) s1 " +
            "on s.service_id = s1.service_id and s.service_version = s1.ver",
            nativeQuery = true)
    List<SystemVersion> findServicesBySystemVersionId(int versionId);
}
