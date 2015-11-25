import java.applet.*;
import java.io.File;
import javax.swing.JApplet;
//“Ù¿÷‘¥
public class Music extends JApplet {
	AudioClip backMusic;
	AudioClip backMusic1;
	AudioClip button;
	AudioClip dice;
	@SuppressWarnings("deprecation")
	public Music() {
		 try {
			 backMusic = newAudioClip( (new File("music/backMusic.wav")).toURL());
			 backMusic1 = newAudioClip( (new File("music/backMusic.wav")).toURL());
			 button = newAudioClip( (new File("music/button.wav")).toURL());
			 dice = newAudioClip( (new File("music/dice.wav")).toURL());
		 }
		 catch (Exception ex) {
			 ex.printStackTrace();
		 }
	}
}
