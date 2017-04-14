package monopoly.card;

import monopoly.building.Creature;
import monopoly.map.Map;
import monopoly.player.Player;
import monopoly.ui.controller.UIController;
import monopoly.util.StrSrc;

/**
 * @author duocai
 * @date 2016年4月21日 下午7:39:00
 */
public class LandCard extends Card {

	/**
	 * 
	 * 2016年4月21日 下午7:39:00
	 */
	public LandCard() {
		super(StrSrc.landCard.getValue(), null);
	}


	@Override
	public void execute(Player player, Map map, UIController uiController) {
		// TODO Auto-generated method stub
		
	}

}
