-- -----------------------------------------------------
-- Schema splitemapp
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `splitemapp` DEFAULT CHARACTER SET utf8 ;
USE `splitemapp` ;

-- -----------------------------------------------------
-- Table `user_status`
-- -----------------------------------------------------
CREATE TABLE `user_status` (
  `id` smallint(6) unsigned NOT NULL,
  `cod` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `title` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cod_u_idx` (`cod`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8;
insert into user_status values(1,'active','Activo');
insert into user_status values(2,'disabled','Desactivado');
insert into user_status values(3,'deleted','Borrado');

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `status_id` smallint(6) unsigned NOT NULL,
  `username` varchar(64) NOT NULL,
  `password` varchar(40) DEFAULT NULL,
  `full_name` varchar(45) NULL,
  `last_login` datetime DEFAULT NULL,
  `login_cnt` int(11) NOT NULL DEFAULT '0',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_ip_address` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_ip_address` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pushed_at` timestamp NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_u_username` (`username`),
  KEY `user__status_id` (`status_id`),
  CONSTRAINT `user__status_id` FOREIGN KEY (`status_id`) REFERENCES `user_status` (`id`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `user_avatar`
-- -----------------------------------------------------
CREATE TABLE `user_avatar` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL,
  `avatar_data` MEDIUMBLOB NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `pushed_at` timestamp NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_u_user_id` (`user_id`),
  CONSTRAINT `user_avatar__user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `user_contact_data`
-- -----------------------------------------------------
CREATE TABLE `user_contact_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL,
  `contact_data` varchar(64) DEFAULT NULL,
  `verified` tinyint(1) NOT NULL DEFAULT '0',
  `verified_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `pushed_at` timestamp NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `idx_contact_data` (`contact_data`),
  UNIQUE KEY `user_id_contact_data_idx` (`user_id`,`contact_data`),
  CONSTRAINT `user_contact_data__user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- -----------------------------------------------------
-- Table `project_status`
-- -----------------------------------------------------
CREATE TABLE `project_status` (
  `id` smallint(6) unsigned NOT NULL,
  `cod` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `title` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cod_u_idx` (`cod`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8;
insert into project_status values(1,'active','Activo');
insert into project_status values(2,'closed','Cerrado');


-- -----------------------------------------------------
-- Table `project_type`
-- -----------------------------------------------------
CREATE TABLE `project_type` (
  `id` smallint(6) unsigned NOT NULL,
  `cod` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `title` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cod_u_idx` (`cod`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8;
insert into project_type values(1,'monthly','Mensual');
insert into project_type values(2,'one_time','Solo una vez');


-- -----------------------------------------------------
-- Table `project`
-- -----------------------------------------------------
CREATE TABLE `project` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `status_id` smallint(6) unsigned NOT NULL,
  `type_id` smallint(6) unsigned NOT NULL,
  `title` varchar(128) NULL,
  `budget` decimal(16,6) NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `pushed_at` timestamp NULL,
  PRIMARY KEY (`id`),
  KEY `project__status_id` (`status_id`),
  KEY `project__type_id` (`type_id`),
  CONSTRAINT `project__status_id` FOREIGN KEY (`status_id`) REFERENCES `project_status` (`id`),
  CONSTRAINT `project__type_id` FOREIGN KEY (`type_id`) REFERENCES `project_type` (`id`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8;


-- -----------------------------------------------------
-- Table `project_cover_image`
-- -----------------------------------------------------
CREATE TABLE `project_cover_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `project_id` bigint(20) unsigned NOT NULL,
  `avatar_data` MEDIUMBLOB null,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `pushed_at` timestamp NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_u_project_id` (`project_id`),
  CONSTRAINT `project_cover_image__project_id` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `user_to_project_status`
-- -----------------------------------------------------
CREATE TABLE `user_to_project_status` (
  `id` smallint(6) unsigned NOT NULL,
  `cod` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `title` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cod_u_idx` (`cod`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8;
insert into user_to_project_status values(1,'active','Activo');
insert into user_to_project_status values(2,'archived','Archivado');
insert into user_to_project_status values(3,'left_project','Dejo el proyecto');

-- -----------------------------------------------------
-- Table `user_to_project`
-- -----------------------------------------------------
CREATE TABLE `user_to_project` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_to_project_status_id` smallint(6) unsigned NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `project_id` bigint(20) unsigned NOT NULL,
  `expenses_share` float(6,3) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `pushed_at` timestamp NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id__project_id_u_idx` (`user_id`,`project_id`),
  KEY `user_to_project__user_id` (`user_id`),
  KEY `user_to_project__project_id` (`project_id`),
  CONSTRAINT `user_to_project__user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_to_project__project_id` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`),
  CONSTRAINT `user_to_project__user_to_project_status_id` FOREIGN KEY (`user_to_project_status_id`) REFERENCES `user_to_project_status` (`id`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `invite_status`
-- -----------------------------------------------------
CREATE TABLE `invite_status` (
  `id` smallint(6) unsigned NOT NULL,
  `cod` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `title` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cod_u_idx` (`cod`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8;
insert into invite_status values(1,'sent','Enviada');
insert into invite_status values(2,'accepted','Aceptada');
insert into invite_status values(3,'rejected','Rechazada');

-- -----------------------------------------------------
-- Table `user_invite`
-- -----------------------------------------------------
CREATE TABLE `user_invite` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `status_id` smallint(6) unsigned NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `project_id` bigint(20) unsigned NOT NULL,
  `email` varchar(64) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `pushed_at` timestamp NULL,
  PRIMARY KEY (`id`),
  KEY `user_invite__status_id` (`status_id`),
  CONSTRAINT `user_invite__user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_invite__project_id` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`),
  CONSTRAINT `user_invite__status_id` FOREIGN KEY (`status_id`) REFERENCES `invite_status` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `invite_status`
-- -----------------------------------------------------
CREATE TABLE `expense_category` (
  `id` smallint(6) unsigned NOT NULL,
  `cod` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `title` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cod_u_idx` (`cod`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8;
insert into expense_category values(1,'car','Automovil');
insert into expense_category values(2,'travel','Viajes');
insert into expense_category values(3,'food','Comida y Bebidas');
insert into expense_category values(4,'family','Familia y Personal');
insert into expense_category values(5,'bills','Cuentas');
insert into expense_category values(6,'entertainment','Entretenimiento');
insert into expense_category values(7,'home','Hogar');
insert into expense_category values(8,'utilities','Utilidades');
insert into expense_category values(9,'shopping','Shopping');

-- -----------------------------------------------------
-- Table `user_expense`
-- -----------------------------------------------------
CREATE TABLE `user_expense` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL,
  `project_id` bigint(20) unsigned NOT NULL,
  `category_id` smallint(6) unsigned NOT NULL,
  `expense` decimal(16,6) NOT NULL,
  `expense_date` timestamp NULL,
  `note` text DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `pushed_at` timestamp NULL,
  PRIMARY KEY (`id`),
  KEY `user_expense__user_id` (`user_id`),
  KEY `user_expense__project_id` (`project_id`),
  CONSTRAINT `user_expense__user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_expense__project_id` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`),
  CONSTRAINT `user_expense__category_id` FOREIGN KEY (`category_id`) REFERENCES `expense_category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `user_session`
-- -----------------------------------------------------
CREATE TABLE `user_session` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL,
  `token` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `gcm_token` varchar(4096) COLLATE utf8_unicode_ci DEFAULT NULL,
  `device` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `os_version` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `last_used_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `token_u_idx` (`token`),
  KEY `user_session__user_id` (`user_id`),
  CONSTRAINT `user_session__user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
