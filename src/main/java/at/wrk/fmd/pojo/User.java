package at.wrk.fmd.pojo;

import org.hibernate.validator.constraints.SafeHtml;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    //@SafeHtml prevents XSS ( Cross-Site Scripting )
    @SafeHtml
    private String username;
    private String password;
    private String anzeigename;
    private String dienstnummer;
    private long id;
}