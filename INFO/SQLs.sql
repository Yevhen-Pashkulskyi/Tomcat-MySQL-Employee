CREATE DATABASE demo_db;

       CREATE TABLE IF NOT EXISTS employee
       (id INTEGER NOT NULL AUTO_INCREMENT,
       name VARCHAR(128) NOT NULL,
           position VARCHAR(128) NOT NULL,
           phone VARCHAR(128) NOT NULL,
           PRIMERY KEY(id)
           );