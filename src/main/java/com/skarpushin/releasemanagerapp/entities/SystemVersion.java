package com.skarpushin.releasemanagerapp.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SystemVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "system_version_generator")
    @SequenceGenerator(name="system_version_generator", sequenceName = "system_version_seq", allocationSize=1)
    private int versionId;

    @Basic(optional = false)
    private int serviceVersion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "service_id",
            referencedColumnName = "serviceId"
    )
    private Service service;
}
