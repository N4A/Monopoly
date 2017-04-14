package monopoly.map;

import java.util.ArrayList;
import java.util.List;

import monopoly.building.Blank;
import monopoly.building.Creature;
import monopoly.player.Player;
import monopoly.util.StrSrc;

/**
 * @author duocai
 * @date 2016年4月18日 下午9:21:10
 */
public class Cell {
	private int x;
	private int y;
	private Map map;
	private String name;
	private List<Creature> creatures = new ArrayList<Creature>();
	private final String[] cellNames = { StrSrc.cell1.getValue(), StrSrc.cell2.getValue(), StrSrc.cell3.getValue(),
			StrSrc.cell14.getValue(), StrSrc.cell25.getValue(), StrSrc.cell36.getValue(), StrSrc.cell4.getValue(),
			StrSrc.cell15.getValue(), StrSrc.cell26.getValue(), StrSrc.cell37.getValue(), StrSrc.cell5.getValue(),
			StrSrc.cell16.getValue(), StrSrc.cell27.getValue(), StrSrc.cell38.getValue(), StrSrc.cell6.getValue(),
			StrSrc.cell17.getValue(), StrSrc.cell28.getValue(), StrSrc.cell39.getValue(), StrSrc.cell7.getValue(),
			StrSrc.cell18.getValue(), StrSrc.cell29.getValue(), StrSrc.cell40.getValue(), StrSrc.cell8.getValue(),
			StrSrc.cell19.getValue(), StrSrc.cell30.getValue(), StrSrc.cell41.getValue(), StrSrc.cell9.getValue(),
			StrSrc.cell20.getValue(), StrSrc.cell31.getValue(), StrSrc.cell42.getValue(), StrSrc.cell10.getValue(),
			StrSrc.cell21.getValue(), StrSrc.cell32.getValue(), StrSrc.cell43.getValue(), StrSrc.cell11.getValue(),
			StrSrc.cell22.getValue(), StrSrc.cell33.getValue(), StrSrc.cell44.getValue(), StrSrc.cell12.getValue(),
			StrSrc.cell23.getValue(), StrSrc.cell34.getValue(), StrSrc.cell45.getValue(), StrSrc.cell13.getValue(),
			StrSrc.cell24.getValue(), StrSrc.cell35.getValue(), StrSrc.cell46.getValue(), StrSrc.cell47.getValue(),
			StrSrc.cell50.getValue(), StrSrc.cell53.getValue(), StrSrc.cell56.getValue(), StrSrc.cell48.getValue(),
			StrSrc.cell51.getValue(), StrSrc.cell54.getValue(), StrSrc.cell57.getValue(), StrSrc.cell49.getValue(),
			StrSrc.cell52.getValue(), StrSrc.cell55.getValue(), StrSrc.cell58.getValue(), StrSrc.cell59.getValue(),
			StrSrc.cell60.getValue() };
	private final int nameLen = 60;
	private final Blank BLANK = new Blank(this);

	public Cell(Map map, int x, int y, String name) {
		super();
		this.x = x;
		this.y = y;
		this.map = map;
		this.name = name;
	}

	public Cell(Map map, int x, int y) {
		super();
		this.x = x;
		this.y = y;
		this.map = map;
		this.name = cellNames[(x * map.getWidth() + y) % nameLen];
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	/**
	 * get cell which is n steps after this cell
	 * 
	 * @param n
	 * @return 2016年4月21日 上午8:26:28
	 */
	public Cell getCellAt(int n) {
		return map.getCellAt(this, n);
	}

	public void addCreature(Creature creature) {
		creatures.add(creature);
	}

	public void rmCreature(Creature creature) {
		creatures.remove(creature);
	}

	/**
	 * get i-th creature
	 * 
	 * @param i
	 * @return 2016年4月21日 上午9:48:18
	 */
	public Creature getCreature(int i) {
		if (creatures.isEmpty()) {
			return BLANK;
		}
		return creatures.get(i);
	}

	// return the last creature string
	public String toTexture() {
		if (creatures.isEmpty()) {
			return BLANK.toTexture();
		}
		return creatures.get(creatures.size() - 1).toTexture();
	}

	/**
	 * this cell handle the event which is triggered by parameter player
	 * happened in it 2016年4月21日 上午8:41:34
	 */
	public void handleEvent(Player player) {
		for (int i = creatures.size() - 1; i >= 0; i--) {
			if (hasCreature(player)) {
				getCreature(i).execute(player, map.getUiController());
			}
		}
	}

	private boolean hasCreature(Creature creature) {
		for (Creature c : creatures) {
			if (c == creature) {
				return true;
			}
		}
		return false;
	}

	public String getName() {
		return name;
	}
	
	public List<Creature> getCreatures() {
		return creatures;
	}

	public String getInfo() {
		StringBuffer sb = new StringBuffer();
		sb.append(StrSrc.name.getValue() + ":" + name + ";\n");
		sb.append(getCreature(0).getInfo() + "\n");
		return sb.toString();
	}
}
