package monopoly.building;

import javax.swing.ImageIcon;

import monopoly.util.ImageSrc;
import monopoly.util.StrSrc;

/**
 * @author duocai
 * @date 2016年4月18日 下午9:25:28
 */
public enum LandType {
	onSell(StrSrc.landOnsell.getValue(),null),
	none(StrSrc.landNone.getValue(),null),
	p1(StrSrc.landP1.getValue(),ImageSrc.landIcons[0]),
	p2(StrSrc.landP2.getValue(),ImageSrc.landIcons[1]),
	p3(StrSrc.landP3.getValue(),ImageSrc.landIcons[2]),
	p4(StrSrc.landP4.getValue(),ImageSrc.landIcons[0]);
	
	private String value;
	private ImageIcon[] icons;
	
	private LandType(String value, ImageIcon[] icons){
		this.value = value;
		this.icons = icons;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public ImageIcon getIcon(int i) {
		if (icons == null)
			return null;
		if (i < icons.length)
			return icons[i];
		return null;
	}
}
