package com.skarpushin.releasemanagerapp.controller;

import com.skarpushin.releasemanagerapp.entities.ServiceData;
import com.skarpushin.releasemanagerapp.service.ReleaseManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReleaseManagerController {

    @Autowired
    private ReleaseManagerService releaseManagerService;

    @PostMapping("/deploy")
    public int deploy(@RequestParam String serviceName, @RequestParam int serviceVersionNumber) {
        return releaseManagerService.deployService(serviceName, serviceVersionNumber);
    }

    @GetMapping("/services")
    @ResponseBody
    public List<ServiceData> getServices(@RequestParam int systemVersion) {
        return releaseManagerService.getServices(systemVersion);
    }
}
