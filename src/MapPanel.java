import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;

public class MapPanel extends JPanel implements Serializable {
	private Image image;
	Image player;
	ImageIcon[] icons = new ImageIcon[48];
	ImageIcon[] barrageIcon = new ImageIcon[48];
	static JButton[] buttons = new JButton[48];//路旁房子信息 
	static JButton[] barrage = new JButton[48];//使用路障卡时使用
	static JButton[] changeDirection = new JButton[48];//使用转向卡时使用
	static JButton[] rob =  new JButton[48];//使用掠夺卡时使用
	static JButton[] zhiliu =  new JButton[48];//使用滞留卡时使用
	static JButton[] turtle =  new JButton[48];//使用滞留卡时使用
	Font font = new Font("hello",Font.ITALIC,20);
	int[] xCoordinate = new int[]{95,127,159,191,191,191,191,223,255,287,319,319,319,319,351,383,415,415,415,415,415,415,415,415,415,415,383,351,319,319,319,287,255,223,191,191,191,159,127,95,95,95,95,95,95,95,95,95};
	int[] yCoordinate = new int[]{95,95,95,95,127,159,191,191,191,191,191,159,127,95,95,95,95,127,159,191,223,255,287,319,351,383,383,383,383,351,319,319,319,319,319,351,383,383,383,383,351,319,287,255,223,191,159,127};
	static String[] nameOfLand = new String[]{"北大","清华","复旦","上交","北大医学部","人大","中科大","浙大","南大","北航","北师","对外经贸","南开","哈工大","央财",
			"上交医学部","北外","天大","同济","北理","北邮","厦大","上财","武大","西交","北交","中国政法","中国传媒","东南","北科","华师","山东大学","宁波诺丁汉",
			"华科","川大","上外","农大","北语","西南财经","北京中医药","大连理工","西北工业","首都医科","北林","中南","北化","电子科技大","哈佛"};
	static int[] ownerOfLand = new int[]{3,3,3,4,5,6,3,3,3,3,3,7,6,4,3,3,3,3,3,8,9,10,5,3,3,3,3,3,4,7,3,3,3,3,3,7,4,3,3,3,3,3,8,9,10,5,3,3}; 
	static int[] levelOfLand = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
	static boolean[] barricades = new boolean[]{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,
		false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,
		false,false,false,false};
	boolean isPlayer;//添加路障时使用
	public MapPanel() {
		setLayout(null);
		ImageIcon imageIcon = new ImageIcon("icons/map.png");
		image = imageIcon.getImage();
		ImageIcon playerIcon = new ImageIcon("icons/player2.gif");
		player = playerIcon.getImage();
		for (int i = 0;i < 48; i++) {
			barrageIcon[i] = new ImageIcon("icons/road1.png");
			barrage[i] = new JButton(barrageIcon[i]);
			add(barrage[i]);
			barrage[i].setBounds(xCoordinate[i] + 4, yCoordinate[i] + 4, 24, 24);
			barrage[i].setVisible(false);
			barrage[i].addActionListener(new ActionListener() {//路障功能
				public void actionPerformed(ActionEvent e) {
					Game.playerPanel.repaint();
					isPlayer = false;
					for (int j =0; j < 48; j++) {
						if (e.getSource() == barrage[j]) {
							for (int k = 0; k < Monopoly.totalAmount; k++) {
								if (Monopoly.players[k].positonOfPlayer == j) {
									JOptionPane.showMessageDialog(Game.playerPanel, "亲，玩家所在处\n不能添加路障", "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[Game.playerPanel.currentPlayer].image));
									isPlayer = true;
								}
							}
							if (!isPlayer) {
								barricades[j] = true;
								for (int x = 0; x < 48; x++) {
									barrage[x].setVisible(false);
								}
							}
						}
					}	
				}
			});
		}
		
		for (int i = 0;i < 48; i++) {
			changeDirection[i] = new JButton(new ImageIcon("icons/blank.png"));
			add(changeDirection[i]);
			changeDirection[i].setBounds(xCoordinate[i], yCoordinate[i], 30, 30);
			changeDirection[i].setVisible(false);
			changeDirection[i].addActionListener(new ActionListener() {//转向功能
				public void actionPerformed(ActionEvent e) {
					Game.playerPanel.repaint();
					if (e.getSource() == changeDirection[Monopoly.players[0].positonOfPlayer]) {
						Monopoly.players[0].directionOfPlayer = (Monopoly.players[0].directionOfPlayer+1)%2;		
					}
					else if (e.getSource() == changeDirection[Monopoly.players[1].positonOfPlayer]) {
						Monopoly.players[1].directionOfPlayer = (Monopoly.players[1].directionOfPlayer+1)%2;
					}
					else {
						Monopoly.players[2].directionOfPlayer = (Monopoly.players[2].directionOfPlayer+1)%2;
					}
					for (int i = 0; i < Monopoly.totalAmount; i++) {
						MapPanel.changeDirection[Monopoly.players[i].positonOfPlayer].setVisible(false);
					}
				}
			});
		}
		for (int i = 0;i < 48; i++) {
			zhiliu[i] = new JButton(new ImageIcon("icons/blank.png"));
			add(zhiliu[i]);
			zhiliu[i].setBounds(xCoordinate[i], yCoordinate[i], 30, 30);
			zhiliu[i].setVisible(false);
			zhiliu[i].addActionListener(new ActionListener() {//转向功能
				public void actionPerformed(ActionEvent e) {
					Game.playerPanel.repaint();
					if (e.getSource() == zhiliu[Monopoly.players[0].positonOfPlayer]) {
						Monopoly.players[0].stop = true;
					}
					else if (e.getSource() == zhiliu[Monopoly.players[1].positonOfPlayer]) {
						Monopoly.players[1].stop = true;
					}
					else {
						Monopoly.players[2].stop = true;
					}
					for (int i = 0; i < Monopoly.totalAmount; i++) {
						MapPanel.zhiliu[Monopoly.players[i].positonOfPlayer].setVisible(false);
					}
				}
			});
		}
		for (int i = 0;i < 48; i++) {
			turtle[i] = new JButton(new ImageIcon("icons/blank.png"));
			add(turtle[i]);
			turtle[i].setBounds(xCoordinate[i], yCoordinate[i], 30, 30);
			turtle[i].setVisible(false);
			turtle[i].addActionListener(new ActionListener() {//转向功能
				public void actionPerformed(ActionEvent e) {
					Game.playerPanel.repaint();
					if (e.getSource() == turtle[Monopoly.players[0].positonOfPlayer]) {
						Monopoly.players[0].turtle = 3;
					}
					else if (e.getSource() == turtle[Monopoly.players[1].positonOfPlayer]) {
						Monopoly.players[1].turtle = 3;
					}
					else {
						Monopoly.players[2].turtle = 3;
					}
					for (int i = 0; i < Monopoly.totalAmount; i++) {
						MapPanel.turtle[Monopoly.players[i].positonOfPlayer].setVisible(false);
					}
				}
			});
		}
		for (int i = 0;i < 48; i++) {
			rob[i] = new JButton(new ImageIcon("icons/blank.png"));
			add(rob[i]);
			rob[i].setBounds(xCoordinate[i], yCoordinate[i], 30, 30);
			rob[i].setVisible(false);
			rob[i].addActionListener(new ActionListener() {//转向功能
				public void actionPerformed(ActionEvent e) {
					Game.playerPanel.repaint();
					if (e.getSource() == rob[Monopoly.players[0].positonOfPlayer]) {
						int i = (int)(Math.random()*5);
						Monopoly.players[0].numberOfcard[i]--;
						Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[i]++;	
						JOptionPane.showMessageDialog(Game.playerPanel, "玩家" + Monopoly.players[Game.playerPanel.currentPlayer].name +"\n掠夺了玩家\n" + Monopoly.players[0].name + "一张" + Monopoly.players[0].nameOfCard[i], "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[Game.playerPanel.currentPlayer].image));					
					}
					else if (e.getSource() == rob[Monopoly.players[1].positonOfPlayer]) {
						int i = (int)(Math.random()*5);
						Monopoly.players[1].numberOfcard[i]--;
						Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[i]++;	
						JOptionPane.showMessageDialog(Game.playerPanel, "玩家" + Monopoly.players[Game.playerPanel.currentPlayer].name +"\n掠夺了玩家\n" + Monopoly.players[1].name + "一张" + Monopoly.players[0].nameOfCard[i], "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[Game.playerPanel.currentPlayer].image));					
						
					}
					else {
						int i = (int)(Math.random()*5);
						Monopoly.players[2].numberOfcard[i]--;
						Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[i]++;	
						JOptionPane.showMessageDialog(Game.playerPanel, "玩家" + Monopoly.players[Game.playerPanel.currentPlayer].name +"\n掠夺了玩家\n" + Monopoly.players[2].name + "一张" + Monopoly.players[0].nameOfCard[i], "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[Game.playerPanel.currentPlayer].image));					
						
					}
					for (int i = 0; i < Monopoly.totalAmount; i++) {
						MapPanel.rob[Monopoly.players[i].positonOfPlayer].setVisible(false);
					}
				}
			});
		}
		for (int i = 46;i < 51; i++) {
			icons[i%48] = new ImageIcon("icons/land.png");
			buttons[i%48] = new JButton(icons[i%48]);
			add(buttons[i%48]);
		}
		for (int i = 6;i < 11; i++) {
			icons[i] = new ImageIcon("icons/land.png");
			buttons[i] = new JButton(icons[i]);
			add(buttons[i]);
			buttons[i].setBounds(xCoordinate[i], yCoordinate[i] + 30, 30, 30);
		}
		for (int i = 14;i < 19; i++) {
			icons[i] = new ImageIcon("icons/land.png");
			buttons[i] = new JButton(icons[i]);
			add(buttons[i]);
			barrage[i].setIcon(new ImageIcon("icons/road1.png"));
		}
		for (int i = 23;i < 28; i++) {
			icons[i] = new ImageIcon("icons/land.png");
			buttons[i] = new JButton(icons[i]);
			add(buttons[i]);
		}
		for (int i = 30;i < 35; i++) {
			icons[i] = new ImageIcon("icons/land.png");
			buttons[i] = new JButton(icons[i]);
			add(buttons[i]);
			buttons[i].setBounds(xCoordinate[i], yCoordinate[i] - 30, 30, 30);
		}
		for (int i = 37;i < 42; i++) {
			icons[i] = new ImageIcon("icons/land.png");
			buttons[i] = new JButton(icons[i]);
			add(buttons[i]);
		}
		icons[3] = new ImageIcon("icons/blank.png");
		buttons[3] = new JButton(icons[3]);
		add(buttons[3]);
		
		for (int i = 0;i < 4; i++) {
			buttons[i].setBounds(xCoordinate[i], yCoordinate[i] - 30, 30, 30);
		}
		
		icons[4] = new ImageIcon("icons/news.png");
		buttons[4] = new JButton(icons[4]);
		add(buttons[4]);
		buttons[4].setBounds(xCoordinate[4] + 30, yCoordinate[4], 30, 30);
		
		icons[5] = new ImageIcon("icons/card.png");
		buttons[5] = new JButton(icons[5]);
		add(buttons[5]);
		buttons[5].setBounds(xCoordinate[5] + 30, yCoordinate[5], 30, 30);
		
		icons[11] = new ImageIcon("icons/point.png");
		buttons[11] = new JButton(icons[11]);
		add(buttons[11]);
		buttons[11].setBounds(xCoordinate[11] - 60, yCoordinate[11], 60, 30);
		
		icons[12] = new ImageIcon("icons/card.png");
		buttons[12] = new JButton(icons[12]);
		add(buttons[12]);
		buttons[12].setBounds(xCoordinate[12] - 30, yCoordinate[12], 30, 30);
		
		icons[13] = new ImageIcon("icons/blank.png");
		buttons[13] = new JButton(icons[13]);
		add(buttons[13]);
		for (int i = 13;i < 17; i++) {
			buttons[i].setBounds(xCoordinate[i], yCoordinate[i] - 30, 30, 30);
		}
		
		icons[19] = new ImageIcon("icons/lottery.png");
		buttons[19] = new JButton(icons[19]);
		add(buttons[19]);
		
		icons[20] = new ImageIcon("icons/tool.png");
		buttons[20] = new JButton(icons[20]);
		add(buttons[20]);
		
		icons[21] = new ImageIcon("icons/bank.png");
		buttons[21] = new JButton(icons[21]);
		add(buttons[21]);
		
		icons[22] = new ImageIcon("icons/news.png");
		buttons[22] = new JButton(icons[22]);
		add(buttons[22]);
		
		for (int i = 17;i < 25; i++) {
			buttons[i].setBounds(xCoordinate[i] + 30, yCoordinate[i], 30, 30);
		}
		
		icons[28] = new ImageIcon("icons/blank.png");
		buttons[28] = new JButton(icons[28]);
		add(buttons[28]);
		
		for (int i = 25;i < 29; i++) {
			buttons[i].setBounds(xCoordinate[i], yCoordinate[i] + 30, 30, 30);
		}
		
		icons[29] = new ImageIcon("icons/point.png");
		buttons[29] = new JButton(icons[29]);
		add(buttons[29]);
		buttons[29].setBounds(xCoordinate[29] + 34, yCoordinate[29], 60, 30);
		
		icons[35] = new ImageIcon("icons/point.png");
		buttons[35] = new JButton(icons[35]);
		add(buttons[35]);
		buttons[35].setBounds(xCoordinate[35] - 60, yCoordinate[35], 60, 30);
		
		icons[36] = new ImageIcon("icons/blank.png");
		buttons[36] = new JButton(icons[36]);
		add(buttons[36]);
		
		for (int i = 36;i < 40; i++) {
			buttons[i].setBounds(xCoordinate[i], yCoordinate[i] + 30, 30, 30);
		}
		
		icons[42] = new ImageIcon("icons/lottery.png");
		buttons[42] = new JButton(icons[42]);
		add(buttons[42]);
		
		icons[43] = new ImageIcon("icons/tool.png");
		buttons[43] = new JButton(icons[43]);
		add(buttons[43]);
		
		icons[44] = new ImageIcon("icons/bank.png");
		buttons[44] = new JButton(icons[44]);
		add(buttons[44]);
		
		icons[45] = new ImageIcon("icons/news.png");
		buttons[45] = new JButton(icons[45]);
		add(buttons[45]);
		
		for (int i = 40;i < 48; i++) {
			buttons[i].setBounds(xCoordinate[i] - 30, yCoordinate[i], 30, 30);
		}
		
		for (int i = 0;i < 48; i++) {
			buttons[i].setToolTipText(nameOfLand[i]);
		}
		
		buttons[4].setToolTipText(nameOfLand[4] + " 新闻点");
		buttons[5].setToolTipText(nameOfLand[5] + " 赠卡处");
		buttons[11].setToolTipText(nameOfLand[11] + " 赠点券处");
		buttons[12].setToolTipText(nameOfLand[12] + " 赠卡处");
		buttons[19].setToolTipText(nameOfLand[19] + " 彩票店");
		buttons[20].setToolTipText(nameOfLand[20] + " 道具店");
		buttons[21].setToolTipText(nameOfLand[21] + " 银行");
		buttons[22].setToolTipText(nameOfLand[22] + " 新闻点");
		buttons[29].setToolTipText(nameOfLand[29] + " 赠点券处");
		buttons[35].setToolTipText(nameOfLand[35] + " 赠点券处");
		buttons[42].setToolTipText(nameOfLand[42] + " 彩票店");
		buttons[43].setToolTipText(nameOfLand[43] + " 道具店");
		buttons[44].setToolTipText(nameOfLand[44] + " 银行");
		buttons[45].setToolTipText(nameOfLand[45] + " 新闻点");
		
		for (int i =0; i < 48; i++) {
			buttons[i].setBorderPainted(false);
			barrage[i].setBorderPainted(false);
		}
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(font);
		g.drawImage(image, 0, 0, 733, 509, this);
		g.drawImage(player, 532, 0, 255, 190, this);
		g.drawString("点券", 580, 310);
		g.drawString("现金", 580, 340);
		g.drawString("存款", 580, 370);
		g.drawString("房产", 580, 400);
		g.drawString("总产", 580, 430);
		g.drawString("Player:", 645, 250);
	}

}
