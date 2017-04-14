package monopoly.feature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import monopoly.player.Player;
import monopoly.player.PlayerType;
import monopoly.ui.controller.UIController;
import monopoly.util.DateUtil;
import monopoly.util.StrSrc;

/**
 * @author duocai
 * @date 2016年4月22日 下午1:24:30
 */
public class StockSystem {

	private volatile static StockSystem stockSystem = null;
	private Map<PlayerType, Investor> investors = new HashMap<>();
	private List<Stock> stocks = new ArrayList<>();
	private DateUtil dateUtil;

	private String[] stocksName = { StrSrc.stock1.getValue(), StrSrc.stock2.getValue(), StrSrc.stock3.getValue(),
			StrSrc.stock4.getValue(), StrSrc.stock5.getValue(), StrSrc.stock6.getValue(), StrSrc.stock7.getValue(),
			StrSrc.stock8.getValue(), StrSrc.stock9.getValue(), StrSrc.stock10.getValue(), };

	/**
	 * 
	 * 2016年4月22日 下午1:24:30
	 */
	private StockSystem() {
		dateUtil = DateUtil.getInstance();
		for (int i = 0; i < stocksName.length; i++) {
			stocks.add(new Stock(stocksName[i], 50));
		}
	}

	/**
	 * get the unique stock system
	 * 
	 * @return 2016年4月25日 下午8:04:58
	 */
	public static StockSystem getStockSystem() {
		if (stockSystem == null) {
			synchronized (StockSystem.class) {
				if (stockSystem == null) {
					stockSystem = new StockSystem();
				}
			}
		}
		return stockSystem;
	}

	/**
	 * stock's price change randomly
	 * 
	 * 2016年4月25日 下午11:10:33
	 */
	public void stocksChange() {
		int weekOfDay = dateUtil.getDayOfWeek();
		if (weekOfDay != 1 && weekOfDay != 7) {//during the weekend, the stock will not change
			for (Stock stock : stocks) {
				stock.randomChange();
			}
		}
	}

	/**
	 * show stocks according to the parameter player
	 * 
	 * @param player
	 *            2016年4月25日 下午10:44:54
	 */
	public void showStocks(Player player, UIController uiController) {
		uiController.showMassage(StrSrc.stockNotice.getValue());
		uiController.showMassage(StrSrc.id.getValue() + "\t" + StrSrc.stockName.getValue() + "\t"
				+ StrSrc.price.getValue() + "\t" + StrSrc.status.getValue() + "\t" + StrSrc.stockLeft.getValue() + "\t"
				+ StrSrc.ownNumber.getValue());
		int i = 0;
		for (Stock stock : stocks) {
			i++;
			Investor investor = getInvestor(player.getType());
			int number = investor.getStockNumber(stock.getName());

			uiController.showMassage(i + "\t" + stock.getName() + "\t" + stock.getPrice() + "\t" + stock.getStatus()
					+ "%\t" + stock.getLeftNumber() + "\t" + number);
		}
		
		int weekOfDay = dateUtil.getDayOfWeek();
		if (weekOfDay != 1 && weekOfDay != 7) {//during the weekend, the stock will not change
			handleStocks(player, uiController);
		}
		else {
			uiController.showMassage(StrSrc.stockWeekendNotice.getValue());
		}
	}

	/**
	 * get investor
	 * 
	 * @param type
	 * @return 2016年4月26日 上午8:05:33
	 */
	private Investor getInvestor(PlayerType type) {
		Investor investor = investors.get(type);
		if (investor == null) {
			investor = new Investor();
			investors.put(type, investor);
		}
		return investor;
	}

	/**
	 * handle stocks
	 * 
	 * @param player
	 * @param uiController
	 *            2016年4月26日 上午8:19:43
	 */
	private void handleStocks(Player player, UIController uiController) {
		boolean over = false;
		while (!over) {
			uiController.showMassage(StrSrc.stockoperate.getValue());
			String choice = player.handleStock(1, 100);
			String[] tokens = null;
			switch (choice) {
			case "x":
				over = true;
				break;
			default:
				tokens = choice.split(" ");
				for (int i = 0; i < tokens.length; i++) {
					tokens[i].trim();
				}
				break;
			}

			if (over)
				break;

			if (tokens.length != 3) {
				uiController.showMassage(StrSrc.invalidInput.getValue());
				continue;
			}

			// change input
			int index = 0, number = 0;
			try {
				index = Integer.parseInt(tokens[1]);
				number = Integer.parseInt(tokens[2]);
				
				if (index < 0 || index >= stocks.size()) {
					uiController.showMassage(StrSrc.noSock.getValue());
					continue;
				}
				if (number < 0) {
					uiController.showMassage(StrSrc.invalidInput.getValue());
					continue;
				}
				
			} catch (NumberFormatException e) {
				uiController.showMassage(StrSrc.invalidInput.getValue());
				continue;
			}
			switch (tokens[0]) {
			case "b":
				playerBuyStock(index - 1, number, player, uiController);
				break;
			case "s":
				playerSellStock(index - 1, number, player, uiController);
				break;
			default:
				uiController.showMassage(StrSrc.invalidInput.getValue());
				break;
			}
		}
	}

