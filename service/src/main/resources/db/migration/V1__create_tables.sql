CREATE TABLE `sala` (
   `id`      BIGINT      NOT NULL AUTO_INCREMENT,
   `nome`    VARCHAR(20) NOT NULL,
   `lugares` INT         NOT NULL,
   `ativa`   TINYINT     NOT NULL,
   PRIMARY KEY (`id`)
);