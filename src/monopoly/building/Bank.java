package monopoly.building;

import java.util.List;

import monopoly.map.Cell;
import monopoly.player.Player;
import monopoly.ui.controller.UIController;
import monopoly.util.StrSrc;

/**
 * @author duocai
 * @date 2016年4月12日 上午11:39:55
 */
public class Bank extends Creature {

	public Bank(Cell cell) {
		super(cell,CreatureType.bank);
	}

	@Override
	public String toTexture() {
		return StrSrc.bank.getValue();
	}
	
	@Override
	public void execute(Player p, UIController uiController) {
		
		uiController.showMassage(StrSrc.youReach.getValue() + StrSrc.bankName.getValue());
		uiController.showMassage(StrSrc.bankYouOwn.getValue() +StrSrc.cash.getValue()+":"+ p.getCash() + ","
								+StrSrc.deposit.getValue()+":" + p.getDeposit());//show deposit and cash
		while (true) {
			boolean success = true;//express whether the operate is over
			uiController.showMassage(StrSrc.bankNotice.getValue());//show how to operate
			String s = p.selectBankOperation();
			switch (s) {
			case "x": break;
			case "w": {
				uiController.showMassage(StrSrc.bankWithDraw.getValue());//show:input how much money to withdraw
				int withdraw = p.withDrawMoney(0, p.getDeposit());
				p.setCash(p.getCash() + withdraw);
				p.setDeposit(p.getDeposit() - withdraw);
				break;
			}
			
			case "s": {
				uiController.showMassage(StrSrc.bankSave.getValue());//show:input how much money to save
				int save = p.saveMoney(0, p.getCash());
				p.setCash(p.getCash() - save);
				p.setDeposit(p.getDeposit() + save);
				break;
			}

			default:
				uiController.showMassage(StrSrc.invalidInput.getValue());//show:invalid input
				success = false;
				break;
			}
			
			//exit if operations are right
			if (success) {
				break;
			}
		}
		uiController.showMassage(StrSrc.bankSucced.getValue());	//operate successfully
	}
	
	/**
	 * calculate interest of each player
	 * @param players
	 * @param uiController
	 * 2016年4月21日 上午10:28:19
	 */
	public static void handleInterest(List<Player> players, UIController uiController) {
		uiController.showMassage(StrSrc.bankGiveInterest.getValue());//show give interest
		
		for (Player player : players) {
			int deposit = player.getDeposit();
			int interest = deposit/10;
			player.setDeposit(deposit + interest);
			uiController.showMassage(StrSrc.congatulatePlayer.getValue() + player.getName() + StrSrc.bankGetInterest.getValue() + interest);
		}
	}
	
}
