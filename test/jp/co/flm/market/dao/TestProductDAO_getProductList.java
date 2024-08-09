package jp.co.flm.market.dao;
/**
 * @author Kevin
 * It will be used for Product search screen.
 *
 */

import java.sql.Connection;
import java.util.ArrayList;

import jp.co.flm.market.entity.Product;

public class TestProductDAO_getProductList {

	public static void main(String[] args) throws Exception {

		if (args.length != 1) {
			System.out.println("使い方: java jp.co.flm.market.test.TestProductDAO_getProductList  <categoryId>");
			System.exit(1);
		}
		String categoryId = args[0];

		Connection con = null;
		try {
			con = ConnectionManager.getConnection();

			ProductDAO pdao = new ProductDAO(con);

			ArrayList<Product> product = pdao.getProductList(categoryId);

			if(product.size()==0) {
				System.out.println("Product list is empty");
			}

			for(int i = 0; i<product.size();i++) {
				System.out.println("Product ID：" + product.get(i).getProductId());
				System.out.println("Product Name：" + product.get(i).getProductName());
				System.out.println("Price：" + product.get(i).getPrice());
				System.out.println("Quantity：" + product.get(i).getStock());
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
