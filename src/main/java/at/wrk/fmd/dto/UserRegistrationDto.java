package at.wrk.fmd.dto;

import java.util.Collection;
import javax.validation.constraints.AssertTrue;
import org.hibernate.validator.constraints.NotEmpty;
import at.wrk.fmd.constraint.ValidPassword;
import at.wrk.fmd.model.Rolle;

//@FieldMatch.List({
//        @FieldMatch(first = "passwort", second = "konfPasswort", message = "Das Passwort muss übereinstimmen!"),
//        @FieldMatch(first = "benutzername", second = "konfBenutzername", message = "Der Benutzername muss übereinstimmen!")
//})
public class UserRegistrationDto {

    @NotEmpty
    private String anzeigename;

    @NotEmpty
    @ValidPassword
    private String passwort;

    @NotEmpty
    @ValidPassword
    private String konfPasswort;

    @NotEmpty
    private String benutzername;

    @NotEmpty
    private String konfBenutzername;

    private String dienstnummer;

    @NotEmpty
    private Collection<Rolle> rollen;

    @AssertTrue
    private Boolean terms;

    public String getAnzeigename() {
        return anzeigename;
    }

    public String getPasswort() {
        return passwort;
    }

    public String getKonfPasswort() {
        return konfPasswort;
    }

    public String getBenutzername() {
        return benutzername;
    }

    public String getKonfBenutzername() {
        return konfBenutzername;
    }

    public String getDienstnummer() {
        return dienstnummer;
    }

    public Boolean getTerms() {
        return terms;
    }

    public void setAnzeigename(String anzeigename) {
        this.anzeigename = anzeigename;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public void setKonfPasswort(String konfPasswort) {
        this.konfPasswort = konfPasswort;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public void setKonfBenutzername(String konfBenutzername) {
        this.konfBenutzername = konfBenutzername;
    }

    public void setDienstnummer(String dienstnummer) {
        this.dienstnummer = dienstnummer;
    }

    public void setTerms(Boolean terms) {
        this.terms = terms;
    }

    public Collection<Rolle> getRollen() {
        return rollen;
    }

    public void setRollen(Collection<Rolle> rollen) {
        this.rollen = rollen;
    }
}