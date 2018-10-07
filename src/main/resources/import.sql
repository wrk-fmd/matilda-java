-- Rollen
INSERT INTO rolle(created_at, bezeichnung) VALUES (now(), 'ADMIN');
INSERT INTO rolle(created_at, bezeichnung) VALUES (now(), 'SUPERVISOR');
INSERT INTO rolle(created_at, bezeichnung) VALUES (now(), 'BENUTZER');
-- Rollen

INSERT INTO benutzer(created_at, anzeigename, benutzername, dienstnummer, passwort) VALUES (now(), 'ADMIN', 'ADMIN', 'ADMIN', '$2a$10$eoJXaB5sKquqydjBbkxiHOIT7iowKUI6A2HUfpyYTrwaibJ5SfPFW');
INSERT INTO benutzer_rolle(benutzer_id,rolle_id) VALUES (1,1);

-- TEST -----------------------------

INSERT INTO veranstaltung (name,zustand) VALUES ('Wien 2012','Frei');
INSERT INTO veranstaltung (name,zustand) VALUES ('Steiermark 2012','Frei');
INSERT INTO einheitentyp (name) VALUES ('TRI');
INSERT INTO materialtyp (name,menge) VALUES ('Asprin','Stuck');
INSERT INTO mitarbeitertyp (kuerzel,name) VALUES ('ZYZ','Zebra Y Zebra');
INSERT INTO lagerstandort (name,adresse,benutzer) VALUES ('Wien-Kenyongasse','Kenyongasse 100',1);