package pers.alexcon.productproducer.pojo;

public class Idx {
	private final int listLength;
	private int idxInList;

	public Idx(int listLength) {
		this.idxInList = 0;
		this.listLength = listLength;
	}

	public int getIdxInList() {
		if(idxInList >= listLength){
			return -1;
		}
		return idxInList;
	}

	public void setIdxInList(int idxInList) {
		this.idxInList = idxInList;
	}

	public int getListLength() {
		return listLength;
	}

}
