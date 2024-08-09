/**
 * @author Kevin
 * It will be used for Product search screen and shopping cart screen.
 *
 */
package jp.co.flm.market.dao;

import java.sql.Connection;

import jp.co.flm.market.entity.Product;

public class TestProductDAO_getProduct {

	public static void main(String[] args) throws Exception {

		if (args.length != 1) {
			System.out.println("使い方: java jp.co.flm.market.test.TestProductDAO_getProduct <productId>");
			System.exit(1);
		}
		String productId = args[0];

		Connection con = null;
		try {
			con = ConnectionManager.getConnection();

			ProductDAO pdao = new ProductDAO(con);

			Product product = pdao.getProduct(productId);
			System.out.println("Product ID：" + product.getProductId());
			System.out.println("Product Name：" + product.getProductName());
			System.out.println("Price：" + product.getPrice());
			System.out.println("Picture：" + product.getPicture());
			System.out.println("Quantity：" + product.getStock());
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
