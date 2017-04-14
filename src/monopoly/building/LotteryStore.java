package monopoly.building;

import monopoly.feature.LotterySystem;
import monopoly.map.Cell;
import monopoly.player.Player;
import monopoly.ui.controller.UIController;
import monopoly.util.StrSrc;

/**
 * @author duocai
 * @date 2016年4月18日 下午10:45:05
 */
public class LotteryStore extends Creature {

	/**
	 * @param cell
	 */
	public LotteryStore(Cell cell) {
		super(cell, CreatureType.lotteryStore);
	}

	/* (non-Javadoc)
	 * @see monopoly.map.Creature#toTexture()
	 */
	@Override
	public String toTexture() {
		// TODO Auto-generated method stub
		return StrSrc.lotteryStore.getValue();
	}

	/* (non-Javadoc)
	 * @see monopoly.map.Creature#execute(monopoly.player.Player)
	 */
	@Override
	public void execute(Player p, UIController uiController) {
		uiController.showMassage(StrSrc.youReach.getValue() + StrSrc.lotteryStoreName.getValue());
		while (true) {
			uiController.showMassage(StrSrc.chooseBuyLottery.getValue());
			String choice = p.chooseBuyLottery();
			if (choice.equals("y")) {
				LotterySystem lotterySystem = LotterySystem.getLotterySystem();
				lotterySystem.sellLottery(p, uiController);
			}
			else if (choice.equals("n")) {
				uiController.showMassage(StrSrc.welcomeNext.getValue());
				break;
			}
			else {
				uiController.showMassage(StrSrc.invalidInput.getValue());
			}
		}
	}

}
