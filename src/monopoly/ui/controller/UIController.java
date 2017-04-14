package monopoly.ui.controller;

import java.util.List;

import monopoly.building.Bank;
import monopoly.feature.LotterySystem;
import monopoly.feature.StockSystem;
import monopoly.map.Cell;
import monopoly.map.Map;
import monopoly.player.Player;
import monopoly.util.DateUtil;
import monopoly.util.StrSrc;

/**
 * @author duocai
 * @date 2016年4月12日 上午11:29:03
 */
public abstract class UIController {
	protected DateUtil dateUtil;
	protected Map map;
	protected final Map originMap;
	
	public UIController(DateUtil dateUtil, Map map, Map originMap) {
		this.dateUtil = dateUtil;
		this.map = map;
		this.originMap = originMap;
	}
	
	/**
	 * show welcome message
	 * choose players and start game
	 * 2016年4月20日 下午8:47:20
	 */
	public abstract void welcome(List<Player> players);
	
	
	/**
	 * player operate the game
	 * @param player
	 * 2016年4月20日 下午8:49:46
	 */
	public abstract void operate(Player player);
	
	/**
	 * get an int in [start,end]
	 * @param start
	 * @param end
	 * @return
	 * 2016年4月21日 上午9:19:39
	 */
	public abstract int getInt(int start, int end);
	
	/**
	 * get a string of which length between [minlen,maxlen]
	 * @param minLen
	 * @param maxLen
	 * @return
	 * 2016年4月21日 上午9:19:43
	 */
	public abstract String getString(int minLen, int maxLen);
	
	/**
	 * //show operations
	 * @param player
	 * 2016年4月20日 下午8:48:41
	 */
	public abstract void showOperations(Player player);
	
	
	/**
	 * //show map
	 * @param map
	 * 2016年4月20日 下午8:49:14
	 */
	public abstract void showMap(Map map);
	
	/**
	 * init players
	 * @param players
	 * @param map
	 * 2016年4月20日 下午8:49:33
	 */
	protected abstract void initPlayers(List<Player> players);

	/**
	 * let player move n steps 
	 * @param player
	 * @param n
	 * 2016年4月21日 上午9:26:19
	 * @param dest 
	 */
	public abstract void movePlayer(Player player, int n, Cell dest);
	
	/**
	 * show the player's cards
	 * @param player
	 * 2016年4月23日 下午3:08:09
	 */
	public abstract void showCards(Player player);
	
	/**
	 * show the specific info of the land n steps away
	 * @param player
	 * 2016年4月23日 下午3:10:30
	 */
	public abstract void showInfoNAway(Player player);
	
	/**
	 * show money of the parameter player
	 * @param player
	 * 2016年4月23日 下午3:11:40
	 */
	public abstract void showMoney(Player player);
	
	/**
	 * let parameter player to see stocks
	 * @param player
	 * 2016年4月23日 下午3:12:52
	 */
	public abstract void showStock(Player player);
	
	/**
	 * show warning within the distance 10
	 * @param player
	 * 2016年4月23日 下午3:09:00
	 */
	public abstract void showWarning(Player player);
	
	
	/**
	 * display the message string in ui
	 * @param string
	 * 2016年4月21日 上午10:02:49
	 */
	public abstract void showMassage(String string);
	
	/**
	 * cycle game
	 * 2016年5月31日 下午10:31:49
	 */
	public void startGame() {
		LotterySystem lotterySystem = LotterySystem.getLotterySystem();
		StockSystem stockSystem = StockSystem.getStockSystem();
		List<Player> players = map.getPlayers();
		//game start
		while (true) {
			for (Player player : players) {
				operate(player);
			}					
			
			if (players.size() == 1) {//only one player left
				showMassage(StrSrc.congatulatePlayer.getValue() + players.get(0).getName() + StrSrc.winGame.getValue());
				break;
			}
			
			dateUtil.passNDays(1);//one day pass
			stockSystem.stocksChange();//stocks change
			
			if (dateUtil.getDay() ==  1) {//new month
				Bank.handleInterest(players, this);//bank give interest
				lotterySystem.checkLotteries(players, this);//lottery draw
			}
			
		}
	}

}
