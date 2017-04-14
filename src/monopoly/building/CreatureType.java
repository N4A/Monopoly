package monopoly.building;

import monopoly.util.StrSrc;

/**
 * @author duocai
 * @date 2016年4月22日 下午12:51:14
 */
public enum CreatureType {
	bank(StrSrc.bankName.getValue()),
	toolStore(StrSrc.toolStoreName.getValue()),
	lotteryStore(StrSrc.lotteryStoreName.getValue()),
	blank(StrSrc.landNoneName.getValue()),
	cardStation(StrSrc.cardStationName.getValue()),
	couponStaion(StrSrc.couponStaionName.getValue()),
	land(StrSrc.landName.getValue()),
	Player(StrSrc.playerName.getValue()),
	news(StrSrc.newsName.getValue()), 
	roadBlock(StrSrc.roadblock.getValue());
	
	private String value;
	
	private CreatureType(String value){
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
