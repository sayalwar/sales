/**
 * jp.co.flm.market.logic.MemberUpdateLogic
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
 * @author Nilesh
 *
 */
public class MemberUpdateLogic {

	/**
	 * @param member It having member details for updating the existing member.
	 * @return If the memeber update successfully in databases it will return result view page "member-update-results-screen.jsp".
	 * @throws MarketBusinessException If now existing memeber ID and password found or not match. It will throw the error.
	 * @throws MarketSystemException If any SQL exception occur it will throw the error message.
	 */
	public boolean updateMember(Member member) throws MarketBusinessException, MarketSystemException {

		Connection con = null;
		//String page = null;
		boolean updatestatus=true;
		try {
			con = ConnectionManager.getConnection();
			
			// 会員ID、パスワードにより会員情報を検索する。
			MemberDAO mdao = new MemberDAO(con);
			 updatestatus = mdao.updateMember(member);

			if (updatestatus == false) {
				// 認証に失敗した場合、エラーを発生させる。
				throw new MarketBusinessException("Member ID or Password is Different.");
			}
//			else {
//				page = "member-update-results-screen.jsp";
//			}
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
		return updatestatus;
	}

}