package monopoly.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import monopoly.building.Bank;
import monopoly.building.Creature;
import monopoly.building.Roadblock;
import monopoly.feature.LotterySystem;
import monopoly.feature.StockSystem;
import monopoly.map.Cell;
import monopoly.map.Map;
import monopoly.player.Direction;
import monopoly.player.Player;
import monopoly.ui.controller.UIController;
import monopoly.util.DateUtil;
import monopoly.util.StrSrc;

/**
 * @author duocai
 * @date 2016年5月31日 下午10:46:44
 */
public class MapPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private ImageIcon imageIcon = new ImageIcon("icons/map.png");
	private Image image;
	private Font font = new Font("hello", Font.ITALIC, 20);
	ImageIcon diceIcon = new ImageIcon("icons/dice1.png");
	JButton dice = new JButton(diceIcon);
	private int walkX;
	private int walkY;
	private Timer walkTimer;//行走动画计时器
	private int diceNum;

	// not a ideal implement just according to the old data
	final int[] pathPosX = new int[] { 95, 127, 159, 191, 191, 191, 191, 223, 255, 287, 319, 319, 319, 319, 351, 383,
			415, 415, 415, 415, 415, 415, 415, 415, 415, 415, 383, 351, 319, 319, 319, 287, 255, 223, 191, 191, 191,
			159, 127, 95, 95, 95, 95, 95, 95, 95, 95, 95 };
	final int[] pathPosY = new int[] { 95, 95, 95, 95, 127, 159, 191, 191, 191, 191, 191, 159, 127, 95, 95, 95, 95, 127,
			159, 191, 223, 255, 287, 319, 351, 383, 383, 383, 383, 351, 319, 319, 319, 319, 319, 351, 383, 383, 383,
			383, 351, 319, 287, 255, 223, 191, 159, 127 };

	final int[] buildPosX = new int[] { 95, 127, 159, 191, 221, 221, 191, 223, 255, 287, 319, 289, 289, 319, 351, 383,
			415, 445, 445, 445, 445, 445, 445, 445, 445, 415, 383, 351, 319, 349, 319, 287, 255, 223, 191, 161, 191,
			159, 127, 95, 65, 65, 65, 65, 65, 65, 65, 65 };
	final int[] buildPosY = new int[] { 65, 65, 65, 65, 127, 159, 221, 221, 221, 221, 221, 159, 127, 65, 65, 65, 65,
			127, 159, 191, 223, 255, 287, 319, 351, 413, 413, 413, 413, 351, 289, 289, 289, 289, 289, 351, 413, 413,
			413, 413, 351, 319, 287, 255, 223, 191, 159, 127 };

	private Map map;
	private UIController uiController;
	private Player curPlayer;
	List<JButton> buildings = new ArrayList<>();

	/**
	 * 2016年5月31日 下午10:46:44
	 */
	public MapPanel(Map map) {
		this.map = map;
		this.uiController = map.getUiController();
		this.curPlayer = map.getPlayers().get(0);
		setCurPPos(this.curPlayer);

		setLayout(null);
		image = imageIcon.getImage();
		drawMap(map);

		//add dice
		add(dice);
		dice.setBounds(247, 73, 50, 50);
		dice.setToolTipText(StrSrc.throwDice.getValue());
		dice.addActionListener(new ActionListener() {
			int timerCount = 0;
			Timer timer = new Timer(50, new ActionListener() {

				@Override
				/**
				 * 骰子转动动画
				 */
				public void actionPerformed(ActionEvent e) {
					int rm = (int) (Math.random() * 6) + 1;
					diceIcon = new ImageIcon("icons/dice" + rm + ".png");
					timerCount++;
					if (timerCount++ > 80) {
						diceIcon = new ImageIcon("icons/dice" + diceNum + ".png");
						dice.setIcon(diceIcon);
						timer.stop();
						timerCount = 0;
						curPlayer.move(diceNum);
					}
					repaint();
				}
			});// throw dice animation

			public void actionPerformed(ActionEvent e) {
				diceNum = curPlayer.throwDice();
				dice.setVisible(false);// avoid clicking the dice constantly
				timer.start();
			}
		});
	}

	/**
	 * 切到下一个玩家
	 * 
	 * 2016年6月4日 下午5:20:34
	 */
	public void nextPlayer() {
		List<Player> players = map.getPlayers();
		int i = players.indexOf(curPlayer);
		if (players.size()>0)
			curPlayer = players.get((i + 1) % players.size());
		setCurPPos(curPlayer);

		if (players.size() <= 1) {
			uiController.showMassage(
					StrSrc.congatulatePlayer.getValue() + curPlayer.getName() + StrSrc.winGame.getValue());
			endGame();
		}

		// drawMap(map);
		if (i == players.size() - 1)
			passOneDay();

		if (curPlayer.getHurt() > 0) {
			repaint();
			curPlayer.subHurt();
			uiController.showMassage(StrSrc.you.getValue() + StrSrc.inHospital.getValue());
			nextPlayer();
		}
	}
	
	/**
	 * 结束游戏
	 * 
	 * 2016年6月4日 下午5:20:53
	 */
	public void endGame() {
		System.exit(0);
	}

	public Player getCurPlayer() {
		return curPlayer;
	}

	/**
	 * 过去一天
	 * 并处理相应事件
	 * 
	 * 2016年6月4日 下午5:21:07
	 */
	private void passOneDay() {
		LotterySystem lotterySystem = LotterySystem.getLotterySystem();
		StockSystem stockSystem = StockSystem.getStockSystem();
		DateUtil dateUtil = DateUtil.getInstance();
		List<Player> players = map.getPlayers();
		// game start
		dateUtil.passNDays(1);// one day pass
		stockSystem.stocksChange();// stocks change

		if (dateUtil.getDay() == 1) {// new month
			Bank.handleInterest(players, uiController);// bank give interest
			lotterySystem.checkLotteries(players, uiController);// lottery draw
		}
	}

	/**
	 * 画玩家，日期信息
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawBackground(g);
		drawPlayerInfo(g);
		drawDate(g);
		drawPlayer(g);

		// draw dice
		g.drawImage(diceIcon.getImage(), 247, 73, 50, 50, this);
	}

	private void drawPlayer(Graphics g) {
		List<Player> players = map.getPlayers();
		for (Player player : players) {
			if (player != this.curPlayer) {
				int pos = map.getIndex(player.getCell());
				g.drawImage(player.getWalkImage(), pathPosX[pos], pathPosY[pos], 26, 31, this);
			}
		}
		g.drawImage(this.curPlayer.getWalkImage(), walkX, walkY, 26, 31, this);
	}

	private void drawBackground(Graphics g) {
		g.drawImage(image, 0, 0, 733, 509, this);
	}

	private void drawPlayerInfo(Graphics g) {
		g.setFont(font);
		// g.drawImage(player, 532, 0, 255, 190, this);
		g.drawString(StrSrc.coupond.getValue(), 580, 310);
		g.drawString(StrSrc.cash.getValue(), 580, 340);
		g.drawString(StrSrc.deposit.getValue(), 580, 370);
		g.drawString(StrSrc.housePropery.getValue(), 580, 400);
		g.drawString(StrSrc.totalMoney.getValue(), 580, 430);
		g.drawString(StrSrc.playerName.getValue(), 645, 250);

		// 画玩家信息
		g.setFont(new Font(" ", Font.ITALIC, 15));
		g.drawImage(curPlayer.getPhoto(), 572, 220, 70, 70, this);
		g.drawString(curPlayer.getName(), 645, 275);
		g.setFont(font);
		g.drawString("" + curPlayer.getCoupond(), 630, 310);
		g.drawString("" + curPlayer.getCash(), 630, 340);
		g.drawString("" + curPlayer.getDeposit(), 630, 370);
		g.drawString("" + curPlayer.getHouseProperty(), 630, 400);
		g.drawString("" + curPlayer.getTotalMoney(), 630, 430);
	}

	private void drawDate(Graphics g) {
		DateUtil date = DateUtil.getInstance();
		g.setFont(font);
		g.setColor(Color.CYAN);
		g.drawString(date.toString(), 593, 100);
		g.drawString(date.getWeekDayString(), 603, 130);
	}

	/**
	 * 绘制地图
	 * @param map
	 * 2016年6月4日 下午5:22:05
	 */
	private void drawMap(Map map) {
		int size = map.getEventCellSize();
		for (int i = 0; i < size; i++) {
			Cell cell = map.getEventCell(i);
			JButton comp = cell.getCreature(0).toSwing();
			this.add(comp);
			comp.setBounds(buildPosX[i], buildPosY[i], 30, 30);
			comp.setVisible(true);
			comp.setBorderPainted(false);
			buildings.add(comp);

			// road block
			List<Creature> creatures = cell.getCreatures();
			int amount = creatures.size();
			if (amount > 0) {
				for (Creature creature : creatures) {

					if (creature instanceof Roadblock) {
						JButton jButton = creature.toSwing();
						this.add(jButton);
						jButton.setBounds(pathPosX[i], pathPosY[i], 26, 30);
						jButton.setVisible(true);
						jButton.setBorderPainted(false);
						buildings.add(jButton);
					}
				}
			}
		}
	}

	/**
	 * 重绘地图
	 * 
	 * 2016年6月4日 下午5:22:20
	 */
	public void repaintMap() {
		for (JButton jButton : buildings) {
			this.remove(jButton);
		}
		buildings = new ArrayList<>();
		drawMap(map);
	}

	/**
	 * 设置当前玩家位置，
	 * 不想在玩家里添加属性，因为这只是实现动画效果
	 * @param player
	 *            2016年6月1日 下午8:19:27
	 */
	private void setCurPPos(Player player) {
		int pos = map.getIndex(player.getCell());
		this.walkX = pathPosX[pos];
		this.walkY = pathPosY[pos];
	}

	/**
	 * 移动玩家
	 * @param player
	 * @param n
	 * 2016年6月4日 下午5:22:54
	 */
	public void movePlayer(Player player, int n) {
		walkTimer = new Timer(100, new WalkListener(player, n));
		walkTimer.start();
	}

	/**
	 * 实现移动玩家动画
	 * @author duocai
	 * @date 2016年6月4日 下午5:22:36
	 */
	class WalkListener implements ActionListener {
		private Player player;
		private int steps;
		private int timerCount = 0;

		public WalkListener(Player player, int n) {
			this.player = player;
			this.steps = Math.abs(n);
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			if (timerCount < steps * 8) {
				int pos = map.getIndex(player.getCell());
				int dest = map.getIndex(map.getCellAt(player.getCell(), 1 * player.getDirection().getValue()));

				walkX += (pathPosX[dest] - pathPosX[pos]) / 8;
				walkY += (pathPosY[dest] - pathPosY[pos]) / 8;

				if (timerCount % 8 == 7) {// 前进一格
					player.setCell(map.getCellAt(player.getCell(), 1 * player.getDirection().getValue()));
				}

				// 设置行走图
				player.setWalkImage(getImageId(dest));

				repaint();
				timerCount++;
			}

			// 走完了
			else {
				walkTimer.stop();
				timerCount = 0;
				player.moveTo(player.getCell());

				repaintMap();
				if (player.getCell().getCreature(0) instanceof Bank && steps < diceNum) {
					player.move(diceNum - steps);
				} else {
					nextPlayer();
					dice.setVisible(true);// allow next player to click dice
				}
				repaint();
			}
		}

		public int getImageId(int dest) {
			int imageId;
			if (player.getDirection() == Direction.forward) {
				if (dest < 4) imageId = timerCount % 3 + 3;
				else if (dest < 7) imageId = timerCount % 3;
				else if (dest < 11)imageId = timerCount % 3 + 3;
				else if (dest < 14)imageId = timerCount % 3 + 6;
				else if (dest < 17)imageId = timerCount % 3 + 3;
				else if (dest < 26)imageId = timerCount % 3;
				else if (dest < 29)imageId = timerCount % 3 + 9;
				else if (dest < 31)imageId = timerCount % 3 + 6;
				else if (dest < 35)imageId = timerCount % 3 + 9;
				else if (dest < 37)imageId = timerCount % 3;
				else if (dest < 40)imageId = timerCount % 3 + 9;
				else imageId = timerCount % 3 + 6;
			} else {// 转向
				if (dest < 3)imageId = timerCount % 3 + 9;
				else if (dest < 6) imageId = timerCount % 3 + 6;
				else if (dest < 10)imageId = timerCount % 3 + 9;
				else if (dest < 13)imageId = timerCount % 3;
				else if (dest < 16)imageId = timerCount % 3 + 9;
				else if (dest < 25)imageId = timerCount % 3 + 6;
				else if (dest < 28)imageId = timerCount % 3 + 3;
				else if (dest < 30)imageId = timerCount % 3;
				else if (dest < 34)imageId = timerCount % 3 + 3;
				else if (dest < 36)imageId = timerCount % 3 + 6;
				else if (dest < 39)imageId = timerCount % 3 + 3;
				else imageId = timerCount % 3;
			}
			return imageId;
		}

	}
}
