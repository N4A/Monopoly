package monopoly.ui.controller;

import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import monopoly.feature.StockSystem;
import monopoly.map.Cell;
import monopoly.map.Map;
import monopoly.player.Player;
import monopoly.ui.MapPanel;
import monopoly.ui.MonopolyFrame;
import monopoly.ui.StockPanel;
import monopoly.ui.UseCardPanel;
import monopoly.ui.WelcomePanel;
import monopoly.util.DateUtil;
import monopoly.util.StrSrc;

/**
 * @author duocai
 * @date 2016年5月31日 下午2:43:26
 */
public class GUIController extends UIController {

	private MonopolyFrame monopolyFrame;
	private MapPanel mapPanel;

	/**
	 * @param dateUtil
	 * @param map
	 * @param originMap
	 *            2016年5月31日 下午2:43:26
	 */
	public GUIController(DateUtil dateUtil, Map map, Map originMap) {
		super(dateUtil, map, originMap);
		monopolyFrame = new MonopolyFrame(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see monopoly.ui.controller.UIController#welcome(java.util.List)
	 */
	@Override
	public void welcome(List<Player> players) {
		monopolyFrame.add(new WelcomePanel(players, map, this));
		monopolyFrame.setVisible(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see monopoly.ui.controller.UIController#showOperations(monopoly.player.
	 * Player)
	 */
	@Override
	public void showOperations(Player player) {
		monopolyFrame.initMenu(map, mapPanel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see monopoly.ui.controller.UIController#showMap(monopoly.map.Map)
	 */
	@Override
	public void showMap(Map map) {
		mapPanel = new MapPanel(map);
		monopolyFrame.add(mapPanel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * monopoly.ui.controller.UIController#movePlayer(monopoly.player.Player,
	 * int, monopoly.map.Cell)
	 */
	@Override
	public void movePlayer(Player player, int n, Cell dest) {
		mapPanel.movePlayer(player, n);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * monopoly.ui.controller.UIController#showCards(monopoly.player.Player)
	 */
	@Override
	public void showCards(Player player) {
		JDialog useCardDialog = new JDialog(monopolyFrame, StrSrc.card.getValue(), true);
		useCardDialog.setLocation(410, 230);
		useCardDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		useCardDialog.setSize(455, 325);
		useCardDialog.setResizable(false);
		useCardDialog.add(new UseCardPanel(map, player, mapPanel));
		useCardDialog.setVisible(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see monopoly.ui.controller.UIController#showStock(java.lang.String)
	 */
	@Override
	public void showStock(Player player) {
		StockSystem stockSystem = StockSystem.getStockSystem();
		JDialog stockDialog = new JDialog(monopolyFrame, StrSrc.stock.getValue(), true);
		stockDialog.add(new StockPanel(stockSystem, player, this, mapPanel));
		stockDialog.setSize(550, 655);
		stockDialog.setLocation(410, 230);
		stockDialog.setResizable(false);
		stockDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		stockDialog.setVisible(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see monopoly.ui.controller.UIController#showMassage(java.lang.String)
	 */
	@Override
	public void showMassage(String string) {
		JOptionPane.showMessageDialog(monopolyFrame, string, "", JOptionPane.INFORMATION_MESSAGE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see monopoly.ui.controller.UIController#getInt(int, int)
	 */
	@Override
	public int getInt(int start, int end) {
		while (true) {
			try {
				int ret = Integer.parseInt(JOptionPane.showInputDialog(StrSrc.input.getValue()));
				if (ret < start || ret > end) {
					showMassage(StrSrc.intRange.getValue() + "[" + start + "," + end + "].");
					continue;
				}
				return ret;
			} catch (Exception e) {
				showMassage(StrSrc.invalidInput.getValue() + "," + StrSrc.intNeed.getValue());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see monopoly.ui.controller.UIController#getString(int, int)
	 */
	@Override
	public String getString(int minLen, int maxLen) {
		while (true) {
			String ret = JOptionPane.showInputDialog(StrSrc.input.getValue());
			if (ret == null || ret.length() < minLen || ret.length() > maxLen) {
				showMassage(StrSrc.strLenRange.getValue() + "[" + minLen + "," + maxLen + "]");
				continue;
			}
			return ret;
		}
	}

	// not need to implement
	@Override
	public void showInfoNAway(Player player) {
		// show on map clearly
	}

	@Override
	public void showMoney(Player player) {
		// show on map clearly
	}

	@Override
	public void showWarning(Player player) {
		// show on map clearly
	}

	@Override
	public void operate(Player player) {
		//
	}

	@Override
	protected void initPlayers(List<Player> players) {
		// TODO Auto-generated method stub

	}
}
