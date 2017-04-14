package monopoly.building;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import monopoly.map.Cell;
import monopoly.player.Player;
import monopoly.ui.controller.UIController;
import monopoly.util.StrSrc;

/**
 * @author duocai
 * @date 2016年4月23日 下午9:07:30
 */
public class Roadblock extends Creature {

	/**
	 * @param cell
	 * @param type
	 * 2016年4月23日 下午9:07:30
	 */
	public Roadblock(Cell cell) {
		super(cell, CreatureType.roadBlock);
	}

	/* (non-Javadoc)
	 * @see monopoly.building.Creature#toTexture()
	 */
	@Override
	public String toTexture() {
		return StrSrc.roadblock.getValue();
	}

	/* (non-Javadoc)
	 * @see monopoly.building.Creature#execute(monopoly.player.Player, monopoly.ui.controller.UIController)
	 */
	@Override
	public void execute(Player p, UIController uiController) {
		uiController.showMassage(StrSrc.roadblockNotice.getValue());
		this.getCell().rmCreature(this);
	}
	@Override
	public JButton toSwing() {
		JButton button = new JButton(new ImageIcon("icons/barrage.png"));
		//button.addActionListener(infoListener);
		return button;
	}
}
