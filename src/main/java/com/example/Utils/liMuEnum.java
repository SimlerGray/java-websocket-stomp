package com.example.Utils;

/**
 * Created by apple on 17/7/13.
 */
public enum liMuEnum{
	MOBILE(1),//手机号类型
	CREDIT(2),//央行信用类型
	JD(3),//京东类型
	TAOBAO(4),//淘宝类型
	HOUSEFUND(5),//公积金类型
	SOCIALSECURITY(6),//社保类型
	EDUCATION(7),//学信网类型
	MAIMAI(8),//脉脉类型
	LINKEDIN(9),//领英类型
	BILL(10),//信用卡账单类型
	EBANK(11),//网银类型
	SHIXIN(12),//失信被执行人
	AUTOINSURANCE(13),//车险
	CTRIP(14),//携程
	DIDITAXI(15);//滴滴出行

	private int status;

	private liMuEnum(int status){
		this.status = status;
	}
	public int getStatus(){
		return status;
	}
}

