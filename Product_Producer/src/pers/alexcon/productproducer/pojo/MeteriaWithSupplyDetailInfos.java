package pers.alexcon.productproducer.pojo;

import java.util.List;

public class MeteriaWithSupplyDetailInfos {
	private String meteriaName;
	private List<MeteriaSupplyDetailInfo> meteriaSupplyDetailInfo;

	public String getMeteriaName() {
		return meteriaName;
	}

	public void setMeteriaName(String meteriaName) {
		this.meteriaName = meteriaName;
	}

	public List<MeteriaSupplyDetailInfo> getMeteriaSupplyDetailInfo() {
		return meteriaSupplyDetailInfo;
	}

	public void setMeteriaSupplyDetailInfo(
			List<MeteriaSupplyDetailInfo> meteriaSupplyDetailInfo) {
		this.meteriaSupplyDetailInfo = meteriaSupplyDetailInfo;
	}

	

}