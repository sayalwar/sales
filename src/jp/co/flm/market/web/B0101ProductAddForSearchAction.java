/**
 * jp.co.flm.market.web.B0101ProductAddForSearchAction
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
import jp.co.flm.market.entity.Product;

import jp.co.flm.market.logic.AddtoCartLogic;
import jp.co.flm.market.logic.ProductSearchInfoLogic;

/**
 * checkSession ==> Checking if the session is present
 * validate ==> Validates id productId os present
 * execute ==> Sets values gathered by the DAO into request or session
 * @author cuevas.kevin.cr
 * @version 1.0 2023/01/13
 */
public class B0101ProductAddForSearchAction {

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
		String productId = req.getParameter("productId");
		if (productId.length() == 0) {
			errorMessageList.add("Product Not found");
		}
		if (errorMessageList.size() != 0) {
			req.setAttribute("errorMessageList", errorMessageList);
			page = "product-search-result-view.jsp";
		}
		return page;
	}

	/**
	 * @param req ==>gets productId, categoryId, categoryName, numberofproducts
	 * @return page
	 */
	public String execute(HttpServletRequest req) {
		String page = null;
		checkSession(req);
		
		page = validate(req);
		if (page == null) {
			//kevin
			try {
				String productId = req.getParameter("productId");
				req.setAttribute("categoryId", req.getParameter("categoryId"));
				req.setAttribute("categoryName", req.getParameter("categoryName"));
				req.setAttribute("numberofproducts", req.getParameter("numberofproducts"));

				ProductSearchInfoLogic logic = new ProductSearchInfoLogic();
				Product product = logic.getProduct(productId);

				if (product == null) {
					req.setAttribute("message", "Product is not available");
				} else {
					//adding to arraylist then session

					HttpSession mysession = req.getSession();
					//		ArrayList<Orders> cartlist1 = null;
					ArrayList<Orders> cartlist = (ArrayList<Orders>) mysession.getAttribute("shoppingCart");
					AddtoCartLogic addtocartlogic = new AddtoCartLogic();
					cartlist = addtocartlogic.addToCart(cartlist, product);
					mysession.setAttribute("shoppingCart", cartlist);
					req.setAttribute("message", "The " + product.getProductName() + "  is added to the cart.");
					req.setAttribute("productName", product.getProductName());
				}
				page = "product-search-result-view.jsp";
			} catch (MarketBusinessException e) {
				String errorMessage = e.getMessage();
				ArrayList<String> errorMessageList = new ArrayList<String>();
				errorMessageList.add(errorMessage);
				req.setAttribute("errorMessageList", errorMessageList);
				page = "product-search-result-view.jsp";
			} catch (MarketSystemException e) {
				String errorMessage = e.getMessage();
				req.setAttribute("errorMessage", errorMessage);
				page = "error.jsp";
			}
		}
		return page;
	}
}
