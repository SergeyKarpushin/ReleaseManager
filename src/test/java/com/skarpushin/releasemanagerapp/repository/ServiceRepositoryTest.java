package com.skarpushin.releasemanagerapp.repository;

import com.skarpushin.releasemanagerapp.entities.Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
class ServiceRepositoryTest {

    @Autowired
    ServiceRepository serviceRepository;

    @Test
    void testFindByName_shouldReturnService() {
        Service service = Service.builder().name("Service 1").build();
        Service savedService = serviceRepository.save(service);

        assertThat(savedService.getServiceId()).isNotNull();
        assertThat(savedService.getName()).isEqualTo("Service 1");
    }
}