package pers.alexcon.productproducer.pojo;


public class Meteria {
	private String meteriaName;
	private MeteriaSupplyDetailInfo meteriaSupplyDetailInfo;

	public Meteria(String productName) {
		super();
		this.meteriaName = productName;
	}

	public String getMeteriaName() {
		return meteriaName;
	}

	public void setMeteriaName(String meteriaName) {
		this.meteriaName = meteriaName;
	}

	public MeteriaSupplyDetailInfo getMeteriaSupplyDetailInfo() {
		return meteriaSupplyDetailInfo;
	}

	public void setMeteriaSupplyDetailInfo(
			MeteriaSupplyDetailInfo meteriaSupplyDetailInfo) {
		this.meteriaSupplyDetailInfo = meteriaSupplyDetailInfo;
	}



}
