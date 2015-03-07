package demo.model;

import demo.model.base.OrderBase;

public class Order extends OrderBase {
	
	public boolean isNew() {
		return getId() == null;
	}

}
