import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

//public class useCardDialog extends JFrame {
//	public useCardDialog() {
//		this.setTitle("���̡���Ⱥɽ���Ƶ���ˮ��");
//	    add(new FrontPanel());
//		this.setLocation(410,230);
//	    this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
//	    this.setSize(455, 325);
//	    this.setResizable(false);
//	    this.setVisible(false);	
//	}
//}	
	class BackPanelOfUseCard extends JPanel {
		Image image;
		ImageIcon monopolyIcon = new ImageIcon("icons/useCard.png");
		public BackPanelOfUseCard() {
			image = monopolyIcon.getImage();
		}
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, 0, 0, 450, 300, this);
		}	
	}
	//ʵ��ʹ�ÿ�Ƭ����
	public class FrontPanelOfUseCard extends BackPanelOfUseCard {
		ImageIcon averageIcon  = new ImageIcon("icons/average.png");
		ImageIcon changeDirectionIcon  = new ImageIcon("icons/changeDirection.png");
		ImageIcon barricadesIcon  = new ImageIcon("icons/barricades.png");
		ImageIcon controlDiceIcon  = new ImageIcon("icons/controlDice.png");
		ImageIcon robIcon  = new ImageIcon("icons/rob.png");
		ImageIcon caipiaoIcon = new ImageIcon("icons/caipiao.png");
		ImageIcon turtleIcon = new ImageIcon("icons/turtle.png");
		ImageIcon stopIcon = new ImageIcon("icons/stop.png");
		ImageIcon goudiIcon = new ImageIcon("icons/goudi.png");
		ImageIcon chaiqianIcon = new ImageIcon("icons/chaiqian.png");
		ImageIcon caishenIcon = new ImageIcon("icons/caishen.png");
		ImageIcon fushenIcon = new ImageIcon("icons/fushen.png");
		ImageIcon monsterIcon = new ImageIcon("icons/monster.png");
		ImageIcon chashuiIcon = new ImageIcon("icons/chashui.png");
		ImageIcon tudiIcon = new ImageIcon("icons/tudi.png");
		ImageIcon noIcon = new ImageIcon("icons/no.png");
		
		JButton average = new JButton(averageIcon);
		JButton changeDirection = new JButton(changeDirectionIcon);
		JButton barricades = new JButton(barricadesIcon);
		JButton controlDice = new JButton(controlDiceIcon);
		JButton rob = new JButton(robIcon);
		JButton caipiao = new JButton(caipiaoIcon);
		JButton turtle = new JButton(turtleIcon);
		JButton stop = new JButton(stopIcon);
		JButton goudi = new JButton(goudiIcon);
		JButton chaiqian = new JButton(chaiqianIcon);
		JButton caishen = new JButton(caishenIcon);
		JButton fushen = new JButton(fushenIcon);
		JButton monster = new JButton(monsterIcon);
		JButton chashui = new JButton(chashuiIcon);
		JButton tudi = new JButton(tudiIcon);
		JButton no = new JButton(noIcon);
		
		Font font = new Font("hello",Font.ITALIC,20);
		public FrontPanelOfUseCard() {
			setLayout(null);
			add(caishen);
			caishen.setToolTipText("����(���ۿ�)��ʹ��ʱ�������10000Ԫ�ֽ𣬰˻غ��ھ����������ز���֧����·��");
			caishen.setBounds(298, 241, 35, 52);
			caishen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Monopoly.music.button.play();
					if (Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[14] > 0) {
						Monopoly.players[Game.playerPanel.currentPlayer].cash += 10000;
						Monopoly.players[Game.playerPanel.currentPlayer].totalMoney += 10000;
						Monopoly.players[Game.playerPanel.currentPlayer].caishen = 8;
						Game.playerPanel.repaint();
						Monopoly.useCardDialog.setVisible(false);
						Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[14]--;
					}
					else
						JOptionPane.showMessageDialog(Game.playerPanel, "û�иÿ���", "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[Game.playerPanel.currentPlayer].image));					
				}	
			});
			add(fushen);
			fushen.setToolTipText("����(���ۿ�)��ʹ��ʱ�������һ�ſ�Ƭ���������ۿ������˻غ��ھ����������ز���֧����·��");
			fushen.setBounds(298, 179, 35, 52);
			fushen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Monopoly.music.button.play();
					if (Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[13] > 0) {
						Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[(int)(Math.random()*15)]++;
						Monopoly.players[Game.playerPanel.currentPlayer].fushen = 8;
						Game.playerPanel.repaint();
						Monopoly.useCardDialog.setVisible(false);
						Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[13]--;
					}
					else
						JOptionPane.showMessageDialog(Game.playerPanel, "û�иÿ���", "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[Game.playerPanel.currentPlayer].image));					
				}	
			});
			add(monster);
			monster.setToolTipText("���޿�(���ۿ�)���ýֵ�������������Ϊ��ʼ״̬����1��");
			monster.setBounds(298, 0, 35, 52);
			monster.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Monopoly.music.button.play();
					if (Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[10] > 0) {
						monster(Game.playerPanel.currentPlayer,Monopoly.players[Game.playerPanel.currentPlayer].positonOfPlayer);
						Game.playerPanel.repaint();
						Monopoly.useCardDialog.setVisible(false);
						Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[10]--;
					}
					else
						JOptionPane.showMessageDialog(Game.playerPanel, "û�иÿ���", "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[Game.playerPanel.currentPlayer].image));					
				}	
			});
			add(chashui);
			chashui.setToolTipText("��˰��(���ۿ�)��ǿ�н��岽�����30%�Ĵ���˰(����˰�������˰���ķ�����)");
			chashui.setBounds(298, 60, 35, 52);
			chashui.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Monopoly.music.button.play();
					if (Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[11] > 0) {
						boolean a = false;
						for (int i = 0; i < Monopoly.totalAmount; i++) {
							if (Math.abs(Monopoly.players[i].positonOfPlayer - Monopoly.players[Game.playerPanel.currentPlayer].positonOfPlayer) < 6&&!(Monopoly.players[Game.playerPanel.currentPlayer].name.equals(Monopoly.players[i].name))) {
								Monopoly.players[i].totalMoney -= Monopoly.players[i].deposit*3/10;
								Monopoly.players[i].deposit -= Monopoly.players[i].deposit*3/10;
								a = true;
							}
						}
						if (a) 
							Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[11]--;
						else 
							JOptionPane.showMessageDialog(Game.playerPanel, "�岽֮��û\n���������", "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[Game.playerPanel.currentPlayer].image));
						Game.playerPanel.repaint();
						Monopoly.useCardDialog.setVisible(false);
					}
					else
						JOptionPane.showMessageDialog(Game.playerPanel, "û�иÿ���", "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[Game.playerPanel.currentPlayer].image));					
				}	
			});
			add(tudi);
			tudi.setToolTipText("���ؿ�(���ۿ�)����ͣ���������˵��������ڵأ������ڽ��ɹ�·�Ѻ󽫸�������Ѿ�Ϊ���У���ͣ������������ֱ�Ӿ�Ϊ���У�������غϡ�");
			tudi.setBounds(298, 120, 35, 52);
			tudi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Monopoly.music.button.play();
					if (Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[12] > 0) {
						Monopoly.players[Game.playerPanel.currentPlayer].land = 5;
						Game.playerPanel.repaint();
						Monopoly.useCardDialog.setVisible(false);
						Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[12]--;
					}
					else
						JOptionPane.showMessageDialog(Game.playerPanel, "û�иÿ���", "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[Game.playerPanel.currentPlayer].image));					
				}	
			});
			add(caipiao);
			caipiao.setToolTipText("��Ʊ��(���ۿ�)��ʹ�ú��´����Ʊ����");
			caipiao.setBounds(198, 241, 35, 52);
			caipiao.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Monopoly.music.button.play();
					if (Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[9] > 0) {
						Monopoly.players[Game.playerPanel.currentPlayer].lottery = true;
						Game.playerPanel.repaint();
						Monopoly.useCardDialog.setVisible(false);
						Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[9]--;
					}
					else
						JOptionPane.showMessageDialog(Game.playerPanel, "û�иÿ���", "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[Game.playerPanel.currentPlayer].image));					
				}	
			});
			add(turtle);
			turtle.setToolTipText("�ڹ꿨(���ۿ�)��ѡ���Լ�������Լ��岽���ڵĶ��֣���ÿ��ֻ����һ�����������غ�");
			turtle.setBounds(198, 179, 35, 52);
			turtle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Monopoly.music.button.play();
					if (Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[8] > 0) {
						for (int i = 0; i < Monopoly.totalAmount; i++) {
							if (Math.abs(Monopoly.players[i].positonOfPlayer - Monopoly.players[Game.playerPanel.currentPlayer].positonOfPlayer) < 6) {
								MapPanel.turtle[Monopoly.players[i].positonOfPlayer].setIcon(new ImageIcon(Monopoly.players[i].smallImage));
								MapPanel.turtle[Monopoly.players[i].positonOfPlayer].setVisible(true);
							}
						}
						Game.playerPanel.repaint();
						Monopoly.useCardDialog.setVisible(false);
						Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[8]--;
					}
					else
						JOptionPane.showMessageDialog(Game.playerPanel, "û�иÿ���", "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[Game.playerPanel.currentPlayer].image));					
				
				}	
			});
			add(stop);
			stop.setToolTipText("������(���ۿ�)���ûغ�ͣ����ԭ�أ����ٴδ���ԭ���¼�");
			stop.setBounds(198, 0, 35, 52);
			stop.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Monopoly.music.button.play();
					if (Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[5] > 0) {
						for (int i = 0; i < Monopoly.totalAmount; i++) {
							if (Math.abs(Monopoly.players[i].positonOfPlayer - Monopoly.players[Game.playerPanel.currentPlayer].positonOfPlayer) < 6) {
								MapPanel.zhiliu[Monopoly.players[i].positonOfPlayer].setIcon(new ImageIcon(Monopoly.players[i].smallImage));
								MapPanel.zhiliu[Monopoly.players[i].positonOfPlayer].setVisible(true);
							}
						}
						Game.playerPanel.repaint();
						Monopoly.useCardDialog.setVisible(false);
						Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[5]--;
					}
					else
						JOptionPane.showMessageDialog(Game.playerPanel, "û�иÿ���", "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[Game.playerPanel.currentPlayer].image));					
				}
			});
			add(chaiqian);
			chaiqian.setToolTipText("��Ǩ��(���ۿ�)���ýֵ��������ط���ȫ������Ϊ��ʼ����״̬��ԭ�����õ����ݵ�ǰ�۸�*150%�Ĳ����ѡ�");
			chaiqian.setBounds(198, 60, 35, 52);
			chaiqian.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Monopoly.music.button.play();
					if (Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[6] > 0) {
						Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[6]--;
						chaiqian(Game.playerPanel.currentPlayer,Monopoly.players[Game.playerPanel.currentPlayer].positonOfPlayer);
						Game.playerPanel.repaint();
						Monopoly.useCardDialog.setVisible(false);
					}
					else
						JOptionPane.showMessageDialog(Game.playerPanel, "û�иÿ���", "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[Game.playerPanel.currentPlayer].image));					
				}	
			});
			add(goudi);
			goudi.setToolTipText("���ؿ�(���ۿ�)��ǿ�����ּ۹����Լ���ǰ����λ�õ����أ���Ȼ�����߲��ܹ����Լ��ķ���");
			goudi.setBounds(198, 120, 35, 52);
			goudi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Monopoly.music.button.play();
					if (Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[7] > 0) {
						if (MapPanel.ownerOfLand[Monopoly.players[Game.playerPanel.currentPlayer].positonOfPlayer] == (Game.playerPanel.currentPlayer+1)%Monopoly.totalAmount) {
							MapPanel.ownerOfLand[Monopoly.players[Game.playerPanel.currentPlayer].positonOfPlayer] = Game.playerPanel.currentPlayer;
							Game.land.buyLand(Game.playerPanel.currentPlayer, Monopoly.players[Game.playerPanel.currentPlayer].positonOfPlayer);
							Monopoly.players[(Game.playerPanel.currentPlayer+1)%Monopoly.totalAmount].numberOfLand--;
							Game.playerPanel.repaint();
							Monopoly.useCardDialog.setVisible(false);
							Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[7]--;
						}
						else if (MapPanel.ownerOfLand[Monopoly.players[Game.playerPanel.currentPlayer].positonOfPlayer] == (Game.playerPanel.currentPlayer+2)%Monopoly.totalAmount&&Monopoly.totalAmount==3) {
							Game.land.buyLand(Game.playerPanel.currentPlayer, Monopoly.players[Game.playerPanel.currentPlayer].positonOfPlayer);
							Monopoly.players[(Game.playerPanel.currentPlayer+2)%Monopoly.totalAmount].numberOfLand--;
							Game.playerPanel.repaint();
							Monopoly.useCardDialog.setVisible(false);
							Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[7]--;
						}
						else
							JOptionPane.showMessageDialog(Game.playerPanel, "�õز���ʹ\n�ù��ؿ�", "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[Game.playerPanel.currentPlayer].image));
					}
					else
						JOptionPane.showMessageDialog(Game.playerPanel, "û�иÿ���", "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[Game.playerPanel.currentPlayer].image));
				}	
			});
			add(average);
			average.setToolTipText("������(���ۿ�)���������˵��ֽ�ƽ������");
			average.setBounds(98, 0, 35, 52);
			average.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Monopoly.music.button.play();
					if (Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[0] > 0) {
						int totalCash = 0;
						for (int i = 0; i < Monopoly.totalAmount; i++) {
							totalCash += Monopoly.players[i].cash;
						}
						for (int i = 0; i < Monopoly.totalAmount; i++) {
							Monopoly.players[i].totalMoney -= (Monopoly.players[i].cash-totalCash/Monopoly.totalAmount);
						}
						Monopoly.players[0].cash = Monopoly.players[1%Monopoly.totalAmount].cash = Monopoly.players[2%Monopoly.totalAmount].cash = totalCash/Monopoly.totalAmount;
						Game.playerPanel.repaint();
						Monopoly.useCardDialog.setVisible(false);
						Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[0]--;
					}
					else
						JOptionPane.showMessageDialog(Game.playerPanel, "û�иÿ���", "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[Game.playerPanel.currentPlayer].image));
				}				
			});
			add(changeDirection);
			changeDirection.setToolTipText("ת��(���ۿ�)�����Լ�������Լ��岽���ڵĶ���ʹ�ã�ʹ���ת����");
			changeDirection.setBounds(98, 60, 35, 52);
			changeDirection.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Monopoly.music.button.play();
					if (Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[1] > 0) {
						for (int i = 0; i < Monopoly.totalAmount; i++) {
							if (Math.abs(Monopoly.players[i].positonOfPlayer - Monopoly.players[Game.playerPanel.currentPlayer].positonOfPlayer) < 6) {
								MapPanel.changeDirection[Monopoly.players[i].positonOfPlayer].setIcon(new ImageIcon(Monopoly.players[i].smallImage));
								MapPanel.changeDirection[Monopoly.players[i].positonOfPlayer].setVisible(true);
							}
						}
						Game.playerPanel.repaint();
						Monopoly.useCardDialog.setVisible(false);
						Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[1]--;
					}
					else
						JOptionPane.showMessageDialog(Game.playerPanel, "û�иÿ���", "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[Game.playerPanel.currentPlayer].image));					
				}
			});
			
			add(barricades);
			barricades.setToolTipText("·�Ͽ�(���ۿ�)�������ڵ�ͼ���ⰲ��һ��·��(�������λ�ó���)��������Ҿ���·��ʱ��ͣ��·������λ�ò���ǰ��");
			barricades.setBounds(98, 120, 35, 52);
			barricades.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Monopoly.music.button.play();
					if (Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[2] > 0) {
						for (int i = 0;i < 48; i++) {
							MapPanel.barrage[i].setVisible(true);
							Monopoly.useCardDialog.setVisible(false);
						}
						for (int i = 0; i < Monopoly.totalAmount; i++) {
							MapPanel.barrage[Monopoly.players[i].positonOfPlayer].setVisible(false);
						}
						Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[2]--;
					}
					else
						JOptionPane.showMessageDialog(Game.playerPanel, "û�иÿ���", "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[Game.playerPanel.currentPlayer].image));
				}
			});
			
			add(controlDice);
			controlDice.setToolTipText("ң�����ӿ�(���ۿ�)��ʹ��ʱ��������������ӵĽ�������ֻ����1-6");
			controlDice.setBounds(98, 179, 35, 52);
			controlDice.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Monopoly.music.button.play();
					if (Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[3] > 0) {
						Game.isControl = true;
						Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[3]--;
						Monopoly.useCardDialog.setVisible(false);
						Monopoly.diceDialog.setVisible(true);
					}
					else
						JOptionPane.showMessageDialog(Game.playerPanel, "û�иÿ���", "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[Game.playerPanel.currentPlayer].image));				
				}
			});
			
			add(rob);
			rob.setToolTipText("�ӶῨ(���ۿ�)���Ծ����Լ��岽���ڵĶ���ʹ�ã��Ӷ���һ�ſ�Ƭ");
			rob.setBounds(99, 241, 35, 52);
			rob.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Monopoly.music.button.play();
					if (Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[4] > 0) {
						for (int i = 0; i < Monopoly.totalAmount; i++) {
							if (Math.abs(Monopoly.players[i].positonOfPlayer - Monopoly.players[Game.playerPanel.currentPlayer].positonOfPlayer) < 6) {
								MapPanel.rob[Monopoly.players[i].positonOfPlayer].setIcon(new ImageIcon(Monopoly.players[i].smallImage));
								MapPanel.rob[Monopoly.players[i].positonOfPlayer].setVisible(true);
								Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[4]--;
							}
						}
						MapPanel.rob[Monopoly.players[Game.playerPanel.currentPlayer].positonOfPlayer].setVisible(false);
						Game.playerPanel.repaint();
						Monopoly.useCardDialog.setVisible(false);
						
					}
					else
						JOptionPane.showMessageDialog(Game.playerPanel, "û�иÿ���", "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[Game.playerPanel.currentPlayer].image));
					
				}
			});
			add(no);
			no.setBounds(420, 0, 30, 30);
			no.setBorderPainted(false);
			no.setRolloverIcon(new ImageIcon("icons/no0.png"));
			no.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Monopoly.music.button.play();
					Monopoly.useCardDialog.setVisible(false);
					Monopoly.monopolyFrame.setEnabled(true);
				}	
			});
		}
		
		protected void monster(int player, int position) {
			if (position< 3 || position == 46 || position == 47) {
				for (int i = 46;i < 51;i++ ) {
					if (MapPanel.ownerOfLand[i%48] == player) {
						MapPanel.levelOfLand[i%48] = 1;
						Game.playerPanel.buttons[position].setIcon(new ImageIcon("icons/owner" + player + (MapPanel.levelOfLand[position] - 1) + ".png"));
						MapPanel.buttons[position].setToolTipText(MapPanel.nameOfLand[position] + "  ӵ���ߣ�" + Monopoly.players[player].name + "  �ȼ���" + MapPanel.levelOfLand[position]);
					}
					if (MapPanel.ownerOfLand[i%48] == (player+1)%Monopoly.totalAmount) {
						MapPanel.levelOfLand[i%48] = 1;
						Game.playerPanel.buttons[position].setIcon(new ImageIcon("icons/owner" + ((player+1)%Monopoly.totalAmount) + (MapPanel.levelOfLand[position] - 1) + ".png"));
						MapPanel.buttons[position].setToolTipText(MapPanel.nameOfLand[position] + "  ӵ���ߣ�" + Monopoly.players[player].name + "  �ȼ���" + MapPanel.levelOfLand[position]);
					}
					if (MapPanel.ownerOfLand[i%48] == (player+2)%Monopoly.totalAmount) {
						MapPanel.levelOfLand[i%48] = 1;
						Game.playerPanel.buttons[position].setIcon(new ImageIcon("icons/owner" + ((player+2)%Monopoly.totalAmount) + (MapPanel.levelOfLand[position] - 1) + ".png"));
						MapPanel.buttons[position].setToolTipText(MapPanel.nameOfLand[position] + "  ӵ���ߣ�" + Monopoly.players[player].name + "  �ȼ���" + MapPanel.levelOfLand[position]);
					}				
				}
			}
			else if (position > 5 && position < 11) {
				for (int i = 6;i < 11;i++) {
					if (MapPanel.ownerOfLand[i%48] == player) {
						MapPanel.levelOfLand[i%48] = 1;
						Game.playerPanel.buttons[position].setIcon(new ImageIcon("icons/owner" + player + (MapPanel.levelOfLand[position] - 1) + ".png"));
						MapPanel.buttons[position].setToolTipText(MapPanel.nameOfLand[position] + "  ӵ���ߣ�" + Monopoly.players[player].name + "  �ȼ���" + MapPanel.levelOfLand[position]);
					}
					if (MapPanel.ownerOfLand[i%48] == (player+1)%Monopoly.totalAmount) {
						MapPanel.levelOfLand[i%48] = 1;
						Game.playerPanel.buttons[position].setIcon(new ImageIcon("icons/owner" + ((player+1)%Monopoly.totalAmount) + (MapPanel.levelOfLand[position] - 1) + ".png"));
						MapPanel.buttons[position].setToolTipText(MapPanel.nameOfLand[position] + "  ӵ���ߣ�" + Monopoly.players[player].name + "  �ȼ���" + MapPanel.levelOfLand[position]);
					}
					if (MapPanel.ownerOfLand[i%48] == (player+2)%Monopoly.totalAmount) {
						MapPanel.levelOfLand[i%48] = 1;
						Game.playerPanel.buttons[position].setIcon(new ImageIcon("icons/owner" + ((player+2)%Monopoly.totalAmount) + (MapPanel.levelOfLand[position] - 1) + ".png"));
						MapPanel.buttons[position].setToolTipText(MapPanel.nameOfLand[position] + "  ӵ���ߣ�" + Monopoly.players[player].name + "  �ȼ���" + MapPanel.levelOfLand[position]);
					}
				}
			}
			else if (position > 13 && position< 19) {
				for (int i = 14;i < 19;i++ ) {
					if (MapPanel.ownerOfLand[i%48] == player) {
						MapPanel.levelOfLand[i%48] = 1;
						Game.playerPanel.buttons[position].setIcon(new ImageIcon("icons/owner" + player + (MapPanel.levelOfLand[position] - 1) + ".png"));
						MapPanel.buttons[position].setToolTipText(MapPanel.nameOfLand[position] + "  ӵ���ߣ�" + Monopoly.players[player].name + "  �ȼ���" + MapPanel.levelOfLand[position]);
					}
					if (MapPanel.ownerOfLand[i%48] == (player+1)%Monopoly.totalAmount) {
						MapPanel.levelOfLand[i%48] = 1;
						Game.playerPanel.buttons[position].setIcon(new ImageIcon("icons/owner" + ((player+1)%Monopoly.totalAmount) + (MapPanel.levelOfLand[position] - 1) + ".png"));
						MapPanel.buttons[position].setToolTipText(MapPanel.nameOfLand[position] + "  ӵ���ߣ�" + Monopoly.players[player].name + "  �ȼ���" + MapPanel.levelOfLand[position]);
					}
					if (MapPanel.ownerOfLand[i%48] == (player+2)%Monopoly.totalAmount) {
						MapPanel.levelOfLand[i%48] = 1;
						Game.playerPanel.buttons[position].setIcon(new ImageIcon("icons/owner" + ((player+2)%Monopoly.totalAmount) + (MapPanel.levelOfLand[position] - 1) + ".png"));
						MapPanel.buttons[position].setToolTipText(MapPanel.nameOfLand[position] + "  ӵ���ߣ�" + Monopoly.players[player].name + "  �ȼ���" + MapPanel.levelOfLand[position]);
					}
				}
			}
			else if (position > 22 && position < 27) {
				for (int i = 23;i < 28;i++ ) {
					if (MapPanel.ownerOfLand[i%48] == player) {
						MapPanel.levelOfLand[i%48] = 1;
						Game.playerPanel.buttons[position].setIcon(new ImageIcon("icons/owner" + player + (MapPanel.levelOfLand[position] - 1) + ".png"));
						MapPanel.buttons[position].setToolTipText(MapPanel.nameOfLand[position] + "  ӵ���ߣ�" + Monopoly.players[player].name + "  �ȼ���" + MapPanel.levelOfLand[position]);
					}
					if (MapPanel.ownerOfLand[i%48] == (player+1)%Monopoly.totalAmount) {
						MapPanel.levelOfLand[i%48] = 1;
						Game.playerPanel.buttons[position].setIcon(new ImageIcon("icons/owner" + ((player+1)%Monopoly.totalAmount) + (MapPanel.levelOfLand[position] - 1) + ".png"));
						MapPanel.buttons[position].setToolTipText(MapPanel.nameOfLand[position] + "  ӵ���ߣ�" + Monopoly.players[player].name + "  �ȼ���" + MapPanel.levelOfLand[position]);
					}
					if (MapPanel.ownerOfLand[i%48] == (player+2)%Monopoly.totalAmount) {
						MapPanel.levelOfLand[i%48] = 1;
						Game.playerPanel.buttons[position].setIcon(new ImageIcon("icons/owner" + ((player+2)%Monopoly.totalAmount) + (MapPanel.levelOfLand[position] - 1) + ".png"));
						MapPanel.buttons[position].setToolTipText(MapPanel.nameOfLand[position] + "  ӵ���ߣ�" + Monopoly.players[player].name + "  �ȼ���" + MapPanel.levelOfLand[position]);
					}
				}
			}
			else if (position > 29 && position < 35) {
				for (int i = 30;i < 35;i++ ){
					if (MapPanel.ownerOfLand[i%48] == player) {
						MapPanel.levelOfLand[i%48] = 1;
						Game.playerPanel.buttons[position].setIcon(new ImageIcon("icons/owner" + player + (MapPanel.levelOfLand[position] - 1) + ".png"));
						MapPanel.buttons[position].setToolTipText(MapPanel.nameOfLand[position] + "  ӵ���ߣ�" + Monopoly.players[player].name + "  �ȼ���" + MapPanel.levelOfLand[position]);
					}
					if (MapPanel.ownerOfLand[i%48] == (player+1)%Monopoly.totalAmount) {
						MapPanel.levelOfLand[i%48] = 1;
						Game.playerPanel.buttons[position].setIcon(new ImageIcon("icons/owner" + ((player+1)%Monopoly.totalAmount) + (MapPanel.levelOfLand[position] - 1) + ".png"));
						MapPanel.buttons[position].setToolTipText(MapPanel.nameOfLand[position] + "  ӵ���ߣ�" + Monopoly.players[player].name + "  �ȼ���" + MapPanel.levelOfLand[position]);
					}
					if (MapPanel.ownerOfLand[i%48] == (player+2)%Monopoly.totalAmount) {
						MapPanel.levelOfLand[i%48] = 1;
						Game.playerPanel.buttons[position].setIcon(new ImageIcon("icons/owner" + ((player+2)%Monopoly.totalAmount) + (MapPanel.levelOfLand[position] - 1) + ".png"));
						MapPanel.buttons[position].setToolTipText(MapPanel.nameOfLand[position] + "  ӵ���ߣ�" + Monopoly.players[player].name + "  �ȼ���" + MapPanel.levelOfLand[position]);
					}
				}
			}
			else if (position > 36 && position < 42)	{
				for (int i = 37;i < 42;i++ ) {
					if (MapPanel.ownerOfLand[i%48] == player) {
						MapPanel.levelOfLand[i%48] = 1;
						Game.playerPanel.buttons[position].setIcon(new ImageIcon("icons/owner" + player + (MapPanel.levelOfLand[position] - 1) + ".png"));
						MapPanel.buttons[position].setToolTipText(MapPanel.nameOfLand[position] + "  ӵ���ߣ�" + Monopoly.players[player].name + "  �ȼ���" + MapPanel.levelOfLand[position]);
					}
					if (MapPanel.ownerOfLand[i%48] == (player+1)%Monopoly.totalAmount) {
						MapPanel.levelOfLand[i%48] = 1;
						Game.playerPanel.buttons[position].setIcon(new ImageIcon("icons/owner" + ((player+1)%Monopoly.totalAmount) + (MapPanel.levelOfLand[position] - 1) + ".png"));
						MapPanel.buttons[position].setToolTipText(MapPanel.nameOfLand[position] + "  ӵ���ߣ�" + Monopoly.players[player].name + "  �ȼ���" + MapPanel.levelOfLand[position]);
					}
					if (MapPanel.ownerOfLand[i%48] == (player+2)%Monopoly.totalAmount) {
						MapPanel.levelOfLand[i%48] = 1;
						Game.playerPanel.buttons[position].setIcon(new ImageIcon("icons/owner" + ((player+2)%Monopoly.totalAmount) + (MapPanel.levelOfLand[position] - 1) + ".png"));
						MapPanel.buttons[position].setToolTipText(MapPanel.nameOfLand[position] + "  ӵ���ߣ�" + Monopoly.players[player].name + "  �ȼ���" + MapPanel.levelOfLand[position]);
					}
				}
			}
			else {
				Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[10]++;
				JOptionPane.showMessageDialog(Game.playerPanel, "�õز���ʹ�ù��޿�", "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[Game.playerPanel.currentPlayer].image));					
				
			}
			
		}
		//��Ǩ������
		protected void chaiqian(int player, int position) {
			if (position< 3 || position == 46 || position == 47) {
				for (int i = 46;i < 51;i++ ) {
					if (MapPanel.ownerOfLand[i%48] == player) {
						Monopoly.players[player].cash += MapPanel.levelOfLand[i%48]*1000*3/2;
						Monopoly.players[player].totalMoney += MapPanel.levelOfLand[i%48]*1000*3/2;
						Monopoly.players[player].numberOfLand--;
						MapPanel.ownerOfLand[i%48] = 3;
						MapPanel.buttons[i%48].setIcon(new ImageIcon("icons/land.png"));
					}
					if (MapPanel.ownerOfLand[i%48] == (player+1)%Monopoly.totalAmount) {
						Monopoly.players[(player+1)%Monopoly.totalAmount].cash += MapPanel.levelOfLand[i%48]*1000*3/2;
						Monopoly.players[(player+1)%Monopoly.totalAmount].totalMoney += MapPanel.levelOfLand[i%48]*1000*3/2;
						Monopoly.players[(player+1)%Monopoly.totalAmount].numberOfLand--;
						MapPanel.ownerOfLand[i%48] = 3;
						MapPanel.buttons[i%48].setIcon(new ImageIcon("icons/land.png"));
					}
					if (MapPanel.ownerOfLand[i%48] == (player+2)%Monopoly.totalAmount&&Monopoly.totalAmount==3) {
						Monopoly.players[(player+2)%Monopoly.totalAmount].cash += MapPanel.levelOfLand[i%48]*1000*3/2;
						Monopoly.players[(player+2)%Monopoly.totalAmount].totalMoney += MapPanel.levelOfLand[i%48]*1000*3/2;
						Monopoly.players[(player+2)%Monopoly.totalAmount].numberOfLand--;
						MapPanel.ownerOfLand[i%48] = 3;
						MapPanel.buttons[i%48].setIcon(new ImageIcon("icons/land.png"));
					}
					MapPanel.buttons[i%48].setToolTipText(MapPanel.nameOfLand[i%48]);
				}
			}
			else if (position > 5 && position < 11) {
				for (int i = 6;i < 11;i++) {
					if (MapPanel.ownerOfLand[i%48] == player) {
						Monopoly.players[player].cash += MapPanel.levelOfLand[i%48]*1000*3/2;
						Monopoly.players[player].totalMoney += MapPanel.levelOfLand[i%48]*1000*3/2;
						MapPanel.ownerOfLand[i%48] = 3;
						MapPanel.buttons[i%48].setIcon(new ImageIcon("icons/land.png"));
					}
					if (MapPanel.ownerOfLand[i%48] == (player+1)%Monopoly.totalAmount) {
						Monopoly.players[(player+1)%Monopoly.totalAmount].cash += MapPanel.levelOfLand[i%48]*1000*3/2;
						Monopoly.players[(player+1)%Monopoly.totalAmount].totalMoney += MapPanel.levelOfLand[i%48]*1000*3/2;
						MapPanel.ownerOfLand[i%48] = 3;
						MapPanel.buttons[i%48].setIcon(new ImageIcon("icons/land.png"));
					}
					if (MapPanel.ownerOfLand[i%48] == (player+2)%Monopoly.totalAmount) {
						Monopoly.players[(player+2)%Monopoly.totalAmount].cash += MapPanel.levelOfLand[i%48]*1000*3/2;
						Monopoly.players[(player+2)%Monopoly.totalAmount].totalMoney += MapPanel.levelOfLand[i%48]*1000*3/2;
						MapPanel.ownerOfLand[i%48] = 3;
						MapPanel.buttons[i%48].setIcon(new ImageIcon("icons/land.png"));
					}
					MapPanel.buttons[i%48].setToolTipText(MapPanel.nameOfLand[i%48]);
				}
			}
			else if (position > 13 && position< 19) {
				for (int i = 14;i < 19;i++ ) {
					if (MapPanel.ownerOfLand[i%48] == player) {
						Monopoly.players[player].cash += MapPanel.levelOfLand[i%48]*1000*3/2;
						Monopoly.players[player].totalMoney += MapPanel.levelOfLand[i%48]*1000*3/2;
						MapPanel.ownerOfLand[i%48] = 3;
						MapPanel.buttons[i%48].setIcon(new ImageIcon("icons/land.png"));
					}
					if (MapPanel.ownerOfLand[i%48] == (player+1)%Monopoly.totalAmount) {
						Monopoly.players[(player+1)%Monopoly.totalAmount].cash += MapPanel.levelOfLand[i%48]*1000*3/2;
						Monopoly.players[(player+1)%Monopoly.totalAmount].totalMoney += MapPanel.levelOfLand[i%48]*1000*3/2;
						MapPanel.ownerOfLand[i%48] = 3;
						MapPanel.buttons[i%48].setIcon(new ImageIcon("icons/land.png"));
					}
					if (MapPanel.ownerOfLand[i%48] == (player+2)%Monopoly.totalAmount) {
						Monopoly.players[(player+2)%Monopoly.totalAmount].cash += MapPanel.levelOfLand[i%48]*1000*3/2;
						Monopoly.players[(player+2)%Monopoly.totalAmount].totalMoney += MapPanel.levelOfLand[i%48]*1000*3/2;
						MapPanel.ownerOfLand[i%48] = 3;
						MapPanel.buttons[i%48].setIcon(new ImageIcon("icons/land.png"));
					}
					MapPanel.buttons[i%48].setToolTipText(MapPanel.nameOfLand[i%48]);
				}
			}
			else if (position > 22 && position < 27) {
				for (int i = 23;i < 28;i++ ) {
					if (MapPanel.ownerOfLand[i%48] == player) {
						Monopoly.players[player].cash += MapPanel.levelOfLand[i%48]*1000*3/2;
						Monopoly.players[player].totalMoney += MapPanel.levelOfLand[i%48]*1000*3/2;
						MapPanel.ownerOfLand[i%48] = 3;
						MapPanel.buttons[i%48].setIcon(new ImageIcon("icons/land.png"));
					}
					if (MapPanel.ownerOfLand[i%48] == (player+1)%Monopoly.totalAmount) {
						Monopoly.players[(player+1)%Monopoly.totalAmount].cash += MapPanel.levelOfLand[i%48]*1000*3/2;
						Monopoly.players[(player+1)%Monopoly.totalAmount].totalMoney += MapPanel.levelOfLand[i%48]*1000*3/2;
						MapPanel.ownerOfLand[i%48] = 3;
						MapPanel.buttons[i%48].setIcon(new ImageIcon("icons/land.png"));
					}
					if (MapPanel.ownerOfLand[i%48] == (player+2)%Monopoly.totalAmount) {
						Monopoly.players[(player+2)%Monopoly.totalAmount].cash += MapPanel.levelOfLand[i%48]*1000*3/2;
						Monopoly.players[(player+2)%Monopoly.totalAmount].totalMoney += MapPanel.levelOfLand[i%48]*1000*3/2;
						MapPanel.ownerOfLand[i%48] = 3;
						MapPanel.buttons[i%48].setIcon(new ImageIcon("icons/land.png"));
					}
					MapPanel.buttons[i%48].setToolTipText(MapPanel.nameOfLand[i%48]);
				}
			}
			else if (position > 29 && position < 35) {
				for (int i = 30;i < 35;i++ ){
					if (MapPanel.ownerOfLand[i%48] == player) {
						Monopoly.players[player].cash += MapPanel.levelOfLand[i%48]*1000*3/2;
						Monopoly.players[player].totalMoney += MapPanel.levelOfLand[i%48]*1000*3/2;
						MapPanel.ownerOfLand[i%48] = 3;
						MapPanel.buttons[i%48].setIcon(new ImageIcon("icons/land.png"));
					}
					if (MapPanel.ownerOfLand[i%48] == (player+1)%Monopoly.totalAmount) {
						Monopoly.players[(player+1)%Monopoly.totalAmount].cash += MapPanel.levelOfLand[i%48]*1000*3/2;
						Monopoly.players[(player+1)%Monopoly.totalAmount].totalMoney += MapPanel.levelOfLand[i%48]*1000*3/2;
						MapPanel.ownerOfLand[i%48] = 3;
						MapPanel.buttons[i%48].setIcon(new ImageIcon("icons/land.png"));
					}
					if (MapPanel.ownerOfLand[i%48] == (player+2)%Monopoly.totalAmount) {
						Monopoly.players[(player+2)%Monopoly.totalAmount].cash += MapPanel.levelOfLand[i%48]*1000*3/2;
						Monopoly.players[(player+2)%Monopoly.totalAmount].totalMoney += MapPanel.levelOfLand[i%48]*1000*3/2;
						MapPanel.ownerOfLand[i%48] = 3;
						MapPanel.buttons[i%48].setIcon(new ImageIcon("icons/land.png"));
					}
					MapPanel.buttons[i%48].setToolTipText(MapPanel.nameOfLand[i%48]);
				}
			}
			else if (position > 36 && position < 42)	{
				for (int i = 37;i < 42;i++ ) {
					if (MapPanel.ownerOfLand[i%48] == player) {
						Monopoly.players[player].cash += MapPanel.levelOfLand[i%48]*1000*3/2;
						Monopoly.players[player].totalMoney += MapPanel.levelOfLand[i%48]*1000*3/2;
						MapPanel.ownerOfLand[i%48] = 3;
						MapPanel.buttons[i%48].setIcon(new ImageIcon("icons/land.png"));
					}
					if (MapPanel.ownerOfLand[i%48] == (player+1)%Monopoly.totalAmount) {
						Monopoly.players[(player+1)%Monopoly.totalAmount].cash += MapPanel.levelOfLand[i%48]*1000*3/2;
						Monopoly.players[(player+1)%Monopoly.totalAmount].totalMoney += MapPanel.levelOfLand[i%48]*1000*3/2;
						MapPanel.ownerOfLand[i%48] = 3;
						MapPanel.buttons[i%48].setIcon(new ImageIcon("icons/land.png"));
					}
					if (MapPanel.ownerOfLand[i%48] == (player+2)%Monopoly.totalAmount) {
						Monopoly.players[(player+2)%Monopoly.totalAmount].cash += MapPanel.levelOfLand[i%48]*1000*3/2;
						Monopoly.players[(player+2)%Monopoly.totalAmount].totalMoney += MapPanel.levelOfLand[i%48]*1000*3/2;
						MapPanel.ownerOfLand[i%48] = 3;
						MapPanel.buttons[i%48].setIcon(new ImageIcon("icons/land.png"));
					}
					MapPanel.buttons[i%48].setToolTipText(MapPanel.nameOfLand[i%48]);
				}
			}
			else {
				Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[6]++;
				JOptionPane.showMessageDialog(Game.playerPanel, "�õز���ʹ�ò�Ǩ��", "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[Game.playerPanel.currentPlayer].image));						
			}
		}

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.CYAN);
			g.setFont(font);
			for (int i =0; i < 3; i++) {
				int a = (i+1)*100 + 45;
				int b = i*5;
				g.drawString("X" + " " + Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[b], a, 40);
				g.drawString("X" + " " + Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[b+1], a, 98);
				g.drawString("X" + " " + Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[b+2], a, 156);
				g.drawString("X" + " " + Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[b+3], a, 217);
				g.drawString("X" + " " + Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[b+4], a, 273);
			}
		}	
		
	}