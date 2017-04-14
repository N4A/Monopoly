package monopoly.util;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * @author duocai
 * @date 2016年6月1日 下午5:11:41
 */
public class ImageSrc {
	//player photos
	public final static Image[] photos = {new ImageIcon("icons/player0.png").getImage(),
			new ImageIcon("icons/player1.png").getImage(),
			new ImageIcon("icons/player2.png").getImage(),
	};
		
	// walk photos of player
	public final static ImageIcon[][] playerWalkIcons = {
			new ImageIcon[] { new ImageIcon("icons/player0_0.png"), new ImageIcon("icons/player0_1.png"),
					new ImageIcon("icons/player0_2.png"), new ImageIcon("icons/player0_3.png"),
					new ImageIcon("icons/player0_4.png"), new ImageIcon("icons/player0_5.png"),
					new ImageIcon("icons/player0_6.png"), new ImageIcon("icons/player0_7.png"),
					new ImageIcon("icons/player0_8.png"), new ImageIcon("icons/player0_9.png"),
					new ImageIcon("icons/player0_10.png"), new ImageIcon("icons/player0_11.png") },
			{ new ImageIcon("icons/player1_0.png"), new ImageIcon("icons/player1_1.png"),
					new ImageIcon("icons/player1_2.png"), new ImageIcon("icons/player1_3.png"),
					new ImageIcon("icons/player1_4.png"), new ImageIcon("icons/player1_5.png"),
					new ImageIcon("icons/player1_6.png"), new ImageIcon("icons/player1_7.png"),
					new ImageIcon("icons/player1_8.png"), new ImageIcon("icons/player1_9.png"),
					new ImageIcon("icons/player1_10.png"), new ImageIcon("icons/player1_11.png") },
			{ new ImageIcon("icons/player2_0.png"), new ImageIcon("icons/player2_1.png"),
					new ImageIcon("icons/player2_2.png"), new ImageIcon("icons/player2_3.png"),
					new ImageIcon("icons/player2_4.png"), new ImageIcon("icons/player2_5.png"),
					new ImageIcon("icons/player2_6.png"), new ImageIcon("icons/player2_7.png"),
					new ImageIcon("icons/player2_8.png"), new ImageIcon("icons/player2_9.png"),
					new ImageIcon("icons/player2_10.png"), new ImageIcon("icons/player2_11.png") } };
	
	//land icon
	public final static ImageIcon[][] landIcons = {
			new ImageIcon[] {new ImageIcon("icons/owner00.png"),new ImageIcon("icons/owner01.png"),
					new ImageIcon("icons/owner02.png"),new ImageIcon("icons/owner03.png"),
					new ImageIcon("icons/owner04.png"),new ImageIcon("icons/owner05.png"),},
			new ImageIcon[] {new ImageIcon("icons/owner10.png"),new ImageIcon("icons/owner11.png"),
					new ImageIcon("icons/owner12.png"),new ImageIcon("icons/owner13.png"),
					new ImageIcon("icons/owner14.png"),new ImageIcon("icons/owner15.png"),},
			new ImageIcon[] {new ImageIcon("icons/owner20.png"),new ImageIcon("icons/owner21.png"),
					new ImageIcon("icons/owner22.png"),new ImageIcon("icons/owner23.png"),
					new ImageIcon("icons/owner24.png"),new ImageIcon("icons/owner25.png"),}
	};
	
}
