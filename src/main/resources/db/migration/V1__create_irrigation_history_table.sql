create table if not exists irrigation_process_history(
id BIGINT PRIMARY KEY,
irrigation_id BIGINT NOT NULL,
process_status VARCHAR(100) NOT NULL,
irrigation_type VARCHAR(250),
amount_of_water DOUBLE,
liquid_unit VARCHAR(100),
duration INT,
created_at TIMESTAMP NOT NULL DEFAULT now(),
version BIGINT)