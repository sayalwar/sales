/**
 * jp.co.flm.market.web.B0102ShoppingCartValAction
 *
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */

// Created by Bala for shopping cart validation

package jp.co.flm.market.web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jp.co.flm.market.common.MarketBusinessException;
import jp.co.flm.market.common.MarketSystemException;
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
public class B0102ShoppingCartValAction {

	//For validate the session
	/**
	 * @param req get session
	 * @return page
	 */
	public String checkSession(HttpServletRequest req) {
		//String page = "shopping-cart-View.jsp";
		String page = null;

		if (req.getSession(false) == null) {
			req.setAttribute("errorMessage", "There is no item in the shopping cart.");
			page = "shopping-no-cart-View.jsp";
		} else {
			HttpSession session = req.getSession();
			ArrayList<Orders> checkOrder = new ArrayList<Orders>();
			checkOrder = (ArrayList<Orders>) session.getAttribute("shoppingCart");
			if(checkOrder == null || checkOrder.isEmpty()) {
				req.setAttribute("errorMessage", "There is no item in the shopping cart.");
				page = "shopping-no-cart-View.jsp";
			}
		}
		return page;
	}

	/**
	 * @param req getSession
	 * @return page
	 */
	public String execute(HttpServletRequest req) {
		String page = null;

		page = checkSession(req);

		if (page == null) {
			try {
				HttpSession session = req.getSession();
				ArrayList<Orders> orderList = new ArrayList<Orders>();

				orderList = (ArrayList<Orders>) session.getAttribute("shoppingCart");

				if(orderList == null || orderList.isEmpty()) {
					req.setAttribute("errorMessage", "There is no item in the shopping cart.");
					page = "shopping-no-cart-View.jsp";
					return page;
				}

				ShoppingCartLogic updateLogic = new ShoppingCartLogic();
				orderList = updateLogic.getStocks(orderList);

				if (!orderList.isEmpty()) {
					page = "shopping-cart-View.jsp";
				}else {
					req.setAttribute("errorMessage", "There is no item in the shopping cart.");
					page = "shopping-no-cart-View.jsp";
				}

			} catch (MarketBusinessException e) {
				String errorMessage = e.getMessage();
				req.setAttribute("errorMessage", errorMessage);
				page = "shopping-no-cart-View.jsp";
			} catch (MarketSystemException e) {
				String errorMessage = e.getMessage();
				req.setAttribute("errorMessage", errorMessage);
				page = "error.jsp";
			}
		}
		return page;
	}
}
