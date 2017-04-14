package monopoly.building;

import monopoly.card.AverageCashCard;
import monopoly.card.BuyLandCard;
import monopoly.card.Card;
import monopoly.card.ChangeDirectionCard;
import monopoly.card.ControlDiceCard;
import monopoly.card.PlunderCard;
import monopoly.card.RoadBlockCard;
import monopoly.card.StayCard;
import monopoly.card.TortoiseCard;
import monopoly.map.Cell;
import monopoly.player.Player;
import monopoly.ui.controller.UIController;
import monopoly.util.StrSrc;

/**
 * @author duocai
 * @date 2016年4月18日 下午9:28:55
 */
public class ToolStore extends Creature {

	private final int price = 5;
	private final static Card[] cards = { new AverageCashCard(), new ChangeDirectionCard(), new RoadBlockCard(),
			new PlunderCard(), new StayCard(), new BuyLandCard(), new ControlDiceCard(), new TortoiseCard()};

	/**
	 * @param cell
	 */
	public ToolStore(Cell cell) {
		super(cell, CreatureType.toolStore);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see monopoly.map.Creature#toTexture()
	 */
	@Override
	public String toTexture() {
		return StrSrc.toolStore.getValue();
	}

	@Override
	public void execute(Player p, UIController uiController) {
		uiController.showMassage(StrSrc.youReach.getValue() + StrSrc.toolStoreName.getValue());

		// show cards;
		for (int i = 0; i < cards.length; i++) {
			uiController.showMassage((i + 1) + "." + cards[i].getName() + ": " + price + StrSrc.coupond.getValue());
		}


		while (true) {
			uiController.showMassage(StrSrc.chooseBuyCard.getValue());

			int choice = p.chooseBuyCard(0,cards.length);// o return
			
			if (choice == 0) {
				uiController.showMassage(StrSrc.welcomeNext.getValue());
				break;
			}
			else {
				if (p.getCoupond() < price) {
					uiController.showMassage(StrSrc.noCoupond.getValue());
					continue;
				}
				p.setCoupond(p.getCoupond() - price);
				p.addCard(cards[choice-1]);
				uiController.showMassage(StrSrc.youBuy.getValue() + StrSrc.oneCard.getValue() + cards[choice-1].getName());
			}
		
		}

	}
	
	/**
	 * get one card randomly
	 * @return
	 * 2016年4月22日 上午10:56:39
	 */
	public static Card getRandomCard() {
		int random = (int)(Math.random()*cards.length);
		return cards[random];
	}

}
