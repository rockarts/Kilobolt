import java.io.FileInputStream;
import java.io.IOException;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;
        
public class Music {
	
	public void startBGMusic() {
		AudioPlayer MGP = AudioPlayer.player;
		AudioStream BGM;
		AudioData MD;
		ContinuousAudioDataStream loop = null;
		try{
		BGM = new AudioStream(new FileInputStream("/Users/stevenrockarts/development/workspace/Kilobolt/src/data/music.wav"));
		MD = BGM.getData();
		loop = new ContinuousAudioDataStream(MD);
		}catch(IOException error){
		System.out.print("file not found");
		}
		MGP.start(loop);
    }
	
}
