CREATE TYPE user_status AS ENUM ('ACTIVE', 'SUSPENDED', 'DELETED', 'BLOCKED');

CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(20) UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    status user_status DEFAULT 'ACTIVE',
    password_reset_token VARCHAR(255),
    password_reset_expires_at TIMESTAMP,
    email_verified_at TIMESTAMP,
    email_verification_token VARCHAR(255),
    email_verification_expires_at TIMESTAMP,
    phone_verified_at TIMESTAMP,
    phone_verification_otp VARCHAR(10),
    phone_verification_expires_at TIMESTAMP,
    verified_at TIMESTAMP,
    last_login_at TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT
);


CREATE TABLE user_details (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    first_name VARCHAR(255) NOT NULL,
    middle_name VARCHAR(255),
    last_name VARCHAR(255),
    date_of_birth DATE,
    gender VARCHAR(10) CHECK(gender IN ('male','female','other','prefer_not_say')),
    address1 VARCHAR(150),
    address2 VARCHAR(150),
    city_id SMALLINT REFERENCES cities(id),
    state_id SMALLINT REFERENCES states(id),
    country_id SMALLINT REFERENCES countries(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

