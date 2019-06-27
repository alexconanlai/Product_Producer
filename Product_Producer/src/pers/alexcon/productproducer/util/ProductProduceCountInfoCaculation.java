package pers.alexcon.productproducer.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import pers.alexcon.productproducer.pojo.Idx;
import pers.alexcon.productproducer.pojo.Meteria;
import pers.alexcon.productproducer.pojo.MeteriaSupplyDetailInfo;
import pers.alexcon.productproducer.pojo.MeteriaWithSupplyDetailInfos;
import pers.alexcon.productproducer.pojo.Product;
import pers.alexcon.productproducer.pojo.ProductSupplyDetailInfo;

public class ProductProduceCountInfoCaculation {
	private static final int INIT_LIST_LENGTH = 8;
	private static final int INIT_MAP_LENGTH = 8;

	public Product calculateProductProduceInfo(Product product,
			List<Meteria> providedMeteriasInfo) {
		if (product == null || providedMeteriasInfo == null
				|| providedMeteriasInfo.isEmpty()) {
			return null;
		}

		List<Meteria> meteriasBelongTheProduct = getMeteriasBelongToTheProduct(
				product, providedMeteriasInfo);
		if (meteriasBelongTheProduct == null
				|| meteriasBelongTheProduct.isEmpty()) {
			return null;
		}

		List<MeteriaWithSupplyDetailInfos> meteriaListWithAllSupplyInfos = createMeteriaWithAllSupplyInfos(
				product, meteriasBelongTheProduct);
		if (meteriaListWithAllSupplyInfos.isEmpty()) {
			return null;
		}

		return calProductCount(product, meteriasBelongTheProduct,
				meteriaListWithAllSupplyInfos);
	}

