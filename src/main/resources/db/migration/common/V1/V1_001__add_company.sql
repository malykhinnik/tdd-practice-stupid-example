CREATE TABLE IF NOT EXISTS company (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    create_date datetime DEFAULT NULL,
    update_date datetime DEFAULT NULL,
    enabled bit(1) NOT NULL,
    name varchar(100) NOT NULL,
    company_type varchar(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY UK_company_name (name)
    ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO company(name,company_type,enabled,create_date,update_date) VALUES('STRINGCONCAT','Publisher',true,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);