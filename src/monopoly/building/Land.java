package monopoly.building;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import monopoly.map.Cell;
import monopoly.player.Player;
import monopoly.ui.controller.UIController;
import monopoly.util.StrSrc;

/**
 * @author duocai
 * @date 2016年4月18日 9:27:03
 */
public class Land extends Creature {

	LandType landType;
	Player owner;
	int price;
	int level;

	/**
	 * @param cell
	 */
	public Land(Cell cell, LandType type) {
		super(cell, CreatureType.land);
		this.landType = type;
		this.price = 1000;
		this.level = 1;
	}

	/**
	 * 
	 * @param cell
	 * @param type
	 * @param level
	 *            2016年4月22日 上午11:46:55
	 */
	public Land(Cell cell, LandType type, int level) {
		super(cell, CreatureType.land);
		this.landType = type;
		this.level = level;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see monopoly.map.Creature#toTexture()
	 */
	@Override
	public String toTexture() {
		return landType.getValue();
	}

	@Override
	public void execute(Player p, UIController uiController) {
		// switch does not support here for p's land type is changeable
		if (landType == LandType.onSell) {
			sellLand(p, uiController);
		} else if (landType == p.getLandType()) {
			upgradeLand(p, uiController);
		} else if (landType == LandType.none) {
			uiController.showMassage(StrSrc.youReach.getValue() + StrSrc.landNoneName.getValue());
		} else {
			payForLand(p, uiController);
		}
	}

	/**
	 * sell this land
	 * 
	 * @param p
	 * @param uiController
	 *            2016年4月22日 下午4:14:40
	 */
	private void sellLand(Player p, UIController uiController) {
		int cost = price * level;

		uiController.showMassage(StrSrc.youReach.getValue() + StrSrc.landOnsellName.getValue()+"\n"
		+StrSrc.whetherSpend.getValue() + price + StrSrc.buyland.getValue()+"\n"
				+StrSrc.landInputNotice.getValue());

		boolean success = false;// donate whether the operation is valid
		while (!success) {
			String s = p.chooseBuyLand();
			switch (s) {
			case "y": {
				if (p.getCash() < cost) {
					uiController.showMassage(StrSrc.noCash.getValue());
				} else {
					p.setCash(p.getCash() - cost);
					this.setOwner(p);
					this.setType(p.getLandType());
					p.addLand(this);
					uiController.showMassage(StrSrc.landSellSuccess.getValue() + level + StrSrc.landLevel.getValue());
					success = true;
				}
				break;
			}
			case "n": {// do nothing
				success = true;
				break;
			}
			default:// invalid input
				success = false;
				uiController.showMassage(StrSrc.invalidInput.getValue());
				break;
			}
		}

	}

	/**
	 * upgrade the land
	 * 
	 * @param p
	 * @param uiController
	 *            2016年4月27日 上午10:12:02
	 */
	private void upgradeLand(Player p, UIController uiController) {
		int cost = (int) (price * 0.5);

		uiController.showMassage(
				StrSrc.youReach.getValue() + StrSrc.landOfSelf.getValue() + "\n" 
				+ StrSrc.whetherSpend.getValue() + cost+ StrSrc.upgradeLand.getValue() + "\n" 
				+ StrSrc.landInputNotice.getValue());

		boolean success = false;// control whether to break loop

		while (!success) {
			String s = p.chooseUpgradeLand();
			switch (s) {
			case "y": {
				if (p.getCash() < cost) {
					uiController.showMassage(StrSrc.noCash.getValue());
				} else {
					if (level >= 6) {
						uiController.showMassage(StrSrc.topLevel.getValue());
					} else {
						p.setCash(p.getCash() - cost);
						level++;
						uiController.showMassage(
								StrSrc.landUpgradeSeccess.getValue() + level + StrSrc.landLevel.getValue());
						success = true;
					}
				}
				break;
			}
			case "n": {
				success = true;
				break;
			}
			default:
				uiController.showMassage(StrSrc.invalidInput.getValue());
				success = false;
				break;
			}
		}
	}

	private void payForLand(Player p, UIController uiController) {
		int paid = p.payForLand(this);
		uiController.showMassage(StrSrc.payToOwner.getValue() + owner.getName() + paid + StrSrc.landFee.getValue());
	}

	@Override
	public String getInfo() {
		StringBuffer sb = new StringBuffer();
		sb.append(StrSrc.type.getValue() + ":" + type.getValue() + ";\n");
		sb.append(StrSrc.initPrice.getValue() + getCost() / level + ";\n");
		sb.append(StrSrc.level.getValue() + ":" + level + ";\n");

		if (landType == LandType.onSell) {
			sb.append(StrSrc.owner.getValue() + ":" + StrSrc.onsellState.getValue() + ";\n");
		} else if (landType == LandType.none) {
			sb.append(StrSrc.owner.getValue() + ":" + StrSrc.landNoneName.getValue() + ";\n");
		} else {
			sb.append(StrSrc.owner.getValue() + ":" + owner.getName() + ";\n");
		}

		return sb.toString();
	}

	public LandType getType() {
		return this.landType;
	}

	public void setType(LandType type) {
		this.landType = type;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public int getCost() {
		return price * level;
	}

	@Override
	public JButton toSwing() {
		JButton button = new JButton(new ImageIcon("icons/land.png"));
		if (landType == LandType.none) {
			button.setIcon(new ImageIcon("icons/none.png"));
		}
		if (owner != null) {
			button.setIcon(landType.getIcon(level - 1));
		}

		button.addActionListener(infoListener);
		return button;
	}
}
