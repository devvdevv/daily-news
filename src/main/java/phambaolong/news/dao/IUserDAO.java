package phambaolong.news.dao;

import phambaolong.news.model.UserModel;

public interface IUserDAO {
	UserModel findBy_username_password_status(String username, String password, int status);
}
