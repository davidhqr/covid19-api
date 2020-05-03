-- CREATE TABLE country_data (
--     id bigint(20) NOT NULL AUTO_INCREMENT,
--     country_id bigint(20) NOT NULL,
--     confirmed int,
--     deaths int,
--     recovered int,
--     active int,
--     PRIMARY KEY(id),
--     FOREIGN KEY(country_id) REFERENCES country(id)
-- );
--
-- CREATE TABLE province_state_data (
--     id bigint(20) NOT NULL AUTO_INCREMENT,
--     province_state_id bigint(20) NOT NULL,
--     confirmed int,
--     deaths int,
--     recovered int,
--     active int,
--     PRIMARY KEY(id),
--     FOREIGN KEY(province_state_id) REFERENCES province_state(id)
-- );

ALTER TABLE country
ADD COLUMN confirmed int AFTER population,
ADD COLUMN deaths int AFTER confirmed,
ADD COLUMN recovered int AFTER deaths,
ADD COLUMN active int AFTER recovered;

ALTER TABLE province_state
ADD COLUMN confirmed int AFTER longitude,
ADD COLUMN deaths int AFTER confirmed,
ADD COLUMN recovered int AFTER deaths,
ADD COLUMN active int AFTER recovered;

