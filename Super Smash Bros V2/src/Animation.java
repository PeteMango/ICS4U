import java.io.File;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Animation {
	//state variables
	
	static Animation fake;
	static Animation fake2;
	
	private ArrayList<Image> frames = new ArrayList<>();//list of frames that make up the animation
	private int Currentframe = 0;//The current frame
	private int frameWait;//frames to wait in between frames
	
	public Animation() {
		
	}
	
	/**
	 * @function create an animation (just a series of images/frames)
	 * @param frames list of frames in this animation
	 * @param frameWait time to wait in between frames
	 */
	
	public Animation(ArrayList<Image> frames,int frameWait) {
		this.frameWait = frameWait;
		this.frames = frames;
	}
	
	/**
	 * @function read files to create an animation
	 * @param directory directory to read the files to create this animation from
	 * @param frameWait the amount of frames to skip in between frames
	 */
	public Animation(String directory, int frameWait) {
		this.frameWait = frameWait;
		directory+="/";
		File folder = new File(directory);
		
		ArrayList<Image> images = new ArrayList<>();
		int frameCount = folder.listFiles().length;
		for(int i=1;i<=frameCount;i++) {
			File frame = new File(directory+i+".png");
			Image frameImage = new Image(frame.toURI().toString());
			images.add(frameImage);
		}
		
		frames = images;
	}
	
	static void makeFake() {
		File fakeImage = new File("restingLeft/1.png");
		File fakeImage2 = new File("restingLeft/2.png");
		Image image = new Image(fakeImage.toURI().toString());
		Image image2 = new Image(fakeImage2.toURI().toString());
		ArrayList<Image> i = new ArrayList<>();
    	i.add(image);
    	i.add(image2);
    	Animation fake = new Animation(i,60);
    	//e.out.println(fake);
    	fake.fake = fake;
	}
	
	static void makeFake2() {
		File fakeImage = new File("restingRight/1.png");
		File fakeImage2 = new File("restingRight/2.png");
		Image image = new Image(fakeImage.toURI().toString());
		Image image2 = new Image(fakeImage2.toURI().toString());
		ArrayList<Image> i = new ArrayList<>();
    	i.add(image);
    	i.add(image2);
    	Animation fake = new Animation(i,60);
    	//e.out.println(fake);
    	fake.fake2 = fake;
	}
	
	/**
	 * @function restart animation from first frame
	 */
	public void reset() {
		Currentframe=0;
	}
	
	/**
	 * @function advance the animation by one frame
	 * @return the next frame in this animation
	 */
	public Image nextFrame() {
		//if the animation hasn't ended, get the next frame in the sequence 
		if((double)Currentframe/(double)frameWait<frames.size()) {
			Currentframe++;//increment frame counter
			return frames.get((Currentframe-1)/frameWait);//get next frame
		}
		//return null upon end
		return null;
	}
}
