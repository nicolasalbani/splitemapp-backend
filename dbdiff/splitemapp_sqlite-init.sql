-- -----------------------------------------------------
-- Schema splitemapp
-- -----------------------------------------------------
--CREATE SCHEMA IF NOT EXISTS "splitemapp" DEFAULT CHARACTER SET utf8 ;
--USE "splitemapp" ;

-- -----------------------------------------------------
-- Enable foreign keys support
-- -----------------------------------------------------
PRAGMA foreign_keys = ON;

-- -----------------------------------------------------
-- Table "user_status"
-- -----------------------------------------------------
CREATE TABLE "user_status" (
  "id" INTEGER PRIMARY KEY NOT NULL,
  "cod" varchar(64) COLLATE binary NOT NULL,
  "title" varchar(64) COLLATE binary NOT NULL
);
CREATE UNIQUE INDEX user_status__cod_u_idx ON user_status(cod);
insert into user_status values(1,"active","Activo");
insert into user_status values(2,"disabled","Desactivado");
insert into user_status values(3,"deleted","Borrado");

-- -----------------------------------------------------
-- Table "user"
-- -----------------------------------------------------
CREATE TABLE "user" (
  "id" INTEGER PRIMARY KEY AUTOINCREMENT,
  "status_id" smallint(6)  NOT NULL,
  "username" varchar(64) NOT NULL,
  "password" varchar(40) DEFAULT NULL,
  "first_name" varchar(45) NULL,
  "last_name" varchar(45) NULL,
  "last_login" datetime DEFAULT NULL,
  "login_cnt" int(11) NOT NULL DEFAULT "0",
  "created_at" timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "created_ip_address" varchar(15) COLLATE binary NOT NULL,
  "updated_at" timestamp NOT NULL DEFAULT "0000-00-00 00:00:00",
  "updated_ip_address" varchar(15) COLLATE binary DEFAULT NULL,
  FOREIGN KEY(status_id) REFERENCES user_status(id)
);
CREATE UNIQUE INDEX idx_u_username ON user(username);

-- -----------------------------------------------------
-- Table "user_contact_data"
-- -----------------------------------------------------
CREATE TABLE "user_contact_data" (
  "id" INTEGER PRIMARY KEY AUTOINCREMENT,
  "user_id" bigint(20)  NOT NULL,
  "contact_data" varchar(64) DEFAULT NULL,
  "verified" tinyint(1) NOT NULL DEFAULT "0",
  "verified_at" timestamp NULL DEFAULT NULL,
  "created_at" timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "updated_at" timestamp NULL DEFAULT NULL,
  FOREIGN KEY(user_id) REFERENCES user(id)
);
CREATE UNIQUE INDEX user_id_contact_data_idx ON user_contact_data(user_id,contact_data);


-- -----------------------------------------------------
-- Table "project_status"
-- -----------------------------------------------------
CREATE TABLE "project_status" (
  "id" INTEGER PRIMARY KEY NOT NULL,
  "cod" varchar(64) COLLATE binary NOT NULL,
  "title" varchar(64) COLLATE binary NOT NULL
);
CREATE UNIQUE INDEX project_status__cod_u_idx ON project_status(cod);
insert into project_status values(1,"active","Activo");
insert into project_status values(2,"closed","Cerrado");


-- -----------------------------------------------------
-- Table "project_type"
-- -----------------------------------------------------
CREATE TABLE "project_type" (
  "id" INTEGER PRIMARY KEY  NOT NULL,
  "cod" varchar(64) COLLATE binary NOT NULL,
  "title" varchar(64) COLLATE binary NOT NULL
);
CREATE UNIQUE INDEX project_type__cod_u_idx ON project_type(cod);
insert into project_type values(1,"monthly","Mensual");
insert into project_type values(2,"one_time","Solo una vez");


-- -----------------------------------------------------
-- Table "project"
-- -----------------------------------------------------
  CREATE TABLE "project" (
    "id" INTEGER PRIMARY KEY AUTOINCREMENT,
    "status_id" smallint(6)  NOT NULL,
    "type_id" smallint(6)  NOT NULL,
    "title" varchar(128) NULL,
    "img_cover" varchar(128) NULL,
    "budget" decimal(16,6) NULL,
    "created_at" timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "updated_at" timestamp NOT NULL DEFAULT "0000-00-00 00:00:00",
	FOREIGN KEY(status_id) REFERENCES project_status(id),
	FOREIGN KEY(type_id) REFERENCES project_type(id)
  );

-- -----------------------------------------------------
-- Table "group_status"
-- -----------------------------------------------------
CREATE TABLE "group_status" (
  "id" INTEGER PRIMARY KEY NOT NULL,
  "cod" varchar(64) COLLATE binary NOT NULL,
  "title" varchar(64) COLLATE binary NOT NULL
);
CREATE UNIQUE INDEX group_status__cod_u_idx ON group_status(cod);
insert into group_status values(1,"active","Activo");
insert into group_status values(2,"disabled","Desactivado");
insert into group_status values(3,"deleted","Borrado");

-- -----------------------------------------------------
-- Table "group"
-- -----------------------------------------------------
CREATE TABLE "group" (
  "id" INTEGER PRIMARY KEY AUTOINCREMENT,
  "status_id" smallint(6)  NOT NULL,
  "cod" varchar(64) COLLATE binary NOT NULL,
  "title" varchar(64) COLLATE binary NOT NULL,
  "img_cover" varchar(128) NULL,
  "created_at" timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "updated_at" timestamp NOT NULL DEFAULT "0000-00-00 00:00:00",
  FOREIGN KEY(status_id) REFERENCES group_status(id)
);
CREATE UNIQUE INDEX group__cod_u_idx ON "group"(cod);

