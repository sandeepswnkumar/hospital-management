
CREATE TABLE doctors (
    id BIGSERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    description TEXT,
    specialization VARCHAR(100) NOT NULL ,
    experience_years INTEGER default 0,
    consultation_fee DECIMAL(10, 2)
);