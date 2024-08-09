package jp.co.flm.market.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CommonLogoutAction {

	public String execute(HttpServletRequest req) {
		HttpSession session = req.getSession();
		if (session != null) {
			session.invalidate();
		}
		String page = "logout-view.jsp";
		return page;
	}
}
