import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

import javax.swing.*;
//游戏主界面，加到MonopolyFrame中
public class PlayerPanel extends MapPanel {
	Timer timer = new Timer(50,new TimerListener());//用于掷骰子
	Timer timerOfForward = new Timer(100,new TimerListener1());//用于控制玩家前进
	int currentPlayer = 0;
	int[] walkNumber = {0,0,0};
	Image[] image = new Image[Monopoly.totalAmount];
	Image[] image0 = new Image[Monopoly.totalAmount];
	ImageIcon[] imageIcon = new ImageIcon[Monopoly.totalAmount];
	ImageIcon diceIcon = new ImageIcon("icons/dice1.png");
	JButton dice = new JButton(diceIcon);
	Image diceImage = diceIcon.getImage();
	JButton readRecord = new JButton(new ImageIcon("icons/readRecord1.png"));
	JButton saveGame = new JButton(new ImageIcon("icons/saveGame1.gif"));
	JButton tool = new JButton(new ImageIcon("icons/toolCastle.gif"));
	JButton lose = new JButton(new ImageIcon("icons/lose.gif"));
	JButton music = new JButton(new ImageIcon("icons/music.gif"));
	int timerCount = 0;//用于控制timer停下
	int rm;//骰子点数
	int controlDice;//遥控骰子得到的点数
	int year = 2015,month = 1,day = 1,dayOfWeek = 4;
	String[] dayOfWeekStr = new String[]{"日","一","二","三","四","五","六"};
	 
