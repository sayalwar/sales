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


public class TestStockDAO_setStock {

	public static void main(String[] args) throws SQLException {
		if (args.length%2!=0) {
			System.out.println(""
					+ "java jp.co.flm.market.test.TestStockDAO_setStock Input parameter Not sufficient");
			System.exit(1);
		}
		ArrayList<Orders> list = new ArrayList<Orders>();
		for(int i=0;i<args.length;i+=2)
		{
			Orders order = new Orders();
			Product product =new Product();
		//	System.out.println(i);
			String productId= args[i];
			String quantity= args[i+1];
			int qty = Integer.parseInt(quantity);
			product.setProductId(productId);
			order.setProduct(product);
			order.setQuantity(qty);
			list.add(order);
		}

		Connection con = null;
		try {
			con = ConnectionManager.getConnection();
			StockDAO odao = new StockDAO(con);
			boolean flag = odao.setStock(list);
			if (flag == true) {
				System.out.println("Product is updated");
			} else {
				System.out.println("Product is not updated");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			// データベース接続を閉じる。
			if (con != null) {
				con.close();
			}
		}
	}
}
