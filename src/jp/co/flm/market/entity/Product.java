package jp.co.flm.market.entity;
import java.io.Serializable;

public class Product implements Serializable {

	private String productId;
	private String productName;
	private int price;
	private String picture;
	private int point;
	private Category category;
	private Stock stock;
	public Product() {
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}

	/**
	 * ポイントを取得する。
	 * @return ポイント
	 */
	public int getPoint() {
		return point;
	}

	/**
	 * ポイントを設定する。
	 * @param point ポイント
	 */
	public void setPoint(int point) {
		this.point = point;
	}

	/**
	 * カテゴリ情報を取得する。
	 * @return カテゴリ情報
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * カテゴリ情報を設定する。
	 * @param category カテゴリ情報
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * 在庫情報を取得する。
	 * @return 在庫情報
	 */
	public Stock getStock() {
		return stock;
	}

	/**
	 * 在庫情報を設定する。
	 * @param stock 在庫情報
	 */
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", price=" + price + ", picture="
				+ picture + ", point=" + point + ", category=" + category + ", stock=" + stock + "]";
	}

}