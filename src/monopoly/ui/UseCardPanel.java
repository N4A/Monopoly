package monopoly.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import monopoly.card.Card;
import monopoly.map.Map;
import monopoly.player.Player;

/**
 * @author duocai
 * @date 2016年6月2日 下午9:19:01
 */
public class UseCardPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2668241591860341851L;
	private Map map;
	private Player player;
	private MapPanel mapPanel;

	Image image;
	ImageIcon useCard = new ImageIcon("icons/useCard.png");
	
	List<JButton> cards = new ArrayList<>();

	/**
	 * 
	 * 2016年6月2日 下午9:19:01
	 * @param mapPanel 
	 */
	public UseCardPanel(Map map, Player player, MapPanel mapPanel) {
		setLayout(null);
		image = useCard.getImage();
		this.map = map;
		this.player = player;
		this.mapPanel = mapPanel;
		
		addCard();
	}
	
	/**
	 * 绘制卡片
	 * 
	 * 2016年6月4日 下午5:32:08
	 */
	private void addCard(){
		List<List<Card>> cList = player.getCards();

		int i = 0;
		for (List<Card> list : cList) {
			Card card = list.get(0);
			JButton comp = card.toSwing();
			cards.add(comp);
			comp.setBounds(98 + (i / 4) * 100, 30 + (i % 4) * 60, 35, 52);
			comp.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					player.useCard(card, map, map.getUiController());
					
					//refresh the panel
					repaintCard();
					mapPanel.repaintMap();
					repaint();
				}
			});
			add(comp);
			i++;
		}
	}
	
	private void repaintCard() {
		for (JButton jButton : cards) {
			this.remove(jButton);
		}
		cards = new ArrayList<>();
		addCard();
	}

	/**
	 * 绘制卡片数量
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, 450, 300, this);
		
		g.setColor(Color.CYAN);
		g.setFont(new Font("hello",Font.ITALIC,20));
		//draw amount of cards
		int i = 0;
		for (List<Card> list : player.getCards()) {
			int amount = list.size();
			int x = (i/4)*100 + 145;
			int y = (i%4)*60 + 70;
			g.drawString("X" + " " + amount, x, y);
			i++;
		}
	}

}
