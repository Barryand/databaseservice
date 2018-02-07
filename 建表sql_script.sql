CREATE TABLE testdb1.users
(
  id VARCHAR(20) PRIMARY KEY,
  password VARCHAR(20) NOT NULL
);

CREATE TABLE testdb1.reference_point
(
  rp_id VARCHAR(20) PRIMARY KEY,
  longitude VARCHAR(20) NOT NULL,
  latitude VARCHAR(20) NOT NULL
);

CREATE TABLE testdb1.reference_point_data
(
  rpd_id VARCHAR(20) PRIMARY KEY,
  AP1 VARCHAR(20) NOT NULL,  RSSI1 VARCHAR(20) NOT NULL,
  AP2 VARCHAR(20) NOT NULL,  RSSI2 VARCHAR(20) NOT NULL,
  AP3 VARCHAR(20) NOT NULL,  RSSI3 VARCHAR(20) NOT NULL,
  AP4 VARCHAR(20) NOT NULL,  RSSI4 VARCHAR(20) NOT NULL,
  AP5 VARCHAR(20) NOT NULL,  RSSI5 VARCHAR(20) NOT NULL,
  CONSTRAINT reference_point_data_referencepoint_rp_id_fk FOREIGN KEY (rpd_id) REFERENCES reference_point (rp_id)
);

CREATE TABLE testdb1.user_file
(
  user_id VARCHAR(20),
  file_name VARCHAR(100),
  file_content BLOB NOT NULL,
  CONSTRAINT user_file_user_id_file_name_pk PRIMARY KEY (user_id, file_name),
  CONSTRAINT user_file_user_id_fk FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE testdb1.user_data
(
  user_id VARCHAR(20),
  file_name VARCHAR(50),
  AP1 VARCHAR(20) NOT NULL,  RSSI1 VARCHAR(20) NOT NULL,
  AP2 VARCHAR(20) NOT NULL,  RSSI2 VARCHAR(20) NOT NULL,
  AP3 VARCHAR(20) NOT NULL,  RSSI3 VARCHAR(20) NOT NULL,
  AP4 VARCHAR(20) NOT NULL,  RSSI4 VARCHAR(20) NOT NULL,
  AP5 VARCHAR(20) NOT NULL,  RSSI5 VARCHAR(20) NOT NULL,
  CONSTRAINT user_data_user_id_file_name_pk PRIMARY KEY (user_id, file_name),
  CONSTRAINT user_data_user_id_file_name_fk FOREIGN KEY (user_id, file_name) REFERENCES user_file (user_id, file_name)
);

CREATE TABLE testdb1.user_position
(
  serial_number SERIAL,
  user_id VARCHAR(20),
  file_name VARCHAR(50),
  longitude VARCHAR(20) NOT NULL,
  latitude VARCHAR(20) NOT NULL,
  CONSTRAINT user_position_user_id_file_name_pk PRIMARY KEY (user_id, file_name),
  CONSTRAINT user_position_user_id_file_name_fk FOREIGN KEY (user_id, file_name) REFERENCES user_file (user_id, file_name)
);
