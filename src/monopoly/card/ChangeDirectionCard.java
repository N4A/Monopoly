package monopoly.card;

import java.util.ArrayList;
import java.util.List;

import monopoly.map.Map;
import monopoly.player.Player;
import monopoly.ui.controller.UIController;
import monopoly.util.StrSrc;

/**
 * @author duocai
 * @date 2016年4月20日 下午4:17:30
 */
public class ChangeDirectionCard extends Card {
	
	private final int max = 5;

	/**
	 * 
	 */
	public ChangeDirectionCard() {
		super(StrSrc.changeDirectionCard.getValue(), StrSrc.changeDirectionCardDes.getValue());
	}


	@Override
	public void execute(Player player, Map map, UIController uiController) {
		List<Player> targets = map.getPlayersWithinDistance(player, max);
		
		//show targets
		int i = 0;
		uiController.showMassage(StrSrc.choosePlayerNotice.getValue());
		for (Player player2 : targets) {
			i++;
			uiController.showMassage(i + ":" + player2.getName());
		}
		
		int choice = player.chooseCDPlayer(1, targets.size());
		
		switch (choice) {
		case 0:
			uiController.showMassage(StrSrc.cancelUseCard.getValue());
			break;
		default:
			targets.get(choice-1).changeDirection();
			uiController.showMassage(StrSrc.useCardsuccess.getValue());
			player.rmCard(this);
			break;
		}
		
	}
}
