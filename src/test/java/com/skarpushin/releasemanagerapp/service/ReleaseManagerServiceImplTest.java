package com.skarpushin.releasemanagerapp.service;

import com.skarpushin.releasemanagerapp.entities.ServiceData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
class ReleaseManagerServiceImplTest {

    @Autowired
    ReleaseManagerService releaseManagerService;

    @Test
    @Transactional
    void testDeployService_shouldIncrementSystemVersion() {
        int currentSystemVersion = releaseManagerService.deployService("Service A", 1);
        int nextSystemVersion = releaseManagerService.deployService("Service B", 1);

        assertThat(nextSystemVersion).isEqualTo(currentSystemVersion + 1);
    }

    @Test
    @Transactional
    void testGetServices_shouldReturnServiceData() {
        releaseManagerService.deployService("Service 1", 1);
        releaseManagerService.deployService("Service 2", 1);
        releaseManagerService.deployService("Service 1", 2);

        List<ServiceData> serviceData = releaseManagerService.getServices(5);

        assertThat(serviceData).isNotEmpty();
        assertThat(serviceData.get(0).getName()).isEqualTo("Service 1");
        assertThat(serviceData.get(0).getVersion()).isEqualTo(2);
        assertThat(serviceData.get(1).getName()).isEqualTo("Service 2");
        assertThat(serviceData.get(1).getVersion()).isEqualTo(1);
    }
}