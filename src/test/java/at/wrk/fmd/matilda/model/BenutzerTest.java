package at.wrk.fmd.matilda.model;

import lombok.Data;

@Data 
public class BenutzerTest {
    private Long id;
    private String username;
    private String passwort;
    private String anzeigename;
    private String dienstnummer;
    
    public BenutzerTest(Long id, String username, String passwort, String anzeigename, String dienstnummer) {
        super();
        this.id = id;
        this.username = username;
        this.passwort = passwort;
        this.anzeigename = anzeigename;
        this.dienstnummer = dienstnummer;
    }
}
