package monopoly.building;

import monopoly.map.Cell;
import monopoly.player.Player;
import monopoly.ui.controller.UIController;
import monopoly.util.StrSrc;

/**
 * @author duocai
 * @date 2016年4月18日 下午10:17:55
 * background
 */
public class Blank extends Creature {

	/**
	 * @param cell
	 */
	public Blank(Cell cell) {
		super(cell,CreatureType.blank);
	}

	/* (non-Javadoc)
	 * @see monopoly.map.Creature#toTexture()
	 */
	@Override
	public String toTexture() {
		// TODO Auto-generated method stub
		return StrSrc.blank.getValue();
	}

	/* (non-Javadoc)
	 * @see monopoly.map.Creature#execute(monopoly.player.Player)
	 */
	@Override
	public void execute(Player p, UIController uiController) {
	}

}
