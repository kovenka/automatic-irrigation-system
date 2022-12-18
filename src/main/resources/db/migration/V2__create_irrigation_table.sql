create table if not exists irrigation (
id BIGINT PRIMARY KEY,
irrigation_type  VARCHAR(250),
amount_of_water  DOUBLE NOT NULL,
liquid_unit  VARCHAR(100) NOT NULL,
duration INT NOT NULL,
delay_interval DOUBLE NOT NULL,
irrigation_status VARCHAR(100) NOT NULL,
next_irrigation_at TIMESTAMP DEFAULT now(),
created_at TIMESTAMP NOT NULL DEFAULT now(),
updated_at TIMESTAMP NOT NULL DEFAULT now(),
version BIGINT
)