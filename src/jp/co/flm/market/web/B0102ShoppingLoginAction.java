/**
 * jp.co.flm.market.web.B0101ProductSearchAction
 *
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */

// Created by Bala for shopping cart member validation

package jp.co.flm.market.web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jp.co.flm.market.common.MarketBusinessException;
import jp.co.flm.market.common.MarketSystemException;
import jp.co.flm.market.entity.Member;
import jp.co.flm.market.logic.ShoppingCartLogic;

/**
 * checkSession ==> Checking if the session is present
 * validate ==> Validates id productId os present
 * execute ==> Sets values gathered by the DAO into request or session
 * @author gopal.balakumar
 * @version 1.0 2023/01/13
 *
 */
public class B0102ShoppingLoginAction {
	/**
	 * @param req ==> gets session
	 */
	public void checkSession(HttpServletRequest req) {
		req.getSession(true);
	}

	/**
	 * @param req gets memberId and  password
	 * @return page
	 */
	public String validate(HttpServletRequest req) {
		String page = null;

		ArrayList<String> errorMessageList = new ArrayList<String>();

		String memberId = req.getParameter("memberId");
		String password = req.getParameter("password");
		if (memberId.length() == 0) {
			errorMessageList.add("MemberId  is required.");
		}
		if (password.length() == 0) {
			errorMessageList.add("Password  is required.");
		}

		if (errorMessageList.size() != 0) {
			req.setAttribute("errorMessageList", errorMessageList);
			page = "member-login-purchase-View.jsp";
		}

		return page;
	}

	/**
	 * @param req gets memberId and password
	 * @return page
	 */
	public String execute(HttpServletRequest req) {

		String page = null;

		checkSession(req);

		page = validate(req);

		if (page == null) {
			try {

				String memberId = req.getParameter("memberId");
				String password = req.getParameter("password");
				ShoppingCartLogic loginLogic = new ShoppingCartLogic();
				Member member = loginLogic.getMember(memberId, password);

				if (member != null) {
					HttpSession session = req.getSession();
					session.setAttribute("purchaseLoginMember", member);
					page = "product-purchase-view.jsp";
				} else {
					req.setAttribute("errorMessage", "The member ID or password is different.");
					page = "member-login-purchase-View.jsp";
				}
			} catch (MarketBusinessException e) {
				String errorMessage = e.getMessage();
				ArrayList<String> errorMessageList = new ArrayList<String>();
				errorMessageList.add(errorMessage);
				req.setAttribute("errorMessageList", errorMessageList);
				page = "member-login-purchase-View.jsp";
			} catch (MarketSystemException e) {
				String errorMessage = e.getMessage();
				req.setAttribute("errorMessage", errorMessage);
				page = "error.jsp";
			}
		}

		return page;
	}
}
