package monopoly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

import monopoly.building.Bank;
import monopoly.building.Blank;
import monopoly.building.CardStation;
import monopoly.building.CouponStaion;
import monopoly.building.Hospital;
import monopoly.building.Land;
import monopoly.building.LandType;
import monopoly.building.LotteryStore;
import monopoly.building.News;
import monopoly.building.ToolStore;
import monopoly.map.*;
import monopoly.player.Player;
import monopoly.ui.controller.CMDController;
import monopoly.ui.controller.GUIController;
import monopoly.ui.controller.UIController;
import monopoly.util.DateUtil;

/**
 * @author duocai
 * @date 2016年4月12日 上午11:31:42
 */
public class Monopoly {
	
	public static void main(String[] args) throws IOException {
		
		//init
		Monopoly monopoly = new Monopoly();
		DateUtil dateUtil = DateUtil.getInstance();
		//dateUtil.set(2016, 5, 2);
		Map map = monopoly.buildMap(0);//init map
		Map originMap = monopoly.buildMap(0);//init origin map and it will not change any more
		//UIController ctrl = new CMDController(dateUtil, map, originMap);//init cmd ui controller
		UIController ctrl = new GUIController(dateUtil, map, originMap);//init gui controller
		map.setUiController(ctrl);//set ui controller
		List<Player> players = map.getPlayers();//copyOnWriteArraylist is concurrent safe
		ctrl.welcome(players);//init players and start game
	}
	
	
	/**
	 * make map
	 * @throws IOException 
	 */
	private Map buildMap(int level) throws IOException {
		BufferedReader reader = new BufferedReader(new StringReader(maps.get(level)));
		String[] dimension = reader.readLine().split(" ");
		Map map = new Map(Integer.parseInt(dimension[0]),Integer.parseInt(dimension[1]));
		
		int number = Integer.parseInt(dimension[2]);
		for (int i = 0; i < number; i++) {
			dimension = reader.readLine().split(" ");
			int x = Integer.parseInt(dimension[1]);
			int y = Integer.parseInt(dimension[2]);
			
			char creature = dimension[0].trim().charAt(0);
			Cell curCell = map.getCellAt(x, y);
			switch(creature){
			case '空':curCell.addCreature(new Land(curCell,LandType.none)); break;
			case '　':curCell.addCreature(new Blank(curCell)); break;
			case '◎':curCell.addCreature(new Land(curCell,LandType.onSell)); break;
			case '新':curCell.addCreature(new News(curCell)); break;
			case '卡':curCell.addCreature(new CardStation(curCell)); break;
			case '券':curCell.addCreature(new CouponStaion(curCell)); break;
			case '银':curCell.addCreature(new Bank(curCell));break;
			case '道':curCell.addCreature(new ToolStore(curCell));break;
			case '彩':curCell.addCreature(new LotteryStore(curCell));break;
			case '医':{
				Hospital hospital = new Hospital(curCell);
				curCell.addCreature(hospital);
				map.setHospital(hospital);
				break;
			}
			}
			
			//add current cell into eventCells which will trigger event
			if(creature !='　')
				map.addEventCell(curCell);
		}
		
		return map;
	}
	
	/**
	 * map
	 */
	private List<String> maps = Arrays.asList(
			"11 10 48\n"
			+ "◎ 0 0\n"
			+ "◎ 0 1\n"
			+ "◎ 0 2\n"
			+ "医 0 3\n"
			+ "新 1 3\n"
			+ "卡 2 3\n"
			+ "◎ 3 3\n"
			+ "◎ 3 4\n"
			+ "◎ 3 5\n"
			+ "◎ 3 6\n"
			+ "◎ 3 7\n"
			+ "券 2 7\n"
			+ "卡 1 7\n"
			+ "空 0 7\n"
			+ "◎ 0 8\n"
			+ "◎ 0 9\n"
			+ "◎ 0 10\n"
			+ "◎ 1 10\n"
			+ "◎ 2 10\n"
			+ "彩 3 10\n"
			+ "道 4 10\n"
			+ "银 5 10\n"
			+ "新 6 10\n"
			+ "◎ 7 10\n"
			+ "◎ 8 10\n"
			+ "◎ 9 10\n"
			+ "◎ 9 9\n"
			+ "◎ 9 8\n"
			+ "空 9 7\n"
			+ "新 8 7\n"
			+ "◎ 7 7\n"
			+ "◎ 7 6\n"
			+ "◎ 7 5\n"
			+ "◎ 7 4\n"
			+ "◎ 7 3\n"
			+ "券 8 3\n"
			+ "空 9 3\n"
			+ "◎ 9 2\n"
			+ "◎ 9 1\n"
			+ "◎ 9 0\n"
			+ "◎ 8 0\n"
			+ "◎ 7 0\n"
			+ "彩 6 0\n"
			+ "道 5 0\n"
			+ "银 4 0\n"
			+ "新 3 0\n"
			+ "◎ 2 0\n"
			+ "◎ 1 0\n"
			);
}


