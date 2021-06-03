CREATE TABLE alert_configuration
(
    id                 BIGINT(20) NOT NULL AUTO_INCREMENT,
    create_date        DATETIME,
    update_date        DATETIME,
    name               VARCHAR(256),
    company_id         BIGINT(20),
    notification_types VARCHAR(1024),
    PRIMARY KEY (id),
    CONSTRAINT FK_alert_configuration_company FOREIGN KEY (company_id) REFERENCES company (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8;

CREATE TABLE alert_configuration_condition
(
    id                     BIGINT(20) NOT NULL AUTO_INCREMENT,
    create_date            DATETIME,
    update_date            DATETIME,
    alert_configuration_id BIGINT(20),
    field                  VARCHAR(256),
    conditions             VARCHAR(256),
    field_values           VARCHAR(1024),
    PRIMARY KEY (id),
    CONSTRAINT FK_alert_configuration_condition_alert_configuration FOREIGN KEY (alert_configuration_id) REFERENCES alert_configuration (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8;