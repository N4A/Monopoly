import java.awt.*;

import javax.swing.*;

public class MonopolyFrame extends JFrame{
	public MonopolyFrame() {
		this.setLayout(new BorderLayout());
		//add�̳���MapPanel��playerPanel������Ϸ������
		this.add(Game.playerPanel,BorderLayout.CENTER);
		this.setTitle("���̡���Ⱥɽ���Ƶĺ�^_^");
	    this.setLocation(250,100);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setSize(739, 535);
	    this.setResizable(false);    
	}	
}
