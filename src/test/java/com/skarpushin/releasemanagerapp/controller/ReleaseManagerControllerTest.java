package com.skarpushin.releasemanagerapp.controller;

import com.skarpushin.releasemanagerapp.entities.ServiceData;
import com.skarpushin.releasemanagerapp.service.ReleaseManagerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReleaseManagerController.class)
class ReleaseManagerControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    ReleaseManagerService releaseManagerService;

    @Test
    void testGetServices() throws Exception {
        ServiceData serviceData1 = new ServiceData("Service 1", 2);
        ServiceData serviceData2 = new ServiceData("Service 2", 1);

        List<ServiceData> serviceDataList = Arrays.asList(serviceData1, serviceData2);

        given(releaseManagerService.getServices(3)).willReturn(serviceDataList);

        mvc.perform(get("/services")
                .contentType(MediaType.APPLICATION_JSON)
                .queryParam("systemVersion", "3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is(serviceData1.getName())))
                .andExpect(jsonPath("$[0].version", is(serviceData1.getVersion())))
                .andExpect(jsonPath("$[1].name", is(serviceData2.getName())))
                .andExpect(jsonPath("$[1].version", is(serviceData2.getVersion())));
    }
}