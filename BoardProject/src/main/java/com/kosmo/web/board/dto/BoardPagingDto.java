package com.kosmo.web.board.dto;

public class BoardPagingDto {
	private int currentPage = 1; //���� ������
	private int totalRecord; //�� ���ڵ��
	private int totalPageCount; //�� ��������
	private int startPageNum; //���� ������ ��ȣ
	private int onePageRecord = 10; //�� �������� ���ڵ� ��
	private int onePageCount = 10; //�� �������� ǥ���� ������ ��
	private int rownumEnd; //������ ������ ���ڵ�
	private int lastPageRecord = 10; //������ ������ ���ڵ� ��

	public void makePaging(int currentPage, int totalRecord) {
		this.lastPageRecord = onePageRecord;
		this.currentPage = currentPage;
		this.totalRecord = totalRecord;
		setTotalPageCount();
		setStartPageNum();
		setRownumEnd();
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getTotalPageCount() {
		return totalPageCount;
	}
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	
	//���������� ���ϱ�
	public void setTotalPageCount(){ 
		this.totalPageCount = (int)Math.ceil(totalRecord/(double)onePageRecord);
	}
	
	public int getStartPageNum() {
		return startPageNum;
	}
	public void setStartPageNum(int startPageNum) {
		this.startPageNum = startPageNum;
	}
	
	//����������
	public void setStartPageNum(){
		if(currentPage%onePageCount == 0){
			startPageNum = currentPage - onePageCount + 1;
		}else{			            
			startPageNum = currentPage/onePageCount*onePageCount + 1;
		}
	}
	
	public int getOnePageRecord() {
		return onePageRecord;
	}
	public void setOnePageRecord(int onePageRecord) {
		this.onePageRecord = onePageRecord;
	}
	public int getOnePageCount() {
		return onePageCount;
	}
	public void setOnePageCount(int onePageCount) {
		this.onePageCount = onePageCount;
	}
	public int getRownumEnd() {
		return rownumEnd;
	}
	public void setRownumEnd(int rownumEnd) {
		this.rownumEnd = rownumEnd;
	}
	
	//������ ������ ���ڵ�
	public void setRownumEnd() {
		rownumEnd = currentPage * onePageRecord;
	}
	
	public int getLastPageRecord() {
		return lastPageRecord;
	}
	public void setLastPageRecord(int lastPageRecord) {
		this.lastPageRecord = lastPageRecord;
	}

}
