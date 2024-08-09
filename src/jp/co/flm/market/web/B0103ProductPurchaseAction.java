/**
 * jp.co.flm.market.web.B0103ProductPurchaseAction
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
public class B0103ProductPurchaseAction {

	/**
	 * @param req ==> gets session
	 */
	public void checkSession(HttpServletRequest req) {

		// セッションを取得（セッションがない場合、作成）する。
		req.getSession(true);
	}

	/**
	 * @param req gets creditCardNo
	 * @return page
	 */
	public String validate(HttpServletRequest req) {
		String page = null;

		// メッセージ格納リストを作成する。
		ArrayList<String> errorMessageList = new ArrayList<String>();

		//Pragati

		String creditCardNo = req.getParameter("creditCardNo");
		if (creditCardNo.length() == 0) {
			errorMessageList.add("Credit Card Number is Required.");
		}
		if ((creditCardNo.length() != 16) && (creditCardNo.length() != 0)) {
			errorMessageList.add("Credit card number should be 16 Digit.");
		}

		//Pragati

		// 入力エラーが発生していたかを確認する。
		if (errorMessageList.size() != 0) {
			req.setAttribute("errorMessageList", errorMessageList);
			page = "product-purchase-view.jsp";
		}

		return page;
	}

	/**
	 * @param req checks creditCardNo
	 * @return page
	 */
	public String execute(HttpServletRequest req) {

		String page = null;
		checkSession(req);
		page = validate(req);
		if (page == null)
			//Pragati
			try {
				{
					String creditCardNo = req.getParameter("creditCardNo");
					ArrayList<Orders> updateCardData = new ArrayList<Orders>();
					HttpSession session = req.getSession();
					updateCardData = (ArrayList<Orders>) session.getAttribute("shoppingCart");
					if(updateCardData == null || updateCardData.isEmpty()) {
						req.setAttribute("errorMessage", "The session has been disabled. Try the operation again from the top screen.");
						page = "error.jsp";
						return page;
					}

					Member memberObj = (Member) session.getAttribute("purchaseLoginMember");
					if(memberObj == null) {
						req.setAttribute("errorMessage", "The session has been disabled. Try the operation again from the top screen.");
						page = "error.jsp";
						return page;
					}

					ProductPurchaseLogic logic = new ProductPurchaseLogic();
					updateCardData = logic.setCreditCard(creditCardNo, memberObj, updateCardData);
					creditCardNo = creditCardNo.replaceAll("\\w(?=\\w{4})", "*");
					req.setAttribute("creditCardNo", creditCardNo);
					page = "product-purchase-confirmation-view.jsp";
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

		return page;
	}
}