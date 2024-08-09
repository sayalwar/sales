/**
 * jp.co.flm.market.web.B0101ProductSearchAction
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
import jp.co.flm.market.logic.MemberRegistrationLogic;

/**
 * checkSession ==> Checking if the session is present
 * validate ==> Validates id productId os present
 * execute ==> Sets values gathered by the DAO into request or session
 * @author waghmare.pragat
 * @version 1.0 2023/01/13
 *
 */
public class B0201MemberRegistrationConfirmationAction {

	/**
	 * @param req gets memberregistration information and checks if it already exist
	 * @return page
	 */
	public String checkSession(HttpServletRequest req) {
		String page = null;
		HttpSession session = req.getSession();
		Member member = (Member) session.getAttribute("memberregistration");
		if (member == null) {
			req.setAttribute("errorMessageList", " User already register.");
			page = "email-address-entry-view.jsp";
		}
		return page;
	}

	/**
	 * @param req gets memberregistration and displays message depending on the if case
	 * @return page
	 */
	public String execute(HttpServletRequest req) {
		String page = null;
		page = checkSession(req);
		boolean flag = false;
		if (page == null) {
			try {
				MemberRegistrationLogic logic = new MemberRegistrationLogic();
				HttpSession session = req.getSession();
				Member member = (Member) session.getAttribute("memberregistration");
				//01/11 start  paras
				if(member == null) {
					req.setAttribute("errorMessage", "The session has been disabled. Try the operation again from the top screen.");
					page = "error.jsp";
					return page;
				}
				req.setAttribute("memberobj", member);
				flag = logic.memberRegistration(member);
				if (session != null) {
					session.removeAttribute("memberregistration");
				}
				//end paras
				if (flag == true) {
					page = "member-registration-result-view.jsp";
					req.setAttribute("SuccessMessage", "Member Has been Succesfully registered");
				}
			}

			catch (MarketBusinessException e) {
				String errorMessage = e.getMessage();
				ArrayList<String> errorMessageList = new ArrayList<String>();
				errorMessageList.add(errorMessage);
				req.setAttribute("errorMessageList", errorMessageList);
				page = "email-address-entry-view.jsp";
			} catch (MarketSystemException e) {
				String errorMessage = e.getMessage();
				req.setAttribute("errorMessage", errorMessage);
				page = "error.jsp";
			}
		}

		return page;
	}

}
