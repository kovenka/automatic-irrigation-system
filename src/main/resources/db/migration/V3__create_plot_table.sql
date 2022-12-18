create table if not exists plot (
id BIGINT PRIMARY KEY,
name VARCHAR(250),
description VARCHAR(1024),
latitude DOUBLE,
longitude DOUBLE,
cultivated_area DOUBLE NOT NULL,
area_unit VARCHAR(100) NOT NULL,
agricultural_crop_type VARCHAR(250),
created_at TIMESTAMP DEFAULT now(),
updated_at TIMESTAMP DEFAULT now(),
irrigation_id BIGINT,
version BIGINT,
CONSTRAINT fk_land_irr_id_irrigation_id FOREIGN KEY (irrigation_id) REFERENCES irrigation (id)
)
