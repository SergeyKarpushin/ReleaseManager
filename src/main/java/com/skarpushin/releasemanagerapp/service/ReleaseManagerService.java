package com.skarpushin.releasemanagerapp.service;

import com.skarpushin.releasemanagerapp.entities.ServiceData;

import java.util.List;

public interface ReleaseManagerService {
    int deployService(String serviceName, int serviceVersionNumber);

    List<ServiceData> getServices(int systemVersion);
}
