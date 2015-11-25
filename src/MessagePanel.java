import java.awt.*;
import javax.swing.*;
//显示AI操作的弹窗界面
public class MessagePanel extends JPanel{
	ImageIcon backGroundIcon = new ImageIcon("icons/backGround.png");
	Image backGround = backGroundIcon.getImage();
	String[] str = new String[]{"","","","",""};
	public MessagePanel() {
		
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backGround, 0, 0, 300, 200, this);
		g.setColor(Color.CYAN);
		g.setFont(Game.useCardFrontPanel.font);
		g.drawString(str[0], 50, 50);
		g.drawString(str[1], 50, 80);
		g.drawString(str[2], 50, 110);
		g.drawString(str[3], 50, 140);
		g.drawString(str[4], 50, 180);
	}	
}
