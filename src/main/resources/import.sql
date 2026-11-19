 DELETE FROM meetroom_booking;
 DELETE FROM meetroom;
 DELETE FROM user_app;

 INSERT INTO meetroom(nombre) VALUES ('sala-1');
 INSERT INTO meetroom(nombre) VALUES ('sala-2');
 INSERT INTO meetroom(nombre) VALUES ('sala-3');

 SELECT setval('meetroom_seq', 3, true);

 INSERT INTO user_app( username, password ) VALUES ( 'admin','$2b$10$3s9ss3Mu1DMdfNFXXo.2Le5qD/5RZ9epZKTrNNDRL/Wu8SoSGL6y.');

 INSERT INTO meetroom_booking( responsable, meetroom_id, fecha_inicio, fecha_fin, nombre_sala) VALUES( 'victor', 1, '2026-11-01 08:00:00', '2026-11-02 16:00:00','sala-1');
 INSERT INTO meetroom_booking( responsable, meetroom_id, fecha_inicio, fecha_fin, nombre_sala) VALUES( 'victor', 1, '2026-11-03 08:00:00', '2026-11-04 16:00:00','sala-1');
 INSERT INTO meetroom_booking( responsable, meetroom_id, fecha_inicio, fecha_fin, nombre_sala) VALUES( 'victor', 1, '2026-11-06 08:00:00', '2026-11-08 16:00:00','sala-1');
 INSERT INTO meetroom_booking( responsable, meetroom_id, fecha_inicio, fecha_fin, nombre_sala) VALUES( 'victor', 1, '2026-11-09 08:00:00', '2026-11-13 16:00:00','sala-1');
 INSERT INTO meetroom_booking( responsable, meetroom_id, fecha_inicio, fecha_fin, nombre_sala) VALUES( 'victor', 1, '2026-11-15 08:00:00', '2026-11-18 16:00:00','sala-1');
 INSERT INTO meetroom_booking( responsable, meetroom_id, fecha_inicio, fecha_fin, nombre_sala) VALUES( 'victor', 1, '2026-11-19 08:00:00', '2026-11-20 16:00:00','sala-1');
 INSERT INTO meetroom_booking( responsable, meetroom_id, fecha_inicio, fecha_fin, nombre_sala) VALUES( 'victor', 1, '2026-11-22 08:00:00', '2026-11-23 16:00:00','sala-1');
 INSERT INTO meetroom_booking( responsable, meetroom_id, fecha_inicio, fecha_fin, nombre_sala) VALUES( 'victor', 1, '2026-11-24 08:00:00', '2026-11-28 16:00:00','sala-1');
 INSERT INTO meetroom_booking( responsable, meetroom_id, fecha_inicio, fecha_fin, nombre_sala) VALUES( 'victor', 1, '2026-11-29 08:00:00', '2026-11-30 16:00:00','sala-1');

 INSERT INTO meetroom_booking( responsable, meetroom_id, fecha_inicio, fecha_fin, nombre_sala) VALUES( 'victor', 2, '2026-11-01 08:00:00', '2026-11-02 16:00:00','sala-2');
 INSERT INTO meetroom_booking( responsable, meetroom_id, fecha_inicio, fecha_fin, nombre_sala) VALUES( 'victor', 2, '2026-11-03 08:00:00', '2026-11-04 16:00:00','sala-2');
 INSERT INTO meetroom_booking( responsable, meetroom_id, fecha_inicio, fecha_fin, nombre_sala) VALUES( 'victor', 2, '2026-11-06 08:00:00', '2026-11-08 16:00:00','sala-2');
 INSERT INTO meetroom_booking( responsable, meetroom_id, fecha_inicio, fecha_fin, nombre_sala) VALUES( 'victor', 2, '2026-11-09 08:00:00', '2026-11-13 16:00:00','sala-2');
 INSERT INTO meetroom_booking( responsable, meetroom_id, fecha_inicio, fecha_fin, nombre_sala) VALUES( 'victor', 2, '2026-11-15 08:00:00', '2026-11-18 16:00:00','sala-2');
 INSERT INTO meetroom_booking( responsable, meetroom_id, fecha_inicio, fecha_fin, nombre_sala) VALUES( 'victor', 2, '2026-11-19 08:00:00', '2026-11-20 16:00:00','sala-2');
 INSERT INTO meetroom_booking( responsable, meetroom_id, fecha_inicio, fecha_fin, nombre_sala) VALUES( 'victor', 2, '2026-11-22 08:00:00', '2026-11-23 16:00:00','sala-2');
 INSERT INTO meetroom_booking( responsable, meetroom_id, fecha_inicio, fecha_fin, nombre_sala) VALUES( 'victor', 2, '2026-11-24 08:00:00', '2026-11-28 16:00:00','sala-2');
 INSERT INTO meetroom_booking( responsable, meetroom_id, fecha_inicio, fecha_fin, nombre_sala) VALUES( 'victor', 2, '2026-11-29 08:00:00', '2026-11-30 16:00:00','sala-2');

 INSERT INTO meetroom_booking( responsable, meetroom_id, fecha_inicio, fecha_fin, nombre_sala) VALUES( 'victor', 3, '2026-11-01 08:00:00', '2026-11-02 16:00:00','sala-3');
 INSERT INTO meetroom_booking( responsable, meetroom_id, fecha_inicio, fecha_fin, nombre_sala) VALUES( 'victor', 3, '2026-11-03 08:00:00', '2026-11-04 16:00:00','sala-3');
 INSERT INTO meetroom_booking( responsable, meetroom_id, fecha_inicio, fecha_fin, nombre_sala) VALUES( 'victor', 3, '2026-11-06 08:00:00', '2026-11-08 16:00:00','sala-3');
 INSERT INTO meetroom_booking( responsable, meetroom_id, fecha_inicio, fecha_fin, nombre_sala) VALUES( 'victor', 3, '2026-11-09 08:00:00', '2026-11-13 16:00:00','sala-3');
 INSERT INTO meetroom_booking( responsable, meetroom_id, fecha_inicio, fecha_fin, nombre_sala) VALUES( 'victor', 3, '2026-11-15 08:00:00', '2026-11-18 16:00:00','sala-3');
 INSERT INTO meetroom_booking( responsable, meetroom_id, fecha_inicio, fecha_fin, nombre_sala) VALUES( 'victor', 3, '2026-11-19 08:00:00', '2026-11-20 16:00:00','sala-3');
 INSERT INTO meetroom_booking( responsable, meetroom_id, fecha_inicio, fecha_fin, nombre_sala) VALUES( 'victor', 3, '2026-11-22 08:00:00', '2026-11-23 16:00:00','sala-3');
 INSERT INTO meetroom_booking( responsable, meetroom_id, fecha_inicio, fecha_fin, nombre_sala) VALUES( 'victor', 3, '2026-11-24 08:00:00', '2026-11-28 16:00:00','sala-3');
 INSERT INTO meetroom_booking( responsable, meetroom_id, fecha_inicio, fecha_fin, nombre_sala) VALUES( 'victor', 3, '2026-11-29 08:00:00', '2026-11-30 16:00:00','sala-3');

 SELECT setval('meetroom_booking_seq', 27, true);
