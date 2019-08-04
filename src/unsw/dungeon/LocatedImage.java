package unsw.dungeon;

import javafx.scene.image.Image;

/**
 * LocatedImage is just an Image object that stores the url of the image By
 * having this we can remove the right image though it is bad design :)
 * 
 * @author z5211173
 *
 */
public class LocatedImage extends Image {

	private String url;

	/**
	 * constructor for located image
	 * 
	 * @param url
	 */
	public LocatedImage(String url) {
		super(url);
		this.url = url;
	}

	/**
	 * getter for url
	 * 
	 * @return url in a string
	 */
	public String getUrl() {
		return url;
	}

}
