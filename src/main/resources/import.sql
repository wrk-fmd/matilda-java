-- Rollen
INSERT INTO rolle(created_at, bezeichnung) VALUES (now(), 'ADMIN');
INSERT INTO rolle(created_at, bezeichnung) VALUES (now(), 'SUPERVISOR');
INSERT INTO rolle(created_at, bezeichnung) VALUES (now(), 'BENUTZER');
-- BENUTZER

INSERT INTO benutzer(created_at, anzeigename, benutzername, dienstnummer, active, passwort) VALUES (now(), 'ADMIN', 'ADMIN', 'ADMIN', true, '$2a$10$eoJXaB5sKquqydjBbkxiHOIT7iowKUI6A2HUfpyYTrwaibJ5SfPFW');
INSERT INTO benutzer_rolle(benutzer_id,rolle_id) VALUES (1,1);

INSERT INTO benutzer(created_at, anzeigename, benutzername, dienstnummer, active, passwort) VALUES (now(), 'Bernd', 'Bernd', 'Bernd', false, '$2a$10$eoJXaB5sKquqydjBbkxiHOIT7iowKUI6A2HUfpyYTrwaibJ5SfPFW');
INSERT INTO benutzer_rolle(benutzer_id,rolle_id) VALUES (2,1);

INSERT INTO benutzer(created_at, anzeigename, benutzername, dienstnummer, active, passwort) VALUES (now(), 'matthias', 'matthias', 'matthias', true, '$2a$10$eoJXaB5sKquqydjBbkxiHOIT7iowKUI6A2HUfpyYTrwaibJ5SfPFW');
INSERT INTO benutzer_rolle(benutzer_id,rolle_id) VALUES (3,3);

UPDATE benutzer SET active = false WHERE id = 2 ;




-- TEST -----------------------------

INSERT INTO lagerstandort (name,adresse,benutzer) VALUES ('Wien-Kenyongasse','Kenyongasse 100',1);
INSERT INTO lagerstandort (name,adresse,benutzer) VALUES ('Wien-Karl-Popperstraße','Karl-Popper-Straße 10',1);
INSERT INTO lagerstandort (name,adresse,benutzer) VALUES ('Graz-Rennweg','Robertgasse 120',1);
INSERT INTO lagerstandort (name,adresse,benutzer) VALUES ('Salzburg-Hellbrunn','Hellbrunnerstraße 230',1);

INSERT INTO einheitentyp (name) VALUES ('TYP 1');
INSERT INTO einheitentyp (name) VALUES ('TYP 2');
INSERT INTO einheitentyp (name) VALUES ('TYP 3');
INSERT INTO einheitentyp (name) VALUES ('TYP 4');
INSERT INTO einheitentyp (name) VALUES ('TYP 5');

INSERT INTO materialtyp (name,menge) VALUES ('Asprin','Stuck');
INSERT INTO materialtyp (name,menge) VALUES ('Antibiotika','Stuck');
INSERT INTO materialtyp (name,menge) VALUES ('Zelt','Stuck');
INSERT INTO materialtyp (name,menge) VALUES ('Verband','Stuck');
INSERT INTO materialtyp (name,menge) VALUES ('Heftpflaster','Stuck');
INSERT INTO materialtyp (name,menge) VALUES ('Rettungsdecken','Stuck');
INSERT INTO materialtyp (name,menge) VALUES ('Halskrause','Stuck');
INSERT INTO materialtyp (name,menge) VALUES ('Blutdruckmessgerät','Stuck');
INSERT INTO materialtyp (name,menge) VALUES ('Pulsoximeter','Stuck');

INSERT INTO materialtyp_einheitentyp (manzahl, materialtyp, einheitentyp) VALUES (25, 1 , 1);
INSERT INTO materialtyp_einheitentyp (manzahl, materialtyp, einheitentyp) VALUES (50, 2 , 1);
INSERT INTO materialtyp_einheitentyp (manzahl, materialtyp, einheitentyp) VALUES (1, 3 , 1);
INSERT INTO materialtyp_einheitentyp (manzahl, materialtyp, einheitentyp) VALUES (1, 3 , 2);
INSERT INTO materialtyp_einheitentyp (manzahl, materialtyp, einheitentyp) VALUES (3, 6 , 2);
INSERT INTO materialtyp_einheitentyp (manzahl, materialtyp, einheitentyp) VALUES (20, 2 , 2);
INSERT INTO materialtyp_einheitentyp (manzahl, materialtyp, einheitentyp) VALUES (5, 9 , 2);
INSERT INTO materialtyp_einheitentyp (manzahl, materialtyp, einheitentyp) VALUES (55, 1 , 3);

