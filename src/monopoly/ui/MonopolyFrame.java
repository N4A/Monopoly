package monopoly.ui;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import monopoly.map.Map;
import monopoly.ui.controller.UIController;
import monopoly.util.StrSrc;

/**
 * @author duocai
 * @date 2016年5月31日 下午2:46:29
 */
@SuppressWarnings("serial")
public class MonopolyFrame extends JFrame {

	/**
	 * @throws HeadlessException
	 * 2016年5月31日 下午2:46:30
	 */
	public MonopolyFrame(boolean bool) throws HeadlessException {
		this.setTitle(StrSrc.title.getValue());
	    this.setLocation(250,100);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLayout(new BorderLayout());
	    this.setSize(739, 537);
	    this.setResizable(false);
	    this.setVisible(bool);	
	}
	
	public void initMenu(Map map, MapPanel mPanel) {
		JMenuBar jMenuBar = createMenuBar(map,mPanel);
		this.setJMenuBar(jMenuBar);
	}
	
	private UIController uiController;
	private MapPanel mapPanel;
	private JMenuBar createMenuBar(Map map, MapPanel mPanel) {
        JMenuBar menuBar;
        uiController = map.getUiController();
        mapPanel = mPanel; 
       
        //Create the menu bar.
        menuBar = new JMenuBar();
        //Build the file menu.
        JMenu file = new JMenu(StrSrc.file.getValue());
        menuBar.add(file);
        JMenuItem save = new JMenuItem(StrSrc.save.getValue());
        file.add(save);
        //sub menu load
        JMenu load = new JMenu(StrSrc.load.getValue());
        ImageIcon icon = new ImageIcon("icons/player00.png");
        JMenuItem data1 = new JMenuItem("2016-05-31", icon);
        load.add(data1);
        //add lod
        file.addSeparator();
        file.add(load);
        menuBar.add(file);
        
        //Build second menu in the menu bar.
        JMenu tool = new JMenu(StrSrc.tool.getValue());
        JMenuItem card = new JMenuItem(StrSrc.card.getValue(), new ImageIcon("icons/card.png"));
        card.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				uiController.showCards(mapPanel.getCurPlayer());
			}
		});
        tool.add(card);
        JMenuItem quit = new JMenuItem(StrSrc.giveUp.getValue(), new ImageIcon("icons/quit.png"));
        quit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mapPanel.getCurPlayer().giveUp();
				mPanel.nextPlayer();
				mapPanel.repaintMap();
				mapPanel.repaint();
			}
		});
        tool.add(quit);
        menuBar.add(tool);
        
        //feature
        JMenu feature = new JMenu(StrSrc.feature.getValue());
        JMenuItem stock = new JMenuItem(StrSrc.stock.getValue());
        stock.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				uiController.showStock(mapPanel.getCurPlayer());
			}
		});
        feature.add(stock);
        menuBar.add(feature);

        return menuBar;
    }
}
