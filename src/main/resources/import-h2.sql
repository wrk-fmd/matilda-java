-- Rollen
INSERT INTO rolle(created_at, bezeichnung) VALUES (now(), 'ADMIN');
INSERT INTO rolle(created_at, bezeichnung) VALUES (now(), 'SUPERVISOR');
INSERT INTO rolle(created_at, bezeichnung) VALUES (now(), 'BENUTZER');
-- Rollen

INSERT INTO benutzer(created_at, anzeigename, benutzername, dienstnummer, active, passwort) VALUES (now(), 'ADMIN', 'ADMIN', 'ADMIN', true, '$2a$10$eoJXaB5sKquqydjBbkxiHOIT7iowKUI6A2HUfpyYTrwaibJ5SfPFW');
INSERT INTO benutzer_rolle(benutzer_id,rolle_id) VALUES (1,1);

INSERT INTO benutzer(created_at, anzeigename, benutzername, dienstnummer, active, passwort) VALUES (now(), 'Bernd', 'Bernd', 'Bernd', false, '$2a$10$eoJXaB5sKquqydjBbkxiHOIT7iowKUI6A2HUfpyYTrwaibJ5SfPFW');
INSERT INTO benutzer_rolle(benutzer_id,rolle_id) VALUES (2,1);

-- TEST -----------------------------

-- Lagerstandort
INSERT INTO lagerstandort (name,adresse,benutzer) VALUES ('Wien-Kenyongasse','Kenyongasse 100',1);

-- Einheitentyp
INSERT INTO einheitentyp (name) VALUES ('TYP 1');

-- Materialtyp
INSERT INTO materialtyp (name,menge) VALUES ('Asprin','Stuck');

-- Materialtyp_Einheitentyp
INSERT INTO materialtyp_einheitentyp (manzahl, materialtyp, einheitentyp) VALUES (25, 1 , 1);

-- Mitarbeitertyp
INSERT INTO mitarbeitertyp (kuerzel,name) VALUES ('Team 1','Team 1');

-- Material
INSERT INTO material (lagerstandort, materialtyp, bezeichnung, bestand, einsatzbereitschaft) VALUES (1, 1, 'ASPIRIN BAYER', 100, true);

-- Veranstaltung
INSERT INTO veranstaltung (name, beginn, ende, zustand,lagerstandort) VALUES ('Wien 2018', '2018-11-04', '2018-11-05', 'In Bearbeitung',1);

-- Veranstaltung_Einheitentyp
INSERT INTO veranstaltung_einheitentyp (veranstaltung, einheitentyp, bezeichnung) VALUES (1,1, '1');