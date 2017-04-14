package monopoly.player;

import monopoly.util.StrSrc;

/**
 * @author duocai
 * @date 2016年4月20日 下午5:00:35
 */
public enum Direction {
	forward(1),back(-1);
	
	private int value;
	
	private Direction(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
	public String toString() {
		switch (value) {
		case 1:
			return StrSrc.clockwise.getValue();
		case -1:
			return StrSrc.anticlockwise.getValue();
		default:
			return StrSrc.clockwise.getValue();
		}
	}
}
