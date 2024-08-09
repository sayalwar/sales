/**
 * jp.co.flm.market.web.B0201MemberRegistrationAction
 *
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */
package jp.co.flm.market.web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jp.co.flm.market.entity.Member;

/**
 * checkSession ==> Checking if the session is present
 * validate ==> Validates id productId os present
 * execute ==> Sets values gathered by the DAO into request or session
 * @author waghmare.pragat
 * @version 1.0 2023/01/13
 *
 */
public class B0201MemberRegistrationAction {

	/**
	 * @param req ==> gets session
	 */
	public void checkSession(HttpServletRequest req) {
		req.getSession(true);
	}

	/**
	 * @param req gets membername, address, phone, password, gender, passwordcheck and checks if everything is present
	 * @return page
	 */
	public String validate(HttpServletRequest req) {
		String page = null;
		ArrayList<String> errorMessageList = new ArrayList<String>();
		String membername = req.getParameter("membername");
		String address = req.getParameter("address");
		String phoneNumber = req.getParameter("phone");
		String password = req.getParameter("password");
		String gender = req.getParameter("gender");
		String passwordcheck = req.getParameter("passwordcheck");
		if (membername.length() == 0) {
			errorMessageList.add("Member Name is Empty. Enter The Member Name.");
		}
		if (gender.length() == 0) {
			errorMessageList.add("Gender is Empty. Select  Gender.");
		}
		if (address.length() == 0) {
			errorMessageList.add("Address is Empty. Enter The Addresse.");
		}
		if (phoneNumber.length() == 0) {
			errorMessageList.add("phoneNumber is Empty. Enter The phoneNumber.");
		}
		if (password.length() == 0) {
			errorMessageList.add("PASSWORD is required");
		}
		if (passwordcheck.length() == 0) {
			errorMessageList.add("Password check is Empty. Enter The password.");
		}
		if (!(password.equals(passwordcheck))) {
			errorMessageList.add("Enter Same Password and Password Check.");
		}
		if (errorMessageList.size() != 0) {
			req.setAttribute("errorMessageList", errorMessageList);
			page = "member-registration-confirmation-view.jsp";
		}
		return page;
	}

	/**
	 * @param req gets membername, address, phone, password, gender, passwordcheck and set it in the sql table and show it in the screen
	 * @return page
	 */
	public String execute(HttpServletRequest req) {
		String page = null;
		checkSession(req);
		page = validate(req);
		if (page == null) {
			String membername = req.getParameter("membername");
			String gender = req.getParameter("gender");
			String address = req.getParameter("address");
			String phoneNumber = req.getParameter("phone");
			String password = req.getParameter("password");
			HttpSession session = req.getSession(false);
			Member member = new Member();
			//member.setMemberId((String)
			member = (Member) session.getAttribute("memberregistration");
			if(member == null) {
				req.setAttribute("errorMessage", "The session has been disabled. Try the operation again from the top screen.");
				page = "error.jsp";
				return page;
			}

			member.setMemberName(membername);
			member.setGender(gender);
			member.setAddress(address);
			member.setPhone(phoneNumber);
			member.setPassword(password);
			session.setAttribute("memberregistration", member);
			page = "member-registration-confirmation-view.jsp";
		}

		return page;
	}

}
