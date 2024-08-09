/**
 * jp.co.flm.market.dao.OrdersDAO
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
import jp.co.flm.market.entity.Product;
import jp.co.flm.market.entity.Stock;

/**
 * @author sayalwar.nilesh
 * 	 *@version 1.0 2023/01/13
 *
 */
public class ProductDAO {

	private Connection con;

	public ProductDAO(Connection con) {
		this.con = con;
	}

	//nilesh kevin
	/**
	 * @param categoryId It is of Type String Used to get the Product list from database on if any present
	 * @return  productList is of type ArrayList<Product> which if\s getting from the database on the basis of Categoryid ,
	 * It will be Null if there is no Orderhistory present.
	 * @throws SQLException
	 */
	public ArrayList<Product> getProductList(String categoryId) throws SQLException {

		String sql = "SELECT product.productid,categoryid,productname,price,stock.quantity FROM product INNER JOIN stock ON product.productid=stock.productid WHERE categoryid=?";
		PreparedStatement stmt = null;
		ResultSet res = null;
		ArrayList<Product> productList = new ArrayList<Product>();

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, categoryId);
			res = stmt.executeQuery();

			while (res.next()) {
				Product product = new Product();
				Stock stock = new Stock();
				Category category = new Category();
				product.setProductId(res.getString("productid"));
				product.setProductName(res.getString("productname"));
				product.setPrice(res.getInt("price"));

				category.setCategoryId(res.getString("categoryid"));
				stock.setQuantity(res.getInt("quantity"));
				product.setStock(stock);
				productList.add(product);
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

		return productList;
	}

	//nilesh kevin end
	// for detail
	//nilesh starts for detail button //kevin addto cart will use same dao
	/**
	 * @param productId It is Of type String used to get the Product infromation from the databases.
	 * @return product  It is of Type Product ,Used to get the particular prodcut from the databases on the basis of the Productid, it is having
	 * productid,productname,price,point,picture,quantity,and stock quantity .It will be Null if product data is not there.
	 * @throws SQLException
	 */
	public Product getProduct(String productId) throws SQLException {

		String sql = "SELECT product.productid,product.productname,product.price,product.point,product.picture,stock.quantity From product INNER JOIN stock ON product.productid=stock.productid where product.productid =? ";
		PreparedStatement stmt = null;
		ResultSet res = null;
		Product product = new Product();
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, productId);
			res = stmt.executeQuery();
			if (res.next()) {
				Stock stock = new Stock();
				//		Category category=new Category();
				product.setProductId(res.getString("productid"));
				product.setProductName(res.getString("productname"));
				product.setPrice(res.getInt("price"));
				product.setPicture(res.getString("picture"));
				product.setPoint(res.getInt("point"));
				stock.setQuantity(res.getInt("quantity"));
				product.setStock(stock);
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
		return product;
	}
	//Nilesh ends for detial button
}
