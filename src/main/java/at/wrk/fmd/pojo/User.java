package at.wrk.fmd.pojo;

import org.hibernate.validator.constraints.SafeHtml;

import at.wrk.fmd.constraint.ValidPassword;
import lombok.Data;

@Data
public class User {
    //@SafeHtml prevents XSS ( Cross-Site Scripting )
    @SafeHtml
    private String username;
    @ValidPassword
    private String password;
    private String anzeigename;
    private String dienstnummer;
    private long id;
    private Boolean isActive;
}