use bd_votaciones;
/*administrador*/
INSERT INTO administrador (`cedula`,`apellido1`,`apellido2`,`nombre`,`usuario`,`clave`) VALUES ('1','perez','perez','admin ','admin','admin');
INSERT INTO administrador (`cedula`,`apellido1`,`apellido2`,`nombre`,`usuario`,`clave`) VALUES ('2','Rodriguez','Rodriguez','Rodrigo','k','2');
/*usuario*/
INSERT INTO usuario (`cedula`,`apellido1`,`apellido2`,`nombre`,`clave`,`activo`) VALUES ('1','Perez','Rodriguez','Luis','1',1);
INSERT INTO usuario (`cedula`,`apellido1`,`apellido2`,`nombre`,`clave`,`activo`) VALUES ('111111111','Perez','Perez','Juan','changeme',0);
INSERT INTO usuario (`cedula`,`apellido1`,`apellido2`,`nombre`,`clave`,`activo`) VALUES ('116960863','Jiménez','Babb','Diego','116960863',0);
INSERT INTO usuario (`cedula`,`apellido1`,`apellido2`,`nombre`,`clave`,`activo`) VALUES ('222222222','Fernandez','Fernandez','Ana','changeme',0);
INSERT INTO usuario (`cedula`,`apellido1`,`apellido2`,`nombre`,`clave`,`activo`) VALUES ('333333333','Rodriguez','Rodriguez','Pedro','changeme',0);
INSERT INTO usuario (`cedula`,`apellido1`,`apellido2`,`nombre`,`clave`,`activo`) VALUES ('402370259','Rodríguez','Alfaro','Ismael','402370259',0);
INSERT INTO usuario (`cedula`,`apellido1`,`apellido2`,`nombre`,`clave`,`activo`) VALUES ('801030879','Jiménez','Arzola','Rachel','801030879',0);
/*voataion*/
INSERT INTO votacion (`id`,`fecha_inicio`,`fecha_apertura`,`fecha_cierre`,`fecha_final`,`estado`) VALUES (1,'2019-06-08 00:00:00','2019-06-09 00:00:00','2019-06-10 00:00:00','2019-06-11 00:00:00',4);
INSERT INTO votacion (`id`,`fecha_inicio`,`fecha_apertura`,`fecha_cierre`,`fecha_final`,`estado`) VALUES (2,'2019-05-01 00:00:00','2019-05-02 00:00:00','2019-05-03 00:00:00','2019-05-04 00:00:00',4);
INSERT INTO votacion (`id`,`fecha_inicio`,`fecha_apertura`,`fecha_cierre`,`fecha_final`,`estado`) VALUES (3,'2019-06-10 00:00:00','2019-06-11 00:00:00','2019-06-12 00:00:00','2019-06-12 00:00:00',4);
INSERT INTO votacion (`id`,`fecha_inicio`,`fecha_apertura`,`fecha_cierre`,`fecha_final`,`estado`) VALUES (4,'2019-06-01 00:00:00','2019-06-01 00:00:00','2019-06-07 00:00:00','2019-06-05 00:00:00',4);
/*partido*/
INSERT INTO partido (`siglas`,`nombre`,`bandera`,`tipo_imagen`,`observaciones`) VALUES ('MP','Malos Perdedores','np','image/jpeg','QQ');
INSERT INTO partido(`siglas`,`nombre`,`bandera`,`tipo_imagen`,`observaciones`) VALUES ('PG','Pros Gamer','np','image/png','lol');
INSERT INTO partido (`siglas`,`nombre`,`bandera`,`tipo_imagen`,`observaciones`) VALUES ('PMP','Partido Madre Patria ','np','image/png','pmp rule');
INSERT INTO partido (`siglas`,`nombre`,`bandera`,`tipo_imagen`,`observaciones`) VALUES ('XML','xion multi locos','np','image/png','????');
/*votacion_partido*/ 
INSERT INTO votacion_partido (`votacion_id`,`partido_siglas`,`cedula_candidato`,`foto_candidato`,`tipo_imagen`,`votos_obtenidos`) VALUES (1,'PG','222222222','n/p','image/jpeg',0);
INSERT INTO votacion_partido (`votacion_id`,`partido_siglas`,`cedula_candidato`,`foto_candidato`,`tipo_imagen`,`votos_obtenidos`) VALUES (1,'XML','1','n/p','image/png',1);
INSERT INTO votacion_partido (`votacion_id`,`partido_siglas`,`cedula_candidato`,`foto_candidato`,`tipo_imagen`,`votos_obtenidos`) VALUES (2,'MP','333333333','n/p','image/jpeg',0);
INSERT INTO votacion_partido (`votacion_id`,`partido_siglas`,`cedula_candidato`,`foto_candidato`,`tipo_imagen`,`votos_obtenidos`) VALUES (2,'PMP','801030879','n/p','image/png',0);
INSERT INTO votacion_partido (`votacion_id`,`partido_siglas`,`cedula_candidato`,`foto_candidato`,`tipo_imagen`,`votos_obtenidos`) VALUES (2,'XML','333333333','n/p','image/png',3);
INSERT INTO votacion_partido (`votacion_id`,`partido_siglas`,`cedula_candidato`,`foto_candidato`,`tipo_imagen`,`votos_obtenidos`) VALUES (3,'MP','402370259','n/p','image/jpeg',3);
INSERT INTO votacion_partido (`votacion_id`,`partido_siglas`,`cedula_candidato`,`foto_candidato`,`tipo_imagen`,`votos_obtenidos`) VALUES (3,'PMP','222222222','n/p','image/jpeg',4);
/*voataion_usario*/
INSERT INTO votacion_usuario (`votacion_id`,`usuario_cedula`,`voto_completado`) VALUES (1,'1',1);
INSERT INTO votacion_usuario (`votacion_id`,`usuario_cedula`,`voto_completado`) VALUES (1,'111111111',1);
INSERT INTO votacion_usuario (`votacion_id`,`usuario_cedula`,`voto_completado`) VALUES (1,'801030879',1);
INSERT INTO votacion_usuario (`votacion_id`,`usuario_cedula`,`voto_completado`) VALUES (2,'1',1);
INSERT INTO votacion_usuario (`votacion_id`,`usuario_cedula`,`voto_completado`) VALUES (2,'333333333',1);
INSERT INTO votacion_usuario (`votacion_id`,`usuario_cedula`,`voto_completado`) VALUES (2,'402370259',1);
INSERT INTO votacion_usuario (`votacion_id`,`usuario_cedula`,`voto_completado`) VALUES (3,'1',1);
INSERT INTO votacion_usuario (`votacion_id`,`usuario_cedula`,`voto_completado`) VALUES (3,'111111111',1);
INSERT INTO votacion_usuario (`votacion_id`,`usuario_cedula`,`voto_completado`) VALUES (3,'116960863',1);
INSERT INTO votacion_usuario (`votacion_id`,`usuario_cedula`,`voto_completado`) VALUES (3,'222222222',1);
INSERT INTO votacion_usuario (`votacion_id`,`usuario_cedula`,`voto_completado`) VALUES (3,'333333333',1);
INSERT INTO votacion_usuario (`votacion_id`,`usuario_cedula`,`voto_completado`) VALUES (3,'402370259',1);
INSERT INTO votacion_usuario (`votacion_id`,`usuario_cedula`,`voto_completado`) VALUES (3,'801030879',1);

