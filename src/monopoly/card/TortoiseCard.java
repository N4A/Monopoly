package monopoly.card;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import monopoly.map.Map;
import monopoly.player.Player;
import monopoly.ui.controller.UIController;
import monopoly.util.StrSrc;

/**
 * @author duocai
 * @date 2016年4月20日 下午4:20:01
 */
public class TortoiseCard extends Card {
	private final int max = 5;

	/**
	 * 
	 */
	public TortoiseCard() {
		super(StrSrc.tortoiseCard.getValue(), StrSrc.tortoiseCardDes.getValue());
	}


	@Override
	public void execute(Player player, Map map, UIController uiController) {
		List<Player> targets = map.getPlayersWithinDistance(player, max);
		
		//show targets
		int i = 0;
		StringBuffer sb = new StringBuffer(StrSrc.choosePlayerNotice.getValue() + "\n");
		for (Player player2 : targets) {
			i++;
			sb.append(i + ":" + player2.getName()+"\n");
		}
		uiController.showMassage(sb.toString());
		
		int choice = player.chooseCDPlayer(0, targets.size());
		
		switch (choice) {
		case 0:
			uiController.showMassage(StrSrc.cancelUseCard.getValue());
			break;
		default:
			targets.get(choice-1).beTortoise();
			uiController.showMassage(StrSrc.useCardsuccess.getValue());
			player.rmCard(this);
			break;
		}
		
	}
	@Override
	public JButton toSwing() {
		JButton button = new JButton(new ImageIcon("icons/turtle.png"));
		button.setToolTipText(description);
		return button;
	}
}
