package phambaolong.news.utils;

import java.util.ResourceBundle;

public class MessageUtil {
	
	private static final String INVALID = "invalid";
	private static final String NO_PERMISSON = "no_permission";
	private static final String LOGIN_NEEDED = "login_needed";
	private static final String DELETE_SUCESS = "detele_success"; 
	private static final String CREATE_SUCESS = "create_success"; 
	private static final String UPDATE_SUCESS = "update_success";
	
	
	public static String getMessage(String message) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
		if (message == null) {
			return null;
		}
		switch (message) {
		case INVALID: return resourceBundle.getString(INVALID);
		case NO_PERMISSON: return resourceBundle.getString(NO_PERMISSON);
		case LOGIN_NEEDED: return resourceBundle.getString(LOGIN_NEEDED);
		case CREATE_SUCESS: return resourceBundle.getString(CREATE_SUCESS);
		case UPDATE_SUCESS: return resourceBundle.getString(UPDATE_SUCESS);
		case DELETE_SUCESS: return resourceBundle.getString(DELETE_SUCESS);
		default: return null;
		}
	}
}
