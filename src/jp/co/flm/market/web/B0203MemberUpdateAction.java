/**
 * jp.co.flm.market.web.B0203MemberUpdateAction
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
 * @author sayalwar.nilesh
 * @version 1.0 2023/01/13
 *
 */
public class B0203MemberUpdateAction {
	/**
	 *
	 * @param req HttpServletRequest ==> Performs a session check
	 */
	public void checkSession(HttpServletRequest req) {

		req.getSession(true);
	}

	/**
	 * @param req gets memberName, memberAddress, phone, memberPassword and checks if everything is provided
	 * @return page
	 */
	public String validate(HttpServletRequest req) {
		String page = null;

		ArrayList<String> errorMessageList = new ArrayList<String>();

		// フォームで指定された会員IDとパスワードを取得する。
		String name = req.getParameter("memberName");
		String address = req.getParameter("memberAddress");
		String phone = req.getParameter("phone");
		String password = req.getParameter("memberPassword");
		String passwordcheck = req.getParameter("memberPasswordCheck");

		// 入力値を確認する（空チェック）。
		if (name.length() == 0) {
			errorMessageList.add("member updateaction");
		}
		if (address.length() == 0) {
			errorMessageList.add("Please Enter the Member Address.");
		}
		if (phone.length() == 0) {
			errorMessageList.add("Please Enter the Member Phone Number.");
		}
		if (password.length() == 0) {
			errorMessageList.add("Please Enter the Member Password.");
		}
		if (passwordcheck.length() == 0) {
			errorMessageList.add("Please Enter the Member Password Check.");
		}
		if (!(passwordcheck.equals(passwordcheck))) {
			errorMessageList.add("Please Enter Same Password and Password Check.");
		}

		// 入力エラーが発生していたかを確認する。
		if (errorMessageList.size() != 0) {
			req.setAttribute("errorMessageList", errorMessageList);
			page = "member-update-screen.jsp";
		}
		return page;
	}

	/**
	 * @param req sets memberName, memberAddress, phone and display it in the jsp page. also it is updating the table.
	 * @return page
	 */
	public String execute(HttpServletRequest req) {
		String page = null;
		checkSession(req);
		page = validate(req);
		if (page == null) {
			HttpSession session = req.getSession();
			// 会員情報をセッションへ格納する。
			Member member = (Member) session.getAttribute("CommonLoginMember");
			if(member == null) {
				req.setAttribute("errorMessage", "The session has been disabled. Try the operation again from the top screen.");
				page = "error.jsp";
				return page;
			}

			member.setMemberName(req.getParameter("memberName"));
			member.setAddress(req.getParameter("memberAddress"));
			member.setPhone(req.getParameter("phone"));
			//member.setPassword(req.getParameter("memberPassword"));
			String password = req.getParameter("memberPassword");
			req.setAttribute("memeberData", password);
			session.setAttribute("updateMember", member);
			page = "member-update-confirmation-screen.jsp";
		}

		return page;
	}
}