	private Product calProductCount(Product product,
			List<Meteria> meteriasBelongTheProduct,
			List<MeteriaWithSupplyDetailInfos> meteriaListWithAllSupplyInfos) {

		Map<String, Idx> idxOfMeteria = new HashMap<String, Idx>();
		Idx length_Idx = null;
		for (MeteriaWithSupplyDetailInfos meteriaNameAndList : meteriaListWithAllSupplyInfos) {
			length_Idx = new Idx(meteriaNameAndList
					.getMeteriaSupplyDetailInfo().size());
			idxOfMeteria.put(meteriaNameAndList.getMeteriaName(), length_Idx);
		}

		String firstMeteria = meteriaListWithAllSupplyInfos.get(0)
				.getMeteriaName();
		int meteriaLength = meteriaListWithAllSupplyInfos.size();

		// for save time of two row both have meteria
		Calendar curMaxMeteriaBeginDate = null;
		Calendar curMinMeteriaEndDate = null;

		// for save the product cnt can be produced
		int minCount = 0;

		Calendar rowMeteriaBeginDate = null;
		Calendar rowMeteriaEndDate = null;

		boolean isAllNeedHaved = false;

		String loopCurMeteria = "";
		int idxInList = 0, firstRowIdxInList = 0;
		MeteriaSupplyDetailInfo meteriaListWithAllSupplyInfo = null;
		while (true) {
			firstRowIdxInList = idxOfMeteria.get(firstMeteria).getIdxInList();
			if (firstRowIdxInList < 0) {
				return product;
			}
			curMaxMeteriaBeginDate = meteriaListWithAllSupplyInfos.get(0)
					.getMeteriaSupplyDetailInfo().get(firstRowIdxInList)
					.getBeginSupplyTime();
			curMinMeteriaEndDate = meteriaListWithAllSupplyInfos.get(0)
					.getMeteriaSupplyDetailInfo().get(firstRowIdxInList)
					.getEndSupplyTime();
			minCount = meteriaListWithAllSupplyInfos.get(0)
					.getMeteriaSupplyDetailInfo().get(firstRowIdxInList)
					.getSupplyCountPerDay()
					/ product.getMeteriasInfo().get(firstMeteria);

			isAllNeedHaved = true;
			for (int idxOfMeteriaList = 1; idxOfMeteriaList < meteriaLength; idxOfMeteriaList++) {
				loopCurMeteria = meteriaListWithAllSupplyInfos.get(
						idxOfMeteriaList).getMeteriaName();
				idxInList = idxOfMeteria.get(loopCurMeteria).getIdxInList();
				if (idxInList < 0) {
					return product;
				}
				meteriaListWithAllSupplyInfo = meteriaListWithAllSupplyInfos
						.get(idxOfMeteriaList).getMeteriaSupplyDetailInfo()
						.get(idxInList);
				rowMeteriaBeginDate = meteriaListWithAllSupplyInfo
						.getBeginSupplyTime();
				rowMeteriaEndDate = meteriaListWithAllSupplyInfo
						.getEndSupplyTime();

				if (curMinMeteriaEndDate.compareTo(rowMeteriaBeginDate) < 0) {// no meteria
					idxOfMeteria.get(firstMeteria).setIdxInList(
							firstRowIdxInList + 1);
					isAllNeedHaved = false;
					break;
				} else if (curMaxMeteriaBeginDate.compareTo(rowMeteriaBeginDate) <= 0
						&& curMinMeteriaEndDate.compareTo(rowMeteriaBeginDate) >= 0
						&& curMinMeteriaEndDate.compareTo(rowMeteriaEndDate) <= 0) {
					idxOfMeteria.get(firstMeteria).setIdxInList(
							firstRowIdxInList + 1);
					curMaxMeteriaBeginDate = rowMeteriaBeginDate;
					minCount = resetCount(minCount,
							meteriaListWithAllSupplyInfo, product,
							loopCurMeteria);
				} else if (curMaxMeteriaBeginDate.compareTo(rowMeteriaBeginDate) >= 0
						&& curMinMeteriaEndDate.compareTo(rowMeteriaEndDate) <= 0) {
					idxOfMeteria.get(firstMeteria).setIdxInList(
							firstRowIdxInList + 1);
					minCount = resetCount(minCount,
							meteriaListWithAllSupplyInfo, product,
							loopCurMeteria);
				} else if (curMaxMeteriaBeginDate.compareTo(rowMeteriaBeginDate) >= 0
						&& curMaxMeteriaBeginDate.compareTo(rowMeteriaEndDate) <= 0
						&& curMinMeteriaEndDate.compareTo(rowMeteriaEndDate) >= 0) {
					idxOfMeteria.get(loopCurMeteria)
							.setIdxInList(idxInList + 1);
					curMinMeteriaEndDate = rowMeteriaEndDate;
					minCount = resetCount(minCount,
							meteriaListWithAllSupplyInfo, product,
							loopCurMeteria);
				} else if (curMaxMeteriaBeginDate.compareTo(rowMeteriaEndDate) > 0) {//no meteria
					idxOfMeteria.get(loopCurMeteria)
							.setIdxInList(idxInList + 1);
					isAllNeedHaved = false;
					break;
				} else if (curMaxMeteriaBeginDate.compareTo(rowMeteriaBeginDate) <= 0
						&& curMinMeteriaEndDate.compareTo(rowMeteriaEndDate) >= 0) {
					idxOfMeteria.get(loopCurMeteria)
							.setIdxInList(idxInList + 1);
					curMaxMeteriaBeginDate = rowMeteriaBeginDate;
					curMinMeteriaEndDate = rowMeteriaEndDate;
					minCount = resetCount(minCount,
							meteriaListWithAllSupplyInfo, product,
							loopCurMeteria);
				} else {
					isAllNeedHaved = false;
					idxOfMeteria.get(loopCurMeteria)
							.setIdxInList(idxInList + 1);
				}
			}
			
			if (isAllNeedHaved) {
				ProductSupplyDetailInfo productInfo = new ProductSupplyDetailInfo();
				productInfo.setBeginSupplyTime(curMaxMeteriaBeginDate);
				productInfo.setEndSupplyTime(curMinMeteriaEndDate);
				productInfo.setProducedCountPerDay(minCount);
				product.getProductInfo().add(productInfo);
			}
		}
	}

