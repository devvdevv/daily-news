package phambaolong.news.utils;

public class PagingUtil {
	
	public static int getTotalPages(Integer totalItems, Integer itemsOnPage) {
		int totalPages = (int) Math.ceil((double) (totalItems / itemsOnPage));
		if (totalPages == 0) {
			if (totalItems > 0) {
				return 1;
			} else {
				return totalPages;
			}
		}
		return totalPages;		
	}
}
