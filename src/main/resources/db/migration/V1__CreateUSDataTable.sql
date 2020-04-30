CREATE TABLE us_data (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    state varchar(100) NOT NULL,
    country varchar(100) NOT NULL,
    last_update varchar(100),
    latitude varchar(100),
    longitude varchar(100),
    confirmed int,
    deaths int,
    recovered int,
    active double,
    mortality_rate double,
    iso3 varchar(40),
    PRIMARY KEY(id)
)