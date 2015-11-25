import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

//public class EventFrame extends JFrame {
//	public EventFrame() {
//		this.setTitle("大富翁——群山环绕的死水湖");
//	    add(Game.eventFrontPanel);
//		this.setLocation(410,230);
//	    this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
//	    this.setSize(350, 260);
//	    this.setResizable(false);
//	    this.setVisible(false);	 
//	}
//}
class backPanel extends JPanel {
	Image image;
	ImageIcon monopolyIcon = new ImageIcon("icons/buyLand.png");
	public backPanel() {
		image = monopolyIcon.getImage();
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, 345, 250, this);
	}
		
}
//实现土地购买和升级窗口
public class frontPanel extends backPanel {
	ImageIcon playerIcon  = new ImageIcon("icons/player0.png");
	Image player = playerIcon.getImage();
	ImageIcon houseIcon = new ImageIcon("icons/land.png");
	ImageIcon yesIcon = new ImageIcon("icons/yes.png");
	ImageIcon noIcon = new ImageIcon("icons/no.png");
	
	JButton house = new JButton(houseIcon);
	JButton yes = new JButton(yesIcon);
	JButton no = new JButton(noIcon);
	public frontPanel() {
		setLayout(null);
		add(house);
		house.setBounds(40, 170, 28, 28);
		house.setBorderPainted(false);
		add(yes);
		yes.setBounds(191, 192, yesIcon.getIconWidth(), yesIcon.getIconHeight());
		yes.setBorderPainted(false);
		yes.setRolloverIcon(new ImageIcon("icons/yes0.png"));
		yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Monopoly.music.button.play();
				//传参是当前玩家和其位置
				if (MapPanel.ownerOfLand[Monopoly.players[Game.playerPanel.currentPlayer].positonOfPlayer] == 3)
					Monopoly.land.buyLand(Game.playerPanel.currentPlayer,Monopoly.players[Game.playerPanel.currentPlayer].positonOfPlayer);
				else
					Monopoly.land.upLand(Game.playerPanel.currentPlayer,Monopoly.players[Game.playerPanel.currentPlayer].positonOfPlayer);
			}
			
		});
		add(no);
		no.setBorderPainted(false);
		no.setRolloverIcon(new ImageIcon("icons/no0.png"));
		no.setBounds(310, 0, noIcon.getIconWidth(), noIcon.getIconHeight());
		no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Monopoly.music.button.play();
				Monopoly.eventDialog.setVisible(false);
			}	
		});
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(player, 20, 80, 80, 80, this);
	}
}
