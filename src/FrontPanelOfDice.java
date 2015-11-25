import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
//public class diceDialog extends JFrame {
//	public diceDialog() {
//		this.setTitle("大富翁——群山环绕的死水湖");
//	    add(new FrontPanel());
//		this.setLocation(450,250);
//	    this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
//	    this.setSize(350, 120);
//	    this.setResizable(false);
//	    this.setVisible(false);	 		
//	}
//}
	class BackPanelOfDice extends JPanel {
		Image image;
		ImageIcon monopolyIcon = new ImageIcon("icons/backGround.png");
		public BackPanelOfDice() {
			image = monopolyIcon.getImage();
		}
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, 0, 0, 345, 120, this);
			g.setFont(Game.playerPanel.font);
			g.setColor(Color.CYAN);
			g.drawString("请选择你要掷的点数", 85,25);
		}			
	}
	//使用遥控骰子时，弹出窗口让玩家选择
	public class FrontPanelOfDice extends BackPanelOfDice {
		JButton[] dice = new JButton[6];
		public FrontPanelOfDice() {
			setLayout(null);
			for ( int i = 0;i < 6;i++) {
				dice[i] = new JButton(new ImageIcon("icons/dice" + (i+1) + ".png"));
				add(dice[i]);					
			}
			dice[0].setBounds(10, 40, 50, 50);
			dice[0].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Monopoly.music.button.play();
					Game.playerPanel.controlDice = 1;
					Monopoly.diceDialog.setVisible(false);
					Monopoly.monopolyFrame.setEnabled(true);
				}
			});
			dice[1].setBounds(65, 40, 50, 50);
			dice[1].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Monopoly.music.button.play();
					Game.playerPanel.controlDice = 2;
					Monopoly.diceDialog.setVisible(false);
					Monopoly.monopolyFrame.setEnabled(true);
				}
			});
			dice[2].setBounds(120, 40, 50, 50);
			dice[2].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Monopoly.music.button.play();
					Game.playerPanel.controlDice = 3;
					Monopoly.diceDialog.setVisible(false);
					Monopoly.monopolyFrame.setEnabled(true);
				}
			});
			dice[3].setBounds(175, 40, 50, 50);
			dice[3].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Monopoly.music.button.play();
					Game.playerPanel.controlDice = 4;
					Monopoly.diceDialog.setVisible(false);
					Monopoly.monopolyFrame.setEnabled(true);
				}
			});
			dice[4].setBounds(230, 40, 50, 50);
			dice[4].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Monopoly.music.button.play();
					Game.playerPanel.controlDice = 5;
					Monopoly.diceDialog.setVisible(false);
					Monopoly.monopolyFrame.setEnabled(true);
				}
			});
			dice[5].setBounds(285, 40, 50, 50);
			dice[5].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Monopoly.music.button.play();
					Game.playerPanel.controlDice = 6;
					Monopoly.diceDialog.setVisible(false);
					Monopoly.monopolyFrame.setEnabled(true);
				}
			});
		}
	
	}