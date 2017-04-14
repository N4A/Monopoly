package monopoly.building;

import monopoly.util.StrSrc;

/**
 * @author duocai
 * @date 2016年4月18日 下午9:25:28
 */
public enum LandType {
	onSell(StrSrc.landOnsell.getValue()),
	none(StrSrc.landNone.getValue()),
	p1(StrSrc.landP1.getValue()),
	p2(StrSrc.landP2.getValue()),
	p3(StrSrc.landP3.getValue()),
	p4(StrSrc.landP4.getValue());
	
	private String value;
	
	private LandType(String value){
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
