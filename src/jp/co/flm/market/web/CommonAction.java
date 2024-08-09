/**
 * jp.co.flm.market.web.CommonAction
 *
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */
package jp.co.flm.market.web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import jp.co.flm.market.common.MarketBusinessException;
import jp.co.flm.market.common.MarketSystemException;
import jp.co.flm.market.entity.Category;
import jp.co.flm.market.logic.CommonLogic;

/**
 * トップ画面へ遷移するアクションクラスです。
 * @author FLM
 * @version 1.0 YYYY/MM/DD
 */
public class CommonAction {

	public String execute(HttpServletRequest req) {

		String page = null;
		try {
			CommonLogic logic = new CommonLogic();

			ArrayList<Category> categoryList = logic.getCategory();
			req.setAttribute("categoryList", categoryList);
			page = "top-screen.jsp";
		} catch (MarketBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MarketSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;
	}
	//	public String execute(HttpServletRequest req) {
	//
	//		String page = "DefaultMenu.jsp";
	//
	//		return page;
	//	}

}