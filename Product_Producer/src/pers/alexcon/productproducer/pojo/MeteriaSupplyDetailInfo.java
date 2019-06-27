package pers.alexcon.productproducer.pojo;

import java.util.Calendar;

public class MeteriaSupplyDetailInfo implements Comparable<MeteriaSupplyDetailInfo>{
	private Calendar beginSupplyTime;
	private Calendar endSupplyTime;
	private int supplyCountPerDay;

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

	public int getSupplyCountPerDay() {
		return supplyCountPerDay;
	}

	public void setSupplyCountPerDay(int supplyCountPerDay) {
		this.supplyCountPerDay = supplyCountPerDay;
	}


	@Override
	public int compareTo(MeteriaSupplyDetailInfo meteriaSupplyDetailInfo) {
		if (getBeginSupplyTime().before(meteriaSupplyDetailInfo.getBeginSupplyTime())) { 
			return -1; 
		} else { 
			return 0;
		}
		
		
	}
}
