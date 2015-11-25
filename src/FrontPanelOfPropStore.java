import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

//public class PropStoreFrame extends JFrame {
//	public PropStoreFrame() {
//		this.setTitle("道具店");
//	    add(new FrontPanel());
//		this.setLocation(410,230);
//	    this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
//	    this.setSize(455, 325);
//	    this.setResizable(false);
//	    this.setVisible(false);	
//	}
//}
	class BackPanelOfPropStore extends JPanel {
		Image image;
		ImageIcon monopolyIcon = new ImageIcon("icons/propStore.png");
		public BackPanelOfPropStore() {
			image = monopolyIcon.getImage();
		}
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, 0, 0, 450, 300, this);
		}	
	}
	//实现道具店界面
	public class FrontPanelOfPropStore extends BackPanelOfPropStore {
		ImageIcon averageIcon  = new ImageIcon("icons/average.png");
		ImageIcon changeDirectionIcon  = new ImageIcon("icons/changeDirection.png");
		ImageIcon barricadesIcon  = new ImageIcon("icons/barricades.png");
		ImageIcon controlDiceIcon  = new ImageIcon("icons/controlDice.png");
		ImageIcon robIcon  = new ImageIcon("icons/rob.png");
		ImageIcon noIcon = new ImageIcon("icons/no.png");
		
		JButton average = new JButton(averageIcon);
		JButton changeDirection = new JButton(changeDirectionIcon);
		JButton barricades = new JButton(barricadesIcon);
		JButton controlDice = new JButton(controlDiceIcon);
		JButton rob = new JButton(robIcon);
		JButton no = new JButton(noIcon);
		
		Font font = new Font("hello",Font.ITALIC,20);
		public FrontPanelOfPropStore() {
			setLayout(null);
			add(average);
			average.setBounds(98, 0, averageIcon.getIconWidth(), averageIcon.getIconHeight());
			average.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Monopoly.music.button.play();
					if (Monopoly.players[Game.playerPanel.currentPlayer].point < 5)
						JOptionPane.showMessageDialog(Game.playerPanel, "怎么办，\n点券不够啊", "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[Game.playerPanel.currentPlayer].image));
					else {
						Monopoly.players[Game.playerPanel.currentPlayer].point -= 5;
						Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[0]++;
						JOptionPane.showMessageDialog(Game.playerPanel, "你买了一张\n均富卡，还\n剩" + Monopoly.players[Game.playerPanel.currentPlayer].point + "点券", "购卡通知",JOptionPane.INFORMATION_MESSAGE, new ImageIcon(Monopoly.players[Game.playerPanel.currentPlayer].image));
						Game.playerPanel.repaint();
					}
				}
				
			});
			add(changeDirection);
			changeDirection.setBounds(411, 41, changeDirectionIcon.getIconWidth(), changeDirectionIcon.getIconHeight());
			changeDirection.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Monopoly.music.button.play();
					if (Monopoly.players[Game.playerPanel.currentPlayer].point < 5)
						JOptionPane.showMessageDialog(Game.playerPanel, "怎么办，\n点券不够啊", "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[Game.playerPanel.currentPlayer].image));
					else {
						Monopoly.players[Game.playerPanel.currentPlayer].point -= 5;
						Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[1]++;
						JOptionPane.showMessageDialog(Game.playerPanel, "你买了一张\n转向卡，还\n剩" + Monopoly.players[Game.playerPanel.currentPlayer].point + "点券", "购卡通知",JOptionPane.INFORMATION_MESSAGE, new ImageIcon(Monopoly.players[Game.playerPanel.currentPlayer].image));
					}
				}
				
			});
			
			add(barricades);
			barricades.setBounds(98, 120, barricadesIcon.getIconWidth(), barricadesIcon.getIconHeight());
			barricades.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Monopoly.music.button.play();
					if (Monopoly.players[Game.playerPanel.currentPlayer].point < 5)
						JOptionPane.showMessageDialog(Game.playerPanel, "怎么办，\n点券不够啊", "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[Game.playerPanel.currentPlayer].image));
					else {
						Monopoly.players[Game.playerPanel.currentPlayer].point -= 5;
						Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[2]++;
						JOptionPane.showMessageDialog(Game.playerPanel, "你买了一张\n路障卡，还\n剩" + Monopoly.players[Game.playerPanel.currentPlayer].point + "点券", "购卡通知",JOptionPane.INFORMATION_MESSAGE, new ImageIcon(Monopoly.players[Game.playerPanel.currentPlayer].image));
					}
				}
				
			});
			
			add(controlDice);
			controlDice.setBounds(410, 195, controlDiceIcon.getIconWidth(), controlDiceIcon.getIconHeight());
			controlDice.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Monopoly.music.button.play();
					if (Monopoly.players[Game.playerPanel.currentPlayer].point < 5)
						JOptionPane.showMessageDialog(Game.playerPanel, "怎么办，\n点券不够啊", "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[Game.playerPanel.currentPlayer].image));
					else {
						Monopoly.players[Game.playerPanel.currentPlayer].point -= 5;
						Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[3]++;
						JOptionPane.showMessageDialog(Game.playerPanel, "你买了一张\n遥控骰子卡，\n还剩" + Monopoly.players[Game.playerPanel.currentPlayer].point + "点券", "购卡通知",JOptionPane.INFORMATION_MESSAGE, new ImageIcon(Monopoly.players[Game.playerPanel.currentPlayer].image));						
					}
				}
				
			});
			
			add(rob);
			rob.setBounds(99, 241, robIcon.getIconWidth(), robIcon.getIconHeight());
			rob.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Monopoly.music.button.play();
					if (Monopoly.players[Game.playerPanel.currentPlayer].point < 5)
						JOptionPane.showMessageDialog(Game.playerPanel, "怎么办，\n点券不够啊", "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[Game.playerPanel.currentPlayer].image));
					else {
						Monopoly.players[Game.playerPanel.currentPlayer].point -= 5;
						Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[4]++;
						JOptionPane.showMessageDialog(Game.playerPanel, "你买了一张\n掠夺卡，还\n剩" + Monopoly.players[Game.playerPanel.currentPlayer].point + "点券", "购卡通知",JOptionPane.INFORMATION_MESSAGE, new ImageIcon(Monopoly.players[Game.playerPanel.currentPlayer].image));		
					}
				}
				
			});
			add(no);
			no.setBorderPainted(false);
			no.setRolloverIcon(new ImageIcon("icons/no0.png"));
			no.setBounds(420, 0, 30, 30);
			no.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Monopoly.music.button.play();
					Game.playerPanel.repaint();
					Monopoly.propStoreDialog.setVisible(false);
					Monopoly.monopolyFrame.setEnabled(true);
				}	
			});
		}
	}