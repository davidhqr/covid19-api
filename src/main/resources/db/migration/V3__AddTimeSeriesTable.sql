CREATE TABLE time_series_global (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    date varchar(225) NOT NULL,
    country_id bigint(20) NOT NULL,
    country_name varchar(100) NOT NULL,
    confirmed int,
    deaths int,
    recovered int,
    PRIMARY KEY(id),
    FOREIGN KEY(country_id) REFERENCES country(id)
);
