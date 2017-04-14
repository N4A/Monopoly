package monopoly.card;

import javax.swing.JButton;

import monopoly.map.Map;
import monopoly.player.Player;
import monopoly.ui.controller.UIController;
import monopoly.util.StrSrc;

/**
 * @author duocai
 * @date 2016年4月21日 下午7:45:12
 */
public class BillikenCard extends Card {

	/**
	 * 
	 * 2016年4月21日 下午7:45:12
	 */
	public BillikenCard() {
		super(StrSrc.billikenCard.getValue(), null);
	}

	@Override
	public void execute(Player player, Map map, UIController uiController) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JButton toSwing() {
		// TODO Auto-generated method stub
		return null;
	}

}
