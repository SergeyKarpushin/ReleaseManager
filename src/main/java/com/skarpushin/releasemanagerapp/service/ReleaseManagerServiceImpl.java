package com.skarpushin.releasemanagerapp.service;

import com.skarpushin.releasemanagerapp.entities.ServiceData;
import com.skarpushin.releasemanagerapp.entities.Service;
import com.skarpushin.releasemanagerapp.entities.SystemVersion;
import com.skarpushin.releasemanagerapp.repository.ServiceRepository;
import com.skarpushin.releasemanagerapp.repository.SystemVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ReleaseManagerServiceImpl implements ReleaseManagerService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private SystemVersionRepository systemVersionRepository;

    @Override
    public int deployService(String serviceName, int serviceVersionNumber) {
        Service service = serviceRepository.findByName(serviceName);
        if (service == null) {
            service = Service.builder()
                    .name(serviceName)
                    .build();
            serviceRepository.save(service);
        }
        SystemVersion systemVersionEntity = SystemVersion.builder()
                .service(service)
                .serviceVersion(serviceVersionNumber)
                .build();
        systemVersionRepository.save(systemVersionEntity);
        return systemVersionEntity.getVersionId();
    }

    @Override
    public List<ServiceData> getServices(int systemVersion) {
        Optional<SystemVersion> systemVersionEntity = systemVersionRepository.findById(systemVersion);
        if (!systemVersionEntity.isPresent()) {
            return Collections.emptyList(); // "System version " + systemVersion + " does not exist");
        }
        List<SystemVersion> systemVersions = systemVersionRepository.findServicesBySystemVersionId(systemVersion);
        List<ServiceData> serviceData = systemVersions.stream()
                .map(sv -> new ServiceData(sv.getService().getName(), sv.getServiceVersion()))
                .collect(Collectors.toList());

        return serviceData;
    }


}
