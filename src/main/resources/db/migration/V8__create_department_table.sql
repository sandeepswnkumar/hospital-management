
CREATE TABLE departments (
    id SERIAL PRIMARY KEY,
    name VARCHAR(150),
    head_doctor_id BIGINT
);


CREATE TABLE doctor_departments (
    doctor_id BIGINT REFERENCES doctors(id) ON DELETE CASCADE,
    department_id INTEGER REFERENCES departments(id) ON DELETE CASCADE,
    PRIMARY KEY (doctor_id, department_id)
);

