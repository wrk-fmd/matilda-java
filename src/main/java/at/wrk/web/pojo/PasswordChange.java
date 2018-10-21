package at.wrk.web.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordChange {
	private String username;
	private String password;
	private boolean isChecked;
}