
CREATE TABLE patients (
     id BIGSERIAL PRIMARY KEY,
     first_name VARCHAR(150),
     middle_name VARCHAR(150),
     last_name VARCHAR(150),
     date_of_birth DATE,
     gender VARCHAR(10) CHECK(gender IN ('Male','Female','Other','Prefer_Not_Say')),
);