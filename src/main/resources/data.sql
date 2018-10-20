-- Setup data
INSERT INTO account (price) VALUES (500);

INSERT INTO user (username, /*login,*/ password, token, /*profile,*/ account_id)
  VALUES ('Bedrick', /*'bedrick@gmail.com',*/ 'bedrick', 'QWERTYUIOPASDFGHJKLÇZXCVBNM',/*,0,*/ 1);

INSERT INTO device (device_id, account_id) VALUES('AGH23FH09', 1);
INSERT INTO device (device_id, account_id) VALUES('DEHBCFH43', 1);
INSERT INTO device (device_id, account_id) VALUES('XXSR2HS51', 1);

-- Valores de teste
INSERT INTO liters_per_hour (flow_rate, date_collection, account_id) VALUES (0.0, {ts '2018-09-24 00:01:000'},1);
INSERT INTO liters_per_hour (flow_rate, date_collection, account_id) VALUES (0.0, {ts '2018-09-24 01:01:000'},1);
INSERT INTO liters_per_hour (flow_rate, date_collection, account_id) VALUES (0.0, {ts '2018-09-24 02:01:000'},1);
INSERT INTO liters_per_hour (flow_rate, date_collection, account_id) VALUES (0.0, {ts '2018-09-24 03:01:000'},1);
INSERT INTO liters_per_hour (flow_rate, date_collection, account_id) VALUES (2.0, {ts '2018-09-24 04:01:000'},1);
INSERT INTO liters_per_hour (flow_rate, date_collection, account_id) VALUES (3.0, {ts '2018-09-24 05:01:000'},1);
INSERT INTO liters_per_hour (flow_rate, date_collection, account_id) VALUES (5.0, {ts '2018-09-24 06:01:000'},1);
INSERT INTO liters_per_hour (flow_rate, date_collection, account_id) VALUES (5.0, {ts '2018-09-24 07:01:000'},1);
INSERT INTO liters_per_hour (flow_rate, date_collection, account_id) VALUES (6.0, {ts '2018-09-24 08:01:000'},1);
INSERT INTO liters_per_hour (flow_rate, date_collection, account_id) VALUES (5.0, {ts '2018-09-24 09:01:000'},1);
INSERT INTO liters_per_hour (flow_rate, date_collection, account_id) VALUES (10.0, {ts '2018-09-24 10:01:000'},1);
INSERT INTO liters_per_hour (flow_rate, date_collection, account_id) VALUES (8.5, {ts '2018-09-24 11:01:000'},1);
INSERT INTO liters_per_hour (flow_rate, date_collection, account_id) VALUES (10.3, {ts '2018-09-24 12:01:000'},1);
INSERT INTO liters_per_hour (flow_rate, date_collection, account_id) VALUES (8.7, {ts '2018-09-24 13:01:000'},1);
INSERT INTO liters_per_hour (flow_rate, date_collection, account_id) VALUES (4.2, {ts '2018-09-24 14:01:000'},1);
INSERT INTO liters_per_hour (flow_rate, date_collection, account_id) VALUES (5.5, {ts '2018-09-24 15:01:000'},1);
INSERT INTO liters_per_hour (flow_rate, date_collection, account_id) VALUES (6.5, {ts '2018-09-24 16:01:000'},1);
INSERT INTO liters_per_hour (flow_rate, date_collection, account_id) VALUES (4.8, {ts '2018-09-24 17:01:000'},1);
INSERT INTO liters_per_hour (flow_rate, date_collection, account_id) VALUES (10.2, {ts '2018-09-24 18:01:000'},1);
INSERT INTO liters_per_hour (flow_rate, date_collection, account_id) VALUES (8.8, {ts '2018-09-24 19:01:000'},1);
INSERT INTO liters_per_hour (flow_rate, date_collection, account_id) VALUES (5.9, {ts '2018-09-24 20:01:000'},1);
INSERT INTO liters_per_hour (flow_rate, date_collection, account_id) VALUES (6.0, {ts '2018-09-24 21:01:000'},1);
INSERT INTO liters_per_hour (flow_rate, date_collection, account_id) VALUES (3.6, {ts '2018-09-24 22:01:000'},1);
INSERT INTO liters_per_hour (flow_rate, date_collection, account_id) VALUES (1.0, {ts '2018-09-24 23:01:000'},1);


INSERT INTO liters_per_day (flow_rate, date_collection, account_id) VALUES (120.0, '2018-09-24',1);
INSERT INTO liters_per_day (flow_rate, date_collection, account_id) VALUES (110.0, '2018-09-25',1);
INSERT INTO liters_per_day (flow_rate, date_collection, account_id) VALUES (90.0, '2018-09-26',1);
INSERT INTO liters_per_day (flow_rate, date_collection, account_id) VALUES (150.0, '2018-09-27',1);
INSERT INTO liters_per_day (flow_rate, date_collection, account_id) VALUES (122.0, '2018-09-28',1);
INSERT INTO liters_per_day (flow_rate, date_collection, account_id) VALUES (131.0, '2018-09-29',1);
INSERT INTO liters_per_day (flow_rate, date_collection, account_id) VALUES (121.0, '2018-09-30',1);
INSERT INTO liters_per_day (flow_rate, date_collection, account_id) VALUES (102.0, '2018-10-01',1);
INSERT INTO liters_per_day (flow_rate, date_collection, account_id) VALUES (99.0, '2018-10-02',1);
INSERT INTO liters_per_day (flow_rate, date_collection, account_id) VALUES (129.0, '2018-10-03',1);
INSERT INTO liters_per_day (flow_rate, date_collection, account_id) VALUES (111.0, '2018-10-04',1);
INSERT INTO liters_per_day (flow_rate, date_collection, account_id) VALUES (110.0, '2018-10-05',1);
INSERT INTO liters_per_day (flow_rate, date_collection, account_id) VALUES (100.0, '2018-10-06',1);
INSERT INTO liters_per_day (flow_rate, date_collection, account_id) VALUES (103.0, '2018-10-07',1);
INSERT INTO liters_per_day (flow_rate, date_collection, account_id) VALUES (99.0, '2018-10-08',1);

INSERT INTO liters_per_month (flow_rate, date_collection, account_id) VALUES (844.0, '2018-09-01',1);
INSERT INTO liters_per_month (flow_rate, date_collection, account_id) VALUES (853.0, '2018-10-01',1);
