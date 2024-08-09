/**
 * jp.co.flm.market.web.B0102ShoppingCartSaveAction
 *
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */

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
public class B0102ShoppingCartSaveAction {

	/**
	 * @param req ==> gets session
	 */
	public void checkSession(HttpServletRequest req) {
		req.getSession(true);
	}

	/**
	 * @param req ==> gets array of productId and array of productQuantity
	 * @return page
	 */
	public String validate(HttpServletRequest req) {
		String page = null;
		String errorMessage = null;
		ArrayList<String> errorMessageList = new ArrayList<String>();

		String prodId[] = req.getParameterValues("productId");
		String productQuantity[] = req.getParameterValues("productQuantity");

		for (String tempProdId : prodId) {
			if (tempProdId.length() == 0) {
				errorMessage = "Product ID  is required.";
				req.setAttribute("errorMessage", errorMessage);
			}
		}

		for (String tempProdQty : productQuantity) {
			if (tempProdQty.length() == 0) {
				errorMessage = "Quantity is required.";
				req.setAttribute("errorMessage", errorMessage);
			}
		}

		if (errorMessageList.size() != 0) {
			req.setAttribute("errorMessageList", errorMessageList);
			page = "error.jsp";
		}

		return page;
	}

	/**
	 * @param req ==> gets array of productId and array of productQuantity
	 * @return page
	 */
	public String execute(HttpServletRequest req) {
		String page = null;

		checkSession(req);

		page = validate(req);

		if (page == null) {
			try {
				HttpSession session = req.getSession();
				ArrayList<Orders> orderList = new ArrayList<Orders>();

				String prodId[] = req.getParameterValues("productId");
				String tempQty[] = req.getParameterValues("productQuantity");

				int[] quantity = new int[tempQty.length];

				for (int i = 0; i < tempQty.length; i++) {
					quantity[i] = Integer.parseInt(tempQty[i]);
				}

				orderList = (ArrayList<Orders>) session.getAttribute("shoppingCart");

				if(orderList == null || orderList.isEmpty()) {
					req.setAttribute("errorMessage", "The session has been disabled. Try the operation again from the top screen.");
					page = "error.jsp";
					return page;
				}

				ShoppingCartLogic updateLogic = new ShoppingCartLogic();
				orderList = updateLogic.updateCartData(prodId, quantity, orderList);

				if (!orderList.isEmpty()) {
					req.setAttribute("successMessage", "Data saved in the shopping cart.");
					page = "shopping-cart-View.jsp";
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
