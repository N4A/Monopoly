package monopoly.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import monopoly.building.LandType;
import monopoly.map.Cell;
import monopoly.map.Map;
import monopoly.player.Player;
import monopoly.player.PlayerType;
import monopoly.player.RPlayer;
import monopoly.ui.controller.UIController;
import monopoly.util.StrSrc;

/**
 * @author duocai
 * @date 2016年5月31日 下午3:00:28
 */
public class WelcomePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private Image image;//背景图
	private ImageIcon monopolyIcon = new ImageIcon("icons/monopoly0.png");//背景图
	
	//控件图片资源
	private final ImageIcon playIcon = new ImageIcon("icons/start.gif");
	private final ImageIcon addIcon1 = new ImageIcon("icons/add.png");
	private final ImageIcon nextIcon = new ImageIcon("icons/next.png");
	private final ImageIcon[] playersIcon = { new ImageIcon("icons/player0Button.png"),
			new ImageIcon("icons/player1Button.png"), new ImageIcon("icons/player2Button.png") };
	private final ImageIcon[] playersRolloverIcon = { new ImageIcon("icons/player0Button0.gif"),
			new ImageIcon("icons/player1Button0.gif"), new ImageIcon("icons/player2Button0.gif") };
	private final int[][] playersIconPos = { { 100, 0 }, { 484, 175 }, { 109, 341 } };
	private final ImageIcon[] numberIcon = { new ImageIcon("icons/number1.png"), new ImageIcon("icons/number2.png"),
			new ImageIcon("icons/number3.png") };

	private JButton play = new JButton(playIcon);
	private JButton add1 = new JButton(addIcon1);// choose player number
	private JButton next = new JButton(nextIcon);
	private JButton number1 = new JButton(numberIcon[0]);
	private JButton[] playersButton = new JButton[playersIcon.length];
	private static int cycleSign;
	private int playerNum = 1;// total number of player
	private int playerInitedNum = 0;
	private List<Player> players;
	private Map map;
	private UIController uiController;

	/**
	 * Constructor
	 * 
	 * @param players
	 *            2016年5月31日 下午8:38:45
	 * @param map
	 * @param uiController
	 */
	public WelcomePanel(List<Player> players, Map map, UIController uiController) {
		this.map = map;
		this.players = players;
		this.uiController = uiController;

		setLayout(null);
		image = monopolyIcon.getImage();

		//切换到选择玩家界面按钮
		add(play);
		play.setBorderPainted(false);
		play.setRolloverIcon(new ImageIcon("icons/start0.gif"));
		play.setPressedIcon(new ImageIcon("icons/start1.gif"));
		play.setToolTipText(StrSrc.startGame.getValue());
		play.setBounds(255, 266, playIcon.getIconWidth(), playIcon.getIconHeight());
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				play.setVisible(false);

				monopolyIcon = new ImageIcon("icons/monopoly2.png");
				image = monopolyIcon.getImage();
				add1.setVisible(true);
				next.setVisible(true);
				number1.setVisible(true);
				repaint();
			}

		});

		//显示玩家人数
		add(number1);
		number1.setBounds(331, 358, numberIcon[0].getIconWidth(), numberIcon[0].getIconHeight());
		number1.setBorderPainted(false);
		number1.setVisible(false);

		//切换玩家人数
		add(add1);
		add1.setBorderPainted(false);
		add1.setRolloverIcon(new ImageIcon("icons/add0.png"));
		add1.setBounds(422, 351, addIcon1.getIconWidth(), addIcon1.getIconHeight());
		add1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerNum = playerNum % numberIcon.length + 1;
				number1.setIcon(numberIcon[playerNum - 1]);
				repaint();
			}

		});
		add1.setBorderPainted(false);
		add1.setVisible(false);

		//切换到下一页面控件
		add(next);
		next.setBorderPainted(false);
		next.setRolloverIcon(new ImageIcon("icons/next0.png"));
		next.setBounds(635, 10, nextIcon.getIconWidth(), nextIcon.getIconHeight());
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				monopolyIcon = new ImageIcon("icons/monopoly3.png");
				image = monopolyIcon.getImage();
				add1.setVisible(false);
				next.setVisible(false);
				number1.setVisible(false);
				for (cycleSign = 0; cycleSign < playersButton.length; cycleSign++) {
					playersButton[cycleSign].setVisible(true);
				}
				repaint();
			}

		});
		next.setVisible(false);

		// set players icon	and player choose event
		for (cycleSign = 0; cycleSign < playersButton.length; cycleSign++) {
			playersButton[cycleSign] = new JButton(playersIcon[cycleSign]);
			add(playersButton[cycleSign]);
			playersButton[cycleSign].setBorderPainted(false);
			playersButton[cycleSign].setRolloverIcon(playersRolloverIcon[cycleSign]);
			playersButton[cycleSign].setBounds(playersIconPos[cycleSign][0], playersIconPos[cycleSign][1],
					playersIcon[cycleSign].getIconWidth(), playersIcon[cycleSign].getIconHeight());
			playersButton[cycleSign].addActionListener(new ActionListener() {
				int i = cycleSign;//I think this is creative
				@Override
				public void actionPerformed(ActionEvent e) {
					initPlayers(i);
					playersButton[i].setVisible(false);
				}
			});
			playersButton[cycleSign].setVisible(false);
		}
	}

	/**
	 * 绘制背景图
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, 733, 509, this);
	}

	final private PlayerType[] playerTypes = { PlayerType.P1, PlayerType.P2, PlayerType.P3, PlayerType.P4 };
	final private LandType[] landTypes = { LandType.p1, LandType.p2, LandType.p3, LandType.p4 };
	/**
	 * 初始化玩家
	 * @param i
	 * 2016年6月4日 下午5:27:43
	 */
	private void initPlayers(int i) {
		Cell cell = map.getEventCell(playerInitedNum);
		Player player = new RPlayer(map, cell, uiController, playerTypes[i], playerTypes[i].getName(), landTypes[i]);
		cell.addCreature(player);
		players.add(player);
		playerInitedNum++;
		
		//change to game view
		if (playerInitedNum == playerNum) {
			this.setVisible(false);
			uiController.showMap(map);
			uiController.showOperations(null);
		}
	}
}