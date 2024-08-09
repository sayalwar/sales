/**
 * jp.co.flm.market.logic.MemberInfoLogic
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
import jp.co.flm.market.entity.Member;
import jp.co.flm.market.entity.Orders;

/**
 * @author Nilesh
 *
 */
public class MemberInfoLogic {

	/**
	 * @param memberId It having MemberId for verify user.
	 * @param password It having Password for verify user.
	 * @return If member and password match in DB will return member details.
	 * @throws MarketBusinessException If member and password is differnent. It will return error.
	 * @throws MarketSystemException If any SQL error occurs it will return with error message.
	 */
	public Member getMember(String memberId, String password)
			throws MarketBusinessException, MarketSystemException {

		Connection con = null;
		Member member = null;

		try {
			con = ConnectionManager.getConnection();

			// 会員ID、パスワードにより会員情報を検索する。
			MemberDAO mdao = new MemberDAO(con);
			member = mdao.getMember(memberId, password);
			if (member == null) {
				// 認証に失敗した場合、エラーを発生させる。
				throw new MarketBusinessException("MemberID or password is Different.");
			}
			//nilesh password casesensitive check STARTS
			if (!(password.equals(member.getPassword()))) {
				// 認証に失敗した場合、エラーを発生させる。
				member.setPassword(null);
				throw new MarketBusinessException("Password is Different.");
			}

		} catch (SQLException e) {
			throw new MarketSystemException("System Error. Contact System Adminstrator.");

		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					throw new MarketSystemException("System Error. Contact system adminstrator.");
				}
			}
		}

		return member;
	}

	/**
	 * @param memberId
	 * @return
	 * @throws MarketSystemException
	 */
	public ArrayList<Orders> getOrderList(String memberId)
			throws MarketSystemException {

		Connection con = null;
		ArrayList<Orders> orderList = null;

		try {
			con = ConnectionManager.getConnection();

			// 会員IDにより購入履歴情報を検索する。
			OrdersDAO odao = new OrdersDAO(con);
			orderList = odao.getOrderList(memberId);

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

}