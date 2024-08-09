/**
 * jp.co.flm.market.logic.AddtoCartLogic
 *
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */

package jp.co.flm.market.logic;

import java.util.ArrayList;
import jp.co.flm.market.common.MarketBusinessException;
import jp.co.flm.market.common.MarketSystemException;
import jp.co.flm.market.entity.Orders;
import jp.co.flm.market.entity.Product;
import jp.co.flm.market.entity.Stock;

/**
 * @author Kevin
 *
 */
public class AddtoCartLogic {
	/**
	 * @param shoppingCart It is collection of Orders data. If product ID not avail. Porduct details will update in shoppingcart.
	 * @param product Product It is holding product details . To update details to ArryList of order
	 * @return ArrayList<Orders>  End of method reach it will return updated collection of orders list.
	 * @throws MarketBusinessException If Product Id available in shopping cart. This error will get
	 * @throws MarketSystemException If the stock not available for product. This error will get.
	 */

	public ArrayList<Orders> addToCart(ArrayList<Orders> shoppingCart, Product product)
			throws MarketBusinessException, MarketSystemException {
		Orders orders = new Orders();
		Stock stock = product.getStock();

		if (stock.getQuantity() == 0) {
			throw new MarketBusinessException("Product " + product.getProductName() + " is Out of Stock");
		} else {
			if (shoppingCart == null) {
				shoppingCart = new ArrayList<Orders>();
				orders.setProduct(product);
				orders.setSubTotal(product.getPrice());
				orders.setSubTotalPoint(product.getPoint());
				orders.setQuantity(1);
				shoppingCart.add(orders);
			} else {
				for (int i = 0; i < shoppingCart.size(); i++) {
					Orders orders1 = shoppingCart.get(i);
					Product ordersproduct = orders1.getProduct();
					orders.setSubTotal(product.getPrice());
					orders.setSubTotalPoint(product.getPoint());
					if (product.getProductId().equals(ordersproduct.getProductId())) {
						throw new MarketBusinessException(
								"Product " + product.getProductName() + " is already Exist in Cart");
					}
				}
				orders.setProduct(product);
				orders.setQuantity(1);
				shoppingCart.add(orders);
			}
		}
		return shoppingCart;
	}
}