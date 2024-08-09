/**
 * jp.co.flm.market.dao.CategoryDAO
 *
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */

package jp.co.flm.market.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import jp.co.flm.market.entity.Category;

/**
 * @author Kevin
 *@version 1.0 2023/01/13
 */
public class CategoryDAO {

	private Connection con;

	public CategoryDAO(Connection con) {
		this.con = con;
	}

	/**
	 *
	 * @return categoryList ArrayList<Category>
	 * @throws SQLException
	 */
	public ArrayList<Category> getCategory() throws SQLException {

		String sql = "select * from category";
		PreparedStatement stmt = null;
		ResultSet res = null;
		ArrayList<Category> categoryList = new ArrayList<Category>();

		try {
			stmt = con.prepareStatement(sql);
			res = stmt.executeQuery();

			while (res.next()) {
				// 会員情報を作成する。
				Category category = new Category();
				category.setCategoryId(res.getString(1));
				category.setCategoryName(res.getString(2));
				category.setPicture(res.getString(3));
				categoryList.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (res != null) {
				res.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		return categoryList;
	}

}