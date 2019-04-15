CREATE TABLE IF NOT EXISTS PUBLIC.doctor(id BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 0 INCREMENT BY 1) NOT NULL PRIMARY KEY, name VARCHAR(30) NOT NULL, surname VARCHAR(30) NOT NULL, patronymic VARCHAR(30), specialization VARCHAR(40) NOT NULL);
CREATE TABLE IF NOT EXISTS PUBLIC.patient(id BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 0 INCREMENT BY 1) NOT NULL PRIMARY KEY, name VARCHAR(30) NOT NULL, surname VARCHAR(30) NOT NULL, patronymic VARCHAR(30), phone_number VARCHAR(15));
CREATE TABLE IF NOT EXISTS PUBLIC.recipe(id BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 0 INCREMENT BY 1) NOT NULL PRIMARY KEY, description VARCHAR(200) NOT NULL, patient_id BIGINT NOT NULL, doctor_id BIGINT NOT NULL, creation_date DATE NOT NULL, expiration_date DATE NOT NULL, priority VARCHAR(20) NOT NULL, CONSTRAINT fk_patient_id FOREIGN KEY (patient_id) REFERENCES patient (id) ON DELETE RESTRICT, CONSTRAINT fk_doctor_id FOREIGN KEY (doctor_id) REFERENCES doctor (id) ON DELETE RESTRICT);
