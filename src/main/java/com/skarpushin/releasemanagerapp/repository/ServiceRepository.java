package com.skarpushin.releasemanagerapp.repository;

import com.skarpushin.releasemanagerapp.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

    Service findByName(String name);
}
