package phambaolong.news.service;

import phambaolong.news.model.UserModel;

public interface IUserService {
	UserModel findBy_username_password_status(String username, String password, int status);
}