INSERT INTO mitarbeitertyp (kuerzel,name) VALUES ('Team 1','Team 1');
INSERT INTO mitarbeitertyp (kuerzel,name) VALUES ('Team 2','Team 2');
INSERT INTO mitarbeitertyp (kuerzel,name) VALUES ('Team 3','Team 3');
INSERT INTO mitarbeitertyp (kuerzel,name) VALUES ('Team 4','Team 4');
INSERT INTO mitarbeitertyp (kuerzel,name) VALUES ('Team 5','Team 5');

INSERT INTO material (lagerstandort, materialtyp, bezeichnung, bestand, einsatzbereitschaft) VALUES (1, 1, 'ASPRIN BAYER', 100, true);
INSERT INTO material (lagerstandort, materialtyp, bezeichnung, bestand, einsatzbereitschaft) VALUES (1, 1, 'ASPRIN OSCAR', 250, true);
INSERT INTO material (lagerstandort, materialtyp, bezeichnung, bestand, einsatzbereitschaft) VALUES (1, 2, 'Levofloxacin USA', 300, false);
INSERT INTO material (lagerstandort, materialtyp, bezeichnung, bestand, einsatzbereitschaft) VALUES (1, 2, 'Levofloxacin DE', 110, true);
INSERT INTO material (lagerstandort, materialtyp, bezeichnung, bestand, einsatzbereitschaft) VALUES (1, 3, '100X200X300', 10, true);
INSERT INTO material (lagerstandort, materialtyp, bezeichnung, bestand, einsatzbereitschaft) VALUES (2, 1, 'ASPRIN BAYER', 50, true);
INSERT INTO material (lagerstandort, materialtyp, bezeichnung, bestand, einsatzbereitschaft) VALUES (2, 3, '200X200X300', 11, true);
INSERT INTO material (lagerstandort, materialtyp, bezeichnung, bestand, einsatzbereitschaft) VALUES (2, 3, '100X200X300', 1, false);
INSERT INTO material (lagerstandort, materialtyp, bezeichnung, bestand, einsatzbereitschaft) VALUES (2, 4, 'MedCA', 1100, true);
INSERT INTO material (lagerstandort, materialtyp, bezeichnung, bestand, einsatzbereitschaft) VALUES (2, 7, 'CE- und FDA-konform', 25, true);
INSERT INTO material (lagerstandort, materialtyp, bezeichnung, bestand, einsatzbereitschaft) VALUES (2, 8, 'Braun-Icheck', 7, true);
INSERT INTO material (lagerstandort, materialtyp, bezeichnung, bestand, einsatzbereitschaft) VALUES (3, 6, 'CE- und FDA-konform', 3, false);
INSERT INTO material (lagerstandort, materialtyp, bezeichnung, bestand, einsatzbereitschaft) VALUES (3, 2, 'Bayer', 701, true);

INSERT INTO veranstaltung (name, beginn, ende, zustand,lagerstandort) VALUES ('Wien 2018', '2018-11-04T00:00', '2018-11-05T23:59', 'In Bearbeitung',1);
INSERT INTO veranstaltung (name, beginn, ende, zustand,lagerstandort) VALUES ('München 2018', '2018-11-03T00:00', '2018-11-04T23:59', 'In Bearbeitung',1);
INSERT INTO veranstaltung (name, beginn, ende, zustand,lagerstandort) VALUES ('LA 2018', '2018-11-06T00:00', '2018-11-07T23:59', 'In Bearbeitung',1);
INSERT INTO veranstaltung (name, beginn, ende, zustand,lagerstandort) VALUES ('Steiermark 2018', '2018-12-04T08:00', '2018-12-04T17:59', 'In Bearbeitung',1);
INSERT INTO veranstaltung (name, beginn, ende, zustand,lagerstandort) VALUES ('Burgenland 2019', '2019-01-04T00:00', '2019-01-07T23:59', 'In Bearbeitung',1);

INSERT INTO veranstaltung_einheitentyp (veranstaltung, einheitentyp, bezeichnung) VALUES (1,1, '1');
INSERT INTO veranstaltung_einheitentyp (veranstaltung, einheitentyp, bezeichnung) VALUES (2,1, '1');
INSERT INTO veranstaltung_einheitentyp (veranstaltung, einheitentyp, bezeichnung) VALUES (3,1, '1');
