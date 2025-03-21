-- -------------------------------------------------------------
--
-- Eugene Zimin
--
-- Database: ums
-- Generation Time: 2020-11-02 00:36:45.2270
-- -------------------------------------------------------------
-- Script for creating the database and populating it with data
-- -------------------------------------------------------------

create database ums;
use ums;


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

DROP TABLE IF EXISTS `last_visit`;

CREATE TABLE `last_visit` (
                              `id` binary(16) NOT NULL,
                              `in` int DEFAULT NULL,
                              `out` int DEFAULT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
                         `id` binary(16) NOT NULL,
                         `name` varchar(45) DEFAULT NULL,
                         `description` varchar(45) DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
                         `id` binary(16) NOT NULL,
                         `name` varchar(45) NOT NULL,
                         `email` varchar(45) NOT NULL,
                         `password` varchar(45) NOT NULL,
                         `created` int NOT NULL,
                         `last_visit_id` binary(16) DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `email_key` (`email`) USING BTREE,
                         KEY `fk_users_last_visit1_idx` (`last_visit_id`),
                         CONSTRAINT `users_ibfk_1` FOREIGN KEY (`last_visit_id`) REFERENCES `last_visit` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `users_has_roles`;
CREATE TABLE `users_has_roles` (
                                   `users_id` binary(16) NOT NULL,
                                   `roles_id` binary(16) NOT NULL,
                                   PRIMARY KEY (`users_id`,`roles_id`),
                                   KEY `fk_users_has_roles_roles1_idx` (`roles_id`),
                                   KEY `fk_users_has_roles_users_idx` (`users_id`),
                                   CONSTRAINT `fk_users_has_roles_roles1` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                                   CONSTRAINT `fk_users_has_roles_users` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `last_visit` (`id`, `in`, `out`) VALUES
                                                 (X'306DCF05D3D64B438E066B6FFE2331FC', '1604249194', '1604249224'),
                                                 (X'3C37571B0F494FED875845BFAE428B29', '1604249181', '1604249209'),
                                                 (X'7AC613D5538745E58C9F9F49A0F271E1', '1604249171', '1604249203'),
                                                 (X'8A0BE660EBC64C47AF813F7123977993', '1604249188', '1604249217'),
                                                 (X'BE76648F49F24FBE9BC434BD8C23C70E', '1604130098', '1604130106');

INSERT INTO `roles` (`id`, `name`, `description`) VALUES
                                                      (X'43DCF1D63AC0429099FD44F9B9F9BECF', 'ADMIN', 'Admistrative toles for the system'),
                                                      (X'B479B3577E2547FA8DBABFDAEECC6C2C', 'SUBSCRIBER', 'Message content consumer'),
                                                      (X'EB932DBB7005422FA6497190AF39E984', 'PRODUCER', 'Message content producer');

INSERT INTO `users` (`id`, `name`, `email`, `password`, `created`, `last_visit_id`) VALUES
                                                                                        (X'0DD03A597DBC4D0081073271B3345434', 'Angela Merkel', 'angela@merkel.de', 'password', '1504249224', X'8A0BE660EBC64C47AF813F7123977993'),
                                                                                        (X'1CD89E11602A4186AFBFE0149B59EB08', 'Emmanuel Macron', 'emmanuel@macron.fr', 'password', '1504249224', X'BE76648F49F24FBE9BC434BD8C23C70E'),
                                                                                        (X'6E27EA06A7164C89AF88813749A8BD48', 'Donald Trump', 'donalt@trump.us', 'password', '1604129987', X'BE76648F49F24FBE9BC434BD8C23C70E'),
                                                                                        (X'70A64B5443C34C18BBEC64590FF7E0CC', 'Justing Trudeau', 'justin@trudeau.ca', 'password', '1504249224', X'3C37571B0F494FED875845BFAE428B29'),
                                                                                        (X'ABB04B9F5D1040DD9076EB27CA76891A', 'Vladimir Putin', 'vladimir@putin.tu', 'password', '1504249224', X'7AC613D5538745E58C9F9F49A0F271E1');

INSERT INTO `users_has_roles` (`users_id`, `roles_id`) VALUES
                                                           (X'0DD03A597DBC4D0081073271B3345434', X'B479B3577E2547FA8DBABFDAEECC6C2C'),
                                                           (X'1CD89E11602A4186AFBFE0149B59EB08', X'B479B3577E2547FA8DBABFDAEECC6C2C'),
                                                           (X'1CD89E11602A4186AFBFE0149B59EB08', X'EB932DBB7005422FA6497190AF39E984'),
                                                           (X'6E27EA06A7164C89AF88813749A8BD48', X'B479B3577E2547FA8DBABFDAEECC6C2C'),
                                                           (X'6E27EA06A7164C89AF88813749A8BD48', X'EB932DBB7005422FA6497190AF39E984'),
                                                           (X'70A64B5443C34C18BBEC64590FF7E0CC', X'B479B3577E2547FA8DBABFDAEECC6C2C'),
                                                           (X'ABB04B9F5D1040DD9076EB27CA76891A', X'B479B3577E2547FA8DBABFDAEECC6C2C');



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;