	/**
	 * player sell stocks
	 * 
	 * @param index
	 * @param number
	 * @param player
	 * @param uiController
	 *            2016年4月26日 上午9:08:59
	 */
	private void playerSellStock(int index, int number, Player player, UIController uiController) {

		Investor investor = getInvestor(player.getType());
		Stock stock = getStock(index);
		int own = investor.getStockNumber(stock.getName());

		if (own < number) {
			uiController.showMassage(StrSrc.stockNotEnough.getValue());
			return;
		}
		investor.subStockNumber(stock.getName(), number);
		stock.setLeftNumber(stock.getLeftNumber() + number);
		int money = (int) stock.getPrice() * number;
		player.setDeposit(player.getDeposit() + money);
		uiController.showMassage(StrSrc.sellStockSuccess.getValue());
	}

	/**
	 * player buy stocks
	 * 
	 * @param index
	 * @param number
	 * @param player
	 * @param uiController
	 *            2016年4月26日 上午9:08:42
	 */
	private void playerBuyStock(int index, int number, Player player, UIController uiController) {

		Stock stock = getStock(index);
		int left = stock.getLeftNumber();

		if (left < number) {// no enough stock left
			uiController.showMassage(StrSrc.stockLeftNotEnough.getValue());
			return;
		}

		int cost = (int) stock.getPrice() * number;
		if (player.getCash() + player.getDeposit() < cost) {// not enough money
			uiController.showMassage(StrSrc.notEnoughDepositAndCash.getValue());
			return;
		}

		// buy stock
		player.setDeposit(player.getDeposit() - cost);
		if (player.getDeposit() < 0) {
			player.setCash(player.getCash() + player.getDeposit());
			player.setDeposit(0);
		}

		Investor investor = getInvestor(player.getType());
		investor.addStockNumber(stock.getName(), number);
		stock.setLeftNumber(stock.getLeftNumber() - number);
		uiController.showMassage(StrSrc.buyStockSuccess.getValue());
	}

	private Stock getStock(int i) {
		if (i < 0) {
			return null;
		}
		return stocks.get(i);
	}

	/**
	 * stock class
	 * 
	 * @author duocai
	 * @date 2016年4月22日 下午1:24:44
	 */
	private class Stock {
		private final int MAX = 20;

		private String name;
		private double price;
		private int leftNumber;
		private double status;

		/**
		 * 
		 * 2016年4月22日 下午1:24:44
		 */
		public Stock(String name, int price) {
			this.name = name;
			this.price = price;
			leftNumber = 1000;
		}

		public void randomChange() {
			status = Math.random() * MAX - 10;
			price += (price * status) / 100;

			// %.2f
			status = (int) (status * 100) / 100.0;
			price = (int) (price * 100) / 100.0;
		}

		public String getName() {
			return name;
		}

		public double getPrice() {
			return price;
		}

		public double getStatus() {
			return status;
		}

		public int getLeftNumber() {
			return leftNumber;
		}

		public void setLeftNumber(int number) {
			leftNumber = number;
		}
	}

	/**
	 * investor class
	 * 
	 * @author duocai
	 * @date 2016年4月25日 下午10:02:42
	 */
	private class Investor {

		private Map<String, Integer> stocks = new HashMap<>();

		public Investor() {

		}

		/**
		 * get the number of parameter stock
		 * 
		 * @param name
		 * @return 2016年4月25日 下午10:28:20
		 */
		public int getStockNumber(String name) {
			Integer number = stocks.get(name);
			if (number != null) {
				return number;
			}
			return 0;
		}

		/**
		 * add stocks
		 * 
		 * @param name
		 * @param number
		 *            2016年4月26日 上午8:14:44
		 */
		@SuppressWarnings("null")
		public void addStockNumber(String name, int number) {
			Integer num = stocks.get(name);
			if (num == null) {
				stocks.put(name, number);
				return;
			}
			num += number;
			stocks.put(name, num);
		}

		/**
		 * sub number
		 * 
		 * @param name
		 * @param number
		 * @return 2016年4月26日 上午8:13:15
		 */
		@SuppressWarnings("null")
		public boolean subStockNumber(String name, int number) {
			Integer num = stocks.get(name);
			if (num == null) {
				return false;
			}
			num -= number;
			if (num < 0) {
				return false;
			}
			stocks.put(name, num);
			return true;
		}
	}

}
