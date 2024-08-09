/**
 * @author Kevin
 * It will be used for product search screen
 *
 */
package jp.co.flm.market.dao;

import java.sql.Connection;
import java.util.ArrayList;

import jp.co.flm.market.entity.Category;

public class TestCategoryDAO_getCategory {

	public static void main(String[] args) throws Exception {

		Connection con = null;

		try {
			con = ConnectionManager.getConnection();

			CategoryDAO cdao = new CategoryDAO(con);

			ArrayList<Category> category = cdao.getCategory();

			if (category.size() == 0) {
				System.out.println("Categories are empty");
			}

			for (int i = 0; i < category.size(); i++) {
				System.out.println("Category ID: " + category.get(i).getCategoryId());
				System.out.println("Category Name: " + category.get(i).getCategoryName());
				System.out.println("Picture: " + category.get(i).getPicture());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}
}
