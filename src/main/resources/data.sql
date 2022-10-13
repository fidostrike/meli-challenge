INSERT INTO "PUBLIC"."SHOW" VALUES
(1, FALSE, U&'Telefe, una marca propiedad de Paramount, y RGB Entertainment anuncian la llegada del show teatral m\00e1s esperado: Casados con hijos.', 'Casados con hijos'),
(2, FALSE, U&'El Teatro Gran Rex informa que los eventos que se realizan en esta sala podr\00edan ser fotografiados o filmados para su posterior difusi\00f3n en medios y /o campa\00f1as publicitarias.', 'Rodrigo Tapari');

INSERT INTO "PUBLIC"."THEATER" VALUES
(1, FALSE, 'Gran Rex');

INSERT INTO "PUBLIC"."GROUPSEAT" VALUES
(1, FALSE, 'Grupo a'),
(2, FALSE, 'Grupo b');

INSERT INTO "PUBLIC"."AUDITORIUM" VALUES
(1, FALSE, 'Sala 1', 1),
(2, FALSE, 'Sala 2', 1);

INSERT INTO "PUBLIC"."FUNCTION" VALUES
(1, TIMESTAMP '2022-12-01 22:00:00', TIMESTAMP '2022-12-01 23:00:00', FALSE, 1, 1),
(2, TIMESTAMP '2022-12-02 22:00:00', TIMESTAMP '2022-12-02 23:00:00', FALSE, 1, 1),
(3, TIMESTAMP '2022-12-01 22:00:00', TIMESTAMP '2022-12-01 23:00:00', FALSE, 2, 2);

INSERT INTO "PUBLIC"."PRICE" VALUES
(1, FALSE, 100.0, 1, 1),
(2, FALSE, 200.0, 1, 2),
(3, FALSE, 100.0, 2, 1),
(4, FALSE, 200.0, 2, 2),
(5, FALSE, 100.0, 3, 1),
(6, FALSE, 200.0, 3, 2);

INSERT INTO "PUBLIC"."SEAT" VALUES
(1, FALSE, 'a1', 1, 1),
(2, FALSE, 'b1', 2, 2),
(3, FALSE, 'a1', 2, 1),
(4, FALSE, 'b1', 1, 2);

