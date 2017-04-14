package monopoly.card;

import monopoly.map.Map;
import monopoly.player.Player;
import monopoly.ui.controller.UIController;
import monopoly.util.StrSrc;

/**
 * @author duocai
 * @date 2016年4月20日 下午4:19:13
 */
public class StayCard extends Card {

	/**
	 * 
	 */
	public StayCard() {
		super(StrSrc.stayCard.getValue(), StrSrc.stayCardDes.getValue());
	}

	@Override
	public void execute(Player player, Map map, UIController uiController) {
		player.rmCard(this);
		uiController.showMassage(StrSrc.useCardsuccess.getValue());
		player.triggerEvent(player.getCell());
	}

}
