package monopoly.player;

import java.awt.Image;

import javax.swing.ImageIcon;

import monopoly.util.ImageSrc;
import monopoly.util.StrSrc;

/**
 * @author duocai
 * @date 2016年4月19日 上午9:15:02
 */
public enum PlayerType {
	
	P1(StrSrc.player1.getValue(),StrSrc.p1.getValue(),ImageSrc.playerWalkIcons[0],ImageSrc.photos[0]),
	P2(StrSrc.player2.getValue(),StrSrc.p2.getValue(),ImageSrc.playerWalkIcons[1],ImageSrc.photos[1]),
	P3(StrSrc.player3.getValue(),StrSrc.p3.getValue(),ImageSrc.playerWalkIcons[2],ImageSrc.photos[2]),
	//no more resources
	P4(StrSrc.player4.getValue(),StrSrc.p1.getValue(),ImageSrc.playerWalkIcons[0],ImageSrc.photos[0]);
	
	private String value;
	private String name;
	private ImageIcon[] walkIcon;
	private Image image;
	
	private PlayerType(String value, String name, ImageIcon[] walk, Image image) {
		this.value = value;
		this.name = name;
		this.image = image;
		this.walkIcon = walk;
		
	}
	
	public String getValue() {
		return this.value;
	}

	public String getName() {
		return name;
	}
	public Image getImage() {
		return image;
	}
	
	public ImageIcon[] getPlayerWalkIcon() {
		return walkIcon;
	}
}
