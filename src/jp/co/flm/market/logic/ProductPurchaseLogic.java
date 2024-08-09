/**
 * jp.co.flm.market.logic.ProductPurchaseLogic
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
import jp.co.flm.market.dao.OrdersDAO;
import jp.co.flm.market.dao.StockDAO;
import jp.co.flm.market.entity.Member;
import jp.co.flm.market.entity.Orders;

/**
 * @author Pragati
 *
 */
public class ProductPurchaseLogic {

	/**
	 * @param creditCardNo It used to set the credit data to orderList
	 * @param member It used to set the memberId to oderList.
	 * @param updateCardData Its having the collection Order data. Credit card data and member Id will set in each orders.
	 * @return If the updateCartData updated successfully . Will return the updateCardData.
	 * @throws MarketBusinessException
	 * @throws MarketSystemException
	 */
	public ArrayList<Orders> setCreditCard(String creditCardNo, Member member, ArrayList<Orders> updateCardData) throws MarketBusinessException, MarketSystemException {

		int upIdx = 0;
		int num = creditCardNo.length() - 4;
		String cardNo = creditCardNo.substring(num);
		String memberId = member.getMemberId();

		if (updateCardData.isEmpty() || creditCardNo.length() == 0) {
			throw new MarketBusinessException("There is no item in the shopping cart.");
		}

		if (updateCardData == null  || member == null) {
			throw new MarketSystemException("The session has been disabled. Try the operation again from the top screen.");
		}

		for (Orders tempOrder : updateCardData) {
			tempOrder.setMemberId(memberId);
			tempOrder.setCreditCardId(cardNo);
			updateCardData.set(upIdx, tempOrder);
			upIdx++;
		}

		return updateCardData;
	}

	/**
	 * @param orderList It will having the order information to insert the data in ORDERS table.
	 * @param member It will having member information to update the MEMBER table.
	 * @return If the order details updated in STOCK/ORDER/MEMBER table successfully it will return TRUE.
	 * @throws MarketSystemException If the orderList is empty or purchase quantity greater than stock. It will return with error message.
	 * @throws MarketBusinessException If any SQL exception occur it will return with error message.
	 */
	public boolean setPurchase(ArrayList<Orders> orderList, Member member)
			throws MarketSystemException, MarketBusinessException {

		boolean status = true;

		Connection con = null;

		if (orderList.size() == 0) {
			throw new MarketBusinessException("Product Already Purchased");
		}
		try {
			con = ConnectionManager.getConnection();
			StockDAO sdao = new StockDAO(con);
			con.setAutoCommit(false);

			status = sdao.setStock(orderList);
			if (status == false) {
				con.rollback();
				throw new MarketBusinessException("Unable to place order due to product Inavailability");
			}

			OrdersDAO orddao = new OrdersDAO(con);
			status = orddao.setOrder(orderList);
			if (status == false) {
				con.rollback();
				throw new MarketSystemException("System Error.Contact your system adminstrator.");
			}
			int totalPoint = 0;
			for (int i = 0; i < orderList.size(); i++) {
				totalPoint += orderList.get(i).getSubTotalPoint();
			}
			MemberDAO mdao = new MemberDAO(con);

			status = mdao.setMember(member, totalPoint);

			if (status == false) {
				con.rollback();
				throw new MarketSystemException("System Error. Contact System Adminstrator.");
			}
		} catch (SQLException e) {
			throw new MarketSystemException("System Error. Contact System Adminstrator.");
		} finally {
			if (con != null) {
				try {
					con.commit();
					con.close();
				} catch (SQLException e) {
					throw new MarketSystemException("System Error. Contact System Adminstrator.");
				}
			}
		}
		return status;

	}
}
