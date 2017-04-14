package monopoly.building;

import monopoly.map.Cell;
import monopoly.player.Player;
import monopoly.ui.controller.UIController;
import monopoly.util.StrSrc;

/**
 * @author duocai
 * @date 2016年4月12日 上午11:29:43
 * all creatures in the map should extend this class
 */
public abstract class Creature {
	protected CreatureType type;
	protected Cell cell;
	public Cell getCell() {
		return cell;
	}
	
	public Creature(Cell cell,CreatureType type){
		this.cell = cell;
		this.type = type;
	}
	
	/**
	 * change the object to text
	 * @return
	 */
	public abstract String toTexture();
	
	/**
	 * get creatures's information
	 * @return
	 * 2016年4月27日 下午4:09:31
	 */
	public String getInfo() {
		return StrSrc.type.getValue() + ":" + type.getValue() + "\n";
	}
	
	/**
	 * execute the event happend here
	 * @param p
	 * @param map 
	 */
	public abstract void execute(Player p, UIController uiController);
	
	
	//get and setter methods
	public void setCell(Cell cell) {
		this.cell = cell;
	}

	public CreatureType getCreatureType() {
		return type;
	}	
}