	private int resetCount(int minCount,
			MeteriaSupplyDetailInfo meteriaListWithAllSupplyInfo,
			Product product, String loopCurMeteria) {
		int count = meteriaListWithAllSupplyInfo.getSupplyCountPerDay()
				/ product.getMeteriasInfo().get(loopCurMeteria);
		return count < minCount ? count : minCount;

	}

	
	private List<MeteriaWithSupplyDetailInfos> createMeteriaWithAllSupplyInfos(
			Product product, List<Meteria> meteriasBelongTheProduct) {
		Map<String, List<MeteriaSupplyDetailInfo>> meteriaWithAllSupplyInfos = new HashMap<String, List<MeteriaSupplyDetailInfo>>(
				INIT_MAP_LENGTH);

		for (Meteria meteria : meteriasBelongTheProduct) {
			if (meteriaWithAllSupplyInfos.containsKey(meteria.getMeteriaName())) {
				meteriaWithAllSupplyInfos.get(meteria.getMeteriaName()).add(
						meteria.getMeteriaSupplyDetailInfo());
			} else {
				List<MeteriaSupplyDetailInfo> meteriaWithAllSupplyInfoList = new ArrayList<MeteriaSupplyDetailInfo>(
						INIT_LIST_LENGTH);
				meteriaWithAllSupplyInfoList.add(meteria
						.getMeteriaSupplyDetailInfo());
				meteriaWithAllSupplyInfos.put(meteria.getMeteriaName(),
						meteriaWithAllSupplyInfoList);
			}
		}
		// no enough kinds of meteria
		if (meteriaWithAllSupplyInfos.size() != product.getMeteriasInfo()
				.size()) {
			return null;
		}

		for (Entry<String, List<MeteriaSupplyDetailInfo>> meteriaWithAllSupplyInfo : meteriaWithAllSupplyInfos
				.entrySet()) {
			Collections.sort(meteriaWithAllSupplyInfo.getValue());
		}

		MeteriaWithSupplyDetailInfos meteriaWithAllSupplyInfo = new MeteriaWithSupplyDetailInfos();
		List<MeteriaWithSupplyDetailInfos> meteriaWithAllSupplyInfoList = new ArrayList<MeteriaWithSupplyDetailInfos>(
				INIT_LIST_LENGTH);

		for (Entry<String, List<MeteriaSupplyDetailInfo>> meteriaNameAndList : meteriaWithAllSupplyInfos
				.entrySet()) {
			meteriaWithAllSupplyInfo
					.setMeteriaName(meteriaNameAndList.getKey());
			meteriaWithAllSupplyInfo
					.setMeteriaSupplyDetailInfo(meteriaNameAndList.getValue());
			meteriaWithAllSupplyInfoList.add(meteriaWithAllSupplyInfo);
			meteriaWithAllSupplyInfo = new MeteriaWithSupplyDetailInfos();
		}

		return meteriaWithAllSupplyInfoList;
	}

	private List<Meteria> getMeteriasBelongToTheProduct(Product product,
			List<Meteria> providedMeteriasInfo) {
		List<Meteria> meteriasBelongToTheProduct = new ArrayList<Meteria>(
				INIT_LIST_LENGTH);

		Set<String> meteriasForProduceTheProduct = product.getMeteriasInfo()
				.keySet();
		int length = providedMeteriasInfo.size();
		Meteria meteria = null;
		for (String meteriaForTheProduct : meteriasForProduceTheProduct) {
			for (int idx = 0; idx < length; idx++) {
				meteria =  providedMeteriasInfo.get(idx);
				if(meteria == null){
					continue;
				}
				if (meteria.getMeteriaName()
						.equals(meteriaForTheProduct)) {
					meteriasBelongToTheProduct.add(providedMeteriasInfo
							.get(idx));
					providedMeteriasInfo.set(idx, null);
				}
			}
		}

		providedMeteriasInfo.clear(); // for save memory

		return meteriasBelongToTheProduct;
	}

}
