package monopoly.player;

import monopoly.util.StrSrc;

/**
 * @author duocai
 * @date 2016年4月19日 上午9:15:02
 */
public enum PlayerType {
	P1(StrSrc.player1.getValue()),
	P2(StrSrc.player2.getValue()),
	P3(StrSrc.player3.getValue()),
	P4(StrSrc.player4.getValue());
	
	private String value;
	
	private PlayerType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
