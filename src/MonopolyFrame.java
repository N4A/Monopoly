import java.awt.*;

import javax.swing.*;

public class MonopolyFrame extends JFrame{
	public MonopolyFrame() {
		this.setLayout(new BorderLayout());
		//add继承了MapPanel的playerPanel，是游戏主界面
		this.add(Game.playerPanel,BorderLayout.CENTER);
		this.setTitle("大富翁——群山环绕的湖^_^");
	    this.setLocation(250,100);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setSize(739, 535);
	    this.setResizable(false);    
	}	
}
