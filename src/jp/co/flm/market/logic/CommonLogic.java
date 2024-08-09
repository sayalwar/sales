/**
 * jp.co.flm.market.logic.CommonLogic
 *
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */

package jp.co.flm.market.logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jp.co.flm.market.common.MarketBusinessException;
import jp.co.flm.market.common.MarketSystemException;
import jp.co.flm.market.dao.CategoryDAO;
import jp.co.flm.market.dao.ConnectionManager;
import jp.co.flm.market.entity.Category;

/**
 * @author Kevin
 *
 */
public class CommonLogic {
	/**
	 * @return It acquire collection catagory and return to back
	 * @throws MarketBusinessException If catagoryList is empty it will return with error message..
	 * @throws MarketSystemException If any SQL error occur. It will return error message
	 */
	public ArrayList<Category> getCategory()
			throws MarketBusinessException, MarketSystemException {

		Connection con = null;
		ArrayList<Category> categorylist = null;
		try {
			con = ConnectionManager.getConnection();

			CategoryDAO cdao = new CategoryDAO(con);
			categorylist = cdao.getCategory();
			if (categorylist == null) {
				throw new MarketBusinessException("Category is empty");
			}

		} catch (SQLException e) {
			throw new MarketSystemException("System Error. Contact System Adminstrator.");
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					throw new MarketSystemException("System Error. Contact System Adminstrator.");
				}
			}
		}

		return categorylist;
	}

}