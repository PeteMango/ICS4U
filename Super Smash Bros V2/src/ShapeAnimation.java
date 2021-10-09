package app;
import java.util.ArrayList;
import javafx.scene.Node;

public abstract class ShapeAnimation {
	//state variables
	private double elapsedFrames = 0;//how far the animation is into its runtime
	private double frameIncrementor;//how much the elapsedframes is incremented by each frame
	private double endFrames;//elapsedframes runtime to end animation at
	
	/**
	 * 
	 * @param frameIncrementor animation speed
	 * @param endFrames time to end animation at
	 */
	public ShapeAnimation(double frameIncrementor, double endFrames) {
		this.frameIncrementor = frameIncrementor;
		this.endFrames = endFrames;
	}
	
	/**
	 * @function all functions the animation needs to perform every frame to keep running
	 * @param elapsedFrames the runtime into the animation we are currently at
	 * @return a list of nodes to be displayed on screen this frame, null if animation has ended
	 */
	public abstract ArrayList<Node> nextFrameComponents(double elapsedFrames);
	
	/**
	 * @function increment the animation time, end the animation at appropriate time, and get the next frame to siplay on screen
	 * @return list of nodes to be displayed on screen, null if animation has ended
	 */
	public ArrayList<Node> nextFrame(){
		//end animation is endframes is reached
		if(elapsedFrames > endFrames) {
			return null;
		}
		elapsedFrames += frameIncrementor;//Increment animation runtime
		return nextFrameComponents(elapsedFrames);//return the next frame's nodes
	}
}
