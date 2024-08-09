/**
 * jp.co.flm.market.web.B0201EmailAddressAction
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
 * @author natarajan.param
 * @version 1.0 2023/01/13
 *
 */
public class B0201EmailAddressAction {

	/**
	 * @param req ==> gets session
	 */
	public void checkSession(HttpServletRequest req) {
		req.getSession(true);
	}

	/**
	 * @param req gets email
	 * @return page
	 */
	public String validate(HttpServletRequest req) {
		String page = null;
		ArrayList<String> errorMessageList = new ArrayList<String>();
		String email = req.getParameter("email");
		if (email.length() == 0) {
			errorMessageList.add("Please enter the Email id.");
		}
		if (errorMessageList.size() != 0) {
			req.setAttribute("errorMessageList", errorMessageList);
			page = "email-address-entry-view";
		}
		return page;
	}

	/**
	 * @param req gets email and checks it
	 * @return page
	 */
	public String execute(HttpServletRequest req) {
		String page = null;
		checkSession(req);
		page = validate(req);
		if (page == null) {
			try {
				String email = req.getParameter("email");
				//	req.setAttribute("email", email);
				Member member = new Member();
				member.setMemberId(email);
				MemberRegistrationLogic logic = new MemberRegistrationLogic();
				boolean flag = logic.checkMemberId(email);
				if (flag == true) {
					HttpSession session = req.getSession(false);
					session.setAttribute("memberregistration", member);
					page = "member-registration-view.jsp";
				} else {
					req.setAttribute("errorMessageList", "Member Already Registered");

					page = "email-address-entry-view.jsp";

				}
			} catch (MarketBusinessException e) {

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