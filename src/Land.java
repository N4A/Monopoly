import java.awt.event.*;

import javax.swing.*;

public class Land {
	/**���ش����¼�����
	0 ���0
	1 ���1
	2 ���2
	3 δ��������
	4 �յ�
	5 ����
	6 ��Ƭ
	7 ��ȯ
	8 ��Ʊ
	9 ����
	10 ����*/
	int timercount = 0;
	Timer delay = new Timer(500,new timerListener());
	class timerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			timercount++;
			if (timercount > 2) {
				timercount = 0;
				delay.stop();
				Monopoly.messageDialog.setVisible(false);
			}
		}	
	}
	public void event(int player,int position) {
		if (MapPanel.ownerOfLand[position] == player) {	
			if (Monopoly.players[player].isAI) {
				Monopoly.land.upLand(player,position);
			}
			else {
				Game.eventFrontPanel.monopolyIcon = new ImageIcon("icons/upLand.png"); 
				Game.eventFrontPanel.image = Game.eventFrontPanel.monopolyIcon.getImage();
				Game.eventFrontPanel.playerIcon = new ImageIcon(Monopoly.players[player].image);
				Game.eventFrontPanel.player = Game.eventFrontPanel.playerIcon.getImage();
				Game.eventFrontPanel.house.setIcon(new ImageIcon("icons/owner" + player + MapPanel.levelOfLand[position] + ".png"));
				Game.eventFrontPanel.repaint();
				Monopoly.eventDialog.add(Game.eventFrontPanel);
				Monopoly.eventDialog.setVisible(true);	
			}
		}
		else if (MapPanel.ownerOfLand[position] == 3) {
			if (Monopoly.players[player].land > 0) {
				Monopoly.players[player].cash += 1000;
				Monopoly.players[player].totalMoney += 1000;
			}//ʹ�������ؿ�����ѹ���
			
			if (Monopoly.players[player].isAI) {
				Monopoly.land.buyLand(player,position);
			}
			else {
				Game.eventFrontPanel.monopolyIcon = new ImageIcon("icons/buyLand.png"); 
				Game.eventFrontPanel.image = Game.eventFrontPanel.monopolyIcon.getImage();
				Game.eventFrontPanel.playerIcon = new ImageIcon(Monopoly.players[player].image);
				Game.eventFrontPanel.player = Game.eventFrontPanel.playerIcon.getImage();
				Game.eventFrontPanel.house.setIcon(new ImageIcon("icons/owner" + player + (MapPanel.levelOfLand[position]-1) + ".png"));
				Game.eventFrontPanel.repaint();
				Monopoly.eventDialog.add(Game.eventFrontPanel);
				Monopoly.eventDialog.setVisible(true);
			}
		}
		else if (MapPanel.ownerOfLand[position] == 4) {
			Game.playerPanel.repaint();
		}
		else if (MapPanel.ownerOfLand[position] == 5) {
				news(player,position);
		}
		else if (MapPanel.ownerOfLand[position] == 6) {
				giveCard(player);
		}
		else if (MapPanel.ownerOfLand[position] == 7) {
				givePoint(player);
		}
		else if (MapPanel.ownerOfLand[position] == 8) {
			if (Monopoly.players[player].isAI) {
				int rm = (int)(Math.random()*10);
				if (Monopoly.players[player].lottery) {
					rm = 5;
					Monopoly.players[player].lottery = false;
				}
				if (rm == 5){
					Game.messagePanel.str[0]= "��ϲ�в�Ʊ2000";
					Game.messagePanel.str[1]= "";
					Game.messagePanel.repaint();
					Monopoly.messageDialog.add(Game.messagePanel);
					Monopoly.messageDialog.setVisible(true);
					delay.start();
				}
				else {
					Game.messagePanel.str[0]= "���ź�����������";
					Game.messagePanel.str[1]= "";
					Game.messagePanel.repaint();
					Monopoly.messageDialog.add(Game.messagePanel);
					Monopoly.messageDialog.setVisible(true);
					delay.start();
				}
					
			}
			else 
				lottery(player);
		}
		else if (MapPanel.ownerOfLand[position] == 9) {	
			if (Monopoly.players[player].isAI) {
				if (Monopoly.players[player].point > 0){
					int a = (int)(Math.random()*5);
					Monopoly.players[player].numberOfcard[a]++;
					Monopoly.players[player].point -= 5;
					Game.messagePanel.str[0]= "����" + Monopoly.players[player].nameOfCard[a];
					Game.messagePanel.str[1]= "";
					Game.messagePanel.repaint();
					Monopoly.messageDialog.add(Game.messagePanel);
					Monopoly.messageDialog.setVisible(true);
					delay.start();
				}
			}
			else {
				Monopoly.propStoreDialog.add(Game.propStoreFrontPanel);
				Monopoly.propStoreDialog.setVisible(true);
			}
		}
		else if(MapPanel.ownerOfLand[position] == 10) {
			//���к����������������ɱ𴦵���
			Game.playerPanel.repaint();
		}
		else {//�ߵ�������������
			if (Monopoly.players[player].caishen > 0) {
				//����Ǯ
				if (Monopoly.players[player].isAI) {
					Game.messagePanel.str[0] = "������";
					Game.messagePanel.str[1] = "�⽻��·��";
					Game.messagePanel.repaint();
					Monopoly.messageDialog.setSize(300, 150);
					Monopoly.messageDialog.add(Game.messagePanel);
					Monopoly.messageDialog.setVisible(true);
					delay.start();
				}
				else
					JOptionPane.showMessageDialog(Game.playerPanel, "������\n�⽻��·��", "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[player].image));
			}
			else if (Monopoly.players[player].fushen > 0) {
				//����Ǯ
				if (Monopoly.players[player].isAI) {
					Game.messagePanel.str[0] = "������";
					Game.messagePanel.str[1] = "�⽻��·��";
					Game.messagePanel.repaint();
					Monopoly.messageDialog.setSize(300, 150);
					Monopoly.messageDialog.add(Game.messagePanel);
					Monopoly.messageDialog.setVisible(true);
					delay.start();
				}
				else
					JOptionPane.showMessageDialog(Game.playerPanel, "������\n�⽻��·��", "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[player].image));
			}
			else	//�ߵ����˵������ϣ�Ӧ����Ǯ
				payMoney(player,position);
		}
	}
	

	//�������غ���
	public void buyLand(int player, int position) {
		if (Monopoly.players[player].cash >= 1000) {
			Monopoly.players[player].cash -= 1000;
			Monopoly.players[player].houseProperty += 1000;
			Monopoly.players[player].numberOfLand++;
			MapPanel.ownerOfLand[position] = player;//�����ظ�����ǰ���
			Game.playerPanel.buttons[position].setIcon(new ImageIcon("icons/owner" + player + (MapPanel.levelOfLand[position] - 1) + ".png"));
			MapPanel.buttons[position].setToolTipText(MapPanel.nameOfLand[position] + "  ӵ���ߣ�" + Monopoly.players[player].name + "  �ȼ���" + MapPanel.levelOfLand[position]);
			Monopoly.eventDialog.setVisible(false);
			Monopoly.monopolyFrame.setFocusable(true);
		}
		else {
			Monopoly.eventDialog.setVisible(false);
			Monopoly.monopolyFrame.setFocusable(true);
			JOptionPane.showMessageDialog(Game.playerPanel, "��ô��\nǮ������", "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[player].image));
		}
		Game.playerPanel.repaint();
	}
	
	
	//������������
	public void upLand(int player, int position) {
		if (Monopoly.players[player].cash >= 500) {
			Monopoly.players[player].cash -= 500;
			Monopoly.players[player].totalMoney += 500;
			Monopoly.players[player].houseProperty += 1000;
			if (MapPanel.levelOfLand[position] < 6) {
				MapPanel.levelOfLand[position]++;
				Game.playerPanel.buttons[position].setIcon(new ImageIcon("icons/owner" + player + (MapPanel.levelOfLand[position] - 1) + ".png"));
				MapPanel.buttons[position].setToolTipText(MapPanel.nameOfLand[position] + "  ӵ���ߣ�" + Monopoly.players[player].name + "  �ȼ���" + MapPanel.levelOfLand[position]);		
			}
			else
				JOptionPane.showMessageDialog(Game.playerPanel, "�������\n�Ѿ��ﵽ\n������", "Congadurations",JOptionPane.INFORMATION_MESSAGE, new ImageIcon(Monopoly.players[player].image));
			Monopoly.eventDialog.setVisible(false);
		}
		else {
			Monopoly.eventDialog.setVisible(false);
			JOptionPane.showMessageDialog(Game.playerPanel, "��ô��\nǮ������", "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[player].image));
		}
		Game.playerPanel.repaint();
	}                
	
	//����·�Ѻ���
	public void payMoney(int player,int position) {
		if (Monopoly.totalAmount == 2) {
			int money = Game.playerPanel.levelOfLand[position]*200;//�����շ�
			if (position< 3 || position == 46 || position == 47) {
				for (int i = 46;i < 51;i++ ) {
					if (MapPanel.ownerOfLand[i%48] == (player+1)%2) {
						money += MapPanel.levelOfLand[i%48]*100;
					}
				}
			}
			if (position > 5 && position < 11) {
				for (int i = 6;i < 11;i++) {
					if (MapPanel.ownerOfLand[i] == (player+1)%2) {
						money += MapPanel.levelOfLand[i]*100;
					}
				}
			}
			if (position > 13 && position< 19) {
				for (int i = 14;i < 19;i++ ) {
					if (MapPanel.ownerOfLand[i] == (player+1)%2)	{
							money += MapPanel.levelOfLand[i]*100;
					}
				}
			}
			if (position > 22 && position < 27) {
				for (int i = 23;i < 28;i++ ) {
					if (MapPanel.ownerOfLand[i] == (player+1)%2) {
						money += MapPanel.levelOfLand[i]*100;
					}
				}
			}
			if (position > 29 && position < 35) {
				for (int i = 30;i < 35;i++ ){
					if (MapPanel.ownerOfLand[i] == (player+1)%2){
						money += MapPanel.levelOfLand[i]*100;
					}
				}
			}
			if (position > 36 && position < 42)	{
				for (int i = 37;i < 42;i++ ) {
					if (MapPanel.ownerOfLand[i] == (player+1)%2) {
						money += MapPanel.levelOfLand[i]*100;
					}
				}
			}
			Monopoly.players[player].cash -= money;
			Monopoly.players[player].totalMoney -= money;
			Monopoly.players[(player+1)%2].cash += money;
			Monopoly.players[(player+1)%2].totalMoney += money;
			if (Monopoly.players[player].isAI) {
				Game.messagePanel.str[0] = "�����" + Monopoly.players[(player+1)%2].name;
				Game.messagePanel.str[1] = "֧����" + money + "��·��";
				Game.messagePanel.repaint();
				Monopoly.messageDialog.setSize(300, 150);
				Monopoly.messageDialog.add(Game.messagePanel);
				Monopoly.messageDialog.setVisible(true);
				delay.start();
			}
			else 
				JOptionPane.showMessageDialog(Game.playerPanel, "�����\n" + Monopoly.players[(player+1)%2].name + "\n֧����" + money + "��·��", "����·��",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[player].image));
		}		
		if (Monopoly.totalAmount == 3) {
			if (MapPanel.ownerOfLand[position] == (player+1)%3) {
				int money = Game.playerPanel.levelOfLand[position]*200;//�����շ�
				if (position< 3 || position == 46 || position == 47) {
					for (int i = 46;i < 51;i++ ) {
						if (MapPanel.ownerOfLand[i%48] == (player+1)%3) {
							money += MapPanel.levelOfLand[i%48]*100;
						}
					}
				}
				if (position > 5 && position < 11) {
					for (int i = 6;i < 11;i++) {
						if (MapPanel.ownerOfLand[i] == (player+1)%3) {
							money += MapPanel.levelOfLand[i]*100;
						}
					}
				}
				if (position > 13 && position< 19) {
					for (int i = 14;i < 19;i++ ) {
						if (MapPanel.ownerOfLand[i] == (player+1)%3)	{
								money += MapPanel.levelOfLand[i]*100;
						}
					}
				}
				if (position > 22 && position < 27) {
					for (int i = 23;i < 28;i++ ) {
						if (MapPanel.ownerOfLand[i] == (player+1)%3) {
							money += MapPanel.levelOfLand[i]*100;
						}
					}
				}
				if (position > 29 && position < 35) {
					for (int i = 30;i < 35;i++ ){
						if (MapPanel.ownerOfLand[i] == (player+1)%3){
							money += MapPanel.levelOfLand[i]*100;
						}
					}
				}
				if (position > 36 && position < 42)	{
					for (int i = 37;i < 42;i++ ) {
						if (MapPanel.ownerOfLand[i] == (player+1)%3) {
							money += MapPanel.levelOfLand[i]*100;
						}
					}
				}
				Monopoly.players[player].cash -= money;
				Monopoly.players[player].totalMoney -= money;
				Monopoly.players[(player+1)%3].cash += money;
				Monopoly.players[(player+1)%3].totalMoney += money;
				if (Monopoly.players[player].isAI) {
					Game.messagePanel.str[0] = "�����" + Monopoly.players[(player+1)%3].name;
					Game.messagePanel.str[1] = "֧����" + money + "��·��";
					Game.messagePanel.repaint();
					Monopoly.messageDialog.setSize(300, 150);
					Monopoly.messageDialog.add(Game.messagePanel);
					Monopoly.messageDialog.setVisible(true);
					delay.start();
				}
				else 
					JOptionPane.showMessageDialog(Game.playerPanel, "�����\n" + Monopoly.players[(player+1)%3].name + "\n֧����" + money + "��·��", "����·��",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[player].image));
			}
			else {
				int money = Game.playerPanel.levelOfLand[position]*200;//�����շ�
				if (position< 3 || position == 46 || position == 47) {
					for (int i = 46;i < 51;i++ ) {
						if (MapPanel.ownerOfLand[i%48] == (player+2)%3) {
							money += MapPanel.levelOfLand[i%48]*100;
						}
					}
				}
				if (position > 5 && position < 11) {
					for (int i = 6;i < 11;i++) {
						if (MapPanel.ownerOfLand[i] == (player+2)%3) {
							money += MapPanel.levelOfLand[i]*100;
						}
					}
				}
				if (position > 13 && position< 19) {
					for (int i = 14;i < 19;i++ ) {
						if (MapPanel.ownerOfLand[i] == (player+2)%3)	{
								money += MapPanel.levelOfLand[i]*100;
						}
					}
				}
				if (position > 22 && position < 27) {
					for (int i = 23;i < 28;i++ ) {
						if (MapPanel.ownerOfLand[i] == (player+2)%3) {
							money += MapPanel.levelOfLand[i]*100;
						}
					}
				}
				if (position > 29 && position < 35) {
					for (int i = 30;i < 35;i++ ){
						if (MapPanel.ownerOfLand[i] == (player+2)%3){
							money += MapPanel.levelOfLand[i]*100;
						}
					}
				}
				if (position > 36 && position < 42)	{
					for (int i = 37;i < 42;i++ ) {
						if (MapPanel.ownerOfLand[i] == (player+2)%3) {
							money += MapPanel.levelOfLand[i]*100;
						}
					}
				}
				Monopoly.players[player].cash -= money;
				Monopoly.players[player].totalMoney -= money;
				Monopoly.players[(player+2)%3].cash += money;
				Monopoly.players[(player+2)%3].totalMoney += money;
				if (Monopoly.players[player].isAI) {
					Game.messagePanel.str[0] = "�����" + Monopoly.players[(player+2 )%3].name;
					Game.messagePanel.str[1] = "֧����" + money + "��·��";
					Game.messagePanel.repaint();
					Monopoly.messageDialog.setSize(300, 150);
					Monopoly.messageDialog.add(Game.messagePanel);
					Monopoly.messageDialog.setVisible(true);
					delay.start();
				}
				else 
					JOptionPane.showMessageDialog(Game.playerPanel, "�����\n" + Monopoly.players[(player+2)%3].name + "\n֧����" + money + "\n��·��", "����·��",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[player].image));
			}
		}
		if (Monopoly.players[player].land > 0) {
			Monopoly.players[player].cash += 1000;
			Monopoly.players[player].totalMoney += 1000;
			Monopoly.land.buyLand(player,position);
			JOptionPane.showMessageDialog(Game.playerPanel, "ʹ�������ؿ�\nǿռ����", "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[player].image));
		}//ʹ�������ؿ���ǿ�й���
		if (Monopoly.players[player].cash <= 0)
			noCash(player);
		Game.playerPanel.repaint();
	}
	
	//�ֽ��㴦����
	public void noCash(int player) {
		String message = "����ֽ��Ѳ��㣬��ϵͳͬ�Զ������д��תΪ�ֽ�\n";
		Monopoly.players[player].cash += Monopoly.players[player].deposit;
		Monopoly.players[player].deposit = 0;
		if (Monopoly.players[player].cash <= 0) {
			int a = 0;//��ʾ����������
			message += "������д��Ҳ���㣬��ϵͳ�Զ�������������\n";
			for (int i = 0 ; i < 48 ; i++ ) {
				if (MapPanel.ownerOfLand[i] == player) {
					Monopoly.players[player].cash += MapPanel.levelOfLand[i]*1000;
					MapPanel.ownerOfLand[i] = 3;
					Game.playerPanel.buttons[i].setToolTipText(MapPanel.nameOfLand[i] + "���" + Monopoly.players[player] + "���۵�����");
					a++;
					message += i + "����������\n";
					if (Monopoly.players[player].cash > 0) {
						message += "����" + a + "�����ء����ֽ�Ϊ" + Monopoly.players[player].cash + "\n";
						break;
					}
				}
			}
			if (Monopoly.players[player].cash <= 0) {
				message += "����" + a + "�����ء��޷�������գ����Ʋ��ˡ�\n";
				JOptionPane.showMessageDialog(Game.playerPanel, message, "Message",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[player].image));
				System.exit(0);
				//��������߳��ĺ���
			}
		}
		JOptionPane.showMessageDialog(Game.playerPanel, message, "Message",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[player].image));
	}
	//���ź���
	private void news(int player,int position) {
		int m = (int)(Math.random()*5);//m��������������
		int n = (int)(Math.random()*500) + 500;//nΪ���Ų��������Ǯ��
		boolean has = true;//��ʾ�Ƿ���������������������
		if (m == 0)
		{
			int max = Monopoly.players[0].numberOfLand, maxIndex = 0;
			for (int i = 0; i < Monopoly.totalAmount; i++) {
				if (max < Monopoly.players[i].numberOfLand) {
					maxIndex = i; 
					max = Monopoly.players[i].numberOfLand;
				}
			}
			for (int i = 0; i < Monopoly.totalAmount; i++) {
				if (max == Monopoly.players[i].numberOfLand && i != maxIndex) {
					has = false;
				}
			}
			if (has) {
				Monopoly.players[maxIndex].cash += n;
				Monopoly.players[maxIndex].totalMoney += n;
				if (Monopoly.players[player].isAI) {
					Game.messagePanel.str[0] = "��ϲ������" + Monopoly.players[maxIndex].name;
					Game.messagePanel.str[1] = "�����" + n + "Ԫ����";
					Game.messagePanel.repaint();
					Monopoly.messageDialog.setSize(300, 150);
					Monopoly.messageDialog.add(Game.messagePanel);
					Monopoly.messageDialog.setVisible(true);
					delay.start();
				}
				else 
					JOptionPane.showMessageDialog(Game.playerPanel, "��ϲ\n������\n" + Monopoly.players[maxIndex].name + "\n�����\n" + n + "Ԫ����", "����", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(Monopoly.players[maxIndex].image));
			}
			else {
				if (Monopoly.players[player].isAI) {
					Game.messagePanel.str[0] = "��˵�еĴ����";
					Game.messagePanel.str[1] = "���ٻ���ѡ��";
					Game.messagePanel.repaint();
					Monopoly.messageDialog.setSize(300, 150);
					Monopoly.messageDialog.add(Game.messagePanel);
					Monopoly.messageDialog.setVisible(true);
					delay.start();
				}
				else 
					JOptionPane.showMessageDialog(Game.playerPanel, "��˵�еĴ�\n�������ٻ�\n��ѡ��(���\nʵ���൱��\nû��������)", "����", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("icons/landlord.png"));
			}
		}
		if (m == 1)
		{
			int min = Monopoly.players[0].numberOfLand, minIndex = 0;
			for (int i = 0; i < Monopoly.totalAmount; i++) {
				if (min > Monopoly.players[i].numberOfLand) {
					minIndex = i; 
					min = Monopoly.players[i].numberOfLand;
				}
			}
			for (int i = 0; i < Monopoly.totalAmount; i++) {
				if (min == Monopoly.players[i].numberOfLand && i != minIndex) {
					has = false;
				}
			}
			if (has) {
				Monopoly.players[minIndex].cash += n;
				Monopoly.players[minIndex].totalMoney += n;
				if (Monopoly.players[player].isAI) {
					Game.messagePanel.str[0] = "��ϲ����������" + Monopoly.players[minIndex].name;
					Game.messagePanel.str[1] = "�����" + n + "Ԫ����";
					Game.messagePanel.repaint();
					Monopoly.messageDialog.setSize(300, 150);
					Monopoly.messageDialog.add(Game.messagePanel);
					Monopoly.messageDialog.setVisible(true);
					delay.start();
				}
				else 
				JOptionPane.showMessageDialog(Game.playerPanel, "��ϲ\n����������\n" + Monopoly.players[minIndex].name + "\n�����\n" + n + "Ԫ����", "����", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(Monopoly.players[minIndex].image));
			}
			else
				if (Monopoly.players[player].isAI) {
					Game.messagePanel.str[0] = "Congradulations!!";
					Game.messagePanel.str[1] = "û������������";
					Game.messagePanel.repaint();
					Monopoly.messageDialog.setSize(300, 150);
					Monopoly.messageDialog.add(Game.messagePanel);
					Monopoly.messageDialog.setVisible(true);
					delay.start();
				}
				else 
					JOptionPane.showMessageDialog(Game.playerPanel, "Congradulations!!\nû������������", "����", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("icons/rich.png"));
		}
		if (m == 2)
		{	
			if (Monopoly.players[player].isAI) {
				Game.messagePanel.str[0] = "���мӷ��������";
				Game.messagePanel.str[1] = "ÿ���˵õ����10%";
				Game.messagePanel.repaint();
				Monopoly.messageDialog.setSize(300, 150);
				Monopoly.messageDialog.add(Game.messagePanel);
				Monopoly.messageDialog.setVisible(true);
				delay.start();
			}
			else 
				JOptionPane.showMessageDialog(Game.playerPanel, "���мӷ���\n�����ÿ����\n�õ����10%", "����", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("icons/interestOfBank.png"));
			for (int i = 0; i < Monopoly.totalAmount; i++) {
				Monopoly.players[i].totalMoney += Monopoly.players[i].deposit/10;
				Monopoly.players[i].deposit += Monopoly.players[i].deposit/10;
			}
		}
		if (m == 3)
		{	
			if (Monopoly.players[player].isAI) {
				Game.messagePanel.str[0] = "�����˽���";
				Game.messagePanel.str[1] = "�Ʋ�˰10%";
				Game.messagePanel.repaint();
				Monopoly.messageDialog.setSize(300, 150);
				Monopoly.messageDialog.add(Game.messagePanel);
				Monopoly.messageDialog.setVisible(true);
				delay.start();
			}
			else 
				JOptionPane.showMessageDialog(Game.playerPanel, "�����˽���\n�Ʋ�˰10%", "����", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("icons/interestOfBank.png"));
			for (int i = 0; i < Monopoly.totalAmount; i++) {
				Monopoly.players[i].totalMoney -= Monopoly.players[i].deposit/10;
				Monopoly.players[i].deposit -= Monopoly.players[i].deposit/10;
			}
		}
		if (m == 4)
		{	
			if (Monopoly.players[player].isAI) {
				Game.messagePanel.str[0] = "ÿ������õ�";
				Game.messagePanel.str[1] = "һ�ſ�Ƭ";
				Game.messagePanel.repaint();
				Monopoly.messageDialog.setSize(300, 150);
				Monopoly.messageDialog.add(Game.messagePanel);
				Monopoly.messageDialog.setVisible(true);
				delay.start();
			}
			else 
				JOptionPane.showMessageDialog(Game.playerPanel, "ÿ������õ�\nһ�ſ�Ƭ�ǲ�\n�Ǻ��뿴����\nƬ�������ʲô", "����", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("icons/cardOfNews.png"));
			for (int i = 0; i < Monopoly.totalAmount; i++) {
				Monopoly.players[i].numberOfcard[(int)(Math.random()*5)] += 1;
			}
		}
		Game.playerPanel.repaint();
	}
	
	//���Ϳ�����
	public void giveCard(int player) {
		int i = (int)(Math.random()*15);
		Monopoly.players[player].numberOfcard[i] += 1;
		if (Monopoly.players[player].isAI) {
			Game.messagePanel.str[0] = "��ϲ���" + Monopoly.players[player].name;
			Game.messagePanel.str[1] = "�����" + Monopoly.players[player].nameOfCard[i];
			Game.messagePanel.repaint();
			Monopoly.messageDialog.setSize(300, 150);
			Monopoly.messageDialog.add(Game.messagePanel);
			Monopoly.messageDialog.setVisible(true);
			delay.start();
		}
		else 
			JOptionPane.showMessageDialog(Game.playerPanel, "��ϲ���\n" + Monopoly.players[player].name + "\n�����\n" + Monopoly.players[player].nameOfCard[i], "���͵���", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(Monopoly.players[player].image));
		Game.playerPanel.repaint();
	}
	
	//���͵�ȯ����
	public void givePoint(int player) {
		int i = (int)(Math.random()*4)*5;
		Monopoly.players[player].point += i;
		if (Monopoly.players[player].isAI) {
			Game.messagePanel.str[0] = "��ϲ���" + Monopoly.players[player].name;
			Game.messagePanel.str[1] = "�����" + i + "��ȯ";
			Game.messagePanel.repaint();
			Monopoly.messageDialog.setSize(300, 150);
			Monopoly.messageDialog.add(Game.messagePanel);
			Monopoly.messageDialog.setVisible(true);
			delay.start();
		}
		else 
			JOptionPane.showMessageDialog(Game.playerPanel, "��ϲ���\n" + Monopoly.players[player].name + "\n�����\n" + i + "��ȯ", "���͵���", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(Monopoly.players[player].image));
		Game.playerPanel.repaint();
	}
	
	//��Ʊ����
	public void lottery(int player) {
		int answer = JOptionPane.showConfirmDialog(Game.playerPanel, "�㵽����\n��Ʊ����\n�Ƿ񻨷�\n200���\nƱ��", "��Ʊ��", JOptionPane.YES_NO_OPTION , JOptionPane.INFORMATION_MESSAGE, new ImageIcon(Monopoly.players[player].image));
		if (answer == JOptionPane.YES_OPTION) {
			if (Monopoly.players[player].cash < 200) {
				JOptionPane.showMessageDialog(Game.playerPanel, "��ô�죬\nǮ������", "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[player].image));
			}
			else {
				int rm = (int)(Math.random()*10);
				if (Monopoly.players[player].lottery) {
					rm = 5;
					Monopoly.players[player].lottery = false;
				}
				if (rm == 5){
					JOptionPane.showMessageDialog(Game.playerPanel, "��ϲ�в�\nƱ2000", "��Ʊ��", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(Monopoly.players[player].image));
				}
				else {
					JOptionPane.showMessageDialog(Game.playerPanel, "���ź���\n������\n����", "��Ʊ��", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(Monopoly.players[player].image));
				}
			}
		}
		Game.playerPanel.repaint();
	}
	
//	//���ߵ꺯��
//	public void propStore(int player) {
//		Game.playerPanel.repaint();
//	}
//	
//	//���к���
//	public void bank(int player) {
//		//�����������ط�����
//		Game.playerPanel.repaint();
//	}
//	
	//���з�Ϣ����
	public void interestOfBank() {
		JOptionPane.showMessageDialog(Game.playerPanel, "�µ׷�Ϣ\n��ÿ����\n�õ����\n10%", "����", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("icons/interestOfBank.png"));
		for (int i = 0; i < Monopoly.totalAmount; i++) {
			Monopoly.players[i].deposit += Monopoly.players[i].deposit/10;
			Monopoly.players[i].totalMoney += Monopoly.players[i].deposit/10;
		}
		Game.playerPanel.repaint();
	}
}
