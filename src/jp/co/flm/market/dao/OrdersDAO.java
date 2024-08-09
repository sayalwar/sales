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

import jp.co.flm.market.entity.Orders;
import jp.co.flm.market.entity.Product;

/**
 * @author sayalwar.nilesh
 * @version 1.0 2023/01/13
 *
 */

public class OrdersDAO {
	private Connection con;

	public OrdersDAO(Connection con) {
		this.con = con;
	}

	/**
	 * @param memberId It is of type String ,used to get the member orderhistory on the basis of Memberid
	 * @return orderList It is of type  ArrayList<Orders> will return order list if there is any orderhistory else Null
	 * @throws SQLException
	 */
	public ArrayList<Orders> getOrderList(String memberId) throws SQLException {
		// 戻り値の準備
		ArrayList<Orders> orderList = new ArrayList<Orders>();

		// SQL文の準備
		String sql = "SELECT orderid, memberid, orderdate, product.productid, product.productname, "
				+ "quantity, orders.price, orders.point, quantity * orders.price AS subtotal, quantity * orders.point AS subtotalpoint "
				+ "FROM orders INNER JOIN product ON orders.productid=product.productid "
				+ "WHERE memberid=? ORDER BY orderid DESC";
		PreparedStatement stmt = null;
		ResultSet res = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, memberId);

			res = stmt.executeQuery();

			while (res.next()) {
				Orders order = new Orders();
				order.setOrderId(res.getInt("orderid"));
				order.setMemberId(res.getString("memberid"));
				order.setOrderDate(res.getString("orderdate").substring(0, 10));

				Product product = new Product();
				product.setProductId(res.getString("productid"));
				product.setProductName(res.getString("productname"));
				product.setPrice(res.getInt("price"));
				product.setPoint(res.getInt("point"));
				order.setProduct(product);

				order.setQuantity(res.getInt("quantity"));
				order.setSubTotal(res.getInt("subtotal"));
				order.setSubTotalPoint(res.getInt("subtotalpoint"));

				orderList.add(order);
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
		return orderList;
	}

	//Pragati Update Starts
	/**
	 * @param orderList It is a type of ArrayList<Orders> which is passed for Updating the Order Databases, it is having
	 * memberid,orderdate,creditcardid,productid,quantity,price,point
	 * @return	flag Its is of type boolean ,returns True When Order list is updated in the Order Data base.
	 * @throws SQLException
	 */
	public boolean setOrder(ArrayList<Orders> orderList) throws SQLException {
		// SQL文の準備
		PreparedStatement stmt = null;
		int result = 0;
		boolean flag = false;
		String sql = "INSERT into ORDERS(memberid,orderdate,creditcardid,productid,quantity,price,point) VALUES(?, now(),?,?,?,?,?)";

		for (Orders tempOrder : orderList) {
			try {
				stmt = con.prepareStatement(sql);
				stmt.setString(1, tempOrder.getMemberId());
				stmt.setString(2, tempOrder.getCreditCardId());
				stmt.setString(3, tempOrder.getProduct().getProductId());
				stmt.setInt(4, tempOrder.getQuantity());
				stmt.setInt(5, tempOrder.getProduct().getPrice());
				stmt.setInt(6, tempOrder.getProduct().getPoint());
				result = stmt.executeUpdate();
				if (result != 1) {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			} finally {
				if (result != 0) {
					stmt.close();
				}
				flag = true;
			}
		}
		return flag;
	}
	//Pragati Update Ends
}