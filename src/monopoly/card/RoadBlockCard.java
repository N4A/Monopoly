package monopoly.card;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import monopoly.building.Roadblock;
import monopoly.map.Cell;
import monopoly.map.Map;
import monopoly.player.Player;
import monopoly.ui.controller.UIController;
import monopoly.util.StrSrc;

/**
 * @author duocai
 * @date 2016年4月20日 下午4:22:11
 */
public class RoadBlockCard extends Card {

	/**
	 * 
	 */
	public RoadBlockCard() {
		super(StrSrc.roadblockCard.getValue(), StrSrc.roadBlockCardDes.getValue());
	}


	@Override
	public void execute(Player player, Map map, UIController uiController) {
		uiController.showMassage(StrSrc.roadblockInput.getValue());
		int choice = player.chooseRoadblockDistance(-8,8)*player.getDirection().getValue();
		if (choice == 0) {
			uiController.showMassage(StrSrc.noBlockHere.getValue());
			return ;
		}
		Cell cell = map.getCellAt(player.getCell(), choice);
		cell.addCreature(new Roadblock(cell));
		player.rmCard(this);
		uiController.showMassage(StrSrc.useCardsuccess.getValue());
	}


	@Override
	public JButton toSwing() {
		JButton button = new JButton(new ImageIcon("icons/barricades.png"));
		button.setToolTipText(description);
		return button;
	}

}
