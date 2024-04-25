package com.kulsin.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Builder
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "PARKING",
        uniqueConstraints = @UniqueConstraint(columnNames = {"VEHICLE_REGISTRATION"}))
public class Parking {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    Long id;

    @Column(name = "VEHICLE_REGISTRATION")
    String vehicleRegistration;

    @Column(name = "VEHICLE_TYPE")
    String vehicleType;

    @Column(name = "BRAND")
    String brand;

    @Column(name = "COLOR")
    String color;

    @Column(name = "SLOT_NUMBER")
    String slotNumber;

    @Column(name = "ENTRY_TIME")
    Date entryTime;

    @Column(name = "EXIT_TIME")
    Date exitTime;

}
