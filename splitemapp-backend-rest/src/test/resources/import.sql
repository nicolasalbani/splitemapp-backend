------------------------------------------------------
-- ALTER TABLES
------------------------------------------------------

-- Populating the user_status alter table
insert into splitemapp.user_status (id,cod,title) 
values 								(1,'active','Activo'),
									(2,'disabled','Desactivado'),
									(3,'deleted','Borrado');

-- Populating the project_status alter table
insert into splitemapp.project_status	(id,cod,title) 
values 									(1,'active','Activo'),
										(2,'closed','Cerrado');

-- Populating project_type alter table
insert into splitemapp.project_type	(id,cod,title) 
values									(1,'monthly','Mensual'),
										(2,'one_time','Solo una vez');

-- Populating user_to_project_status alter table
insert into splitemapp.user_to_project_status	(id,cod,title) 
values											(1,'active','Activo'),
												(2,'removed','Eliminado'),
												(3,'left_group','Dejo el grupo');

-- Populating invite_status alter table
insert into splitemapp.invite_status	(id,cod,title) 
values									(1,'sent','Enviada'),
										(2,'accepted','Aceptada'),
										(3,'rejected','Rechazada');

										
										
------------------------------------------------------			
-- NORMAL TABLES
------------------------------------------------------
										
-- Populating the expense_category table
insert into splitemapp.expense_category	(id,cod,title) 
values										(1,'car','Automovil'); 

-- Populating the project table
insert into splitemapp.project	(id,status_id,type_id,title,budget,created_at,updated_at) 
values							(1,1,1,'Primer Proyecto',4000,'2014-09-21 02:17:36','2014-09-21 02:17:36');

-- Populating the user table
insert into splitemapp.user	(id,status_id,username,password,full_name,last_login,login_cnt,created_at,created_ip_address,updated_at,updated_ip_address)  
values							(1,1,'juanperez','01b307acba4f54f55aafc33bb06bbbf6ca803e9a','juan perez','2014-09-21 02:17:36',1,'2014-09-21 02:17:36','127.0.0.1','2014-09-21 02:17:36','127.0.0.1');


-- Populating the user_contact_data table
insert into splitemapp.user_contact_data	(id,user_id,contact_data,verified,verified_at,created_at,updated_at) 
values										(1,1,'juan@perez.com',1,'2014-09-21 02:17:36','2014-09-21 02:17:36','2014-09-21 02:17:36');

-- Populating the user_expense table
insert into splitemapp.user_expense		(id,user_id,project_id,category_id,expense,expense_date,note) 
values									(1,1,1,1,200,'2014-09-21 02:17:36','Nafta');

-- Populating the user_to_project table
insert into splitemapp.user_to_project	(id,user_to_project_status_id,user_id,project_id,expenses_share) 
values									(1,1,1,1,100);

-- Populating the user_invite table
insert into splitemapp.user_invite	(id,status_id,user_id,project_id,email,created_at,updated_at) 
values								(1,1,1,1,'pablo@perez.com','2014-09-21 02:17:36','2014-09-21 02:17:36');