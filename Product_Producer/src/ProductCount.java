import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pers.alexcon.productproducer.pojo.Meteria;
import pers.alexcon.productproducer.pojo.MeteriaSupplyDetailInfo;
import pers.alexcon.productproducer.pojo.Product;
import pers.alexcon.productproducer.pojo.ProductSupplyDetailInfo;
import pers.alexcon.productproducer.util.ProductProduceCountInfoCaculation;

public class ProductCount {
	private static Calendar calendar = Calendar.getInstance();
	private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static List<Meteria> meteriaList = new ArrayList<Meteria>();

	public static void main(String[] args) {
		Product product = new Product("98100201");
		Map<String, Integer> meteriasInfo = new HashMap<String, Integer>();
		meteriasInfo.put(Const.RAW_ROSE_005, 14);
		meteriasInfo.put(Const.CAPACITY, 1);
		product.setMeteriasInfo(meteriasInfo);

		meteriaList.add(getMeteria(Const.RAW_EUCALYPTUS_001,
				"2014-02-04 00:00:00", "2014-11-30 00:00:00", 6000));
		meteriaList.add(getMeteria(Const.RAW_EUCALYPTUS_001,
				"2015-02-01 00:00:00", "2038-01-19 00:00:00", 6000));
		meteriaList.add(getMeteria(Const.RAW_ROSE_005, "2014-10-01 00:00:00",
				"2014-10-31 00:00:00", 18));
		meteriaList.add(getMeteria(Const.RAW_ROSE_005, "2015-01-01 00:00:00",
				"2015-01-31 00:00:00", 666));
		meteriaList.add(getMeteria(Const.CAPACITY, "2014-02-04 00:00:00",
				"2015-01-15 00:00:00", 6000));

		ProductProduceCountInfoCaculation productCountCalc = new ProductProduceCountInfoCaculation();
		productCountCalc.calculateProductProduceInfo(product, meteriaList);
		System.out.println("product 98100201 outcome:");
		for (ProductSupplyDetailInfo info : product.getProductInfo()) {
			System.out.println(product.getId() + "	"
					+ df.format(info.getBeginSupplyTime().getTime()) + "	"
					+ df.format(info.getEndSupplyTime().getTime()) + "	"
					+ info.getProducedCountPerDay());
		}

		System.out.println("=========================================");

		product = new Product("98102601");
		meteriasInfo = new HashMap<String, Integer>();
		meteriasInfo.put(Const.RAW_ROSE_005, 12);
		meteriasInfo.put(Const.CAPACITY, 1);
		meteriasInfo.put(Const.RAW_EUCALYPTUS_001, 14);
		meteriaList.add(getMeteria(Const.RAW_EUCALYPTUS_001,
				"2014-02-04 00:00:00", "2014-11-30 00:00:00", 6000));
		meteriaList.add(getMeteria(Const.RAW_EUCALYPTUS_001,
				"2015-02-01 00:00:00", "2038-01-19 00:00:00", 6000));
		meteriaList.add(getMeteria(Const.RAW_ROSE_005, "2014-10-01 00:00:00",
				"2014-10-31 00:00:00", 18));
		meteriaList.add(getMeteria(Const.RAW_ROSE_005, "2015-01-01 00:00:00",
				"2015-01-31 00:00:00", 666));
		meteriaList.add(getMeteria(Const.CAPACITY, "2014-02-04 00:00:00",
				"2015-01-15 00:00:00", 6000));
		product.setMeteriasInfo(meteriasInfo);
		productCountCalc.calculateProductProduceInfo(product, meteriaList);
		System.out.println("product 98102601 outcome:");
		for (ProductSupplyDetailInfo info : product.getProductInfo()) {
			System.out.println(product.getId() + "	"
					+ df.format(info.getBeginSupplyTime().getTime()) + "	"
					+ df.format(info.getEndSupplyTime().getTime()) + "	"
					+ info.getProducedCountPerDay());
		}
		List<MeteriaSupplyDetailInfo> fs = new ArrayList<MeteriaSupplyDetailInfo>();

		MeteriaSupplyDetailInfo f = new MeteriaSupplyDetailInfo();
		calendar = Calendar.getInstance();
		try {
			calendar.setTime(df.parse("2015-02-01 00:00:00"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		f.setBeginSupplyTime(calendar);
		f.setSupplyCountPerDay(1);
		fs.add(f);
		f = new MeteriaSupplyDetailInfo();
		calendar = Calendar.getInstance();
		try {
			calendar.setTime(df.parse("2015-01-16 00:00:00"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		f.setBeginSupplyTime(calendar);
		f.setSupplyCountPerDay(2);
		fs.add(f);
		for (int c = 0; c < fs.size(); c++) {

			System.out.println(fs.get(c).getSupplyCountPerDay());
		}

		
		Collections.sort(fs);

		for (int c = 0; c < fs.size(); c++) {

			System.out.println(fs.get(c).getSupplyCountPerDay());
		}

	}

	private static Meteria getMeteria(String mrtName, String beginT,
			String endT, int cnt) {
		Meteria meteria = new Meteria(mrtName);
		try {
			meteria.setMeteriaSupplyDetailInfo(getMeteriaSupplyDetailInfo(
					beginT, endT, cnt));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return meteria;
	}

	private static MeteriaSupplyDetailInfo getMeteriaSupplyDetailInfo(
			String beginT, String endt, int cnt) throws ParseException {
		MeteriaSupplyDetailInfo mtrInfo = new MeteriaSupplyDetailInfo();
		calendar = Calendar.getInstance();
		calendar.setTime(df.parse(beginT));
		mtrInfo.setBeginSupplyTime(calendar);
		calendar = Calendar.getInstance();
		calendar.setTime(df.parse(endt));
		mtrInfo.setEndSupplyTime(calendar);
		mtrInfo.setSupplyCountPerDay(cnt);
		return mtrInfo;
	}

}
