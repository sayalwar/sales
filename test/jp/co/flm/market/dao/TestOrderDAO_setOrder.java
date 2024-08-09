/**
 * @author Prameshiwari
 * It will be used for Product purchase screen.
 *
 */

package jp.co.flm.market.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jp.co.flm.market.entity.Orders;
import jp.co.flm.market.entity.Product;

public class TestOrderDAO_setOrder {
/*  Argument for test specification
	prashooooo@jp.co
	1234
	a04
	180
	599
	8
*/

public static void main(String[] args) throws SQLException {
		// コマンドライン引数を確認する。
		if (args.length != 6) {
			System.out.println("使い方: java jp.co.flm.market.test.TestOrdersDAO02 <会員ID>");
			System.exit(1);
		}

		String memberId = args[0];
		String creditCardId=args[1];
		String productId=args[2];
		String quantity=args[3];
		int Qty =Integer.parseInt(quantity);
		String price=args[4];
		int Prs =Integer.parseInt(price);
		String point=args[5];
		int Pt = Integer.parseInt(point);

		Connection con = null;
		try {

			con = ConnectionManager.getConnection();
			OrdersDAO odao = new OrdersDAO(con);
			ArrayList<Orders> list = new ArrayList<Orders>();
            Orders order = new Orders();
            Product product=new Product();

            order.setMemberId(memberId);
            order.setCreditCardId(creditCardId);
            product.setProductId(productId);
            order.setQuantity(Qty);
            order.setProduct(product);
            product.setPrice(Prs);
            product.setPoint(Pt);
            list.add(order);
            boolean flag=odao.setOrder(list);
            System.out.println(flag);
            if(flag == true) {
            	System.out.println("Record is inserted successfully");
            }else {

            	System.out.println("The record is not inserted ");

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			// データベース接続を閉じる。
			if(con != null){
				con.close();
			}
		}
	}
}
