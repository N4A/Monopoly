package monopoly.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

import monopoly.feature.StockSystem;
import monopoly.feature.StockSystem.Stock;
import monopoly.player.Player;
import monopoly.ui.controller.UIController;
import monopoly.util.DateUtil;
import monopoly.util.StrSrc;

/**
 * @author duocai
 * @date 2016年6月3日 下午2:47:12
 */
public class StockPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5166848319279934975L;

	private JTable stockTatble;//股票表格，声明全局因为要实现重新绘制
	private StatusPanel statusPanel;//存放单个股票状态图
	private JScrollPane tablePane = new JScrollPane();//存放股票表格

	private StockSystem stockSystem;
	private UIController uiController;
	private MapPanel mapPanel;

	/**
	 * 
	 * 2016年6月3日 下午2:47:12
	 */
	public StockPanel(StockSystem stockSystem, Player player, UIController uiController, MapPanel mapPanel) {
		this.stockSystem = stockSystem;
		this.uiController = uiController;
		this.mapPanel = mapPanel;

		initTable(player, uiController);

		// initialize status of each stock
		statusPanel = new StatusPanel(stockSystem.getStock(0));
		JScrollPane scrollPane2 = new JScrollPane(statusPanel);
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollPane2, tablePane);

		this.add(splitPane);
	}

	/**
	 * 初始化表格
	 * @param player
	 * @param uiController
	 * 2016年6月4日 下午5:33:13
	 */
	private void initTable(Player player, UIController uiController) {
		stockTatble = new NoEditTable(stockSystem.getStockDetial(player), stockSystem.getStockClum());
		tablePane.setViewportView(stockTatble);
		stockTatble.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int count = e.getClickCount();
				int row = ((JTable) e.getSource()).getSelectedRow();
				if (count == 1) {
					statusPanel.setStock(stockSystem.getStock(row));
					statusPanel.repaint();
				} else if (count == 2) {
					DateUtil dateUtil = DateUtil.getInstance();
					int weekOfDay = dateUtil.getDayOfWeek();

					// during the weekend, the stock will not change
					if (weekOfDay != 1 && weekOfDay != 7) {
						handleStocks(player, row);
					} else {
						uiController.showMassage(StrSrc.stockWeekendNotice.getValue());
					}
				}
			}
		});

	}

	/**
	 * 股票交易
	 * 
	 * @param player
	 * @param uiController
	 * @param stcok
	 *            2016年6月4日 上午10:52:44
	 */
	private void handleStocks(Player player, int stcok) {
		String title = StrSrc.stock.getValue();
		String message = StrSrc.choose.getValue();
		String[] options = { StrSrc.buy.getValue(), StrSrc.sell.getValue(), StrSrc.cancel.getValue() };
		int messageType = JOptionPane.QUESTION_MESSAGE;
		// 由于我们的optionType设置成JOptionPane.YES_NO_CANCEL_OPTION,因此对话框中会有三个按钮.我们在options
		// 的String Arrray中设置这三个按钮的名称,并以options[0]按钮为默认值,若将options参数设为null,系统会原来
		// 的按钮名称来显示.
		int optionType = JOptionPane.YES_NO_CANCEL_OPTION;

		int result = JOptionPane.showOptionDialog(this, message, title, optionType, messageType, null, options,
				options[0]);

		switch (result) {
		case JOptionPane.YES_OPTION:// buy
			int num = getNumber(0, 1000);
			stockSystem.playerBuyStock(stcok, num, player, uiController);
			repaintTable(player, uiController);
			mapPanel.repaint();
			break;
		case JOptionPane.NO_OPTION:// sell
			int num1 = getNumber(0, 1000);
			stockSystem.playerSellStock(stcok, num1, player, uiController);
			repaintTable(player, uiController);
			mapPanel.repaint();
			break;
		default: // close or cancel
			break;
		}
	}

	/**
	 * 交易股票数
	 * 
	 * @param min
	 * @param max
	 * @return 2016年6月4日 上午11:10:21
	 */
	private int getNumber(int min, int max) {
		while (true) {
			try {
				int ret = Integer.parseInt(JOptionPane.showInputDialog(this, StrSrc.inputNum.getValue()));
				if (ret < min || ret > max) {
					uiController.showMassage(StrSrc.intRange.getValue() + "[" + min + "," + max + "].");
					continue;
				}
				return ret;
			} catch (Exception e) {
				uiController.showMassage(StrSrc.invalidInput.getValue() + "," + StrSrc.intNeed.getValue());
			}
		}
	}

	private void repaintTable(Player player, UIController uiController) {
		tablePane.remove(stockTatble);
		initTable(player, uiController);
	}

	/**
	 * 实现股票状态图
	 * @author duocai
	 * @date 2016年6月4日 下午5:32:44
	 */
	private class StatusPanel extends JPanel {

		private final int size = 7;
		private int[] x = new int[size], y = new int[size];

		private Stock stock;

		private int[] origin = new int[2];
		private int gridWidth;
		private int gridHeight;
		private double startPoint;
		private double endPoint;
		private double dataDis;

		/**
		 * 
		 */
		private static final long serialVersionUID = 967702707765424317L;

		public StatusPanel(Stock stock) {
			setPreferredSize(new Dimension(500, 410));
			this.stock = stock;
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			init();
			// 坐标轴
			g.drawLine(origin[0], origin[1], 30, 30);
			g.drawString(StrSrc.price.getValue(), 30, 30);
			g.drawLine(origin[0], origin[1], getWidth() - 30, getHeight() - 30);
			g.drawString(StrSrc.date.getValue(), getWidth() - 30, getHeight() - 30);

			// 画方格线
			for (int i = 0; i < 7; i++) {
				g.setFont(new Font(null, Font.ITALIC, 1));
				g.drawLine(origin[0] + (i + 1) * gridWidth, origin[1], origin[0] + (i + 1) * gridWidth, 30);
				g.setFont(new Font(null, Font.ITALIC, 20));
				g.drawString(i + "", origin[0] + i * gridWidth, origin[1] + 25);
			}
			g.drawString(7 + "", origin[0] + 7 * gridWidth, origin[1] + 25);
			for (int i = 0; i < 10; i++) {
				g.setFont(new Font(null, Font.ITALIC, 1));
				g.drawLine(origin[0], origin[1] - (i + 1) * gridHeight, getWidth() - 30,
						origin[1] - (i + 1) * gridHeight);
				g.setFont(new Font(null, Font.ITALIC, 10));
				g.drawString(((startPoint + dataDis * i) + "").substring(0, 4), 0, origin[1] - i * gridHeight);
			}
			g.drawString(((startPoint + dataDis * 10) + "").substring(0, 4), 0, origin[1] - 10 * gridHeight);

			// 画数据线
			g.setFont(new Font(null, Font.BOLD, 10));
			g.setColor(Color.blue);
			if (stock.getRecordSize() > size)
				g.drawPolyline(x, y, size);
			else
				g.drawPolyline(x, y, stock.getRecordSize());

			// 画股票名
			g.setFont(new Font(null, Font.BOLD, 20));
			g.setColor(Color.black);
			g.drawString(stock.getName(), getWidth() / 2, 25);
		}

		/**
		 * 
		 * initialize the info of the panel according to current stock 2016年6月4日
		 * 上午10:03:19
		 */
		private void init() {
			origin[0] = 30;
			origin[1] = getHeight() - 30;

			int size = this.stock.getRecordSize();
			startPoint = endPoint = stock.getRecord(size - 1);
			for (int i = size - 1; i >= 0 && size - i <= this.size + 1; i--) {
				double data = stock.getRecord(i);
				if (data < startPoint) {
					startPoint = data;
				}

				if (data > endPoint) {
					endPoint = data;
				}
			}

			gridWidth = (getWidth() - 60) / this.size;
			gridHeight = (origin[1] - 30) / 10;
			dataDis = (endPoint - startPoint) / 10;
			if (size > this.size) {

				int i = size - 1;
				for (int j = this.size - 1; j >= 0; j--) {
					x[j] = origin[0] + j * gridWidth;
					double data = stock.getRecord(i);
					y[j] = origin[1] - (int) ((data - startPoint) / dataDis * gridHeight);
					i--;
				}
			} else {
				for (int i = 0; i < size; i++) {
					x[i] = origin[0] + i * gridWidth;
					double data = stock.getRecord(i);
					y[i] = origin[1] - (int) ((data - startPoint) / dataDis * gridHeight);
				}
			}

		}

		public void setStock(Stock stock) {
			this.stock = stock;
		}
	}

	private class NoEditTable extends JTable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 4232404934251700375L;

		public NoEditTable(Object[][] stockDetial, String[] stockClum) {
			super(stockDetial, stockClum);
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	}
}
