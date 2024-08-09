/**
 * jp.co.flm.market.logic.ShoppingCartLogic
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
import jp.co.flm.market.dao.MemberDAO;
import jp.co.flm.market.dao.ProductDAO;
import jp.co.flm.market.entity.Member;
import jp.co.flm.market.entity.Orders;
import jp.co.flm.market.entity.Product;

/**
 * @author gopal.balakumar
 *
 */
public class ShoppingCartLogic {

	/**
	 * @param memberId It used to check the member exists  in MEMBER table.
	 * @param password If used to check the member exists in MEMBER table.
	 * @return If member details available in MEMBER table .It will return member details.
	 * @throws MarketBusinessException If member and password mismatch it return with error message.
	 * @throws MarketSystemException If SQL exception occur it will return with error message.
	 */
	public Member getMember(String memberId, String password)
			throws MarketBusinessException, MarketSystemException {

		Connection con = null;
		Member member = null;

		try {
			con = ConnectionManager.getConnection();

			MemberDAO mdao = new MemberDAO(con);
			member = mdao.getMember(memberId, password);

			if (member == null) {
				throw new MarketBusinessException("MemberID or password is Different.");
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
		return member;
	}

	/**
	 * @param prodId It used to delete the product details from the orderList.
	 * @param orderList If product ID found the data will remove from the list.
	 * @return If product that perticular product ID removed from orderList. It will return the updated orderList data.
	 * @throws MarketBusinessException If the orderList is empty it will return the message "There is no item in the shopping cart."
	 */
	public ArrayList<Orders> delProductId(String prodId, ArrayList<Orders> orderList) throws MarketBusinessException {

		int rmIdx = 0;

		for (Orders order : orderList) {

			if (prodId.equals(getProductId(order.getProduct()))) {
				orderList.remove(rmIdx);
				if (orderList.isEmpty()) {
					throw new MarketBusinessException("There is no item in the shopping cart.");
				}
				return orderList;
			}
			rmIdx++;
		}

		return orderList;
	}

	/**
	 * @param prodId It is holding a collection productID to update the updateOrdersList data.
	 * @param quantity It is holding a collection quantity to update the updateOrdersList data based on product.
	 * @param updateOrdersList It used to hold the updated values of the amount,points and quantity.
	 * @return If all quantity,amount and points updated successfully in updateOrdersList. It will return the updateOrdersList data.
	 * @throws MarketBusinessException If the prodId,quantity,updateOrdersList of size is zero it will throw the error.
	 * @throws MarketSystemException if the prodId,quantity,updateOrdersList of size is mismatch it will throw the error.
	 */
	public ArrayList<Orders> updateCartData(String prodId[], int quantity[], ArrayList<Orders> updateOrdersList)
			throws MarketBusinessException, MarketSystemException {
		int upIdx = 0;

		if (updateOrdersList == null || prodId.length == 0 || quantity.length == 0) {
			throw new MarketBusinessException("There is no item in the shopping cart.");
		}
		if (updateOrdersList.size() != prodId.length
				|| updateOrdersList.size() != quantity.length
				|| prodId.length != quantity.length) {
			throw new MarketSystemException("Data mismatch");
		}

		for (Orders tempOrder : updateOrdersList) {
			for (int i = 0; i < prodId.length; i++) {
				int points = 0;
				int price = 0;
				if ((prodId[i].equals(getProductId(tempOrder.getProduct()))) &&
						(quantity[i]) != tempOrder.getQuantity()) {
					points = getProductPts(tempOrder.getProduct());
					price = getProductPrice(tempOrder.getProduct());

					tempOrder.setQuantity(quantity[i]);
					tempOrder.setSubTotal(quantity[i] * price);
					tempOrder.setSubTotalPoint(quantity[i] * points);
					updateOrdersList.set(upIdx, tempOrder);
				}
			}
			upIdx++;
		}
		return updateOrdersList;
	}

	/**
	 * @param orderList It used to update stock details in orderList.
	 * @return If the stock quantity set in orderList successfully. It will return orderList.
	 * @throws MarketBusinessException If the orderList size if zero or product not found in ProductDAO it will throw the error.
	 * @throws MarketSystemException If any SQL exception occur in ProductDAO. It will throw the error.
	 */
	public ArrayList<Orders> getStocks(ArrayList<Orders> orderList)
			throws MarketBusinessException, MarketSystemException {

		Connection con = null;
		Product product = null;
		int cnt = 0;

		try {
			con = ConnectionManager.getConnection();
			ProductDAO pdao = new ProductDAO(con);
			if (orderList.size() == 0) {
				throw new MarketBusinessException("There is no item in the shopping cart.");
			}
			for (Orders tempOrder : orderList) {

				product = pdao.getProduct(tempOrder.getProduct().getProductId());
				if (product == null) {
					throw new MarketBusinessException("Product is not found.");
				} else if (product.getStock().getQuantity() == 0) {
					orderList.remove(cnt);
					if (orderList.size() == 0) {
						throw new MarketBusinessException("There is no item in the shopping cart.");
					}
				} else {
					tempOrder.setProduct(product);
					orderList.set(cnt, tempOrder);
				}
				cnt++;
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
		return orderList;
	}

	/**
	 * @param tempProd It used to acquire the product object for productID.
	 * @return It will return productId.
	 */
	public String getProductId(Product tempProd) {
		return tempProd.getProductId();
	}

	/**
	 * @param tempProd It used to acquire the product object for  Points.
	 * @return It will return the points.
	 */
	public int getProductPts(Product tempProd) {
		return tempProd.getPoint();
	}

	/**
	 * @param tempProd It used to acquire the product object for  price.
	 * @return It will return points.
	 */
	public int getProductPrice(Product tempProd) {
		return tempProd.getPrice();
	}

}
