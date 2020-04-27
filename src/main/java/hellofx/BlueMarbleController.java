package hellofx;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.effect.ColorAdjust; 

public class BlueMarbleController {

	BlueMarble bm = new BlueMarble();

    @FXML
    private ImageView imageEarth;

    @FXML
    private DatePicker dateEarth;

    @FXML
    private Button buttonSubmit;

    @FXML
    private Text textCaption;
 
    @FXML
    private Text textError;

    @FXML
    private CheckBox chkboxEnhanced;

    @FXML
    private CheckBox chkboxBlackWhite;

    @FXML
    void handleChkboxBlackWhite(ActionEvent event) {
    	if (chkboxBlackWhite.isSelected()) {
    		ColorAdjust colorAdjust = new ColorAdjust();
    		colorAdjust.setSaturation(-1);
    		imageEarth.setEffect(colorAdjust);
    	} else {
    		ColorAdjust colorAdjust = new ColorAdjust();
    		colorAdjust.setSaturation(0);
    		imageEarth.setEffect(colorAdjust);
    	}
    }
    
    @FXML
    void handleDateEarth(ActionEvent event) {
    }
  
    @FXML
    void handleChkboxEnhanced(ActionEvent event) {
    	if (chkboxEnhanced.isSelected()) {
    		bm.setEnhanced(true);
    	} else {
    		bm.setEnhanced(false);    		
    	}
    }

    @FXML
    void handleButtonSubmit(ActionEvent event) {
    	LocalDate today = LocalDate.now();
    	
		boolean isDateNull = false;
    	try {
			if (dateEarth.getValue() == null) {
            	throw new NullPointerException("The date must not be null");
            }
            else if (dateEarth.getValue().isAfter(today)) {
            	throw new Exception("This is a future date.");
    		}
			else if (dateEarth.getValue().isBefore(LocalDate.of(2020, 2, 12))) {
				throw new Exception("There's no data before 2/12/20.");
			}
    	} catch (NullPointerException e) {    		
    		System.out.println("You did not select a date.");
           	textError.setText("Please select a date.");
    		isDateNull = true;
    	} catch (Exception e) {   
    		if (e.getMessage() == "This is a future date.") {
	    		System.out.println("This is a future date.");
	           	textError.setText("The date you selected " + dateEarth.getValue() + " is after today's date.");
    		} else if (e.getMessage() == "There's no data before 2/12/20.") {
    			System.out.println("There's no data before 2/12/20.");
	           	textError.setText("There's no data for " + dateEarth.getValue() + ".");
    		}
	    	isDateNull = true;
    	}

	    if (!isDateNull) {
           	textError.setText(null);
	    	String dateString = dateEarth.getValue().toString();
    		bm.setDate(dateString);
    		Image image = new Image(bm.getImage());
    		String caption = bm.getCaption();
    		imageEarth.setImage(image);
    		textCaption.setText(caption);  		
    	}
    }
}
