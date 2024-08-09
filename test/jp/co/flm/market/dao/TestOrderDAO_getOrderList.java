/**
 * @author Nilesh
 * It will be used for Product enquiry screen.
 *
 */

package jp.co.flm.market.dao;

import java.sql.Connection;
import java.util.ArrayList;

import jp.co.flm.market.entity.Orders;
import jp.co.flm.market.entity.Product;

public class TestOrderDAO_getOrderList {

	public static void main(String[] args) throws Exception {

		if (args.length != 1) {
			System.out.println("使い方: java jp.co.flm.market.test.TestOrderDAO_getOrderList  <memberId>");
			System.exit(1);
		}

		String memberId = args[0];

		Connection con = null;

		try {
			con = ConnectionManager.getConnection();

			OrdersDAO odao = new OrdersDAO(con);
			ArrayList<Orders> list = odao.getOrderList(memberId);

			if(list.size()==0) {
				System.out.println("Order list is empty");
			}

			for(Orders order : list){
				Product product = order.getProduct();
				System.out.println("Order ID：" + order.getOrderId());
				System.out.println("Date：" + order.getOrderDate());
				System.out.println("Product ID：" + product.getProductId());
				System.out.println("Product Name：" + product.getProductName());
				System.out.println("Quantity：" + order.getQuantity());
				System.out.println("Price：" + product.getPrice());
				System.out.println("Point：" + product.getPoint());
				System.out.println("Sub Total Points：" + order.getSubTotalPoint());
				System.out.println("Sub Total Amount：" + order.getSubTotal());
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			if(con != null){
				con.close();
			}
		}
	}
}
