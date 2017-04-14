package monopoly.player;

import monopoly.building.LandType;
import monopoly.map.Cell;
import monopoly.map.Map;
import monopoly.ui.controller.UIController;
import monopoly.util.StrSrc;

/**
 * @author duocai
 * @date 2016年4月20日 下午5:13:23
 * real player
 */
public class RPlayer extends Player {

	/**
	 * @param cell
	 * @param playerType
	 * 2016年4月20日 下午5:13:23
	 */
	public RPlayer(Map map,Cell cell, UIController uiController, PlayerType playerType, String name, LandType landType) {
		super(map, cell, uiController, playerType, name, landType);
		// TODO Auto-generated constructor stub
	}

	

	@Override
	protected int inputInt(int start, int end) {
		return uiController.getInt(start, end);
	}

	@Override
	protected String inputString(int minLen, int maxLen) {
		return uiController.getString(minLen, maxLen);
	}



	@Override
	public int operate(int min, int max) {
		return inputInt(min, max);
	}



	@Override
	public String chooseBuyLand() {
		return inputString(1, 1);// y or s
	}



	@Override
	public String chooseUpgradeLand() {
		return inputString(1, 1);//y or s
	}



	@Override
	public int chooseCard(int min, int max) {
		return inputInt(min, max);
	}



	@Override
	public String selectBankOperation() {
		return inputString(1, 1);//w,x,s
	}



	@Override
	public int withDrawMoney(int min, int max) {
		return inputInt(min, max);
	}



	@Override
	public int saveMoney(int min, int max) {
		return inputInt(min, max);
	}



	@Override
	public int chooseBuyCard(int min, int max) {
		return inputInt(min, max);
	}



	@Override
	public String chooseBuyLottery() {
		return inputString(1, 1);//y or s
	}



	@Override
	public String chooseDitance() {
		return inputString(1, 3);
	}



	@Override
	public int chooseCDPlayer(int min, int max) {
		return inputInt(min, max);
	}



	@Override
	public int chooseDice(int min, int max) {
		return inputInt(min, max);
	}



	@Override
	public int chooseRoadblockDistance(int min, int max) {
		return inputInt(min, max);
	}



	@Override
	public String handleStock(int min, int max) {
		return inputString(min, max);
	}



	@Override
	public String getInfo() {
		return StrSrc.type.getValue() + ":" + type.getValue();
	}
	
}
