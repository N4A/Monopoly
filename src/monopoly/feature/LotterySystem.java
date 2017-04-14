package monopoly.feature;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import monopoly.player.Player;
import monopoly.ui.controller.UIController;
import monopoly.util.StrSrc;

/**
 * @author duocai
 * @date 2016年4月22日 下午1:24:01 lottery ticket with ten times compensation, 1/15
 *       chance
 */
public class LotterySystem {
	private volatile static LotterySystem lotterySystem = null;
	final private int MAX = 30;
	private int money;
	final private int lotteryPrice = 200;
	final private List<Lottery> lotteries = new CopyOnWriteArrayList<>();

	/**
	 * private ensure there is only one lottery system 2016年4月22日 下午1:24:01
	 */
	private LotterySystem() {
		money = 0;
		produceLotteries();
	}

	/**
	 * get the unique lottery system
	 * 
	 * @return 2016年4月23日 上午10:15:03
	 */
	public static LotterySystem getLotterySystem() {
		if (lotterySystem == null) {
			synchronized (LotterySystem.class) {
				if (lotterySystem == null) {
					lotterySystem = new LotterySystem();
				}			
			}
		}

		return lotterySystem;
	}

	/**
	 * produce new lotteries
	 * 
	 * 2016年4月25日 下午9:46:29
	 */
	private void produceLotteries() {
		lotteries.clear();
		for (int i = 0; i < MAX; i++) {
			lotteries.add(new Lottery(i));
		}
	}

	private Lottery getRandomLottery() {
		if (lotteries.isEmpty()) {
			return null;
		}
		int size = lotteries.size();
		int index = (int) (Math.random() * size);
		Lottery lottery = lotteries.get(index);
		lotteries.remove(lottery);
		return lottery;
	}
	
	private int getLeftLottery() {
		return lotteries.size();
	}

	/**
	 * check whether the players' lotteries win
	 * 
	 * @param players
	 * @param uiController
	 *            2016年4月23日 上午10:17:12
	 */
	public void checkLotteries(List<Player> players, UIController uiController) {
		int winNumber = getWinNumber();
		uiController.showMassage(StrSrc.lotteryDraw.getValue());// lottery draw
		uiController.showMassage(StrSrc.lotteryWin.getValue() + winNumber);// win
																			// number
		for (Player player : players) {
			for (Lottery lottery : player.getLotteries()) {
				if (lottery.getNumber() == winNumber) {
					player.setCash(player.getCash() + money);
					uiController.showMassage(StrSrc.congatulatePlayer.getValue() + player.getName()
							+ StrSrc.get.getValue() + money);
					money = 0;// Lottery system pay it
				}
			}
			player.clearLottery();
		}
		
		produceLotteries();//produce new lotteries
	}

	/**
	 * sell a lottery to parameter player
	 * 
	 * @param player
	 * @param uiController
	 *            2016年4月23日 上午10:16:42
	 */
	public void sellLottery(Player player, UIController uiController) {
		StringBuffer sb = new StringBuffer();
		if (player.getCash() < lotteryPrice) {
			sb.append(StrSrc.noCash.getValue()+"\n");
		} else {
			Lottery sell = null;
			if ((sell = getRandomLottery()) == null) {
				sb.append(StrSrc.noLotteryLeft.getValue()+"\n");
				uiController.showMassage(sb.toString());
				return ;
			}
			player.setCash(player.getCash() - lotteryPrice);
			player.addLottery(sell);
			money += lotteryPrice;// Lottery System earn it
			sb.append(StrSrc.lotterySuccess.getValue()+"\n");
		}
		sb.append(StrSrc.leftLottery.getValue() + getLeftLottery()+"\n");
		uiController.showMassage(sb.toString());
	}

	/**
	 * get win number
	 * @return
	 * 2016年4月25日 下午9:55:09
	 */
	private int getWinNumber() {
		return (int) (Math.random() * MAX);
	}

	/**
	 * lottery class
	 * 
	 * @author duocai
	 * @date 2016年4月23日 上午10:14:03
	 */
	public class Lottery {

		private int number;

		public Lottery(int i) {
			number = i;
		}

		public int getNumber() {
			return number;
		}
	}
}
