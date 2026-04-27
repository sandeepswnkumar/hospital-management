CREATE TABLE hospitals (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL ,
    registration_number VARCHAR(255) NOT NULL ,
    email VARCHAR(150),
    phone VARCHAR(50),
    website VARCHAR(255),
    address1 VARCHAR(255),
    address2 VARCHAR(255),
    city_id BIGINT REFERENCES cities(id),
    state_id BIGINT REFERENCES states(id),
    country_id INTEGER REFERENCES countries(id),
    pincode VARCHAR(10),
    active BOOLEAN DEFAULT TRUE
);


CREATE TABLE doctor_hospitals (
    hospital_id BIGINT REFERENCES hospitals(id) ON DELETE CASCADE,
    doctor_id BIGINT REFERENCES doctors(id) ON DELETE CASCADE,
    PRIMARY KEY (hospital_id, doctor_id),
    consultation_fee DECIMAL(10, 2),
    role VARCHAR(50),
    joining_date DATE,
    active BOOLEAN DEFAULT TRUE

)
