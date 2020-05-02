CREATE TABLE country (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    country_name varchar(100) NOT NULL,
    iso2 varchar(40),
    iso3 varchar(40),
    latitude varchar(40),
    longitude varchar(40),
    population int,
    PRIMARY KEY(id)
);

CREATE TABLE province_state (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    country_id bigint(20) NOT NULL,
    province_state varchar(100) NOT NULL,
    latitude varchar(40),
    longitude varchar(40),
    PRIMARY KEY(id),
    FOREIGN KEY(country_id) REFERENCES country(id)
);