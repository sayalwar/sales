/**
 * jp.co.flm.market.web.B0103ProductPurchaseConfirmationAction
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
import jp.co.flm.market.entity.Orders;
import jp.co.flm.market.logic.ProductPurchaseLogic;

/**
 * checkSession ==> Checking if the session is present
 * validate ==> Validates id productId os present
 * execute ==> Sets values gathered by the DAO into request or session
 * @author waghmare.pragat
 * @version 1.0 2023/01/13
 *
 */
public class B0103ProductPurchaseConfirmationAction {
	/**
	 * @param req ==> gets session
	 */
	public void checkSession(HttpServletRequest req) {
		req.getSession(true);
	}

	/**
	 * @param req checks shoppingCart
	 * @return page
	 */
	public String validate(HttpServletRequest req) {
		String page = null;
		if (req.getSession(false) == null) {
			req.setAttribute("errorMessage", "There is no item in the shopping cart.");
			page = "shopping-no-cart-View.jsp";
		} else {
			HttpSession session = req.getSession();
			ArrayList<Orders> checkOrder = new ArrayList<Orders>();
			checkOrder = (ArrayList<Orders>) session.getAttribute("shoppingCart");
			
			if (checkOrder == null) {
				req.setAttribute("errorMessage", "There is no item in the shopping cart,Product Already Purchased.");
				page = "shopping-no-cart-View.jsp";
			}
		}

		// メッセージ格納リストを作成する。
		ArrayList<String> errorMessageList = new ArrayList<String>();
		//HttpSession session = req.getSession();

		if (errorMessageList.size() != 0) {
			req.setAttribute("errorMessageList", errorMessageList);
			page = "product-purchase-confirmation-view.jsp";
		}

		return page;
	}

	/**
	 * @param req checks ArrayList<Orders>
	 * @return page
	 */
	public String execute(HttpServletRequest req) {

		String page = null;

		checkSession(req);

		page = validate(req);
		boolean status = false ;

		if (page == null) {
			try {
				Member member = new Member();

				ArrayList<Orders> orderList = new ArrayList<Orders>();
				HttpSession session = req.getSession();
				orderList = (ArrayList<Orders>) session.getAttribute("shoppingCart");

				if(orderList == null || orderList.isEmpty()) {
					req.setAttribute("errorMessage", "The session has been disabled. Try the operation again from the top screen.");
					page = "error.jsp";
					return page;
				}
				member = (Member) session.getAttribute("purchaseLoginMember");
				if(member == null) {
					req.setAttribute("errorMessage", "The session has been disabled. Try the operation again from the top screen.");
					page = "error.jsp";
					return page;
				}

				ProductPurchaseLogic logic = new ProductPurchaseLogic();
				status = logic.setPurchase(orderList, member);
				if(status != true) {
					req.setAttribute("errorMessage", "System Error.Contact your system adminstrator.");
					page = "error.jsp";
				}
				req.setAttribute("orderList", orderList);
				if (session != null) {
					session.removeAttribute("shoppingCart");
					session.removeAttribute("purchaseLoginMember");
				}

				page = "product-purchase-result-view.jsp";
			} catch (MarketSystemException e) {
				String errorMessage = e.getMessage();
				req.setAttribute("errorMessage", errorMessage);
				page = "error.jsp";
			} catch (MarketBusinessException e) {
				String errorMessage = e.getMessage();
				req.setAttribute("errorMessage", errorMessage);
				page = "error.jsp";
			}
		}
		return page;
	}
}