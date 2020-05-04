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

