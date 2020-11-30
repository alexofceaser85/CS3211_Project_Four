package src.viewmodel;

import javafx.scene.image.Image;

import java.text.DecimalFormat;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import src.data.JSONLoader;
import src.data.QueryManager;
import src.data.ReviewsQuery;
import src.model.MainManager;
import src.model.Price;
import src.model.Restaurant;
import src.model.RestaurantManager;
import src.model.Review;
import src.model.ReviewManager;

/**
 * ViewModel class of the RestaurantViewCodeBehind.
 * 
 * @author Furichous Jones IV
 * @version Fall 2020
 **/
public class RestaurantViewModel {
	private StringProperty nameProperty;
	private ObjectProperty<Image> imageProperty;
	private StringProperty locationProperty;
	private StringProperty priceRangeProperty;
	private StringProperty distanceProperty;
	private StringProperty reviewScoreProperty;
	private MainManager mainManager;
	private String restaurantID;


	private StringProperty menuURLProperty;
	private Restaurant pickedRestaurant;

	/**
	 * Constructor of RestaurantViewModel
	 * 
	 * @param mainManager the MainManager of the application
	 * 
	 * @precondition mainManager != null
	 * @postcondition none
	 */
	public RestaurantViewModel(MainManager mainManager) {
		if (mainManager == null) {
			throw new IllegalArgumentException();
		}
		this.nameProperty = new SimpleStringProperty();
		this.imageProperty = new SimpleObjectProperty<Image>();
		this.locationProperty = new SimpleStringProperty();
		this.priceRangeProperty = new SimpleStringProperty();
		this.distanceProperty = new SimpleStringProperty();
		this.reviewScoreProperty = new SimpleStringProperty();
		this.restaurantID = "";
		this.menuURLProperty = new SimpleStringProperty();
		this.mainManager = mainManager;
	}

	/**
	 * Picks a random restaurant
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return boolean of whether a restaurant was picked
	 */
	public boolean pickARestaurant() {
		RestaurantManager theManager = this.mainManager.getRestaurantManager();
		this.pickedRestaurant = theManager.pickRandom();
		if (this.pickedRestaurant == null) {
			return false;
		}

		String name = this.pickedRestaurant.getName();
		String location = this.pickedRestaurant.getLocation();
		Price price = this.pickedRestaurant.getPrice();

		int distance = this.pickedRestaurant.getDistance();
		String distanceFormatted = Integer.toString(distance) + " mi";

		Image image = this.buildImage(this.pickedRestaurant);
		this.imageProperty.set(image);

		double reviewScore = this.pickedRestaurant.getReviewScore();
		DecimalFormat df = new DecimalFormat("#.#");
		String reviewScoreFormatted = df.format(reviewScore);

		this.nameProperty.set(name);

		this.locationProperty.set(location);
		this.priceRangeProperty.set(price.toString());
		this.distanceProperty.set(distanceFormatted);
		this.reviewScoreProperty.set(reviewScoreFormatted);
		this.restaurantID = this.pickedRestaurant.getId();
		this.menuURLProperty.set(this.pickedRestaurant.getMenuURL());
		return true;
	}

	private Image buildImage(Restaurant pickedRestaurant) {
		String imageURL = pickedRestaurant.getImageURL();
		Image image;
		try {
			image = new Image(imageURL);
		} catch (Exception e) {
			image = new Image(JSONLoader.DEFAULT_IMAGE);
		}
		return image;
	}

	/**
	 * Resets the filters of the filter page and returns to that page.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 */
	public void resetFilters() {
		this.mainManager.setResetFilters(true);
	}

	/**
	 * Gets the menuURl Property.
	 * 
	 * @precondition none
	 * @postcondition none
	 * Gets the menu URL
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the value of menuURLProperty
	 */
	public StringProperty menuURLProperty() {
		return this.menuURLProperty;
	}

	/**
	 * Sends the review query to update the review manager.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return true if reviews was successfully obtained
	 */
	public boolean sendReviewsQuery() {
		if (this.restaurantID == null) {
			return false;
		}
		ReviewsQuery query = new ReviewsQuery(this.restaurantID);

		String response = QueryManager.sendQuery(query);
		if (response == null || response.isBlank()) {
			return false;
		}
		List<Review> reviews = JSONLoader.parseReviews(response);

		ReviewManager theManager = this.mainManager.getReviewManager();
		theManager.setReviews(reviews);
		return !reviews.isEmpty();
	}

	/**
	 * Returns the menuURL property
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the menuURL property
	 */
	public StringProperty nameProperty() {
		return this.nameProperty;
	}

	/**
	 * Returns the menuURL property
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the menuURL property
	 */
	public ObjectProperty<Image> imageProperty() {
		return this.imageProperty;
	}

	/**
	 * Returns the menuURL property
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the menuURL property
	 */
	public StringProperty locationProperty() {
		return this.locationProperty;
	}

	/**
	 * Returns the menuURL property
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the menuURL property
	 */
	public StringProperty priceRangeProperty() {
		return this.priceRangeProperty;
	}

	/**
	 * Returns the menuURL property
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the menuURL property
	 */
	public StringProperty distanceProperty() {
		return this.distanceProperty;
	}

	/**
	 * Returns the menuURL property
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the menuURL property
	 */
	public StringProperty reviewScoreProperty() {
		return this.reviewScoreProperty;
	}

	/**
	 * Returns the mainManager
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the mainManager value
	 */
	public MainManager getMainManager() {
		return this.mainManager;
	}

	/**
	 * Gets the restaurant ID.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the value of restaurantID
	 */
	public String getRestaurantID() {
		return this.restaurantID;
	}
	
	/**
	 * Gets the restaurant ID.
	 * 
	 * @param restaurantID the new value of restaurantID
	 * 
	 * @precondition none
	 * @postcondition this.restaurantID == restaurantID
	 * 
	 */
	public void setRestaurantID(String restaurantID) {
		this.restaurantID = restaurantID;
	}
	
	/**
	 * Removes the previous randomly picked restaurant
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 */
	
	public void removePickedRestaurant() {
		this.getMainManager().removeRestaurant(this.pickedRestaurant);
	}

}
