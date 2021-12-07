package csi3471.edu.baylor.ecs.BaylorBurgers.Business;

import java.util.ArrayList;
import java.util.List;

public final class Cart {

	protected static List<CartItem> items = new ArrayList<CartItem>();
	protected static double discount = 0.0;

	public List<CartItem> getItems() {
		return items;
	}

	public void setItems(List<CartItem> items) {
		Cart.items = items;
	}
	
	public void addItem(CartItem item) {
		if(!items.contains(item)){
			Cart.items.add(item);
		}
	}
	public static void removeItem(CartItem item) {
			Cart.items.remove(item);
	}
	
	public Double getTotalPrice() {
		Double total = 0.0d;
		
		for (int i = 0; i < items.size(); i++) {
			total += items.get(i).getTotal();
		}
		
		total *= ((100.0 - discount) / 100.0);
		
		return total;
	}
	
	public void addDiscount(Double discountGot) {
		discount = discountGot;
	}
	
}
