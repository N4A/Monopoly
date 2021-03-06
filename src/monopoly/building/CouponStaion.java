package monopoly.building;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import monopoly.map.Cell;
import monopoly.player.Player;
import monopoly.ui.controller.UIController;
import monopoly.util.StrSrc;

/**
 * @author duocai
 * @date 2016年4月18日 下午9:34:51
 */
public class CouponStaion extends Creature {

	/**
	 * @param cell
	 */
	public CouponStaion(Cell cell) {
		super(cell, CreatureType.couponStaion);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see monopoly.map.Creature#toTexture()
	 */
	@Override
	public String toTexture() {
		// TODO Auto-generated method stub
		return StrSrc.couponStaion.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see monopoly.map.Creature#execute(monopoly.map.Player)
	 */
	@Override
	public void execute(Player p, UIController uiController) {
		int number = (int) (Math.random() * 5) * 5;// 0 5 10 15 20
		p.setCoupond(p.getCoupond() + number);
		uiController.showMassage(StrSrc.youReach.getValue() + StrSrc.couponStaionName.getValue() + "\n"
				+ StrSrc.congratulateYou.getValue() + StrSrc.get.getValue() + number + StrSrc.coupond.getValue());
	}

	@Override
	public JButton toSwing() {
		JButton button = new JButton(new ImageIcon("icons/point.png"));
		button.addActionListener(infoListener);
		return button;
	}

}
