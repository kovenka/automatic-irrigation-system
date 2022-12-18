insert into irrigation(id, irrigation_type, amount_of_water, liquid_unit, duration, delay_interval, irrigation_status, next_irrigation_at, created_at, updated_at, version)
values
(1000, 'SPRINKLER', 1000, 'GALLON', 3600 , 21600, 'ACTIVE', now(), now(), now(), 0),
(1001, 'SPRINKLER', 3500, 'GALLON', 7200 , 10800, 'ACTIVE', now(), now(), now(), 0),
(1002, 'CENTER_PIVOT', 4500, 'GALLON', 8200 , 20400, 'ACTIVE', now(), now(), now(), 0),
(1003, 'LATERAL_MOVE', 5500, 'GALLON', 9200 , 30600, 'ACTIVE', now(), now(), now(), 0),
(1004, 'RAIN', 6500, 'GALLON', 10200 , 40200, 'ACTIVE', now(), now(), now(), 0),
(1005, 'FOG', 7500, 'GALLON', 11200 , 50800, 'ACTIVE', now(), now(), now(), 0);

insert into plot(id, name, description, latitude, longitude, cultivated_area, area_unit, agricultural_crop_type, created_at, updated_at, irrigation_id, version)
values
(1000, 'Corn Land', 'Corn Farm in Andhra', 10.1234567,18.0987651, 2000, 'SQUARE_METER', 'CORN', now(), now(), 1000, 0),
(1001, 'Cotton Land', 'Contton Farm in Telangana', 20.1234567,170.0987651, 3050, 'SQUARE_METER', 'COTTON', now(), now(), 1001, 0),
(1002, 'Ragi Land', 'Anshayapatra Farm Land', 30.1234567,70.0987651, 4020, 'SQUARE_METER', 'COTTON', now(), now(), 1002, 0),
(1003, 'Pears Land', 'Sid Farm', 40.1234567,170.0987651, 5010, 'SQUARE_METER', 'COTTON', now(), now(), 1003, 0),
(1004, 'Sugar Land', 'Organic Farm', 50.1234567,170.0987651, 7865, 'SQUARE_METER', 'COTTON', now(), now(), 1004, 0),
(1005, 'Onions Land', 'Onions Farm', 60.1234567,170.0987651, 1234, 'SQUARE_METER', 'COTTON', now(), now(), 1005, 0);

insert into irrigation_process_history(id, irrigation_id, process_status, irrigation_type, amount_of_water, liquid_unit, duration, created_at, version)
values
(1000, 1000, 'SUCCESS', 'SPRINKLER', 1000, 'GALLON', 3600, now(), 0),
(1001, 1001, 'SUCCESS', 'SPRINKLER', 1000, 'GALLON', 3600, now(), 0),
(1002, 1002, 'FAILED', 'SPRINKLER', 1000, 'GALLON', 3600, now(), 0);