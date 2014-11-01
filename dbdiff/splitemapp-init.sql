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
  `first_name` varchar(45) NULL,
  `last_name` varchar(45) NULL,
  `last_login` datetime DEFAULT NULL,
  `login_cnt` int(11) NOT NULL DEFAULT '0',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_ip_address` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_ip_address` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_u_username` (`username`),
  KEY `user__status_id` (`status_id`),
  CONSTRAINT `user__status_id` FOREIGN KEY (`status_id`) REFERENCES `user_status` (`id`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8;

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
  `updated_at` timestamp NULL DEFAULT NULL,
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
    `img_cover` varchar(128) NULL,
    `budget` decimal(16,6) NULL,
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
    PRIMARY KEY (`id`),
    KEY `project__status_id` (`status_id`),
    KEY `project__type_id` (`type_id`),
    CONSTRAINT `project__status_id` FOREIGN KEY (`status_id`) REFERENCES `project_status` (`id`),
    CONSTRAINT `project__type_id` FOREIGN KEY (`type_id`) REFERENCES `project_type` (`id`)
  ) ENGINE = InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `group_status`
-- -----------------------------------------------------
CREATE TABLE `group_status` (
  `id` smallint(6) unsigned NOT NULL,
  `cod` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `title` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cod_u_idx` (`cod`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8;
insert into group_status values(1,'active','Activo');
insert into group_status values(2,'disabled','Desactivado');
insert into group_status values(3,'deleted','Borrado');

-- -----------------------------------------------------
-- Table `group`
-- -----------------------------------------------------
CREATE TABLE `group` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `status_id` smallint(6) unsigned NOT NULL,
  `cod` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `title` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `img_cover` varchar(128) NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `cod_u_idx` (`cod`),
  CONSTRAINT `group__status_id` FOREIGN KEY (`status_id`) REFERENCES `group_status` (`id`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `user_to_group_status`
-- -----------------------------------------------------
CREATE TABLE `user_to_group_status` (
  `id` smallint(6) unsigned NOT NULL,
  `cod` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `title` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cod_u_idx` (`cod`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8;
insert into user_to_group_status values(1,'active','Activo');
insert into user_to_group_status values(2,'removed','Eliminado');
insert into user_to_group_status values(3,'left_group','Dejo el grupo');

-- -----------------------------------------------------
-- Table `user_to_user_group`
-- -----------------------------------------------------
CREATE TABLE `user_to_group` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_status_id` smallint(6) unsigned NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `is_admin` tinyint(1) NOT NULL DEFAULT '0',
  `group_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id__group_id_u_idx` (`user_id`,`group_id`),
  KEY `user_to_group__user_id_idx` (`user_id`),
  KEY `user_to_group__group_id_idx` (`group_id`),
  CONSTRAINT `user_to_group__user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_to_group__group_id` FOREIGN KEY (`group_id`) REFERENCES `group` (`id`),
  CONSTRAINT `user_to_group__user_status_id` FOREIGN KEY (`user_status_id`) REFERENCES `user_to_group_status` (`id`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8;

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
insert into user_to_project_status values(2,'removed','Eliminado');
insert into user_to_project_status values(3,'left_project','Dejo el proyecto');

-- -----------------------------------------------------
-- Table `user_to_project`
-- -----------------------------------------------------
CREATE TABLE `user_to_project` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_status_id` smallint(6) unsigned NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `project_id` bigint(20) unsigned NOT NULL,
  `expenses_share` decimal(3,2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id__project_id_u_idx` (`user_id`,`project_id`),
  KEY `user_to_project__user_id` (`user_id`),
  KEY `user_to_project__project_id` (`project_id`),
  CONSTRAINT `user_to_project__user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_to_project__project_id` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`),
  CONSTRAINT `user_to_project__user_status_id` FOREIGN KEY (`user_status_id`) REFERENCES `user_to_project_status` (`id`)
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
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
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

-- -----------------------------------------------------
-- Table `user_expenses`
-- -----------------------------------------------------
CREATE TABLE `user_expenses` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL,
  `project_id` bigint(20) unsigned NOT NULL,
  `category_id` smallint(6) unsigned NOT NULL,
  `expense` decimal(16,6) NOT NULL,
  `expense_date` timestamp NULL,
  `note` text DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_expenses__user_id` (`user_id`),
  KEY `user_expenses__project_id` (`project_id`),
  CONSTRAINT `user_expenses__user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_expenses__project_id` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`),
  CONSTRAINT `user_expenses__category_id` FOREIGN KEY (`category_id`) REFERENCES `expense_category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;