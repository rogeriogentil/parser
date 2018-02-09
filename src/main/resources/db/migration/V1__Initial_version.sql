CREATE TABLE log (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Identifier of the table.',
    ip VARCHAR(20) NOT NULL COMMENT 'IP that made many requests and was blocked.',
    comment_block VARCHAR(255) NOT NULL COMMENT 'Comment on why IP was blocked.',
    date_register DATETIME NOT NULL DEFAULT NOW() COMMENT 'Registration date',
    PRIMARY KEY(id)
) ENGINE=InnoDB;

CREATE TABLE accesslog (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Identifier of the table.',
    date TIMESTAMP NOT NULL COMMENT 'Date of the HTTP request',
    ip VARCHAR(20) NOT NULL COMMENT 'IP that made HTTP request',
    request VARCHAR(100) NOT NULL COMMENT 'The HTTP request',
    status CHAR(3) NOT NULL COMMENT 'HTTP Status Code', 
    user_agent VARCHAR(255) NOT NULL COMMENT 'User Agent',
    PRIMARY KEY(id)
) ENGINE=InnoDB;