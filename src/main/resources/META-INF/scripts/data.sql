INSERT INTO PUBLIC.doctor(name, surname, patronymic, specialization) VALUES ('Анатолий', 'Анатольев', 'Анатольевич', 'Аллерголог');
INSERT INTO PUBLIC.doctor(name, surname, patronymic, specialization) VALUES ('Геннадий', 'Геннадьев', 'Геннадьевич', 'Диетолог');
INSERT INTO PUBLIC.doctor(name, surname, patronymic, specialization) VALUES ('Борис', 'Борисов', 'Борисович', 'Психиатр');
INSERT INTO PUBLIC.doctor(name, surname, patronymic, specialization) VALUES ('Оксана', 'Оксанова', 'Олеговна', 'Стоматолог');
INSERT INTO PUBLIC.doctor(name, surname, patronymic, specialization) VALUES ('Дарья', 'Дарьева', 'Борисовна', 'Терапевт');
INSERT INTO PUBLIC.patient(name, surname, patronymic, phone_number) VALUES ('Ксения', 'Юрьева', '', '+792788881118');
INSERT INTO PUBLIC.patient(name, surname, patronymic, phone_number) VALUES ('Ксения', 'Валерьева', 'Олеговна', '+792777781118');
INSERT INTO PUBLIC.patient(name, surname, patronymic, phone_number) VALUES ('Илон', 'Маск', 'Иванович', '+792766671118');
INSERT INTO PUBLIC.patient(name, surname, patronymic, phone_number) VALUES ('Марк', 'Марков', 'Маркович', '+792722221118');
INSERT INTO PUBLIC.patient(name, surname, patronymic, phone_number) VALUES ('Егор', 'Егоров', '', '+792785431118');
INSERT INTO PUBLIC.recipe(description, patient_id, doctor_id, creation_date, expiration_date, priority) VALUES ('Мезим', 0, 0, SYSDATE, SYSDATE, 'Normal');
INSERT INTO PUBLIC.recipe(description, patient_id, doctor_id, creation_date, expiration_date, priority) VALUES ('Антигриппин', 1, 1, SYSDATE, SYSDATE, 'Cito');
INSERT INTO PUBLIC.recipe(description, patient_id, doctor_id, creation_date, expiration_date, priority) VALUES ('Тестер', 2, 2, SYSDATE, SYSDATE, 'Statim');
INSERT INTO PUBLIC.recipe(description, patient_id, doctor_id, creation_date, expiration_date, priority) VALUES ('Иммунитет плюс', 3, 3, SYSDATE, SYSDATE, 'Normal');
INSERT INTO PUBLIC.recipe(description, patient_id, doctor_id, creation_date, expiration_date, priority) VALUES ('Достойный препарат', 4, 4, SYSDATE, SYSDATE, 'Cito');