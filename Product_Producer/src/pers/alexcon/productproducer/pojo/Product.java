package pers.alexcon.productproducer.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Product {
	private static final int INIT_HASHMAP_SIZE = 8;
	private static final int INIT_LIST_SIZE = 8;

	private String id;
	private Map<String, Integer> meteriasInfo;
	private List<ProductSupplyDetailInfo> productInfos;

	public Product(String id) {
		this.id = id;
		meteriasInfo = new HashMap<String, Integer>(INIT_HASHMAP_SIZE);
		productInfos = new ArrayList<ProductSupplyDetailInfo>(INIT_LIST_SIZE);
	}

	public Map<String, Integer> getMeteriasInfo() {
		return meteriasInfo;
	}

	public void setMeteriasInfo(Map<String, Integer> meteriasInfo) {
		this.meteriasInfo = meteriasInfo;
	}

	public String getId() {
		return id;
	}

	public List<ProductSupplyDetailInfo> getProductInfo() {
		return productInfos;
	}

}
