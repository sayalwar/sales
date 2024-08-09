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

public class StockDAO {
	private Connection con;

	/**
	 * @author sayalwar.nilesh
	 * 	 *@version 1.0 2023/01/13
	 *
	 */
	public StockDAO(Connection con) {
		this.con = con;
	}

	//Pragati starts
	/**
	 * @param orderList It is of type Orderlist, we are sending the orderlist to update the Quantity purchased ,Stock quantity will be reduced on
	 * the basis of purchases quantity, It is having Productid Product quantity
	 * @return flag it is of Boolean if the data is succesfully updated it will return True else False.
	 * @throws SQLException
	 */
	public boolean setStock(ArrayList<Orders> orderList) throws SQLException {

		//SQL文の準備
		boolean flag = false;
		String selectQry = "SELECT quantity from STOCK WHERE productid=? for update";
		String updateQry = "UPDATE stock SET quantity = ? WHERE productid=?";

		for (Orders tempOrder : orderList) {
			int tempQty = tempOrder.getQuantity();
			String tempProd = tempOrder.getProduct().getProductId();
			PreparedStatement stmt = null;
			ResultSet res = null;

			try {
				stmt = con.prepareStatement(selectQry);
				stmt.setString(1, tempProd);
				res = stmt.executeQuery();
				if (res.next()) {
					int totQuantity = res.getInt("quantity");

					if (totQuantity < tempQty) {
						return false;
					} else {

						if (stmt != null) {
							stmt.close();
						}
						stmt = con.prepareStatement(updateQry);
						stmt.setInt(1, totQuantity - tempQty);
						stmt.setString(2, tempProd);
						int result = stmt.executeUpdate();

						if (result == 0) {
							throw new SQLException("product is not found");
						}
					}

				} else {
					throw new SQLException("product is not found");
				}
				flag = true;
			}

			catch (SQLException e) {
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

		}
		return flag;
	}
	//Pragati ends
}
