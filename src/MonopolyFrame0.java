import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.*;
//游戏开始前的玩家选择界面
public class MonopolyFrame0 extends JFrame {
	//Music music = new Music();
	public MonopolyFrame0() {
		add(new frontPanel());
		//music.loop(music.backMusic);
		this.setTitle("大富翁——群山环绕的湖");
	    this.setLocation(250,100);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setSize(739, 537);
	    this.setResizable(false);
	    this.setVisible(true);	 
		
	}
	class backPanel extends JPanel {
		Image image;
		ImageIcon monopolyIcon = new ImageIcon("icons/monopoly0.png");
		public backPanel() {
			image = monopolyIcon.getImage();
		}
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, 0, 0, 733, 509, this);
		}
	}
	
	class frontPanel extends backPanel {
		ImageIcon[] player00 = new ImageIcon[]{
				new ImageIcon("icons/player0_0.png"),new ImageIcon("icons/player0_1.png"),new ImageIcon("icons/player0_2.png")
				,new ImageIcon("icons/player0_3.png"),new ImageIcon("icons/player0_4.png"),new ImageIcon("icons/player0_5.png")
				,new ImageIcon("icons/player0_6.png"),new ImageIcon("icons/player0_7.png"),new ImageIcon("icons/player0_8.png")
				,new ImageIcon("icons/player0_9.png"),new ImageIcon("icons/player0_10.png"),new ImageIcon("icons/player0_11.png")};
		ImageIcon[] player11 = new ImageIcon[]{
				new ImageIcon("icons/player1_0.png"),new ImageIcon("icons/player1_1.png"),new ImageIcon("icons/player1_2.png")
				,new ImageIcon("icons/player1_3.png"),new ImageIcon("icons/player1_4.png"),new ImageIcon("icons/player1_5.png")
				,new ImageIcon("icons/player1_6.png"),new ImageIcon("icons/player1_7.png"),new ImageIcon("icons/player1_8.png")
				,new ImageIcon("icons/player1_9.png"),new ImageIcon("icons/player1_10.png"),new ImageIcon("icons/player1_11.png")};
		ImageIcon[] player22 = new ImageIcon[]{
				new ImageIcon("icons/player2_0.png"),new ImageIcon("icons/player2_1.png"),new ImageIcon("icons/player2_2.png")
				,new ImageIcon("icons/player2_3.png"),new ImageIcon("icons/player2_4.png"),new ImageIcon("icons/player2_5.png")
				,new ImageIcon("icons/player2_6.png"),new ImageIcon("icons/player2_7.png"),new ImageIcon("icons/player2_8.png")
				,new ImageIcon("icons/player2_9.png"),new ImageIcon("icons/player2_10.png"),new ImageIcon("icons/player2_11.png")};
		
		private int playerNumber = 0;//选择玩家时用于控制玩家选择
		ImageIcon playIcon = new ImageIcon("icons/start.gif");
		ImageIcon restartIcon = new ImageIcon("icons/restart.png");
		ImageIcon readRecordIcon = new ImageIcon("icons/readRecord.png");
		ImageIcon quitIcon = new ImageIcon("icons/quit.png");
		ImageIcon addIcon1 = new ImageIcon("icons/add.png");
		ImageIcon addIcon2 = new ImageIcon("icons/add.png");
		ImageIcon nextIcon = new ImageIcon("icons/next.png");
		ImageIcon player0Icon = new ImageIcon("icons/player0Button.png");
		ImageIcon player1Icon = new ImageIcon("icons/player1Button.png");
		ImageIcon player2Icon = new ImageIcon("icons/player2Button.png");
		ImageIcon[] numberIcon = {new ImageIcon("icons/number1.png"),new ImageIcon("icons/number2.png"),new ImageIcon("icons/number3.png")};
		
		JButton play = new JButton(playIcon);
		JButton restart = new JButton(restartIcon);
		JButton readRecord = new JButton(readRecordIcon);
		JButton quit = new JButton(quitIcon);
		JButton add1 = new JButton(addIcon1);//选择玩家人数
		JButton add2 = new JButton(addIcon2);//选择玩家总数
		JButton next = new JButton(nextIcon);
		JButton player0 = new JButton(player0Icon);
		JButton player1 = new JButton(player1Icon);
		JButton player2 = new JButton(player2Icon);
		JButton number1 = new JButton(numberIcon[0]);
		JButton number2 = new JButton(numberIcon[0]);
		public frontPanel() {
			setLayout(null);
			add(play);
			play.setBorderPainted(false);
			play.setRolloverIcon(new ImageIcon("icons/start0.gif"));
			play.setPressedIcon(new ImageIcon("icons/start1.gif"));
			play.setToolTipText("点我开始游戏");
			play.setBounds(255, 266, playIcon.getIconWidth(), playIcon.getIconHeight());
			play.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Monopoly.music.button.play();
					monopolyIcon = new ImageIcon("icons/monopoly1.png");
					image = monopolyIcon.getImage();
					play.setVisible(false);
					restart.setVisible(true);
					readRecord.setVisible(true);
					quit.setVisible(true);
					repaint();
				}
				
			});
			
			add(restart);
			restart.setBorderPainted(false);
			restart.setRolloverIcon(new ImageIcon("icons/restart0.png"));
			restart.setBounds(215, 230, restartIcon.getIconWidth(), restartIcon.getIconHeight());
			restart.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Monopoly.music.button.play();
					monopolyIcon = new ImageIcon("icons/monopoly2.png");
					image = monopolyIcon.getImage();
					restart.setVisible(false);
					readRecord.setVisible(false);
					quit.setVisible(false);
					add1.setVisible(true);
					add2.setVisible(true);
					next.setVisible(true);
					number1.setVisible(true);
					number2.setVisible(true);
					repaint();
				}
				
			});
			restart.setVisible(false);
			
			add(readRecord);
			readRecord.setBorderPainted(false);
			readRecord.setRolloverIcon(new ImageIcon("icons/readRecord0.png"));
			readRecord.setBounds(275, 318, readRecordIcon.getIconWidth(), readRecordIcon.getIconHeight());
			readRecord.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Monopoly.music.button.play();
					Monopoly.music.backMusic.stop();
					try {
						ImageIcon[] player0 = new ImageIcon[]{
								new ImageIcon("icons/player0_0.png"),new ImageIcon("icons/player0_1.png"),new ImageIcon("icons/player0_2.png")
								,new ImageIcon("icons/player0_3.png"),new ImageIcon("icons/player0_4.png"),new ImageIcon("icons/player0_5.png")
								,new ImageIcon("icons/player0_6.png"),new ImageIcon("icons/player0_7.png"),new ImageIcon("icons/player0_8.png")
								,new ImageIcon("icons/player0_9.png"),new ImageIcon("icons/player0_10.png"),new ImageIcon("icons/player0_11.png")};
						 	ImageIcon[] player1 = new ImageIcon[]{
						 			new ImageIcon("icons/player1_0.png"),new ImageIcon("icons/player1_1.png"),new ImageIcon("icons/player1_2.png")
						 			,new ImageIcon("icons/player1_3.png"),new ImageIcon("icons/player1_4.png"),new ImageIcon("icons/player1_5.png")
						 			,new ImageIcon("icons/player1_6.png"),new ImageIcon("icons/player1_7.png"),new ImageIcon("icons/player1_8.png")
						 			,new ImageIcon("icons/player1_9.png"),new ImageIcon("icons/player1_10.png"),new ImageIcon("icons/player1_11.png")};
						 	ImageIcon[] player2 = new ImageIcon[]{
						 			new ImageIcon("icons/player2_0.png"),new ImageIcon("icons/player2_1.png"),new ImageIcon("icons/player2_2.png")
						 			,new ImageIcon("icons/player2_3.png"),new ImageIcon("icons/player2_4.png"),new ImageIcon("icons/player2_5.png")
						 			,new ImageIcon("icons/player2_6.png"),new ImageIcon("icons/player2_7.png"),new ImageIcon("icons/player2_8.png")
						 			,new ImageIcon("icons/player2_9.png"),new ImageIcon("icons/player2_10.png"),new ImageIcon("icons/player2_11.png")};
						Scanner in = new Scanner(new File("save.txt"));
						while(in.hasNext()) {
							Monopoly.totalAmount = in.nextInt();
							Monopoly.players = new Player[Monopoly.totalAmount];
							for (int j = 0; j < Monopoly.totalAmount; j++) {
								Monopoly.players[j] = new Player();
							}
							for (int i = 0; i < Monopoly.totalAmount; i++) {
								Monopoly.players[i].image = in.next(); 
								Monopoly.players[i].name = in.next();
								Monopoly.players[i].smallImage = in.next(); 
							}
							Monopoly.monopolyFrame = new MonopolyFrame();
							Monopoly.monopolyFrame0.setVisible(false);
							Monopoly.monopolyFrame.setVisible(true);
							
							Game.playerPanel.currentPlayer = in.nextInt();
							Game.playerPanel.year = in.nextInt();
							Game.playerPanel.month = in.nextInt();
							Game.playerPanel.day = in.nextInt();
							Game.playerPanel.dayOfWeek = in.nextInt();
							for (int i = 0; i < 48; i++) {
								MapPanel.ownerOfLand[i] = in.nextInt();
								MapPanel.levelOfLand[i] = in.nextInt();
								if (in.next().equals("true"))
									MapPanel.barricades[i] = true;
								else
									MapPanel.barricades[i] = false;
							}
							for (int i = 0; i < 48; i++) {
								for (int j = 0; j < Monopoly.totalAmount; j++) {
									if (MapPanel.ownerOfLand[i] == j) {
										Game.playerPanel.buttons[i].setIcon(new ImageIcon("icons/owner" + j + (MapPanel.levelOfLand[i] - 1) + ".png"));
										MapPanel.buttons[i].setToolTipText(MapPanel.nameOfLand[i] + "  拥有者：" + Monopoly.players[j].name + "  等级：" + MapPanel.levelOfLand[i]);	
									}
									if (MapPanel.ownerOfLand[i] == 3) {
										Game.playerPanel.buttons[i].setIcon(new ImageIcon("icons/land.png"));
									}
								}
							}
							for (int i = 0; i < Monopoly.totalAmount; i++) {
								Monopoly.players[i].cash = in.nextInt();
								Monopoly.players[i].deposit = in.nextInt();
								Monopoly.players[i].directionOfPlayer = in.nextInt(); 
								Monopoly.players[i].houseProperty = in.nextInt();
								Monopoly.players[i].numberOfLand = in.nextInt(); 
								Monopoly.players[i].point = in.nextInt();
								Monopoly.players[i].positonOfPlayer = in.nextInt();
								Monopoly.players[i].totalMoney = in.nextInt();
								Monopoly.players[i].x = in.nextInt();
								Monopoly.players[i].y = in.nextInt();
								Monopoly.players[i].numberOfcard[0] = in.nextInt();
								Monopoly.players[i].numberOfcard[1] = in.nextInt();
								Monopoly.players[i].numberOfcard[2] = in.nextInt();
								Monopoly.players[i].numberOfcard[3] = in.nextInt();
								Monopoly.players[i].numberOfcard[4] = in.nextInt();	
								if (in.next().equals("true")) Monopoly.players[i].isAI = true;
								else Monopoly.players[i].isAI = false;
								if(Monopoly.players[i].name.equals("荻野千寻")) {
									for (int j = 0; j < 12;j++) {
										Monopoly.players[i].imageOfWalk[j] = player0[j].getImage();
									}
								}
								if(Monopoly.players[i].name.equals("崛越二郎")) {
									for (int j = 0; j < 12;j++) {
										Monopoly.players[i].imageOfWalk[j] = player1[j].getImage();
									}
								}
								if(Monopoly.players[i].name.equals("娜乌西卡")) {
									for (int j = 0; j < 12;j++) {
										Monopoly.players[i].imageOfWalk[j] = player2[j].getImage();
									}
								}
							}
							Game.playerPanel.repaint();
						}
					} 
					catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
					catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}		
			});
			readRecord.setVisible(false);
			
			add(quit);
			quit.setBorderPainted(false);
			quit.setRolloverIcon(new ImageIcon("icons/quit0.png"));
			quit.setBounds(205, 415, quitIcon.getIconWidth(), quitIcon.getIconHeight());
			quit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Monopoly.music.button.play();
					System.exit(0);
				}
				
			});
			quit.setVisible(false);
			
			add(number1);
			number1.setBounds(331, 358, numberIcon[0].getIconWidth(), numberIcon[0].getIconHeight());
			number1.setBorderPainted(false);
			number1.setVisible(false);
			
			add(number2);
			number2.setBounds(331, 430, numberIcon[1].getIconWidth(), numberIcon[1].getIconHeight());
			number2.setBorderPainted(false);
			number2.setVisible(false);
			
			add(add1);
			add1.setBorderPainted(false);
			add1.setRolloverIcon(new ImageIcon("icons/add0.png"));
			add1.setBounds(422, 351, addIcon1.getIconWidth(), addIcon1.getIconHeight());
			add1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Monopoly.music.button.play();
					Monopoly.playerAmount = Monopoly.playerAmount%3 + 1;
					number1.setIcon(numberIcon[Monopoly.playerAmount - 1]);
					if (Monopoly.playerAmount > Monopoly.totalAmount) {
						Monopoly.totalAmount = Monopoly.totalAmount%3 + 1;
						number2.setIcon(numberIcon[Monopoly.totalAmount - 1]);
					}
					repaint();
				}
				
			});
			add1.setBorderPainted(false);
			add1.setVisible(false);
			
			add(add2);
			add2.setBorderPainted(false);
			add2.setRolloverIcon(new ImageIcon("icons/add0.png"));
			add2.setBounds(420, 421, addIcon2.getIconWidth(), addIcon2.getIconHeight());
			add2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Monopoly.music.button.play();
					Monopoly.totalAmount = Monopoly.totalAmount%3 + 1;
					if (Monopoly.totalAmount == 1&&(Monopoly.playerAmount == 2||Monopoly.playerAmount == 3)) {
						Monopoly.playerAmount = 1;
						number1.setIcon(numberIcon[Monopoly.playerAmount - 1]);
					}
					if (Monopoly.totalAmount == 2&&Monopoly.playerAmount == 3) {
						Monopoly.playerAmount = 2;
						number1.setIcon(numberIcon[Monopoly.playerAmount - 1]);
					}
					number2.setIcon(numberIcon[Monopoly.totalAmount - 1]);
					repaint();
				}
				
			});
			add2.setBorderPainted(false);
			add2.setVisible(false);
			
			add(next);
			next.setBorderPainted(false);
			next.setRolloverIcon(new ImageIcon("icons/next0.png"));
			next.setBounds(635, 10, nextIcon.getIconWidth(), nextIcon.getIconHeight());
			next.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Monopoly.music.button.play();
					monopolyIcon = new ImageIcon("icons/monopoly3.png");
					image = monopolyIcon.getImage();
					add1.setVisible(false);
					add2.setVisible(false);
					next.setVisible(false);
					number1.setVisible(false);
					number2.setVisible(false);
					player0.setVisible(true);
					player1.setVisible(true);
					player2.setVisible(true);
					Monopoly.players = new Player[Monopoly.totalAmount];
					for (int i = 0; i < Monopoly.totalAmount; i++) {
						Monopoly.players[i] = new Player();
					}
					repaint();
				}
				
			});
			next.setVisible(false);
			
			add(player0);
			player0.setBorderPainted(false);
			player0.setRolloverIcon(new ImageIcon("icons/player0Button0.gif"));
			player0.setBounds(100, 0, player0Icon.getIconWidth(), player0Icon.getIconHeight());
			player0.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Monopoly.music.button.play();
					if (playerNumber == 0) {
						Monopoly.players[0].image = "icons/player0.png";
						Monopoly.players[0].smallImage = "icons/player0_0.png";
						Monopoly.players[0].name = "荻野千寻";
						for(int i = 0; i < 12; i++) {
							Monopoly.players[0].imageOfWalk[i] = player00[i].getImage();
						}
						playerNumber++;
						player0.setVisible(false);
					}
					else if (playerNumber == 1) {
						Monopoly.players[1].image = "icons/player0.png";
						Monopoly.players[1].smallImage = "icons/player0_0.png";
						Monopoly.players[1].name = "荻野千寻";
						for(int i = 0; i < 12; i++) {
							Monopoly.players[1].imageOfWalk[i] = player00[i].getImage();
						}
						playerNumber++;
						player0.setVisible(false);
					}
					else {
						Monopoly.players[2].image = "icons/player0.png";
						Monopoly.players[2].smallImage = "icons/player0_0.png";
						Monopoly.players[2].name = "荻野千寻";
						for(int i = 0; i < 12; i++) {
							Monopoly.players[2].imageOfWalk[i] = player00[i].getImage();
						}
						playerNumber++;
						player0.setVisible(false);
					}
					if (playerNumber == Monopoly.playerAmount) {
						boolean[] isChosen = {false,false,false};
						for (int j = 0; j < Monopoly.playerAmount; j++) {
							if (Monopoly.players[j].name.equals("荻野千寻")) isChosen[0] = true;
							if (Monopoly.players[j].name.equals("崛越二郎")) isChosen[1] = true;
							if (Monopoly.players[j].name.equals("娜乌西卡")) isChosen[2] = true;
						}
						if (Monopoly.playerAmount < Monopoly.totalAmount) {
							for (int i = Monopoly.playerAmount; i < Monopoly.totalAmount; i++) {
								while (true) {
									int j = (int)(Math.random()*3);
									if (!isChosen[j]) {
										if (j == 1) {
											Monopoly.players[i].image = "icons/player1.png";
											Monopoly.players[i].smallImage = "icons/player1_0.png";
											Monopoly.players[i].name = "崛越二郎";
											Monopoly.players[i].isAI = true;
											for(int k = 0; k < 12; k++) {
												Monopoly.players[i].imageOfWalk[k] = player11[k].getImage();
											}
											isChosen[j] = true;
											break;
										}
										if (j == 2) {
											Monopoly.players[i].image = "icons/player2.png";
											Monopoly.players[i].smallImage = "icons/player2_0.png";
											Monopoly.players[i].name = "娜乌西卡";
											Monopoly.players[i].isAI = true;
											for(int k = 0; k < 12; k++) {
												Monopoly.players[i].imageOfWalk[k] = player22[k].getImage();
											}
											isChosen[j] = true;
											break;
										}
									}
								}
							}
						}
						Monopoly.music.backMusic.stop();
						Monopoly.monopolyFrame0.setVisible(false);
						Monopoly.monopolyFrame = new MonopolyFrame();
						Monopoly.monopolyFrame.setVisible(true);
					}						
				}
				
			});
			player0.setVisible(false);
			
			add(player1);
			player1.setBorderPainted(false);
			player1.setRolloverIcon(new ImageIcon("icons/player1Button0.gif"));
			player1.setBounds(484, 175, player1Icon.getIconWidth(), player1Icon.getIconHeight());
			player1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Monopoly.music.button.play();
					if (playerNumber == 0) {
						Monopoly.players[0].image = "icons/player1.png";
						Monopoly.players[0].smallImage = "icons/player1_0.png";
						Monopoly.players[0].name = "崛越二郎";
						for(int i = 0; i < 12; i++) {
							Monopoly.players[0].imageOfWalk[i] = player11[i].getImage();
						}
						playerNumber++;
						player1.setVisible(false);
					}
					else if (playerNumber == 1) {
						Monopoly.players[1].image = "icons/player1.png";
						Monopoly.players[1].smallImage = "icons/player1_0.png";
						Monopoly.players[1].name = "崛越二郎";
						for(int i = 0; i < 12; i++) {
							Monopoly.players[1].imageOfWalk[i] = player11[i].getImage();
						}
						playerNumber++;
						player1.setVisible(false);
					}
					else {
						Monopoly.players[2].image = "icons/player1.png";
						Monopoly.players[2].smallImage = "icons/player1_0.png";
						Monopoly.players[2].name = "崛越二郎";
						for(int i = 0; i < 12; i++) {
							Monopoly.players[2].imageOfWalk[i] = player11[i].getImage();
						}
						playerNumber++;
						player1.setVisible(false);
					}
					if (playerNumber == Monopoly.playerAmount) {
						boolean[] isChosen = {false,false,false};
						for (int j = 0; j < Monopoly.playerAmount; j++) {
							if (Monopoly.players[j].name.equals("荻野千寻")) isChosen[0] = true;
							if (Monopoly.players[j].name.equals("崛越二郎")) isChosen[1] = true;
							if (Monopoly.players[j].name.equals("娜乌西卡")) isChosen[2] = true;
						}
						if (Monopoly.playerAmount < Monopoly.totalAmount) {
							for (int i = Monopoly.playerAmount; i < Monopoly.totalAmount; i++) {
								while (true) {
									int j = (int)(Math.random()*3);
									if (!isChosen[j]) {
										if (j == 0) {
											Monopoly.players[i].image = "icons/player0.png";
											Monopoly.players[i].smallImage = "icons/player0_0.png";
											Monopoly.players[i].name = "荻野千寻";
											Monopoly.players[i].isAI = true;
											for(int k = 0; k < 12; k++) {
												Monopoly.players[i].imageOfWalk[k] = player00[k].getImage();
											}
											isChosen[j] = true;
											break;
										}
										if (j == 2) {
											Monopoly.players[i].image = "icons/player2.png";
											Monopoly.players[i].smallImage = "icons/player2_0.png";
											Monopoly.players[i].name = "娜乌西卡";
											Monopoly.players[i].isAI = true;
											for(int k = 0; k < 12; k++) {
												Monopoly.players[i].imageOfWalk[k] = player22[k].getImage();
											}
											isChosen[j] = true;
											break;
										}
									}
								}
							}
						}
						Monopoly.music.backMusic.stop();
						Monopoly.monopolyFrame0.setVisible(false);
						Monopoly.monopolyFrame = new MonopolyFrame();
						Monopoly.monopolyFrame.setVisible(true);
					}
				}
				
			});
			player1.setVisible(false);
			
			add(player2);
			player2.setBorderPainted(false);
			player2.setRolloverIcon(new ImageIcon("icons/player2Button0.gif"));
			player2.setBounds(109, 341, player2Icon.getIconWidth(), player2Icon.getIconHeight());
			player2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Monopoly.music.button.play();
					if (playerNumber == 0) {
						Monopoly.players[0].image = "icons/player2.png";
						Monopoly.players[0].smallImage = "icons/player2_0.png";
						Monopoly.players[0].name = "娜乌西卡";
						for(int i = 0; i < 12; i++) {
							Monopoly.players[0].imageOfWalk[i] =player22[i].getImage();
						}
						playerNumber++;
						player2.setVisible(false);
					}
					else if (playerNumber == 1) {
						Monopoly.players[1].image = "icons/player2.png";
						Monopoly.players[1].smallImage = "icons/player2_0.png";
						Monopoly.players[1].name = "娜乌西卡";
						for(int i = 0; i < 12; i++) {
							Monopoly.players[1].imageOfWalk[i] = player22[i].getImage();
						}
						playerNumber++;
						player2.setVisible(false);
					}
					else {
						Monopoly.players[2].image = "icons/player2.png";
						Monopoly.players[2].smallImage = "icons/player2_0.png";
						Monopoly.players[2].name = "娜乌西卡";
						for(int i = 0; i < 12; i++) {
							Monopoly.players[2].imageOfWalk[i] = player22[i].getImage();
						}
						playerNumber++;
						player2.setVisible(false);
					}
					if (playerNumber == Monopoly.playerAmount) {
						boolean[] isChosen = {false,false,false};
						for (int j = 0; j < Monopoly.playerAmount; j++) {
							if (Monopoly.players[j].name.equals("荻野千寻")) isChosen[0] = true;
							if (Monopoly.players[j].name.equals("崛越二郎")) isChosen[1] = true;
							if (Monopoly.players[j].name.equals("娜乌西卡")) isChosen[2] = true;
						}
						if (Monopoly.playerAmount < Monopoly.totalAmount) {
							for (int i = Monopoly.playerAmount; i < Monopoly.totalAmount; i++) {
								while (true) {
									int j = (int)(Math.random()*3);
									if (!isChosen[j]) {
										if (j == 0) {
											Monopoly.players[i].image = "icons/player0.png";
											Monopoly.players[i].smallImage = "icons/player0_0.png";
											Monopoly.players[i].name = "荻野千寻";
											Monopoly.players[i].isAI = true;
											for(int k = 0; k < 12; k++) {
												Monopoly.players[i].imageOfWalk[k] = player00[k].getImage();
											}
											isChosen[j] = true;
											break;
										}
										if (j == 1) {
											Monopoly.players[i].image = "icons/player1.png";
											Monopoly.players[i].smallImage = "icons/player1_0.png";
											Monopoly.players[i].name = "崛越二郎";
											Monopoly.players[i].isAI = true;
											for(int k = 0; k < 12; k++) {
												Monopoly.players[i].imageOfWalk[k] = player11[k].getImage();
											}
											isChosen[j] = true;
											break;
										}
									}
								}
							}
						}
						Monopoly.music.backMusic.stop();
						Monopoly.monopolyFrame0.setVisible(false);
						Monopoly.monopolyFrame = new MonopolyFrame();
						Monopoly.monopolyFrame.setVisible(true);
					}
				}
				
			});
			player2.setVisible(false);
			
		}
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
		}
	}
}
