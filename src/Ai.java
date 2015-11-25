import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;


public class Ai {
	int timercount = 0;
	Timer delay = new Timer(500,new timerListener());
	//���ڵ�����ʱ
	class timerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			timercount++;
			if (timercount > 2) {
				timercount = 0;
				delay.stop();
				Monopoly.messageDialog.setVisible(false);
				delay1.start();
			}
		}	
	}
	int timercount1 = 0;
	Timer delay1 = new Timer(100,new timerListener1());
	//����ai��ʱ
	class timerListener1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			timercount1++;
			if (timercount1 < 2) {
				int a = (int)(Math.random()*5);
				//�ж��Ƿ�ʹ��·�Ͽ�
				for (int i = 0; i < 48; i++) {
					if (a == 0&&MapPanel.ownerOfLand[i] == Game.playerPanel.currentPlayer&&Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[2] > 0&&!MapPanel.barricades[i]&&Monopoly.players[Game.playerPanel.currentPlayer].positonOfPlayer != i) {
						MapPanel.barricades[i] = true;
						Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[2]--;
						Game.playerPanel.repaint();
						break;
					}
				}
				//�ж��Ƿ�ʹ�ò�˰��
				if (Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[11] > 0) {
					for (int i = 0; i < Monopoly.totalAmount; i++) {
						if (a == 1&&Math.abs(Monopoly.players[i].positonOfPlayer - Monopoly.players[Game.playerPanel.currentPlayer].positonOfPlayer) < 6&&!(Monopoly.players[Game.playerPanel.currentPlayer].name.equals(Monopoly.players[i].name))) {
							Monopoly.players[i].totalMoney -= Monopoly.players[i].deposit*3/10;
							Monopoly.players[i].deposit -= Monopoly.players[i].deposit*3/10;
							Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[11]--;
							Game.messagePanel.str[0] = "���" + Monopoly.players[Game.playerPanel.currentPlayer].name;
							Game.messagePanel.str[1] = "ʹ���˲�˰��";
							Game.messagePanel.repaint();
							Monopoly.messageDialog.setSize(300, 150);
							Monopoly.messageDialog.add(Game.messagePanel);
							Monopoly.messageDialog.setVisible(true);
							delay.start();
							delay1.stop();
							break;
						}
					}
				}
				//�ж��Ƿ�ʹ���ӶῨ
				if (Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[4] > 0) {
					for (int i = 0; i < Monopoly.totalAmount; i++) {
						if (a == 2&&Math.abs(Monopoly.players[i].positonOfPlayer - Monopoly.players[Game.playerPanel.currentPlayer].positonOfPlayer) < 6&&!(Monopoly.players[Game.playerPanel.currentPlayer].name.equals(Monopoly.players[i].name))) {
							int j = (int)(Math.random()*15);
							Monopoly.players[1].numberOfcard[j]--;
							Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[j]++;	
							Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[4]--;
							Game.messagePanel.str[0] = "���" + Monopoly.players[Game.playerPanel.currentPlayer].name;
							Game.messagePanel.str[1] = "ʹ�����ӶῨ";
							Game.messagePanel.repaint();
							Monopoly.messageDialog.setSize(300, 150);
							Monopoly.messageDialog.add(Game.messagePanel);
							Monopoly.messageDialog.setVisible(true);
							delay.start();
							delay1.stop();
							break;
						}
					}
				}
				//�ж��Ƿ�ʹ�þ�����
				if (Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[0] > 0) {
					boolean is = true;
					for (int i = 0; i < Monopoly.totalAmount; i++) {
						if (Monopoly.players[i].cash <= Monopoly.players[Game.playerPanel.currentPlayer].cash&&i!=Game.playerPanel.currentPlayer) {
							is = false;
						}
					}
					if (is) {
						int totalCash = 0;
						for (int i = 0; i < Monopoly.totalAmount; i++) {
							totalCash += Monopoly.players[i].cash;
						}
						for (int i = 0; i < Monopoly.totalAmount; i++) {
							Monopoly.players[i].totalMoney -= (Monopoly.players[i].cash-totalCash/Monopoly.totalAmount);
						}
						Monopoly.players[0].cash = Monopoly.players[1%Monopoly.totalAmount].cash = Monopoly.players[2%Monopoly.totalAmount].cash = totalCash/Monopoly.totalAmount;
						Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[11]--;
						Game.messagePanel.str[0] = "���" + Monopoly.players[Game.playerPanel.currentPlayer].name;
						Game.messagePanel.str[1] = "ʹ���˾�����";
						Game.messagePanel.repaint();
						Monopoly.messageDialog.setSize(300, 150);
						Monopoly.messageDialog.add(Game.messagePanel);
						Monopoly.messageDialog.setVisible(true);
						delay.start();
						delay1.stop();
					}
				}
			}
			else if (timercount1 < 3) {
				//ʹ��ң����������
				for (int i = Monopoly.players[Game.playerPanel.currentPlayer].positonOfPlayer + 1;i < Monopoly.players[Game.playerPanel.currentPlayer].positonOfPlayer + 7; i++) {
					if (MapPanel.ownerOfLand[i%48] == Game.playerPanel.currentPlayer&&Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[3] > 0) {
						Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[3]--;
						Game.isControl = true;
						Game.playerPanel.controlDice = i - Monopoly.players[Game.playerPanel.currentPlayer].positonOfPlayer;
						Game.messagePanel.str[0] = "���" + Monopoly.players[Game.playerPanel.currentPlayer].name;
						Game.messagePanel.str[1] = "ʹ����ң�����ӿ�";
						Game.messagePanel.repaint();
						Monopoly.messageDialog.setSize(300, 150);
						Monopoly.messageDialog.add(Game.messagePanel);
						Monopoly.messageDialog.setVisible(true);
						delay.start();
						delay1.stop();
						break;
					}
				}
			}
			else if (timercount1 < 4) {
				int a = (int)(Math.random()*3);
				//�Ƿ�ʹ�ø���
				if (a == 0&&Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[13] > 0&&Monopoly.players[Game.playerPanel.currentPlayer].fushen <= 0) {
					Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[13]--;
					Monopoly.players[Game.playerPanel.currentPlayer].fushen = 8;
					Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[(int)(Math.random()*15)]++;
					Game.messagePanel.str[0] = "���" + Monopoly.players[Game.playerPanel.currentPlayer].name;
					Game.messagePanel.str[1] = "ʹ���˸���";
					Game.messagePanel.repaint();
					Monopoly.messageDialog.setSize(300, 150);
					Monopoly.messageDialog.add(Game.messagePanel);
					Monopoly.messageDialog.setVisible(true);
					delay.start();
					delay1.stop();
				}
			}
			else if (timercount1 < 5) {
				int a = (int)(Math.random()*2);
				//�Ƿ�ʹ�ò���
				if (a == 0&&Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[14] > 0&&Monopoly.players[Game.playerPanel.currentPlayer].caishen <= 0&&Monopoly.players[Game.playerPanel.currentPlayer].fushen <= 0) {
					Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[14]--;
					Monopoly.players[Game.playerPanel.currentPlayer].caishen = 8;
					Monopoly.players[Game.playerPanel.currentPlayer].cash += 10000;
					Monopoly.players[Game.playerPanel.currentPlayer].totalMoney += 10000;
					Game.messagePanel.str[0] = "���" + Monopoly.players[Game.playerPanel.currentPlayer].name;
					Game.messagePanel.str[1] = "ʹ���˲���";
					Game.messagePanel.repaint();
					Monopoly.messageDialog.setSize(300, 150);
					Monopoly.messageDialog.add(Game.messagePanel);
					Monopoly.messageDialog.setVisible(true);
					delay.start();
					delay1.stop();
				}
			}
			else if (timercount1 > 5) {
				timercount1 = 0;
				Monopoly.music.dice.play();
				Game.playerPanel.timer.start();
				Game.playerPanel.dice.setVisible(true);
				delay1.stop();
			}
			else {
			}
		}	
	}
	public void option(int player,int position) {
		delay.start();
	}
}
