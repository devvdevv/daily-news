package phambaolong.news.service.impl;

import javax.inject.Inject;

import phambaolong.news.dao.IUserDAO;
import phambaolong.news.model.UserModel;
import phambaolong.news.service.IUserService;

public class UserService implements IUserService {
	
	@Inject
	private IUserDAO userDAO;

	@Override
	public UserModel findBy_username_password_status(String username, String password, int status) {
		return userDAO.findBy_username_password_status(username, password, status);
	}

}
