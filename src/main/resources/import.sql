-- Rollen
INSERT INTO rolle(created_at, bezeichnung) VALUES (now(), 'ADMIN');
INSERT INTO rolle(created_at, bezeichnung) VALUES (now(), 'SUPERVISOR');
INSERT INTO rolle(created_at, bezeichnung) VALUES (now(), 'BENUTZER');
-- Rollen

INSERT INTO benutzer(created_at, anzeigename, benutzername, dienstnummer, passwort) VALUES (now(), 'ADMIN', 'ADMIN', 'ADMIN', '$2a$10$eoJXaB5sKquqydjBbkxiHOIT7iowKUI6A2HUfpyYTrwaibJ5SfPFW');
UPDATE benutzer set active = true;
INSERT INTO benutzer_rolle(benutzer_id,rolle_id) VALUES (1,1);

INSERT INTO benutzer(created_at, anzeigename, benutzername, dienstnummer, passwort) VALUES (now(), 'Bernd', 'Bernd', 'Bernd', '$2a$10$eoJXaB5sKquqydjBbkxiHOIT7iowKUI6A2HUfpyYTrwaibJ5SfPFW');
UPDATE benutzer set active = false WHERE anzeigename = 'Bernd';
INSERT INTO benutzer_rolle(benutzer_id,rolle_id) VALUES (2,1);



-- TEST -----------------------------

INSERT INTO veranstaltung (name, beginn, ende, zustand,lagerstandort) VALUES ('Wien 2018', '2018-11-04T00:00', '2018-11-04T23:59', 'In Bearbeitung',1);
INSERT INTO veranstaltung (name, beginn, ende, zustand,lagerstandort) VALUES ('Steiermark 2018', '2018-12-04T08:00', '2018-12-04T17:59', 'In Bearbeitung',1);
INSERT INTO veranstaltung (name, beginn, ende, zustand,lagerstandort) VALUES ('Burgenland 2019', '2019-01-04T00:00', '2019-01-07T23:59', 'In Bearbeitung',1);
INSERT INTO einheitentyp (name) VALUES ('ERSTE');
INSERT INTO einheitentyp (name) VALUES ('ZWEITE');
INSERT INTO materialtyp (name,menge) VALUES ('Asprin','Stuck');
INSERT INTO materialtyp (name,menge) VALUES ('Antibiotika','Stuck');
INSERT INTO materialtyp (name,menge) VALUES ('Ampule','Stuck');
INSERT INTO materialtyp_einheitentyp (manzahl, materialtyp, einheitentyp) VALUES (25, 1 , 1);
INSERT INTO materialtyp_einheitentyp (manzahl, materialtyp, einheitentyp) VALUES (50, 2 , 1);
INSERT INTO mitarbeitertyp (kuerzel,name) VALUES ('ZYZ','Zebra Y Zebra');
INSERT INTO lagerstandort (name,adresse,benutzer) VALUES ('Wien-Kenyongasse','Kenyongasse 100',1);
INSERT INTO material (lagerstandort, materialtyp, bezeichnung, bestand, einsatzbereitschaft) VALUES (1, 1, 'ASPRIN BAYER', 100, true);
INSERT INTO material (lagerstandort, materialtyp, bezeichnung, bestand, einsatzbereitschaft) VALUES (1, 1, 'ASPRIN OSCAR', 250, true);
INSERT INTO material (lagerstandort, materialtyp, bezeichnung, bestand, einsatzbereitschaft) VALUES (1, 2, 'AMERICAN', 300, false);
INSERT INTO veranstaltung_einheitentyp (veranstaltung, einheitentyp, bezeichnung) VALUES (1,1, '1');
