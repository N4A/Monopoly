import java.awt.event.*;

import javax.swing.*;

public class Land {
	/**土地触发事件函数
	0 玩家0
	1 玩家1
	2 玩家2
	3 未购买土地
	4 空地
	5 新闻
	6 卡片
	7 点券
	8 彩票
	9 道具
	10 银行*/
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
			}//使用了土地卡，免费购买
			
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
					Game.messagePanel.str[0]= "恭喜中彩票2000";
					Game.messagePanel.str[1]= "";
					Game.messagePanel.repaint();
					Monopoly.messageDialog.add(Game.messagePanel);
					Monopoly.messageDialog.setVisible(true);
					delay.start();
				}
				else {
					Game.messagePanel.str[0]= "很遗憾，差点就中了";
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
					Game.messagePanel.str[0]= "购入" + Monopoly.players[player].nameOfCard[a];
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
			//银行函数由于其特殊性由别处调用
			Game.playerPanel.repaint();
		}
		else {//走到其他人土地上
			if (Monopoly.players[player].caishen > 0) {
				//不付钱
				if (Monopoly.players[player].isAI) {
					Game.messagePanel.str[0] = "财神护体";
					Game.messagePanel.str[1] = "免交过路费";
					Game.messagePanel.repaint();
					Monopoly.messageDialog.setSize(300, 150);
					Monopoly.messageDialog.add(Game.messagePanel);
					Monopoly.messageDialog.setVisible(true);
					delay.start();
				}
				else
					JOptionPane.showMessageDialog(Game.playerPanel, "财神护体\n免交过路费", "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[player].image));
			}
			else if (Monopoly.players[player].fushen > 0) {
				//不付钱
				if (Monopoly.players[player].isAI) {
					Game.messagePanel.str[0] = "财神护体";
					Game.messagePanel.str[1] = "免交过路费";
					Game.messagePanel.repaint();
					Monopoly.messageDialog.setSize(300, 150);
					Monopoly.messageDialog.add(Game.messagePanel);
					Monopoly.messageDialog.setVisible(true);
					delay.start();
				}
				else
					JOptionPane.showMessageDialog(Game.playerPanel, "福神护体\n免交过路费", "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[player].image));
			}
			else	//走到别人的土地上，应当给钱
				payMoney(player,position);
		}
	}
	

	//购买土地函数
	public void buyLand(int player, int position) {
		if (Monopoly.players[player].cash >= 1000) {
			Monopoly.players[player].cash -= 1000;
			Monopoly.players[player].houseProperty += 1000;
			Monopoly.players[player].numberOfLand++;
			MapPanel.ownerOfLand[position] = player;//把土地附给当前玩家
			Game.playerPanel.buttons[position].setIcon(new ImageIcon("icons/owner" + player + (MapPanel.levelOfLand[position] - 1) + ".png"));
			MapPanel.buttons[position].setToolTipText(MapPanel.nameOfLand[position] + "  拥有者：" + Monopoly.players[player].name + "  等级：" + MapPanel.levelOfLand[position]);
			Monopoly.eventDialog.setVisible(false);
			Monopoly.monopolyFrame.setFocusable(true);
		}
		else {
			Monopoly.eventDialog.setVisible(false);
			Monopoly.monopolyFrame.setFocusable(true);
			JOptionPane.showMessageDialog(Game.playerPanel, "怎么办\n钱不够啊", "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[player].image));
		}
		Game.playerPanel.repaint();
	}
	
	
	//土地升级函数
	public void upLand(int player, int position) {
		if (Monopoly.players[player].cash >= 500) {
			Monopoly.players[player].cash -= 500;
			Monopoly.players[player].totalMoney += 500;
			Monopoly.players[player].houseProperty += 1000;
			if (MapPanel.levelOfLand[position] < 6) {
				MapPanel.levelOfLand[position]++;
				Game.playerPanel.buttons[position].setIcon(new ImageIcon("icons/owner" + player + (MapPanel.levelOfLand[position] - 1) + ".png"));
				MapPanel.buttons[position].setToolTipText(MapPanel.nameOfLand[position] + "  拥有者：" + Monopoly.players[player].name + "  等级：" + MapPanel.levelOfLand[position]);		
			}
			else
				JOptionPane.showMessageDialog(Game.playerPanel, "你的土地\n已经达到\n顶级了", "Congadurations",JOptionPane.INFORMATION_MESSAGE, new ImageIcon(Monopoly.players[player].image));
			Monopoly.eventDialog.setVisible(false);
		}
		else {
			Monopoly.eventDialog.setVisible(false);
			JOptionPane.showMessageDialog(Game.playerPanel, "怎么办\n钱不够啊", "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[player].image));
		}
		Game.playerPanel.repaint();
	}                
	
	//付过路费函数
	public void payMoney(int player,int position) {
		if (Monopoly.totalAmount == 2) {
			int money = Game.playerPanel.levelOfLand[position]*200;//房屋收费
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
				Game.messagePanel.str[0] = "向玩家" + Monopoly.players[(player+1)%2].name;
				Game.messagePanel.str[1] = "支付了" + money + "过路费";
				Game.messagePanel.repaint();
				Monopoly.messageDialog.setSize(300, 150);
				Monopoly.messageDialog.add(Game.messagePanel);
				Monopoly.messageDialog.setVisible(true);
				delay.start();
			}
			else 
				JOptionPane.showMessageDialog(Game.playerPanel, "向玩家\n" + Monopoly.players[(player+1)%2].name + "\n支付了" + money + "过路费", "交过路费",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[player].image));
		}		
		if (Monopoly.totalAmount == 3) {
			if (MapPanel.ownerOfLand[position] == (player+1)%3) {
				int money = Game.playerPanel.levelOfLand[position]*200;//房屋收费
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
					Game.messagePanel.str[0] = "向玩家" + Monopoly.players[(player+1)%3].name;
					Game.messagePanel.str[1] = "支付了" + money + "过路费";
					Game.messagePanel.repaint();
					Monopoly.messageDialog.setSize(300, 150);
					Monopoly.messageDialog.add(Game.messagePanel);
					Monopoly.messageDialog.setVisible(true);
					delay.start();
				}
				else 
					JOptionPane.showMessageDialog(Game.playerPanel, "向玩家\n" + Monopoly.players[(player+1)%3].name + "\n支付了" + money + "过路费", "交过路费",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[player].image));
			}
			else {
				int money = Game.playerPanel.levelOfLand[position]*200;//房屋收费
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
					Game.messagePanel.str[0] = "向玩家" + Monopoly.players[(player+2 )%3].name;
					Game.messagePanel.str[1] = "支付了" + money + "过路费";
					Game.messagePanel.repaint();
					Monopoly.messageDialog.setSize(300, 150);
					Monopoly.messageDialog.add(Game.messagePanel);
					Monopoly.messageDialog.setVisible(true);
					delay.start();
				}
				else 
					JOptionPane.showMessageDialog(Game.playerPanel, "向玩家\n" + Monopoly.players[(player+2)%3].name + "\n支付了" + money + "\n过路费", "交过路费",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[player].image));
			}
		}
		if (Monopoly.players[player].land > 0) {
			Monopoly.players[player].cash += 1000;
			Monopoly.players[player].totalMoney += 1000;
			Monopoly.land.buyLand(player,position);
			JOptionPane.showMessageDialog(Game.playerPanel, "使用了土地卡\n强占土地", "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[player].image));
		}//使用了土地卡，强行购买
		if (Monopoly.players[player].cash <= 0)
			noCash(player);
		Game.playerPanel.repaint();
	}
	
	//现金不足处理函数
	public void noCash(int player) {
		String message = "你的现金已不足，现系统同自动将银行存款转为现金。\n";
		Monopoly.players[player].cash += Monopoly.players[player].deposit;
		Monopoly.players[player].deposit = 0;
		if (Monopoly.players[player].cash <= 0) {
			int a = 0;//表示卖出土地数
			message += "你的银行存款也不足，现系统自动变卖你的田产。\n";
			for (int i = 0 ; i < 48 ; i++ ) {
				if (MapPanel.ownerOfLand[i] == player) {
					Monopoly.players[player].cash += MapPanel.levelOfLand[i]*1000;
					MapPanel.ownerOfLand[i] = 3;
					Game.playerPanel.buttons[i].setToolTipText(MapPanel.nameOfLand[i] + "玩家" + Monopoly.players[player] + "出售的土地");
					a++;
					message += i + "号土地卖出\n";
					if (Monopoly.players[player].cash > 0) {
						message += "卖出" + a + "块土地。现现金为" + Monopoly.players[player].cash + "\n";
						break;
					}
				}
			}
			if (Monopoly.players[player].cash <= 0) {
				message += "卖出" + a + "块土地。无法解决亏空，你破产了。\n";
				JOptionPane.showMessageDialog(Game.playerPanel, message, "Message",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[player].image));
				System.exit(0);
				//将此玩家踢出的函数
			}
		}
		JOptionPane.showMessageDialog(Game.playerPanel, message, "Message",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[player].image));
	}
	//新闻函数
	private void news(int player,int position) {
		int m = (int)(Math.random()*5);//m用来获得随机新闻
		int n = (int)(Math.random()*500) + 500;//n为新闻产生的随机钱数
		boolean has = true;//表示是否有最大地主或土地最少者
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
					Game.messagePanel.str[0] = "恭喜最大地主" + Monopoly.players[maxIndex].name;
					Game.messagePanel.str[1] = "获得了" + n + "元奖励";
					Game.messagePanel.repaint();
					Monopoly.messageDialog.setSize(300, 150);
					Monopoly.messageDialog.add(Game.messagePanel);
					Monopoly.messageDialog.setVisible(true);
					delay.start();
				}
				else 
					JOptionPane.showMessageDialog(Game.playerPanel, "恭喜\n最大地主\n" + Monopoly.players[maxIndex].name + "\n获得了\n" + n + "元奖励", "新闻", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(Monopoly.players[maxIndex].image));
			}
			else {
				if (Monopoly.players[player].isAI) {
					Game.messagePanel.str[0] = "传说中的大地主";
					Game.messagePanel.str[1] = "在召唤候选人";
					Game.messagePanel.repaint();
					Monopoly.messageDialog.setSize(300, 150);
					Monopoly.messageDialog.add(Game.messagePanel);
					Monopoly.messageDialog.setVisible(true);
					delay.start();
				}
				else 
					JOptionPane.showMessageDialog(Game.playerPanel, "传说中的大\n地主在召唤\n候选人(大家\n实力相当，\n没有最大地主)", "新闻", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("icons/landlord.png"));
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
					Game.messagePanel.str[0] = "恭喜土地最少者" + Monopoly.players[minIndex].name;
					Game.messagePanel.str[1] = "获得了" + n + "元补助";
					Game.messagePanel.repaint();
					Monopoly.messageDialog.setSize(300, 150);
					Monopoly.messageDialog.add(Game.messagePanel);
					Monopoly.messageDialog.setVisible(true);
					delay.start();
				}
				else 
				JOptionPane.showMessageDialog(Game.playerPanel, "恭喜\n土地最少者\n" + Monopoly.players[minIndex].name + "\n获得了\n" + n + "元补助", "新闻", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(Monopoly.players[minIndex].image));
			}
			else
				if (Monopoly.players[player].isAI) {
					Game.messagePanel.str[0] = "Congradulations!!";
					Game.messagePanel.str[1] = "没有土地最少者";
					Game.messagePanel.repaint();
					Monopoly.messageDialog.setSize(300, 150);
					Monopoly.messageDialog.add(Game.messagePanel);
					Monopoly.messageDialog.setVisible(true);
					delay.start();
				}
				else 
					JOptionPane.showMessageDialog(Game.playerPanel, "Congradulations!!\n没有土地最少者", "新闻", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("icons/rich.png"));
		}
		if (m == 2)
		{	
			if (Monopoly.players[player].isAI) {
				Game.messagePanel.str[0] = "银行加发储金红利";
				Game.messagePanel.str[1] = "每个人得到存款10%";
				Game.messagePanel.repaint();
				Monopoly.messageDialog.setSize(300, 150);
				Monopoly.messageDialog.add(Game.messagePanel);
				Monopoly.messageDialog.setVisible(true);
				delay.start();
			}
			else 
				JOptionPane.showMessageDialog(Game.playerPanel, "银行加发储\n金红利每个人\n得到存款10%", "新闻", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("icons/interestOfBank.png"));
			for (int i = 0; i < Monopoly.totalAmount; i++) {
				Monopoly.players[i].totalMoney += Monopoly.players[i].deposit/10;
				Monopoly.players[i].deposit += Monopoly.players[i].deposit/10;
			}
		}
		if (m == 3)
		{	
			if (Monopoly.players[player].isAI) {
				Game.messagePanel.str[0] = "所有人缴纳";
				Game.messagePanel.str[1] = "财产税10%";
				Game.messagePanel.repaint();
				Monopoly.messageDialog.setSize(300, 150);
				Monopoly.messageDialog.add(Game.messagePanel);
				Monopoly.messageDialog.setVisible(true);
				delay.start();
			}
			else 
				JOptionPane.showMessageDialog(Game.playerPanel, "所有人缴纳\n财产税10%", "新闻", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("icons/interestOfBank.png"));
			for (int i = 0; i < Monopoly.totalAmount; i++) {
				Monopoly.players[i].totalMoney -= Monopoly.players[i].deposit/10;
				Monopoly.players[i].deposit -= Monopoly.players[i].deposit/10;
			}
		}
		if (m == 4)
		{	
			if (Monopoly.players[player].isAI) {
				Game.messagePanel.str[0] = "每人随机得到";
				Game.messagePanel.str[1] = "一张卡片";
				Game.messagePanel.repaint();
				Monopoly.messageDialog.setSize(300, 150);
				Monopoly.messageDialog.add(Game.messagePanel);
				Monopoly.messageDialog.setVisible(true);
				delay.start();
			}
			else 
				JOptionPane.showMessageDialog(Game.playerPanel, "每人随机得到\n一张卡片是不\n是很想看看卡\n片库里多了什么", "新闻", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("icons/cardOfNews.png"));
			for (int i = 0; i < Monopoly.totalAmount; i++) {
				Monopoly.players[i].numberOfcard[(int)(Math.random()*5)] += 1;
			}
		}
		Game.playerPanel.repaint();
	}
	
	//赠送卡函数
	public void giveCard(int player) {
		int i = (int)(Math.random()*15);
		Monopoly.players[player].numberOfcard[i] += 1;
		if (Monopoly.players[player].isAI) {
			Game.messagePanel.str[0] = "恭喜玩家" + Monopoly.players[player].name;
			Game.messagePanel.str[1] = "获得了" + Monopoly.players[player].nameOfCard[i];
			Game.messagePanel.repaint();
			Monopoly.messageDialog.setSize(300, 150);
			Monopoly.messageDialog.add(Game.messagePanel);
			Monopoly.messageDialog.setVisible(true);
			delay.start();
		}
		else 
			JOptionPane.showMessageDialog(Game.playerPanel, "恭喜玩家\n" + Monopoly.players[player].name + "\n获得了\n" + Monopoly.players[player].nameOfCard[i], "赠送道具", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(Monopoly.players[player].image));
		Game.playerPanel.repaint();
	}
	
	//赠送点券函数
	public void givePoint(int player) {
		int i = (int)(Math.random()*4)*5;
		Monopoly.players[player].point += i;
		if (Monopoly.players[player].isAI) {
			Game.messagePanel.str[0] = "恭喜玩家" + Monopoly.players[player].name;
			Game.messagePanel.str[1] = "获得了" + i + "点券";
			Game.messagePanel.repaint();
			Monopoly.messageDialog.setSize(300, 150);
			Monopoly.messageDialog.add(Game.messagePanel);
			Monopoly.messageDialog.setVisible(true);
			delay.start();
		}
		else 
			JOptionPane.showMessageDialog(Game.playerPanel, "恭喜玩家\n" + Monopoly.players[player].name + "\n获得了\n" + i + "点券", "赠送道具", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(Monopoly.players[player].image));
		Game.playerPanel.repaint();
	}
	
	//彩票函数
	public void lottery(int player) {
		int answer = JOptionPane.showConfirmDialog(Game.playerPanel, "你到达卖\n彩票处，\n是否花费\n200买彩\n票？", "彩票店", JOptionPane.YES_NO_OPTION , JOptionPane.INFORMATION_MESSAGE, new ImageIcon(Monopoly.players[player].image));
		if (answer == JOptionPane.YES_OPTION) {
			if (Monopoly.players[player].cash < 200) {
				JOptionPane.showMessageDialog(Game.playerPanel, "怎么办，\n钱不够啊", "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[player].image));
			}
			else {
				int rm = (int)(Math.random()*10);
				if (Monopoly.players[player].lottery) {
					rm = 5;
					Monopoly.players[player].lottery = false;
				}
				if (rm == 5){
					JOptionPane.showMessageDialog(Game.playerPanel, "恭喜中彩\n票2000", "彩票店", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(Monopoly.players[player].image));
				}
				else {
					JOptionPane.showMessageDialog(Game.playerPanel, "很遗憾，\n差点你就\n中了", "彩票店", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(Monopoly.players[player].image));
				}
			}
		}
		Game.playerPanel.repaint();
	}
	
//	//道具店函数
//	public void propStore(int player) {
//		Game.playerPanel.repaint();
//	}
//	
//	//银行函数
//	public void bank(int player) {
//		//银行在其他地方调用
//		Game.playerPanel.repaint();
//	}
//	
	//银行发息函数
	public void interestOfBank() {
		JOptionPane.showMessageDialog(Game.playerPanel, "月底发息\n，每个人\n得到存款\n10%", "银行", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("icons/interestOfBank.png"));
		for (int i = 0; i < Monopoly.totalAmount; i++) {
			Monopoly.players[i].deposit += Monopoly.players[i].deposit/10;
			Monopoly.players[i].totalMoney += Monopoly.players[i].deposit/10;
		}
		Game.playerPanel.repaint();
	}
}
