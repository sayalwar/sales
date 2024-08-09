/**
 * jp.co.flm.market.logic.ProductSearchInfoLogic
 *
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */
package jp.co.flm.market.logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jp.co.flm.market.common.MarketBusinessException;
import jp.co.flm.market.common.MarketSystemException;
import jp.co.flm.market.dao.ConnectionManager;
import jp.co.flm.market.dao.ProductDAO;
import jp.co.flm.market.entity.Product;

/**
 * @author kevin
 *
 */
public class ProductSearchInfoLogic {

	// for all products where category id is matching
	/**
	 * @param categoryId It used to get the product list basced on catagory ID
	 * @return If all product details acquired successfully will return collection of product list.
	 * @throws MarketBusinessException If the catagory list not available for that catagory. It will throw error.
	 * @throws MarketSystemException If the SQL exception occur while acquire product data .It will throw.
	 */
	public ArrayList<Product> getProductList(String categoryId)
			throws MarketBusinessException, MarketSystemException {

		Connection con = null;
		ArrayList<Product> productList = null;
		try {
			con = ConnectionManager.getConnection();

			ProductDAO pdao = new ProductDAO(con);
			productList = pdao.getProductList(categoryId);
			if (productList == null) {
				throw new MarketBusinessException("Category is Empty.");
			}

		} catch (SQLException e) {
			throw new MarketSystemException("System Error. Contact System Adminstrator.\"");
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					throw new MarketSystemException("System Error. Contact System Adminstrator.\"");
				}
			}
		}

		return productList;
	}

	//for detail by NIlesh
	//nilesh update starts detial
	/**
	 * @param productId It used to get the product details on basis of the product ID.
	 * @return If product details available in databases it will return the product details.
	 * @throws MarketBusinessException If no prodct found .It will throw the error message.
	 * @throws MarketSystemException If any SQL exception occur while acquire product data it will throw the error message.
	 */
	public Product getProduct(String productId)
			throws MarketBusinessException, MarketSystemException {

		Connection con = null;
		Product product = null;
		try {
			con = ConnectionManager.getConnection();

			ProductDAO pdao = new ProductDAO(con);
			product = pdao.getProduct(productId);
			if (product == null) {
				throw new MarketBusinessException("Product is not found.");
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
		return product;
	}
	//Nilesh updated end 	detail
}
