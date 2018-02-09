CREATE TABLE log (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Identifier of the table.',
    ip VARCHAR(20) NOT NULL COMMENT 'IP that made many requests and was blocked.',
    comment_block VARCHAR(255) NOT NULL COMMENT 'Comment on why IP was blocked.',
    date_block DATETIME NOT NULL DEFAULT NOW() COMMENT 'Block date',
    PRIMARY KEY(id)
) ENGINE=InnoDB;
