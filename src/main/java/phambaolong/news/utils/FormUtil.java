package phambaolong.news.utils;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class FormUtil {
	
	@SuppressWarnings("unchecked")
	public static <T> T toModel(HttpServletRequest request, Class<T> clazz) {
		Object object = null;
		try {
			object = clazz.newInstance();
			System.out.println(request.getParameterMap());
			BeanUtils.populate(object,	request.getParameterMap());
			return (T) object;
		} catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
