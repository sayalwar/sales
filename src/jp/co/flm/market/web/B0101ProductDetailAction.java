/**
 * jp.co.flm.market.web.B0101ProductDetailAction
 *
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */

package jp.co.flm.market.web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jp.co.flm.market.common.MarketBusinessException;
import jp.co.flm.market.common.MarketSystemException;

import jp.co.flm.market.entity.Product;
import jp.co.flm.market.logic.ProductSearchInfoLogic;

/**
 * checkSession ==> Checking if the session is present
 * validate ==> Validates id productId os present
 * execute ==> Sets values gathered by the DAO into request or session
 * @author cuevas.kevin.cr
 * @version 1.0 2023/01/13
 */
public class B0101ProductDetailAction {
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
		ArrayList<String> errorMessageList = new ArrayList<String>();
		//nilesh udated
		String productId = req.getParameter("productId");
		if (productId.length() == 0) {
			errorMessageList.add("Product Detail Not found");
		}
		if (errorMessageList.size() != 0) {
			req.setAttribute("errorMessageList", errorMessageList);
			page = "product-detail-inquiry-view.jsp";
		}
		//nilesh updae end
		return page;
	}

	/**
	 * @param req ==>gets productId, categoryId, categoryName
	 * @return page
	 */
	public String execute(HttpServletRequest req) {
		String page = null;
		checkSession(req);

		page = validate(req);
		if (page == null) {
			try {
				//nilesh
				String productId = req.getParameter("productId");
				String categoryName = req.getParameter("categoryName");//passing with the detail button
				String categoryId = req.getParameter("categoryId");

				HttpSession session = req.getSession(false);

				ProductSearchInfoLogic logic = new ProductSearchInfoLogic();
				Product product = logic.getProduct(productId);
				if (product == null) {
					req.setAttribute("message", "Product is not available");
				} else {
					session.setAttribute("product", product);
					req.setAttribute("categoryName", categoryName);
					req.setAttribute("categoryId", categoryId);
					//	req.setAttribute("numberofproducts", productList.size());
				}
				page = "product-detail-inquiry-view.jsp";

			} catch (MarketBusinessException e) {
				String errorMessage = e.getMessage();
				ArrayList<String> errorMessageList = new ArrayList<String>();
				errorMessageList.add(errorMessage);
				req.setAttribute("errorMessageList", errorMessageList);
				page = "product-detail-inquiry-view.jsp";
			} catch (MarketSystemException e) {
				String errorMessage = e.getMessage();
				req.setAttribute("errorMessage", errorMessage);
				page = "error.jsp";
			}
		}
		return page;
	}
}
