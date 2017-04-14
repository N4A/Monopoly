package monopoly.card;

import java.util.ArrayList;
import java.util.List;

import monopoly.building.Creature;
import monopoly.map.Map;
import monopoly.player.Player;
import monopoly.ui.controller.UIController;
import monopoly.util.StrSrc;

/**
 * @author duocai
 * @date 2016年4月21日 下午7:38:18
 */
public class PlunderCard extends Card {
	
	private final int max = 5;

	/**
	 * 
	 * 2016年4月21日 下午7:38:18
	 */
	public PlunderCard() {
		super(StrSrc.plunderCard.getValue(), StrSrc.plunderCardDes.getValue());
	}


	@Override
	public void execute(Player player, Map map, UIController uiController) {
		List<Player> players = map.getPlayers();
		List<Player> targets = new ArrayList<>();
		
		for (Player player2 : players) {
			int distance = map.getDistance(player, player2);
			if (distance <= max && !player2.getCards().isEmpty() && player2 != player) {
				targets.add(player2);
			}
		}
		
		//show targets and cards
		int i = 0;
		uiController.showMassage(StrSrc.choosePlayerNotice.getValue());
		for (Player player2 : targets) {
			i++;
			uiController.showMassage(i + ":" + player2.getName());
		}
		
		int choice = player.chooseCDPlayer(0, targets.size());
		
		switch (choice) {
		case 0:
			uiController.showMassage(StrSrc.cancelUseCard.getValue());
			break;
		default:
			Player target = targets.get(choice-1);
			Card card = target.getCards().get(0).get(0);
			target.rmCard(card);
			player.addCard(card);
			uiController.showMassage(StrSrc.useCardsuccess.getValue());
			uiController.showMassage(StrSrc.plunderSuccess.getValue() + card.getName());
			player.rmCard(this);
			break;
		}
		
	}

}
