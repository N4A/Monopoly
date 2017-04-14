package monopoly.card;

import java.util.List;

import monopoly.map.Map;
import monopoly.player.Player;
import monopoly.ui.controller.UIController;
import monopoly.util.StrSrc;

/**
 * @author duocai
 * @date 2016年4月21日 下午7:36:54
 */
public class AverageCashCard extends Card {

	/**
	 * 
	 * 2016年4月21日 下午7:36:54
	 */
	public AverageCashCard() {
		super(StrSrc.averageCashCard.getValue(), StrSrc.averageCashCardDes.getValue());
	}

	@Override
	public void execute(Player player, Map map, UIController uiController) {
		List<Player> players = map.getPlayers();
		int toalCash = 0;
		//calculate total cash
		for (Player player2 : players) {
			toalCash+=player2.getCash();
		}
		//average
		int average = toalCash/players.size();
		for (Player player2 : players) {
			player2.setCash(average);
		}
		
		player.rmCard(this);
		uiController.showMassage(StrSrc.useCardsuccess.getValue() + name);
	}
}
