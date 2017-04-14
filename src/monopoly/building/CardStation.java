package monopoly.building;

import monopoly.card.Card;
import monopoly.map.Cell;
import monopoly.player.Player;
import monopoly.ui.controller.UIController;
import monopoly.util.StrSrc;

/**
 * @author duocai
 * @date 2016年4月18日 下午9:35:35
 */
public class CardStation extends Creature {

	/**
	 * @param cell
	 */
	public CardStation(Cell cell) {
		super(cell, CreatureType.cardStation);
	}

	/* (non-Javadoc)
	 * @see monopoly.map.Creature#toTexture()
	 */
	@Override
	public String toTexture() {
		// TODO Auto-generated method stub
		return StrSrc.cardStation.getValue();
	}

	/* (non-Javadoc)
	 * @see monopoly.map.Creature#execute(monopoly.map.Player)
	 */
	@Override
	public void execute(Player p, UIController uiController) {
		uiController.showMassage(StrSrc.youReach.getValue() + StrSrc.cardStationName.getValue());
		Card card = ToolStore.getRandomCard();
		p.addCard(card);
		uiController.showMassage(StrSrc.congratulateYou.getValue()+ StrSrc.get.getValue() +StrSrc.oneCard.getValue()+card.getName());
	}

}
