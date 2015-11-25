import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

//几张卡片函数
public class Card {
	public void average() {
		if (Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[0] > 0) {
			int totalCash = 0;
			for (int i = 0; i < Monopoly.totalAmount; i++) {
				totalCash += Monopoly.players[i].cash;
			}
			Monopoly.players[0].cash = Monopoly.players[1%Monopoly.totalAmount].cash = Monopoly.players[2%Monopoly.totalAmount].cash = totalCash/Monopoly.totalAmount;
			Game.playerPanel.repaint();
			Monopoly.useCardDialog.setVisible(false);
			Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[0]--;
		}
		else
			JOptionPane.showMessageDialog(Game.playerPanel, "没有该卡了", "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[Game.playerPanel.currentPlayer].image));
	}
	public void changeDirection() {
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
			JOptionPane.showMessageDialog(Game.playerPanel, "没有该卡了", "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[Game.playerPanel.currentPlayer].image));					
	}
	public void barricades() {
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
			JOptionPane.showMessageDialog(Game.playerPanel, "没有该卡了", "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[Game.playerPanel.currentPlayer].image));
	}
	public void controlDice() {
		if (Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[3] > 0) {
			Game.isControl = true;
			Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[3]--;
			Monopoly.useCardDialog.setVisible(false);
			Monopoly.diceDialog.setVisible(true);
		}
		else
			JOptionPane.showMessageDialog(Game.playerPanel, "没有该卡了", "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[Game.playerPanel.currentPlayer].image));				
	}
	public void rob() {
		if (Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[4] > 0) {
			for (int i = 0; i < Monopoly.totalAmount; i++) {
				MapPanel.rob[Monopoly.players[i].positonOfPlayer].setIcon(new ImageIcon(Monopoly.players[i].smallImage));
				MapPanel.rob[Monopoly.players[i].positonOfPlayer].setVisible(true);
			}
			MapPanel.rob[Monopoly.players[Game.playerPanel.currentPlayer].positonOfPlayer].setVisible(false);
			Game.playerPanel.repaint();
			Monopoly.useCardDialog.setVisible(false);
			Monopoly.players[Game.playerPanel.currentPlayer].numberOfcard[4]--;
		}
		else
			JOptionPane.showMessageDialog(Game.playerPanel, "没有该卡了", "WARNING",JOptionPane.WARNING_MESSAGE, new ImageIcon(Monopoly.players[Game.playerPanel.currentPlayer].image));
	
	}
}
