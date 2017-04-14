package monopoly.ui.controller;

import java.util.List;
import java.util.Scanner;

import monopoly.building.Bank;
import monopoly.building.Creature;
import monopoly.building.LandType;
import monopoly.building.Roadblock;
import monopoly.card.Card;
import monopoly.feature.StockSystem;
import monopoly.feature.LotterySystem.Lottery;
import monopoly.map.Cell;
import monopoly.map.Map;
import monopoly.player.Player;
import monopoly.player.PlayerType;
import monopoly.player.RPlayer;
import monopoly.util.DateUtil;
import monopoly.util.StrSrc;

/**
 * @author duocai
 * @date 2016年4月20日 下午4:31:17
 */
public class CMDController extends UIController {
	
	private int dice;

	public CMDController(DateUtil dateUtil, Map map, Map originMap) {
		super(dateUtil, map, originMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see monopoly.ui.controller.UIController#showWelcome()
	 */
	@Override
	public void welcome(List<Player> players) {
		println(StrSrc.startLine.getValue());
		initPlayers(players);
		startGame();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see monopoly.ui.controller.UIController#operate()
	 */
	@Override
	public void operate(Player player) {
		boolean over = false;
		while (!over) {
			showOperations(player);
			int op = player.operate(0,9);// get command

			switch (op) {
			case 0:
				showMap(map);
				break;
			case 1:
				showMap(originMap);
				break;
			case 2:
				showCards(player);
				break;
			case 3:
				showWarning(player);
				break;
			case 4:
				showInfoNAway(player);
				break;
			case 5:
				showMoney(player);
				break;
			case 6: {
				dice = player.throwDice();
				player.move(dice);
				over = true;
				break;
			}
			case 7: {
				player.giveUp();
				println(StrSrc.playerName.getValue() + player.getName() + StrSrc.giveUp.getValue());
				over = true;
				break;
			}
			case 8:
				showStock(player);
				break;
				
			case 9:
				showLotteries(player);
				break;
			}
			
		}
	}

	private void showLotteries(Player player) {
		List<Lottery> lotteries = player.getLotteries();
		int i = 0;
		boolean has = false;
		println(StrSrc.lotteryNotice.getValue());
		for (Lottery lottery : lotteries) {
			has = true;
			i++;
			println(i + ":" + lottery.getNumber());
		}
		
		if (!has) {
			println(StrSrc.noLottery.getValue());
		}
		
		anyInputExit();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see monopoly.ui.controller.UIController#showOperations()
	 */
	@Override
	public void showOperations(Player player) {
		println(StrSrc.toady.getValue() + dateUtil.toString()+","+dateUtil.getWeekDayString());
		println(StrSrc.now.getValue() + StrSrc.playerName.getValue() + player.getName() + StrSrc.oprateTime.getValue()
				+ StrSrc.forwardDirection.getValue() + player.getDirection().toString());
		println(StrSrc.operationsNotice.getValue());
		println(StrSrc.operation1.getValue());
		println(StrSrc.operation2.getValue());
		println(StrSrc.operation3.getValue());
		println(StrSrc.operation4.getValue());
		println(StrSrc.operation5.getValue());
		println(StrSrc.operation6.getValue());
		println(StrSrc.operation7.getValue());
		println(StrSrc.operation8.getValue());
		println(StrSrc.operation9.getValue());
		println(StrSrc.operation10.getValue());
		println(StrSrc.pleaseChoose.getValue());
	}

	@Override
	public void showMap(Map map) {
		println(map.toTexture());
		anyInputExit();
	}

	@Override
	protected void initPlayers(List<Player> players) {
		println(StrSrc.choosePNum.getValue());
		int playerNumber = getInt(2, 4);
		// player 1 to 4
		final PlayerType[] playerType = { PlayerType.P1, PlayerType.P2, PlayerType.P3, PlayerType.P4 };
		final LandType[] landTypes = { LandType.p1, LandType.p2, LandType.p3, LandType.p4 };
		for (int i = 0; i < playerNumber; i++) {
			Cell cell = map.getEventCell(i);
			println(StrSrc.input.getValue() + StrSrc.playerName.getValue() + (i + 1) + StrSrc.nameLen.getValue());
			String name = getString(2, 7);
			Player player = new RPlayer(map, cell, this, playerType[i], name, landTypes[i]);
			cell.addCreature(player);
			players.add(player);
		}
	}

	@Override
	@SuppressWarnings("resource")
	public int getInt(int start, int end) {
		Scanner input = new Scanner(System.in);
		while (true) {
			try {
				int ret = Integer.parseInt(input.next());
				if (ret < start || ret > end) {
					println(StrSrc.intRange.getValue() + "[" + start + "," + end + "].");
					continue;
				}
				return ret;
			} catch (Exception e) {
				println(StrSrc.invalidInput.getValue() + "," + StrSrc.intNeed.getValue());
			}
		}
	}

	@SuppressWarnings("resource")
	@Override
	public String getString(int minLen, int maxLen) {
		Scanner input = new Scanner(System.in);
		while (true) {
			String ret = input.nextLine();
			if (ret.length() < minLen || ret.length() > maxLen) {
				println(StrSrc.strLenRange.getValue() + "[" + minLen + "," + maxLen + "]");
				continue;
			}
			return ret;
		}
	}

	/**
	 * print string
	 * 
	 * @param str
	 *            2016年4月20日 下午10:16:35
	 */
	private static void println(String str) {
		System.out.println(str);
	}

	@Override
	public void movePlayer(Player player, int n, Cell dest) {
		println(StrSrc.youForward.getValue() + Math.abs(n) + StrSrc.step.getValue() + "," + StrSrc.reach.getValue()
		+ dest.getName());
		player.moveTo(dest);
		
		if (player.getCell().getCreature(0) instanceof Bank && Math.abs(n) < dice) {
			player.move(dice - Math.abs(n));
		}
		
	}

	@Override
	public void showMassage(String string) {
		println(string);
	}

	@Override
	public void showCards(Player player) {
		while (true) {
			List<List<Card>> list = player.getCards();
			int i = 0;
			for (List<Card> cards : list) {
				i++;
				println(i+"." + cards.get(0).getName() + "*" + cards.size()+"\t"+cards.get(0).getDescription());
			}
			
			print(StrSrc.useCardNotice.getValue());
			
			int choice = player.chooseCard(0, list.size());
			
			if (choice == 0) {
				break;//over
			}
			else {
				player.useCard(list.get(choice-1).get(0), map, this);
			}
		}			
	}

	@Override
	public void showInfoNAway(Player player) {
		println(StrSrc.ndistanceNotice.getValue());

		boolean over = false;

		while (!over) {
			String choice = player.chooseDitance();
			switch (choice) {
			case "x":
				over = true;
				break;
			default: {
				try {
					int distance = Integer.parseInt(choice);
					if (distance < -12 || distance > 12) {
						println(StrSrc.intRange.getValue() + ":[-12,12]");
						break;
					}
					Cell dest = map.getCellAt(player.getCell(), distance);
					println(dest.getInfo());
					break;
				} catch (NumberFormatException e) {
					println(StrSrc.invalidInput.getValue());
				}
				break;
			}
			}
		}
	}

	@Override
	public void showMoney(Player player) {
		println(StrSrc.playerInfoNotice.getValue());
		println(StrSrc.playerInfoTitle.getValue());
		List<Player> players = map.getPlayers();
		for (Player player2 : players) {
			println(player2.getName() + "\t" + player2.getCoupond() + "\t" + player2.getCash() + "\t"
					+ player2.getDeposit() + "\t" + player2.getHouseProperty() + "\t" + player2.getTotalMoney());
		}
		anyInputExit();
	}

	@Override
	public void showStock(Player player) {
		StockSystem stockSystem = StockSystem.getStockSystem();
		stockSystem.showStocks(player, this);
	}

	@Override
	public void showWarning(Player player) {
		println(StrSrc.warning.getValue());
		for (int i = 1; i < 11; i++) {
			Cell cell = map.getCellAt(player.getCell(), i);
			List<Creature> creatures = cell.getCreatures();
			for (Creature creature : creatures) {
				if (creature instanceof Bank) {
					println(StrSrc.foward.getValue() + i + StrSrc.step.getValue() + StrSrc.hasBank.getValue());
				} else if (creature instanceof Roadblock) {
					println(StrSrc.foward.getValue() + i + StrSrc.step.getValue() + StrSrc.hasRoadblock.getValue());
				}
			}
		}
		anyInputExit();
	}

	@SuppressWarnings("resource")
	private void anyInputExit() {
		println(StrSrc.exit.getValue());
		Scanner input = new Scanner(System.in);
		input.nextLine();
	}
	
	/**
	 * System.out.print(string)
	 * @param string
	 * 2016年4月24日 上午11:46:50
	 */
	private void print(String string) {
		System.out.print(string);
	}
}
