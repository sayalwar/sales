/**
 * jp.co.flm.market.web.B0102ShoppingCartDelAction
 *
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */

//Created by Bala for delete shopping cart data

package jp.co.flm.market.web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jp.co.flm.market.common.MarketBusinessException;
import jp.co.flm.market.entity.Orders;
import jp.co.flm.market.logic.ShoppingCartLogic;

/**
 * checkSession ==> Checking if the session is present
 * validate ==> Validates id productId os present
 * execute ==> Sets values gathered by the DAO into request or session
 * @author gopal.balakumar
 * @version 1.0 2023/01/13
 *
 */
public class B0102ShoppingCartDelAction {

	/**
	 * @param req ==> gets session
	 */
	public void checkSession(HttpServletRequest req) {
		req.getSession(true);
	}

	/**
	 * @param req ==> gets productId
	 * @return page
	 */
	public String validate(HttpServletRequest req) {
		String page = null;
		String errorMessage = null;

		String prodId = req.getParameter("productId");

		if (prodId.length() == 0) {
			errorMessage = "Product ID  is required.";
			req.setAttribute("errorMessage", errorMessage);
			page = "error.jsp";
		}

		return page;
	}

	/**
	 * @param req ==> gets productId
	 * @return page
	 */
	public String execute(HttpServletRequest req) {
		String page = null;

		checkSession(req);
		page = validate(req);

		if (page == null) {
			HttpSession session = req.getSession();
			String productId = req.getParameter("productId");
			ArrayList<Orders> orderList = new ArrayList<Orders>();
			orderList = (ArrayList<Orders>) session.getAttribute("shoppingCart");

			if(orderList == null || orderList.isEmpty()) {
				req.setAttribute("errorMessage", "The session has been disabled. Try the operation again from the top screen.");
				page = "error.jsp";
				return page;
			}

			try {
				ShoppingCartLogic delLogic = new ShoppingCartLogic();
				orderList = delLogic.delProductId(productId, orderList);
				req.setAttribute("successMessage", "I deleted the items in the shopping cart.");
				page = "shopping-cart-View.jsp";
			} catch (MarketBusinessException e) {
				String errorMessage = e.getMessage();
				req.setAttribute("errorMessage", errorMessage);
				session.removeAttribute("shoppingCart");
				page = "shopping-no-cart-View.jsp";
			}
		}
		return page;
	}
}
