package com.hospital.hospital_managment.hospital.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "hospitals")
@Getter
@Setter
public class Hospital {
//    id BIGSERIAL PRIMARY KEY,
//    name VARCHAR(255),
//    registration_number VARCHAR(255),
//    email VARCHAR(150),
//    phone VARCHAR(50),
//    website VARCHAR(255),
//    address1 VARCHAR(255),
//    address2 VARCHAR(255),
//    city_id BIGINT REFERENCES cities(id),
//    state_id BIGINT REFERENCES states(id),
//    country_id INTEGER REFERENCES countries(id),
//    pincode VARCHAR(10),
//    active BOOLEAN DEFAULT TRUE

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "registration_number", unique = true, nullable = false)
    private String registrationNumber;
    private String email;
    private String phone;
    private String website;
    private String address1;
    private String address2;

    @OneToOne
    @JoinColumn(name = "city_id")
    private String city;

    @OneToOne
    @JoinColumn(name = "state_id")
    private String state;

    @OneToOne
    @JoinColumn(name = "country_id")
    private String country;
    private String pincode;
    private Boolean active = true;


}
