
package covid19.game;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
// import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;



public class BackgroundMP3Player {

	private AdvancedPlayer player;
	private boolean loop;
	private File file;

	public BackgroundMP3Player(String filename) throws FileNotFoundException, JavaLayerException {
		file = new File(filename);
	}

	public void circularPlay() {
		System.out.println("play music");
		Thread currentThread;
		// continuously run in new thread to play in background
		this.loop = true;
		currentThread = new Thread() {
			@Override
			public void run() {
				try {
					do {
						playOnce();
					} while (loop);
				} catch (Exception e) {
					throw new RuntimeException(e.getMessage());
				}
			}
		};
		currentThread.start();
	}

	public void play() {
		Thread currentThread;
		// run in new thread to play in background
		currentThread = new Thread() {
			@Override
			public void run() {
				try {
					playOnce();
				} catch (Exception e) {
					throw new RuntimeException(e.getMessage());
				}
			}
		};
		currentThread.start();
	}

	private void playOnce() throws FileNotFoundException, JavaLayerException {
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		player = new AdvancedPlayer(bis);
		player.play();
	}

	// To intentionally terminate the player 
	public void close() {
		System.out.println("close musice");
		this.loop = false;
		player.close();
	}

	
}