	public PlayerPanel() {
		setLayout(null);
		Monopoly.music.backMusic1.loop();
		if (Monopoly.totalAmount == 1) {
			Monopoly.players[0].x = xCoordinate[Monopoly.players[0].positonOfPlayer];
			Monopoly.players[0].y = yCoordinate[Monopoly.players[0].positonOfPlayer];
		}
		else if (Monopoly.totalAmount == 2) {
			Monopoly.players[0].x = xCoordinate[Monopoly.players[0].positonOfPlayer];
			Monopoly.players[0].y = yCoordinate[Monopoly.players[0].positonOfPlayer];
			Monopoly.players[1].positonOfPlayer = Monopoly.players[1].positonOfPlayer + 16;
			Monopoly.players[1].x = xCoordinate[Monopoly.players[1].positonOfPlayer];
			Monopoly.players[1].y = yCoordinate[Monopoly.players[1].positonOfPlayer];
		}
		else {
			Monopoly.players[0].x = xCoordinate[Monopoly.players[0].positonOfPlayer];
			Monopoly.players[0].y = yCoordinate[Monopoly.players[0].positonOfPlayer];
			Monopoly.players[1].positonOfPlayer = Monopoly.players[1].positonOfPlayer + 16;
			Monopoly.players[1].x = xCoordinate[Monopoly.players[1].positonOfPlayer];
			Monopoly.players[1].y = yCoordinate[Monopoly.players[1].positonOfPlayer];
			Monopoly.players[2].positonOfPlayer = Monopoly.players[2].positonOfPlayer + 25;
			Monopoly.players[2].x = xCoordinate[Monopoly.players[2].positonOfPlayer];
			Monopoly.players[2].y = yCoordinate[Monopoly.players[2].positonOfPlayer];
		}
		for (int i =0; i < Monopoly.totalAmount; i++) {
			imageIcon[i] = new ImageIcon(Monopoly.players[i].image);
			image[i] = imageIcon[i].getImage();
		}
		add(dice);
		dice.setBounds(247, 73, 50, 50);
		dice.setToolTipText("点击掷骰子前进");
		dice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Monopoly.music.button.play();
				Monopoly.music.dice.play();
				timer.start();
			}		
		});
		add(readRecord);
		readRecord.setBorderPainted(false);
		readRecord.setPressedIcon(new ImageIcon("icons/readRecord1.png"));
		readRecord.setDisabledIcon(new ImageIcon("icons/readRecord1.png"));
		readRecord.setBounds(128, 129, 63, 63);
		readRecord.setToolTipText("点我读取游戏记录");
		readRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Monopoly.music.button.play();
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

						Game.playerPanel.currentPlayer = in.nextInt();
						year = in.nextInt();
						month = in.nextInt();
						day = in.nextInt();
						dayOfWeek = in.nextInt();
						for (int i = 0; i < 48; i++) {
							MapPanel.ownerOfLand[i] = in.nextInt();
							MapPanel.levelOfLand[i] = in.nextInt();
							if (in.next().equals("true"))
								MapPanel.barricades[i] = true;
							else
								MapPanel.barricades[i] = false;
						}
						//初始化人物头像
						image = new Image[Monopoly.totalAmount];
						imageIcon = new ImageIcon[Monopoly.totalAmount];
						for (int i =0; i < Monopoly.totalAmount; i++) {
							imageIcon[i] = new ImageIcon(Monopoly.players[i].image);
							image[i] = imageIcon[i].getImage();
						}
						//初始化土地信息
						for (int i = 0; i < 48; i++) {
							for (int j = 0; j < Monopoly.totalAmount; j++) {
								if (MapPanel.ownerOfLand[i] == j) {
									Game.playerPanel.buttons[i].setIcon(new ImageIcon("icons/owner" + j + (MapPanel.levelOfLand[i] - 1) + ".png"));
									MapPanel.buttons[i].setToolTipText(MapPanel.nameOfLand[i] + "  拥有者：" + Monopoly.players[j].name + "  等级：" + MapPanel.levelOfLand[i]);	
								}
								if (MapPanel.ownerOfLand[i] == 3) {
									Game.playerPanel.buttons[i].setIcon(new ImageIcon("icons/land.png"));
									MapPanel.buttons[i].setToolTipText(MapPanel.nameOfLand[i]);	
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
		add(saveGame);
		saveGame.setBorderPainted(false);
		saveGame.setPressedIcon(new ImageIcon("icons/saveGame1.png"));
		saveGame.setBounds(352, 129, 63, 63);
		saveGame.setToolTipText("点我保存游戏");
		saveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Monopoly.music.button.play();
				try {
					PrintWriter out = new PrintWriter(new File("save.txt"));
					out.print(Monopoly.totalAmount + " ");
					for (int i = 0; i < Monopoly.totalAmount; i++) {
						out.print(Monopoly.players[i].image + " " + Monopoly.players[i].name + " " + Monopoly.players[i].smallImage + " ");
					}
					out.println(Game.playerPanel.currentPlayer + " " + year + " " + month + " " + day + " " + dayOfWeek
							+ " ");
					for (int i = 0; i < 48; i++) {
						out.print(MapPanel.ownerOfLand[i] + " ");
						out.print(MapPanel.levelOfLand[i] + " ");
						out.print(MapPanel.barricades[i] + " ");
					}
					for (int i = 0; i < Monopoly.totalAmount; i++) {
						out.print(Monopoly.players[i].cash + " " + Monopoly.players[i].deposit + " " + Monopoly.players[i].directionOfPlayer + " " + 
								Monopoly.players[i].houseProperty + " " + Monopoly.players[i].numberOfLand + " " + 
								Monopoly.players[i].point + " " + Monopoly.players[i].positonOfPlayer + " " + " " + 
								Monopoly.players[i].totalMoney + " " + Monopoly.players[i].x + " " + Monopoly.players[i].y + " " + 
								Monopoly.players[i].numberOfcard[0] + " " + Monopoly.players[i].numberOfcard[1] + " " + Monopoly.players[i].numberOfcard[2] + " " + 
								Monopoly.players[i].numberOfcard[3] + " " + Monopoly.players[i].numberOfcard[4] + " " + Monopoly.players[i].isAI + " ");
					}
					out.close();
					JOptionPane.showMessageDialog(Game.playerPanel, "储存成功", "储存", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(Monopoly.players[Game.playerPanel.currentPlayer].image));
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
		add(tool);
		tool.setBorderPainted(false);
		tool.setBounds(223, 349, 94, 96);
		tool.setToolTipText("我是城堡也是卡片库");
		tool.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Monopoly.music.button.play();
				Game.useCardFrontPanel.repaint();
				Monopoly.useCardDialog.add(Game.useCardFrontPanel);
				Monopoly.useCardDialog.setVisible(true);
			}		
		});
		add(lose);
		lose.setBorderPainted(false);
		lose.setPressedIcon(new ImageIcon("icons/lose.png"));
		lose.setBounds(352, 287, 61, 61);
		lose.setToolTipText("点我认输退出游戏");
		lose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Monopoly.music.button.play();
				for (int i = 0; i < 48; i++) {
					if (MapPanel.ownerOfLand[i] == Game.playerPanel.currentPlayer) {
						MapPanel.ownerOfLand[i] = 3;
						Game.playerPanel.buttons[i].setToolTipText(MapPanel.nameOfLand[i] + " 玩家" + Monopoly.players[Game.playerPanel.currentPlayer].name + "放弃的土地");
					}
				}
				//玩家退出函数
				System.exit(0);
				System.out.println("hello");
			}		
		});
		add(music);
		music.setBorderPainted(false);
		music.setDisabledIcon(new ImageIcon("icons/music.png"));
		music.setMnemonic('m');
		music.setPressedIcon(new ImageIcon("icons/music.png"));
		music.setBounds(127, 287, 61, 61);
		music.setToolTipText("点我设置音效");
		music.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Monopoly.music.button.play();
			}		
		});
		
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(diceImage, 247, 73, 50, 50, this);
		//画日期
		g.setFont(font);
		g.setColor(Color.CYAN);
		g.drawString(year + "/" + month + "/" + day, 593, 100);
		g.drawString("星期" + dayOfWeekStr[dayOfWeek], 603, 130);
		
		//画玩家信息
		g.setFont(new Font(" ",Font.ITALIC,15));
		g.drawImage(image[currentPlayer], 572, 220, 70, 70, this);
		g.drawString(Monopoly.players[currentPlayer].name,645,275);
		g.setFont(font);
		g.drawString("" + Monopoly.players[currentPlayer].point, 630, 310);
		g.drawString("" + Monopoly.players[currentPlayer].cash, 630, 340);
		g.drawString("" + Monopoly.players[currentPlayer].deposit, 630, 370);
		g.drawString("" + Monopoly.players[currentPlayer].houseProperty, 630, 400);
		g.drawString("" + Monopoly.players[currentPlayer].totalMoney, 630, 430);
		
		//画路障
		for (int i = 0; i < 48; i ++) {
			if (barricades[i]) {
				g.drawImage(new ImageIcon("icons/barrage.png").getImage(), xCoordinate[i] + 2, yCoordinate[i] + 2, 26, 26, this);
			}
		}
		
		//玩家行走
		g.drawImage(Monopoly.players[currentPlayer].imageOfWalk[walkNumber[currentPlayer]], Monopoly.players[currentPlayer].x+2, Monopoly.players[currentPlayer].y, 26, 31, this);
		g.drawImage(Monopoly.players[(currentPlayer+1)%Monopoly.totalAmount].imageOfWalk[walkNumber[(currentPlayer+1)%Monopoly.totalAmount]], Monopoly.players[(currentPlayer+1)%Monopoly.totalAmount].x+2, Monopoly.players[(currentPlayer+1)%Monopoly.totalAmount].y, 26, 31, this);
		g.drawImage(Monopoly.players[(currentPlayer+2)%Monopoly.totalAmount].imageOfWalk[walkNumber[(currentPlayer+2)%Monopoly.totalAmount]], Monopoly.players[(currentPlayer+2)%Monopoly.totalAmount].x+2, Monopoly.players[(currentPlayer+2)%Monopoly.totalAmount].y, 26, 31, this);		
	}
	
	//掷骰子动画
	class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
				rm = (int)(Math.random()*6) + 1;
				dice.setIcon(new ImageIcon("icons/dice" + rm + ".png"));
				timerCount++;
				if (timerCount++ > 80) {
					timer.stop();
					timerCount = 0;
					if (Game.isControl) {
						rm = controlDice;
						Game.isControl = false;
						dice.setIcon(new ImageIcon("icons/dice" + rm + ".png"));
					}
					diceImage = new ImageIcon("icons/dice" + rm + ".png").getImage();
					dice.setVisible(false);
					timerOfForward.start();
				}
		}
	}
	
	//玩家行走动画
	class TimerListener1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//玩家行走动态
			if (Game.isControl) {
				rm = controlDice;
				Game.isControl = false;
			}
			//乌龟卡
			if (Monopoly.players[currentPlayer].turtle > 0) {
				if (timerCount%8 == 7)
					Monopoly.players[currentPlayer].turtle--;
				rm = 1;
			}
			//滞留卡
			if (Monopoly.players[currentPlayer].stop) {
				Monopoly.players[currentPlayer].stop = false;
				rm = 0;
			}
			//土地卡
			if (Monopoly.players[currentPlayer].land > 0) {
				if (rm != 0) {
					if (timerCount%(rm*8) == 7)//不一定要为7，只是让在行走过程中该代码只执行一次
						Monopoly.players[currentPlayer].land--;
				}
				else
					Monopoly.players[currentPlayer].land--;
			}
			//财神卡
			if (Monopoly.players[currentPlayer].caishen > 0) {
				if (rm != 0) {
					if (timerCount%(rm*8) == 7)//不一定要为7，只是让在行走过程中该代码只执行一次
						Monopoly.players[currentPlayer].caishen--;
				}
				else
					Monopoly.players[currentPlayer].caishen--;
				System.out.println(Monopoly.players[currentPlayer].caishen);
			}
			//福神卡
			if (Monopoly.players[currentPlayer].fushen > 0) {
				if (rm != 0) {
					if (timerCount%(rm*8) == 7)//不一定要为7，只是让在行走过程中该代码只执行一次
						Monopoly.players[currentPlayer].fushen--;
				}
				else
					Monopoly.players[currentPlayer].fushen--;
				System.out.println(Monopoly.players[currentPlayer].fushen);
			}
			if (timerCount < rm*8) {
				if (timerCount%8 == 0) {
					if (Monopoly.players[currentPlayer].directionOfPlayer == 0)
						Monopoly.players[currentPlayer].positonOfPlayer = (Monopoly.players[currentPlayer].positonOfPlayer + 1)%48;
					else
						Monopoly.players[currentPlayer].positonOfPlayer = (Monopoly.players[currentPlayer].positonOfPlayer - 1 + 48)%48;
				}
				
				if (Monopoly.players[currentPlayer].directionOfPlayer == 0) {
						Monopoly.players[currentPlayer].x += (xCoordinate[(Monopoly.players[currentPlayer].positonOfPlayer)%48] -xCoordinate[(Monopoly.players[currentPlayer].positonOfPlayer - 1 + 48)%48])/8;
						Monopoly.players[currentPlayer].y += (yCoordinate[(Monopoly.players[currentPlayer].positonOfPlayer)%48] -yCoordinate[(Monopoly.players[currentPlayer].positonOfPlayer - 1 + 48)%48])/8;
				}
				else {//转向
						Monopoly.players[currentPlayer].x += (xCoordinate[(Monopoly.players[currentPlayer].positonOfPlayer)%48] -xCoordinate[(Monopoly.players[currentPlayer].positonOfPlayer + 1)%48])/8;
						Monopoly.players[currentPlayer].y += (yCoordinate[(Monopoly.players[currentPlayer].positonOfPlayer)%48] -yCoordinate[(Monopoly.players[currentPlayer].positonOfPlayer + 1)%48])/8;	
				}
				
				//画行走动态
				if (Monopoly.players[currentPlayer].directionOfPlayer == 0) {
					if (Monopoly.players[currentPlayer].positonOfPlayer == 0)
						walkNumber[currentPlayer] = timerCount%3 + 6;
					else if (Monopoly.players[currentPlayer].positonOfPlayer < 4)
						walkNumber[currentPlayer] = timerCount%3 + 3;
					else if (Monopoly.players[currentPlayer].positonOfPlayer < 7)
						walkNumber[currentPlayer] = timerCount%3;
					else if (Monopoly.players[currentPlayer].positonOfPlayer < 11)
						walkNumber[currentPlayer] = timerCount%3 + 3;
					else if (Monopoly.players[currentPlayer].positonOfPlayer < 14)
						walkNumber[currentPlayer] = timerCount%3 + 6;
					else if (Monopoly.players[currentPlayer].positonOfPlayer < 17)
						walkNumber[currentPlayer] = timerCount%3 + 3;
					else if (Monopoly.players[currentPlayer].positonOfPlayer < 26)
						walkNumber[currentPlayer] = timerCount%3;
					else if (Monopoly.players[currentPlayer].positonOfPlayer < 29)
						walkNumber[currentPlayer] = timerCount%3 + 9;
					else if (Monopoly.players[currentPlayer].positonOfPlayer < 31)
						walkNumber[currentPlayer] = timerCount%3 + 6;
					else if (Monopoly.players[currentPlayer].positonOfPlayer < 35)
						walkNumber[currentPlayer] = timerCount%3 + 9;
					else if (Monopoly.players[currentPlayer].positonOfPlayer < 37)
						walkNumber[currentPlayer] = timerCount%3;
					else if (Monopoly.players[currentPlayer].positonOfPlayer < 40)
						walkNumber[currentPlayer] = timerCount%3 + 9;
					else 
						walkNumber[currentPlayer] = timerCount%3 + 6;
				}
				else {//转向
					if (Monopoly.players[currentPlayer].positonOfPlayer < 3)
						walkNumber[currentPlayer] = timerCount%3 + 9;
					else if (Monopoly.players[currentPlayer].positonOfPlayer < 6)
						walkNumber[currentPlayer] = timerCount%3 + 6;
					else if (Monopoly.players[currentPlayer].positonOfPlayer < 10)
						walkNumber[currentPlayer] = timerCount%3 + 9;
					else if (Monopoly.players[currentPlayer].positonOfPlayer < 13)
						walkNumber[currentPlayer] = timerCount%3;
					else if (Monopoly.players[currentPlayer].positonOfPlayer < 16)
						walkNumber[currentPlayer] = timerCount%3 + 9;
					else if (Monopoly.players[currentPlayer].positonOfPlayer < 25)
						walkNumber[currentPlayer] = timerCount%3 + 6;
					else if (Monopoly.players[currentPlayer].positonOfPlayer < 28)
						walkNumber[currentPlayer] = timerCount%3 + 3;
					else if (Monopoly.players[currentPlayer].positonOfPlayer < 30)
						walkNumber[currentPlayer] = timerCount%3;
					else if (Monopoly.players[currentPlayer].positonOfPlayer < 34)
						walkNumber[currentPlayer] = timerCount%3 + 3;
					else if (Monopoly.players[currentPlayer].positonOfPlayer < 36)
						walkNumber[currentPlayer] = timerCount%3 + 6;
					else if (Monopoly.players[currentPlayer].positonOfPlayer < 39)
						walkNumber[currentPlayer] = timerCount%3 + 3;
					else 
						walkNumber[currentPlayer] = timerCount%3;
				}
				repaint();
				if (timerCount%8 == 7) {
					if (ownerOfLand[Monopoly.players[currentPlayer].positonOfPlayer] == 10) {//10代表银行
						timerOfForward.stop();
						if (Monopoly.players[currentPlayer].isAI) {
							Monopoly.players[currentPlayer].cash -= 1000;
							Monopoly.players[currentPlayer].deposit += 1000;
							try {
								Thread.sleep(2000);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
							repaint();
							timerOfForward.start();
						}
						else {
							Monopoly.bankDialog.add(Game.bankFrontPanel);
							Monopoly.bankDialog.setVisible(true);
						}
					}
					if (barricades[Monopoly.players[currentPlayer].positonOfPlayer]) {
						barricades[Monopoly.players[currentPlayer].positonOfPlayer] = false;
						timerOfForward.stop();
						timerCount = 0;
						Monopoly.land.event(currentPlayer,Monopoly.players[currentPlayer].positonOfPlayer);
						timePass(currentPlayer);
						currentPlayer = (currentPlayer + 1)%Monopoly.totalAmount;
						dice.setVisible(true);
						if (Monopoly.players[currentPlayer].isAI) {
							dice.setVisible(false);
							Game.ai.option(currentPlayer, Monopoly.players[currentPlayer].positonOfPlayer);
						}
					}
				}
				timerCount++;
			}
			
			//走完了
			else {
				timerOfForward.stop();
				timerCount = 0;
				Monopoly.land.event(currentPlayer,Monopoly.players[currentPlayer].positonOfPlayer);
				timePass(currentPlayer);
				currentPlayer = (currentPlayer + 1)%Monopoly.totalAmount;
				tool.setVisible(true);
				dice.setVisible(true);
				repaint();
				if (Monopoly.players[currentPlayer].isAI) {
					dice.setVisible(false);
					tool.setVisible(false);
					Game.ai.option(currentPlayer, Monopoly.players[currentPlayer].positonOfPlayer);
				}
			}		
			
		}
	}
	
	//i表示玩家，玩家一个轮回，时间加一天
	public void timePass(int i) {
		i += 1;
		if (i%Monopoly.totalAmount==0)
		{
			day += 1;
			dayOfWeek = (dayOfWeek + 1)%7;
			if (day > 31 && (month == 1||month == 3||month==5||month==7||month == 8||month == 10||month == 12))
			{
				month += 1;
				day %= 31;
				Monopoly.land.interestOfBank();
			}
			if (day > 30 && (month == 4||month == 6||month == 9||month ==11))
			{
				month += 1;
				day %= 30;
				Monopoly.land.interestOfBank();
			}
			if (day > 29 && month == 2 && (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))
			{
	
				month += 1;
				day %= 29;
				Monopoly.land.interestOfBank();
			}
			if (day > 28 && month == 2 && !((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)))
			{
				month += 1;
				day %= 28;
				Monopoly.land.interestOfBank();
			}
		}
	}
}
