/**
 * jp.co.flm.market.web.B0203updateMemberConfirmationAction
 *
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */

package jp.co.flm.market.web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jp.co.flm.market.common.MarketBusinessException;
import jp.co.flm.market.common.MarketSystemException;
import jp.co.flm.market.entity.Member;
import jp.co.flm.market.logic.MemberUpdateLogic;

/**
 * checkSession ==> Checking if the session is present
 * validate ==> Validates id productId os present
 * execute ==> Sets values gathered by the DAO into request or session
 * @author sayalwar.nilesh
 * @version 1.0 2023/01/13
 */
public class B0203updateMemberConfirmationAction {
	/**
	 *
	 * @param req HttpServletRequest ==> Performs a session check
	 */
	public void checkSession(HttpServletRequest req) {
		req.getSession(true);
	}
	public String validate(HttpServletRequest req) {
		String page = null;
		String password = (String) req.getParameter("tempData");
		//ArrayList<String> errorMessageList = new ArrayList<String>();
		HttpSession session = req.getSession();
		//Member updatemember = (Member) session.getAttribute("updateMember");
		if((session.getAttribute("updateMember")==null) || password == null)
		{
		//errorMessageList.add("Member Detail not found");
			req.setAttribute("errorMessage", "The session has been disabled. Try the operation again from the top screen.");
			page = "error.jsp";
		}

		return page;
	}
	/**
	 * @param req check session and sets errorMessageList
	 * @return page
	 */
	public String execute(HttpServletRequest req) {
		String page = null;
		page = validate(req);
		checkSession(req);
		boolean updatestatus;
		if(page ==null)
		{
		try {
			HttpSession session = req.getSession();
			Member updatemember = (Member) session.getAttribute("updateMember");
			String password = (String) req.getParameter("tempData");

			updatemember.setPassword(password);
			
			MemberUpdateLogic logic = new MemberUpdateLogic();
			updatestatus = logic.updateMember(updatemember);
			if(updatestatus==true)
			{
				if (session.getAttribute("updateMember") != null) {
					session.removeAttribute("updateMember");
				}
				page = "member-update-results-screen.jsp";
			}
			else {
				req.setAttribute("errorMessage", "Member Updation failed");
				page = "error.jsp";
				}
		} catch (MarketBusinessException e) {
			String errorMessage = e.getMessage();
			ArrayList<String> errorMessageList = new ArrayList<String>();
			errorMessageList.add(errorMessage);
			req.setAttribute("errorMessageList", errorMessageList);
			page = "member-update-results-screen.jsp";
		} catch (MarketSystemException e) {
			// エラーメッセージを取得する。
			String errorMessage = e.getMessage();
			// エラーメッセージをリクエストスコープへ格納する。
			req.setAttribute("errorMessage", errorMessage);
			page = "error.jsp";
		}
		}
		return page;
	}
}
