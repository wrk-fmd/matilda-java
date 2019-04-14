package at.wrk.fmd.matilda.pojo;

import at.wrk.fmd.matilda.constraint.ValidPassword;
import org.hibernate.validator.constraints.SafeHtml;

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