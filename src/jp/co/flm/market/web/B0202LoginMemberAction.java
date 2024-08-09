/**
 * jp.co.flm.market.web.B0202LoginMemberAction
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
import jp.co.flm.market.logic.MemberInfoLogic;

/**
 * checkSession ==> Checking if the session is present
 * validate ==> Validates id productId os present
 * execute ==> Sets values gathered by the DAO into request or session
 * @author sayalwar.nilesh
 * @version 1.0 2023/01/13
 *
 */
public class B0202LoginMemberAction {

	/**
	 *
	 * @param req HttpServletRequest ==> Performs a session check
	 */
	public void checkSession(HttpServletRequest req) {
		req.getSession(true);

	}

	/**
	 * @param req memberId and passowrd and checks it inside the if statement
	 * @return page
	 */
	public String validate(HttpServletRequest req) {
		String page = null;
		ArrayList<String> errorMessageList = new ArrayList<String>();
		HttpSession session = req.getSession();
		String memberId=null;
		String password=null;
		if (session.getAttribute("CommonLoginMember") != null) {
			Member member = (Member) session.getAttribute("CommonLoginMember");
			memberId=member.getMemberId();
			password = member.getPassword();
		}
		else {
		// フォームで指定された会員IDとパスワードを取得する。
		memberId = req.getParameter("memberId");
		password = req.getParameter("password");
		}
		// 入力値を確認する（空チェック）。
		if (memberId.length() == 0) {
			errorMessageList.add("MEMBERID is required.");
		}
		if (password.length() == 0) {
			errorMessageList.add("PASSWORD is required.");
		}

		// 入力エラーが発生していたかを確認する。
		if (errorMessageList.size() != 0) {
			req.setAttribute("errorMessageList", errorMessageList);
			page = "member-login-view.jsp";
		}

		return page;
	}

	/**
	 * @param req memberId and passowrd and checks it inside the if statement
	 * @return page
	 */
	public String execute(HttpServletRequest req) {

		String page = null;

		checkSession(req);

		page = validate(req);
		String memberId = null;
		String password = null;
		if (page == null) {
			try {
				// フォームで指定された会員IDとパスワードを取得する。
				HttpSession session = req.getSession();
				if (session.getAttribute("CommonLoginMember") != null) {
					Member member = (Member) session.getAttribute("CommonLoginMember");
					memberId=member.getMemberId();
					password = member.getPassword();
				}
				else {
				 memberId = req.getParameter("memberId");
				password = req.getParameter("password");
				}
				// 会員情報を取得する。
				MemberInfoLogic logic = new MemberInfoLogic();
				Member member = logic.getMember(memberId, password);

				// セッションを取得する。
				//	HttpSession session = req.getSession(false);
				// 会員情報をセッションへ格納する。

				session.setAttribute("CommonLoginMember", member);

				// 購入履歴情報を取得する。
				ArrayList<Orders> orderList = logic.getOrderList(memberId);

				// 購入履歴情報をリクエストスコープへ格納する。
				req.setAttribute("orderList", orderList);

				if (orderList.size() == 0) {
					// 購入履歴情報がなかった場合、メッセージをリクエストスコープへ格納する。
					req.setAttribute("message", "No order History found");
				}

				page = "member-info-view.jsp";
			} catch (MarketBusinessException e) {
				// エラーメッセージを取得する。
				String errorMessage = e.getMessage();
				// メッセージ格納リストを作成し、エラーメッセージを設定する。
				ArrayList<String> errorMessageList = new ArrayList<String>();
				errorMessageList.add(errorMessage);
				// エラーメッセージをリクエストスコープへ格納する。
				req.setAttribute("errorMessageList", errorMessageList);

				page = "member-login-view.jsp";
			} catch (MarketSystemException e) {
				// エラーメッセージを取得する。
				String errorMessage = e.getMessage();
				// エラーメッセージをリクエストスコープへ格納する。
				req.setAttribute("errorMessage", errorMessage);

				page = "error.jsp";
			}
		}

		return page;
	}
}