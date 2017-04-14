package monopoly.card;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import monopoly.building.Creature;
import monopoly.building.Land;
import monopoly.building.LandType;
import monopoly.map.Map;
import monopoly.player.Player;
import monopoly.ui.controller.UIController;
import monopoly.util.StrSrc;

/**
 * @author duocai
 * @date 2016年4月21日 下午7:34:49
 */
public class BuyLandCard extends Card {

	/**
	 * 
	 * 2016年4月21日 下午7:34:50
	 */
	public BuyLandCard() {
		super(StrSrc.buyLandCard.getValue(), StrSrc.buyLandCardDes.getValue());
	}

	@Override
	public void execute(Player player, Map map, UIController uiController) {
		Creature creature = player.getCell().getCreature(0);
		Land land;
		if (creature instanceof Land && (land = (Land) creature).getType() != LandType.none
				&& land.getOwner() != player) {
			int cost = land.getCost();
			if (player.getCash() < cost) {
				uiController.showMassage(StrSrc.noCash.getValue());
			} else {
				player.setCash(player.getCash() - cost);
				if (land.getOwner() != null) {// avoid on sell land
					land.getOwner().rmLand(land);
				}
				land.setOwner(player);
				land.setType(player.getLandType());
				player.addLand(land);
				player.rmCard(this);
				uiController.showMassage(StrSrc.landSellSuccess.getValue() + land.getLevel()
						+ StrSrc.landLevel.getValue() + "\n" + StrSrc.useCardsuccess.getValue() + name);
			}
		} else {
			uiController.showMassage(StrSrc.cantUseCard.getValue() + "\n" + StrSrc.buyLandCardWarning.getValue());
		}
	}

	@Override
	public JButton toSwing() {
		JButton button = new JButton(new ImageIcon("icons/goudi.png"));
		button.setToolTipText(description);
		return button;
	}

}
