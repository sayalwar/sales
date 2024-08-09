/**
 * jp.co.flm.market.web.FrontControllerServlet
 *
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */

package jp.co.flm.market.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * is responsible for pages in the system
 * @author cuevas.kevin.cr
 * @version 1.0 2023/01/13
 */
@WebServlet("/mserv")
public class FrontControllerServlet extends HttpServlet {

	/**
	 * Servlet Services
	 * @param req HttpServletRequest
	 * @param res HttpServletResponse
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * Servlet Services
	 * @param req HttpServletRequest
	 * @param res HttpServletResponse
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String flag = request.getParameter("flag");
		String page = null;

		if (flag == null) {
			flag = "TOP";
		}
		switch (flag) {
		case "TOP":
			CommonAction commonAction = new CommonAction();
			page = commonAction.execute(request);
			break;
		case "CommonLogin":
			CommonLoginAction commonLoginAction = new CommonLoginAction();
			page = commonLoginAction.execute(request);
			break;
		case "B0202LoginMember":
			B0202LoginMemberAction b0202LoginMemberAction = new B0202LoginMemberAction();
			page = b0202LoginMemberAction.execute(request);
			break;
		case "B0203G01":
			page = "member-update-screen.jsp";
			break;
		case "B0203G01back":
			page = "member-info-view.jsp";
			break;
		case "B0203G02update":
			//after clicking update button
			B0203MemberUpdateAction b0203MemberUpdateAction = new B0203MemberUpdateAction();
			page = b0203MemberUpdateAction.execute(request);
			break;
		case "B0203G02back":
			//after clicking update button
			page = "member-update-screen.jsp";
			break;
		case "B0203G03":
			//after clicking  confirm button
			B0203updateMemberConfirmationAction b0203updateMemberConfirmationAction = new B0203updateMemberConfirmationAction();
			page = b0203updateMemberConfirmationAction.execute(request);
			break;
		//kevin start
		case "CommonLogoutAction":
			CommonLogoutAction commonLogoutAction = new CommonLogoutAction();
			page = commonLogoutAction.execute(request);
			break;
		case "B0101ProductSearch":
			B0101ProductSearchAction productSearchAction = new B0101ProductSearchAction();
			page = productSearchAction.execute(request);
			break;
		//kevin end
		//kevin nilesh update start
		case "detail":
			B0101ProductDetailAction detialaction = new B0101ProductDetailAction();
			page = detialaction.execute(request);
			//page="product-detail-inquiry-view.jsp";
			//page = productSearchAction.execute(request);
			break;
		//addtocart starts
		case "B0101ProductAddForSearch":
			B0101ProductAddForSearchAction addActionForSearch = new B0101ProductAddForSearchAction();
			page = addActionForSearch.execute(request);
			break;
		case "B0101ProductAddForDetail":
			B0101ProductAddForDetailAction addActionForDetail = new B0101ProductAddForDetailAction();
			page = addActionForDetail.execute(request);

			break;
		//add to cart ends
		//kevin nilesh update end

		// parsu update starts
		case "B0201EmailAddress":
			B0201EmailAddressAction b0201EmailAddress = new B0201EmailAddressAction();
			page = b0201EmailAddress.execute(request);
			break;
		case "B0201MemberRegistration":
			B0201MemberRegistrationAction b0201MemberRegistration = new B0201MemberRegistrationAction();
			page = b0201MemberRegistration.execute(request);
			break;
		case "B0201MemberRegistrationConfirmation":
			B0201MemberRegistrationConfirmationAction b0201MemberConfirmation = new B0201MemberRegistrationConfirmationAction();
			page = b0201MemberConfirmation.execute(request);
			break;
		case "B0201G01Emailentry":
			page = "email-address-entry-view.jsp";
			break;
		case "backB0201G02":
			page = "member-registration-view.jsp";
			break;

		//parsu update end

		// Added by BALA Shopping cart  starts
		case "B0101CartValidate":
			B0102ShoppingCartValAction shoppingCartValAction = new B0102ShoppingCartValAction();
			page = shoppingCartValAction.execute(request);
			break;
		case "B0101CartDelete":
			B0102ShoppingCartDelAction shoppingCartDelAction = new B0102ShoppingCartDelAction();
			page = shoppingCartDelAction.execute(request);
			break;
		case "B0101CartBuy":
			B0102ShoppingCartBuyAction shoppingCartBuyAction = new B0102ShoppingCartBuyAction();
			page = shoppingCartBuyAction.execute(request);
			break;
		case "B0202LoginPurchase":
			B0102ShoppingLoginAction shoppingLoginAction = new B0102ShoppingLoginAction();
			page = shoppingLoginAction.execute(request);
			break;
		case "B0101CartSave":
			B0102ShoppingCartSaveAction saveAction = new B0102ShoppingCartSaveAction();
			page = saveAction.execute(request);
			break;
		//Added by BALA Shopping cart  ends

		//Pragati waghmare front controller update starts
		case "B0103PurchaseProduct":
			B0103ProductPurchaseAction b0103ProductPurchaseAction = new B0103ProductPurchaseAction();
			page = b0103ProductPurchaseAction.execute(request);
			break;
		case "B0103ProductConfirmation":
			B0103ProductPurchaseConfirmationAction b0103ProductPurchaseConfirmationAction = new B0103ProductPurchaseConfirmationAction();
			page = b0103ProductPurchaseConfirmationAction.execute(request);
			break;

		case "backB0103G01":
			page = "product-purchase-view.jsp";
			break;
		default:
			CommonAction notvalidlink = new CommonAction();
			page = notvalidlink.execute(request);
			break;
		//Pragati waghmare front controller update Ends
		}
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/" + page);
		rd.forward(request, response);
	}
}
