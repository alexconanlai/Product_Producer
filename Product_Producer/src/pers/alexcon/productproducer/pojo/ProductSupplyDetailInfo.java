package pers.alexcon.productproducer.pojo;

import java.util.Calendar;

public class ProductSupplyDetailInfo {
	private Calendar beginSupplyTime;
	private Calendar endSupplyTime;
	private int producedCountPerDay;
	
	public Calendar getBeginSupplyTime() {
		return beginSupplyTime;
	}

	public void setBeginSupplyTime(Calendar beginSupplyTime) {
		this.beginSupplyTime = beginSupplyTime;
	}

	public Calendar getEndSupplyTime() {
		return endSupplyTime;
	}

	public void setEndSupplyTime(Calendar endSupplyTime) {
		this.endSupplyTime = endSupplyTime;
	}

	public int getProducedCountPerDay() {
		return producedCountPerDay;
	}

	public void setProducedCountPerDay(int producedCountPerDay) {
		this.producedCountPerDay = producedCountPerDay;
	}
}
