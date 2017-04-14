package monopoly.building;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import monopoly.map.Cell;
import monopoly.player.Player;
import monopoly.ui.controller.UIController;
import monopoly.util.StrSrc;

/**
 * @author duocai
 * @date 2016年6月4日 下午1:06:59
 */
public class Hospital extends Creature {

	/**
	 * @param cell
	 * @param type
	 * 2016年6月4日 下午1:06:59
	 */
	public Hospital(Cell cell) {
		super(cell, CreatureType.hospital);
	}

	/* (non-Javadoc)
	 * @see monopoly.building.Creature#toTexture()
	 */
	@Override
	public String toTexture() {
		return StrSrc.hospital.getValue();
	}

	/* (non-Javadoc)
	 * @see monopoly.building.Creature#toSwing()
	 */
	@Override
	public JButton toSwing() {
		JButton button = new JButton(new ImageIcon("icons/hospital.png"));
		button.addActionListener(infoListener);
		return button;
	}

	/* (non-Javadoc)
	 * @see monopoly.building.Creature#execute(monopoly.player.Player, monopoly.ui.controller.UIController)
	 */
	@Override
	public void execute(Player p, UIController uiController) {
		uiController.showMassage(StrSrc.youReach.getValue() + StrSrc.hospitalName.getValue()+"\n");
	}

}
