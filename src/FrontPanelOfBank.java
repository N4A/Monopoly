import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
//原来使用frame弹窗，后来改成了dialog
//public class BankFrame extends JFrame {
//	public BankFrame() {
//		this.setTitle("银行");
//	    add(new FrontPanel());
//		this.setLocation(410,230);
//	    this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
//	    this.setSize(395, 298);
//	    this.setResizable(false);
//	    this.setVisible(false);	
//	}
//}
	class BackPanelOfBank extends JPanel {
		Image image;
		ImageIcon monopolyIcon = new ImageIcon("icons/bankOption.png");
		public BackPanelOfBank() {
			image = monopolyIcon.getImage();
		}
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, 0, 0, 390, 270, this);
			g.setFont(new Font("",Font.ITALIC,30));
			g.drawString("" + Monopoly.players[Game.playerPanel.currentPlayer].cash, 200, 134);
			g.drawString("" + Monopoly.players[Game.playerPanel.currentPlayer].deposit, 200, 195);
		}	
	}
	public class FrontPanelOfBank extends BackPanelOfBank {
		JButton withdraw = new JButton(new ImageIcon("icons/withdraw.png"));
		JButton save = new JButton(new ImageIcon("icons/save.png"));
		JButton ok = new JButton(new ImageIcon("icons/ok.png"));
		public FrontPanelOfBank() {
			setLayout(null);
			add(withdraw);
			withdraw.setBorderPainted(false);
			withdraw.setRolloverIcon(new ImageIcon("icons/withdraw0.png"));
			withdraw.setBounds(340, 112, 32, 25);
			withdraw.setToolTipText("点我取款,每次取500");
			withdraw.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Monopoly.music.button.play();
					Monopoly.players[Game.playerPanel.currentPlayer].cash += 500;
					Monopoly.players[Game.playerPanel.currentPlayer].deposit -= 500;
					if (Monopoly.players[Game.playerPanel.currentPlayer].cash < 500)
						save.setVisible(false);
					else
						save.setVisible(true);
					if (Monopoly.players[Game.playerPanel.currentPlayer].deposit < 500)
						withdraw.setVisible(false);
					else
						withdraw.setVisible(true);
					repaint();
				}
				
			});
			add(save);
			save.setBorderPainted(false);
			save.setRolloverIcon(new ImageIcon("icons/save0.png"));
			save.setBounds(339, 173, 30, 24);
			save.setToolTipText("点我存款，每次存500");
			save.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Monopoly.music.button.play();
					Monopoly.players[Game.playerPanel.currentPlayer].cash -= 500;
					Monopoly.players[Game.playerPanel.currentPlayer].deposit += 500;
					if (Monopoly.players[Game.playerPanel.currentPlayer].cash < 500)
						save.setVisible(false);
					else
						save.setVisible(true);
					if (Monopoly.players[Game.playerPanel.currentPlayer].deposit < 500)
						withdraw.setVisible(false);
					else
						withdraw.setVisible(true);
					repaint();
				}
				
			});
			add(ok);
			ok.setBorderPainted(false);
			ok.setRolloverIcon(new ImageIcon("icons/ok0.png"));
			ok.setBounds(203, 234, 40, 28);
			ok.setToolTipText("点我完成银行操作");
			ok.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Monopoly.music.button.play();
					Monopoly.bankDialog.setVisible(false);
					Game.playerPanel.repaint();
					Game.playerPanel.timerOfForward.start();
				}
				
			});
		}
	}
