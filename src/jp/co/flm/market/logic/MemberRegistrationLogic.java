/**
 * jp.co.flm.market.logic.MemberRegistrationLogic
 *
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */
package jp.co.flm.market.logic;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.flm.market.common.MarketBusinessException;
import jp.co.flm.market.common.MarketSystemException;
import jp.co.flm.market.dao.ConnectionManager;
import jp.co.flm.market.dao.MemberDAO;
import jp.co.flm.market.entity.Member;

/**
 * @author Parameshwari
 *
 */
public class MemberRegistrationLogic {

	/**
	 * @param email It will holding the memberId for validate the member exists or not.
	 * @return If the given memberId not available in database will return TRUE
	 * @throws MarketBusinessException If MemberId exists in DB in it will return with error message.
	 * @throws MarketSystemException If any SQL error occurs it will return with error message.
	 */
	public boolean checkMemberId(String email) throws MarketBusinessException, MarketSystemException {

		Connection con = null;
		boolean flag = false;

		try {
			con = ConnectionManager.getConnection();
			MemberDAO mdao = new MemberDAO(con);
			flag = mdao.checkMemberId(email);

			if (flag == false) {
				throw new MarketBusinessException("Email Already exists. Please enter new Email-ID.");
			}

		} catch (SQLException e) {
			throw new MarketSystemException("Contact System Administrator.");
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					throw new MarketSystemException("Contact System Administrator.");
				}
			}
		}
		return flag;
	}

	/**
	 * @param member It will having new member information for register in database.
	 * @return If the member data registered successfully True will return.
	 * @throws MarketBusinessException If unable to register data it will return with error message.
	 * @throws MarketSystemException If any SQL exception occur it will return with error message.
	 */
	public boolean memberRegistration(Member member) throws MarketBusinessException, MarketSystemException {
		Connection con = null;
		boolean flag = false;

		try {
			con = ConnectionManager.getConnection();
			MemberDAO mdao = new MemberDAO(con);
			if (checkMemberId(member.getMemberId()) == true)
				;
			{
				flag = mdao.memberRegistration(member);
			}
			if (flag != true) {
				throw new MarketBusinessException("Record is Not Insert. Please Register New One.");
			}

		} catch (SQLException e) {
			throw new MarketSystemException("Contact System Administrator.");
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					throw new MarketSystemException("Contact System Administrator.");
				}
			}
		}
		return true;
	}

}
