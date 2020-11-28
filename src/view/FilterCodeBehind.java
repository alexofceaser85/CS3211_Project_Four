package src.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.converter.NumberStringConverter;
import src.model.Radius;
import src.viewmodel.FilterViewModel;
/**
 * Code Behind for the Filter Section of the GUI.
 * @author Alexander Ayers
 *
 */
public class FilterCodeBehind extends BaseCodeBehind {

	  @FXML
	    private Text enterLocationText;

	    @FXML
	    private TextField locationAddressTextBox;

	    @FXML
	    private Text enterRadiusText;

	    @FXML
	    private ComboBox<Radius> radiusComboBox;

	    @FXML
	    private Button beginButton;
	    
	    @FXML
	    private Text categoriesText;

	    @FXML
	    private TextField categoriesTextBox;

	    @FXML
	    private TextField reviewScoreTextBox;

	    @FXML
	    private CheckBox price1Checkbox;

	    @FXML
	    private CheckBox price2Checkbox;

	    @FXML
	    private CheckBox price4Checkbox;

	    @FXML
	    private CheckBox price3Checkbox;

	    @FXML
	    private CheckBox currentOpenCheckbox;

	    @FXML
	    private CheckBox handicapAccessibleCheckbox;

	    @FXML
	    private CheckBox acceptsReservationsCheckbox;

	    @FXML
	    private CheckBox newRestaurantsCheckbox;

	    @FXML
	    private CheckBox genderNeutralBathroomsCheckbox;

	    @FXML
	    private Button backButton;

	    @FXML
	    private Button submitButton;
	    
	    private FilterViewModel viewModel;
	    
		/**
		 * Zero-parameter constructor
		 * 
		 * @precondition none
		 * @postcondition none
		 */
	    public FilterCodeBehind(FilterViewModel viewModel) {
	    	this.viewModel = viewModel;
	    }
	    
		/**
		 * Initializes all properties to needed settings.
		 * 
		 * @precondition none
		 * @postcondition none
		 */
	    public void initalize()
	    {
	    	this.bindToViewModel();
	    	this.beginButton.disableProperty().bind(this.locationAddressTextBox.textProperty().isEmpty());
	    	//TODO Setup Regex in reviewScore
	    	//TODO Make Location and Radius text boxes required
	    }
	    
	    @FXML
	    void handleBegin(ActionEvent event) {
			//TODO Go over with Furichous during meeting
	    	super.getController().activate("Filter");
	    }
	    
	    @FXML
	    void handleBack(ActionEvent event) {
			//TODO Go over with Furichous during meeting
			super.getController().activate("Location");
	    }

	    @FXML
	    void handleSubmit(ActionEvent event) {
	    	//TODO Go over with Furichous during meeting
	    	this.viewModel.sendRestaurantQuery();
	    	super.getController().activate("Restaurant");
	    }
	    
	    private void bindToViewModel() {
	    	this.locationAddressTextBox.textProperty().bindBidirectional(this.viewModel.locationAddressProperty());
	    	this.radiusComboBox.itemsProperty().bind(this.viewModel.radiusProperty());
	    	this.categoriesTextBox.textProperty().bindBidirectional(this.viewModel.categoriesProperty());
	    	this.acceptsReservationsCheckbox.selectedProperty().bindBidirectional(this.viewModel.acceptsReservationsProperty());
	    	this.price1Checkbox.selectedProperty().bindBidirectional(this.viewModel.price1Property());
	    	this.price2Checkbox.selectedProperty().bindBidirectional(this.viewModel.price2Property());
	    	this.price3Checkbox.selectedProperty().bindBidirectional(this.viewModel.price3Property());
	    	this.price4Checkbox.selectedProperty().bindBidirectional(this.viewModel.price4Property());
	    	this.currentOpenCheckbox.selectedProperty().bindBidirectional(this.viewModel.currentlyOpenProperty());
	    	this.handicapAccessibleCheckbox.selectedProperty().bindBidirectional(this.viewModel.handicapAccessibleProperty());
	    	this.genderNeutralBathroomsCheckbox.selectedProperty().bindBidirectional(this.viewModel.genderNeutralBathroomProperty());
	    	this.newRestaurantsCheckbox.selectedProperty().bindBidirectional(this.viewModel.newRestaurantsProperty());
	    	this.reviewScoreTextBox.textProperty().bindBidirectional(this.viewModel.reviewScoreProperty(), new NumberStringConverter());
	    }

		@Override
		public void onActivation() {
			//TODO reset properties based on MainManager
		}
	}

