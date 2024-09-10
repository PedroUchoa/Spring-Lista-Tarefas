CREATE TABLE items(
    id VARCHAR(255) NOT NULL PRIMARY KEY UNIQUE,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE,
    is_active BOOLEAN NOT NULL,
    status varchar(255) NOT NULL,
    priority BOOLEAN NOT NULL
)