-- -----------------------------------------------------
-- Table "user_to_group_status"
-- -----------------------------------------------------
CREATE TABLE "user_to_group_status" (
  "id" INTEGER PRIMARY KEY NOT NULL,
  "cod" varchar(64) COLLATE binary NOT NULL,
  "title" varchar(64) COLLATE binary NOT NULL
);
CREATE UNIQUE INDEX user_to_group_status__cod_u_idx ON user_to_group_status(cod);
insert into user_to_group_status values(1,"active","Activo");
insert into user_to_group_status values(2,"removed","Eliminado");
insert into user_to_group_status values(3,"left_group","Dejo el grupo");

-- -----------------------------------------------------
-- Table "user_to_user_group"
-- -----------------------------------------------------
CREATE TABLE "user_to_group" (
  "id" INTEGER PRIMARY KEY AUTOINCREMENT,
  "user_status_id" smallint(6)  NOT NULL,
  "user_id" bigint(20)  NOT NULL,
  "is_admin" tinyint(1) NOT NULL DEFAULT "0",
  "group_id" bigint(20)  NOT NULL,
  FOREIGN KEY(user_id) REFERENCES user(id),
  FOREIGN KEY(group_id) REFERENCES "group"(id),
  FOREIGN KEY(user_status_id) REFERENCES user_to_group_status(id)
);
CREATE UNIQUE INDEX user_id__group_id_u_idx ON user_to_group(user_id,group_id);

-- -----------------------------------------------------
-- Table "user_to_project_status"
-- -----------------------------------------------------
CREATE TABLE "user_to_project_status" (
  "id" INTEGER PRIMARY KEY NOT NULL,
  "cod" varchar(64) COLLATE binary NOT NULL,
  "title" varchar(64) COLLATE binary NOT NULL
);
CREATE UNIQUE INDEX user_to_project_status__cod_u_idx ON user_to_project_status(cod);
insert into user_to_project_status values(1,"active","Activo");
insert into user_to_project_status values(2,"removed","Eliminado");
insert into user_to_project_status values(3,"left_project","Dejo el proyecto");

-- -----------------------------------------------------
-- Table "user_to_project"
-- -----------------------------------------------------
CREATE TABLE "user_to_project" (
  "id" INTEGER PRIMARY KEY AUTOINCREMENT,
  "user_status_id" smallint(6)  NOT NULL,
  "user_id" bigint(20)  NOT NULL,
  "project_id" bigint(20)  NOT NULL,
  "expenses_share" decimal(3,2) NOT NULL,
  FOREIGN KEY(user_id) REFERENCES user(id),
  FOREIGN KEY(project_id) REFERENCES project(id),
  FOREIGN KEY(user_status_id) REFERENCES user_to_project_status(id)
);
CREATE UNIQUE INDEX user_id__project_id_u_idx ON user_to_project(user_id,project_id);

-- -----------------------------------------------------
-- Table "invite_status"
-- -----------------------------------------------------
CREATE TABLE "invite_status" (
  "id" INTEGER PRIMARY KEY NOT NULL,
  "cod" varchar(64) COLLATE binary NOT NULL,
  "title" varchar(64) COLLATE binary NOT NULL
);
CREATE UNIQUE INDEX invite_status__cod_u_idx ON invite_status(cod);
insert into invite_status values(1,"sent","Enviada");
insert into invite_status values(2,"accepted","Aceptada");
insert into invite_status values(3,"rejected","Rechazada");

-- -----------------------------------------------------
-- Table "user_invite"
-- -----------------------------------------------------
CREATE TABLE "user_invite" (
  "id" INTEGER PRIMARY KEY AUTOINCREMENT,
  "status_id" smallint(6)  NOT NULL,
  "user_id" bigint(20)  NOT NULL,
  "project_id" bigint(20)  NOT NULL,
  "email" varchar(64) DEFAULT NULL,
  "created_at" timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "updated_at" timestamp NOT NULL DEFAULT "0000-00-00 00:00:00",
  FOREIGN KEY(user_id) REFERENCES user(id),
  FOREIGN KEY(project_id) REFERENCES project(id),
  FOREIGN KEY(status_id) REFERENCES invite_status(id)
);

-- -----------------------------------------------------
-- Table "invite_status"
-- -----------------------------------------------------
CREATE TABLE "expense_category" (
  "id" INTEGER PRIMARY KEY NOT NULL,
  "cod" varchar(64) COLLATE binary NOT NULL,
  "title" varchar(64) COLLATE binary NOT NULL
);
CREATE UNIQUE INDEX expense_category__cod_u_idx ON expense_category(cod);
insert into expense_category values(1,"car","Automovil");

-- -----------------------------------------------------
-- Table "user_expenses"
-- -----------------------------------------------------
CREATE TABLE "user_expenses" (
  "id" INTEGER PRIMARY KEY AUTOINCREMENT,
  "user_id" bigint(20)  NOT NULL,
  "project_id" bigint(20)  NOT NULL,
  "category_id" smallint(6)  NOT NULL,
  "expense" decimal(16,6) NOT NULL,
  "expense_date" timestamp NULL,
  "note" text DEFAULT NULL,
  FOREIGN KEY(user_id) REFERENCES user(id),
  FOREIGN KEY(project_id) REFERENCES project(id),
  FOREIGN KEY(category_id) REFERENCES expense_category(id)
);
