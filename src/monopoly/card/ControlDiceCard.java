package monopoly.card;

import monopoly.map.Map;
import monopoly.player.Player;
import monopoly.ui.controller.UIController;
import monopoly.util.StrSrc;

/**
 * @author duocai
 * @date 2016年4月20日 下午4:20:54
 */
public class ControlDiceCard extends Card {

	/**
	 * 
	 */
	public ControlDiceCard() {
		super(StrSrc.controlDiceCard.getValue(), StrSrc.controlDiceCardDes.getValue());
	}

	
	@Override
	public void execute(Player player, Map map, UIController uiController) {
		uiController.showMassage(StrSrc.inputDice.getValue());
		int dice = player.chooseDice(1,6);
		player.setControlDice(dice);
		player.rmCard(this);
		uiController.showMassage(StrSrc.useCardsuccess.getValue());
	}

}
