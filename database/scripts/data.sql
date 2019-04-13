INSERT INTO "specialization" ("name")
VALUES ('Аллерголог');
INSERT INTO "specialization" ("name")
VALUES ('Диетолог');
INSERT INTO "specialization" ("name")
VALUES ('Психиатр');
INSERT INTO "specialization" ("name")
VALUES ('Стоматолог');
INSERT INTO "specialization" ("name")
VALUES ('Терапевт');
INSERT INTO "doctor"("name", "surname", "patronymic", "specialization_id")
VALUES ('Анатолий', 'Анатольев', 'Анатольевич', 0);
INSERT INTO "doctor"("name", "surname", "patronymic", "specialization_id")
VALUES ('Генадий', 'Генадьев', 'Генадьевич', 1);
INSERT INTO "doctor"("name", "surname", "patronymic", "specialization_id")
VALUES ('Борис', 'Борисов', 'Борисович', 2);
INSERT INTO "doctor"("name", "surname", "patronymic", "specialization_id")
VALUES ('Оксана', 'Оксанова', 'Олеговна', 3);
INSERT INTO "doctor"("name", "surname", "patronymic", "specialization_id")
VALUES ('Дарья', 'Дарьева', 'Борисовна', 4);
INSERT INTO "patient"("name", "surname", "patronymic", "phone_number")
VALUES ('Ксения', 'Юрьева', '', '892788881118');
INSERT INTO "patient"("name", "surname", "patronymic", "phone_number")
VALUES ('Ксения', 'Валерьева', 'Олеговна', '892777781118');
INSERT INTO "patient"("name", "surname", "patronymic", "phone_number")
VALUES ('Илон', 'Маск', 'Иванович', '892766671118');
INSERT INTO "patient"("name", "surname", "patronymic", "phone_number")
VALUES ('Марк', 'Марков', 'Маркович', '892722221118');
INSERT INTO "patient"("name", "surname", "patronymic", "phone_number")
VALUES ('Егор', 'Егоров', '', '892785431118');
INSERT INTO "priority" ("name")
VALUES ('Нормальный');
INSERT INTO "priority" ("name")
VALUES ('Cрочный');
INSERT INTO "priority" ("name")
VALUES ('Немедленный');
INSERT INTO "recipe" ("description", "patient_id", "doctor_id", "creation_date", "expiration_date", "priority_id")
VALUES ('Мезим', 0, 0, SYSDATE, SYSDATE, 0);
INSERT INTO "recipe" ("description", "patient_id", "doctor_id", "creation_date", "expiration_date", "priority_id")
VALUES ('Антигрипин', 1, 1, SYSDATE, SYSDATE, 1);
INSERT INTO "recipe" ("description", "patient_id", "doctor_id", "creation_date", "expiration_date", "priority_id")
VALUES ('Тестер', 2, 2, SYSDATE, SYSDATE, 2);
INSERT INTO "recipe" ("description", "patient_id", "doctor_id", "creation_date", "expiration_date", "priority_id")
VALUES ('Иммунитет плюс', 3, 3, SYSDATE, SYSDATE, 0);
INSERT INTO "recipe" ("description", "patient_id", "doctor_id", "creation_date", "expiration_date", "priority_id")
VALUES ('Достойный препарат', 4, 4, SYSDATE, SYSDATE, 1);