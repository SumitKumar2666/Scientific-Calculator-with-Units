package calculator;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import calculator.BusinessLogic;

/*
 * <p> Title: UserInterface Class. </p>
 *
 * <p> Description: The Java/FX-based user interface for the calculator. The class works with String
 * objects and passes work to other classes to deal with all other aspects of the computation. Moreover, this class work on the
 * error messaged for the operadns and for error terms also</p>
 *
 * <p> Copyright: Lynn Robert Carter � 2017 </p>
 *
 * @author Lynn Robert Carter and Sumit Kumar 170002
 * @version 4.4 2018-03-4 the Jva based UI consist of required controls and perfrom calling task for the error messages
 * @version 4.5 2018-03-06 UI consist of required controls but along wiht the error Terms
 * @version 4.6 2018-10-06 UI based on the required control which also see the error messages for teh operands as well as for error terms
 * @version 4.7 2019-07-26 Units added in the userinterface
 */ 

public class UserInterface {

	/**********************************************************************************************

	Attributes

	**********************************************************************************************/

	/* Constants used to parameterize the graphical user interface.  We do not use a layout manager for
	   this application. Rather we manually control the location of each graphical element for exact
	   control of the look and feel. */
	private final double BUTTON_WIDTH = 60;
	private final double BUTTON_OFFSET = BUTTON_WIDTH/2;

	// These are the application values required by the user interface
	private Label label_DoubleCalculator = new Label("Science and Engineering Calclator with Units");
	private Label label_Operand1 = new Label("First operand");
	private TextField text_Operand1 = new TextField();
	//private Label label_ErrorTerm1 = new Label("Error Term 1");
	private TextField text_ErrorTerm1 = new TextField();
	private Label label_Operand2 = new Label("Second operand");
	private TextField text_Operand2 = new TextField();
	//private Label label_ErrorTerm2 = new Label("Error Term 2");
	private TextField text_ErrorTerm2 = new TextField();
	private Label label_Result = new Label("Result");
	private TextField text_Result = new TextField();
	private Label label_ErrorResult = new Label("Result");
	private TextField text_ErrorResult = new TextField();
	private Label label_Units = new Label("Units");
	//These signs are the sign of +- which was placed before ErrorTerm1 and ErrorTerm2 operand
	private Label label_variable = new Label("\u00B1");
	private Label label_variable1 = new Label("\u00B1");
	private Label label_variable2 = new Label("\u00B1");
	
	private Button button_Add = new Button("+");
	private Button button_Sub = new Button("-");
	private Button button_Mpy = new Button("\u00D7");				// The multiply symbol: \u00D7
	private Button button_Div = new Button("\u00F7");				// The divide symbol: \u00F7
	private Button button_Sqrt = new Button("\u221A");              // The root symbol: \u221A
	
	private Label label_errOperand1 = new Label("");                // Label to display specific
	private Label label_errOperand2 = new Label("");                // error messages
	private Label label_errErrorTerm1 = new Label("");                // Label to display specific
	private Label label_errErrorTerm2 = new Label("");
	
	private Label label_errOperand1My = new Label("");               // Label to display a error message
	private Label label_errOperand2R = new Label("");	            // when user tries to perform any function
	private Label label_errErrorTerm3A = new Label("");               // Label to display a error message
	private Label label_errErrorTerm4My = new Label("");
	private Label label_errResult = new Label("");
	private Label label_errErrorResult = new Label("");
	
	private TextFlow err1;
    private Text operand1ErrPart1 = new Text();
    private Text operand1ErrPart2 = new Text();
   
    private TextFlow err2;
    private Text operand2ErrPart1 = new Text();
    private Text operand2ErrPart2 = new Text();
    
    private TextFlow err3;
    private Text errorTerm1ErrPart1 = new Text();
    private Text errorTerm1ErrPart2 = new Text();
   
    private TextFlow err4;
    private Text errorTerm2ErrPart1 = new Text();
    private Text errorTerm2ErrPart2 = new Text();
    
    //combo box for units
    
    private ComboBox Units1 = new ComboBox();                    
    private ComboBox Units2 = new ComboBox();
    private ComboBox Units3 = new ComboBox();


	// If the multiplication and/or division symbols do not display properly, replace the
	// quoted strings used in the new Button constructor call with the <backslash>u00xx values
	// shown on the same line. This is the Unicode representation of those characters and will
	// work regardless of the underlying hardware being used. 

	private double buttonSpace;
	                                // This is the white space between the operator buttons.

	/* This is the link to the business logic */
	public BusinessLogic perform = new BusinessLogic();


	/**********************************************************************************************

	Constructors

	**********************************************************************************************/

	/**********
	 * This method initializes all of the elements of the graphical user interface. These assignments
	 * determine the location, size, font, color, and change and event handlers for each GUI object.
	 * 
	 * @param theRoot = method determine all the elements
	 */

	public UserInterface(Pane theRoot) {

		// There are five gaps. Compute the button space accordingly.
		buttonSpace = Calculator.WINDOW_WIDTH / 5;

		// Label theScene with the name of the calculator, centered at the top of the pane
		setupLabelUI(label_DoubleCalculator, "Arial", 24, Calculator.WINDOW_WIDTH, Pos.CENTER, 0, 10);

		// Label the operand1 just above it, left aligned
		setupLabelUI(label_Operand1, "Arial", 18, Calculator.WINDOW_WIDTH-250, Pos.BASELINE_LEFT, 10, 75);
		label_Operand1.setLayoutX(35);

		// Establish the first text input operand field and when anything changes in operand 1,
		// process both fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand1, "Arial", 18, 350, Pos.BASELINE_LEFT, 150, 70, true);
		text_Operand1.textProperty().addListener((observable, oldValue, newValue) -> {setOperand1(); });
		// Move focus to the second operand when the user presses the enter (return) key
		text_Operand1.setOnAction((event) -> { text_ErrorTerm1.requestFocus(); });

		// Establish an error message for the operand1 just above it with, left aligned
		setupLabelUI(label_errOperand1My, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 400, 45);
		label_errOperand1My.setTextFill(Color.RED);

		//Bottom proper error message
		label_errOperand1.setTextFill(Color.RED);
		label_errOperand1.setAlignment(Pos.BASELINE_LEFT);
		setupLabelUI(label_errOperand1, "Arial", 14, 150, Pos.BASELINE_LEFT, 160, 128);
		setupLabelUI(label_variable, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 525, 75);

		// Label the Error Term1 just above it, left aligned
		//setupLabelUI(label_ErrorTerm1, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 400, 40);

		// Establish the Third text input operand field and when anything changes in operand 3,
		// process both fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_ErrorTerm1, "Arial", 18, 250, Pos.BASELINE_LEFT, 550, 70, true);
		text_ErrorTerm1.textProperty().addListener((observable, oldValue, newValue) -> {setOperand3(); });
		// Move focus to the Third operand when the user presses the enter (return) key
		text_ErrorTerm1.setOnAction((event) -> { text_Operand2.requestFocus(); });

		// Establish an error message for the ErrorTerm 1 just above it with, left aligned
		setupLabelUI(label_errErrorTerm3A, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_RIGHT, 400, 45);
		label_errErrorTerm3A.setTextFill(Color.RED);

		//Bottom proper error message
		label_errErrorTerm1.setTextFill(Color.RED);
		label_errErrorTerm1.setAlignment(Pos.BASELINE_RIGHT);
		setupLabelUI(label_errErrorTerm1, "Arial", 14, Calculator.WINDOW_WIDTH-100-10, Pos.BASELINE_LEFT, 560, 128);

		// Label the operand2 just above it, left aligned
		setupLabelUI(label_Operand2, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 185);

		// Establish the second text input operand field and when anything changes in operand 2,
		// process both fields to ensure that we are ready to perform as son as possible.
		setupTextUI(text_Operand2, "Arial", 18, 350, Pos.BASELINE_LEFT, 150, 180, true);
		text_Operand2.textProperty().addListener((observable, oldValue, newValue) -> {setOperand2(); });

		// Move the focus to the result when the user presses the enter (return) key
		text_Operand2.setOnAction((event) -> { text_ErrorTerm2 .requestFocus(); });

		// Establish an error message for the second operand just above it with, left aligned
		setupLabelUI(label_errOperand2R, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 400, 155);
		label_errOperand2R.setTextFill(Color.RED);

		//Bottom proper error message
		label_errOperand2.setTextFill(Color.RED);
		label_errOperand2.setAlignment(Pos.BASELINE_RIGHT);
		setupLabelUI(label_errOperand2, "Arial", 14, Calculator.WINDOW_WIDTH-150-10, Pos.BASELINE_LEFT, 160, 233);
		label_errOperand2.setTextFill(Color.RED);
		setupLabelUI(label_variable1, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 525, 190);

		// Label the ErrorTerm2 just above it, left aligned
			//setupLabelUI(label_ErrorTerm2, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 400, 155);

		// Establish the Fourth text input operand field and when anything changes in EErrorTerm2,
		// process both fields to ensure that we are ready to perform as soon as possible.
			setupTextUI(text_ErrorTerm2 , "Arial", 18, 250, Pos.BASELINE_LEFT, 550, 180, true);
			text_ErrorTerm2 .textProperty().addListener((observable, oldValue, newValue) -> {setOperand4(); });

		// Establish an error message for the ErrorTerm 2 just above it with, left aligned
		setupLabelUI(label_errErrorTerm4My, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 400, 155);
		label_errErrorTerm4My.setTextFill(Color.RED);

		//Bottom proper error message
		label_errErrorTerm2.setTextFill(Color.RED);
		label_errErrorTerm2.setAlignment(Pos.BASELINE_RIGHT);
		setupLabelUI(label_errErrorTerm2, "Arial", 14, Calculator.WINDOW_WIDTH-150-10, Pos.BASELINE_LEFT, 560, 233);
		label_errErrorTerm2.setTextFill(Color.RED);
				

		// Label the result just above the result output field, left aligned
		setupLabelUI(label_Result, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 40, 300);
		setupLabelUI(label_Units, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 920, 30);

		// Establish the result output field.  It is not editable, so the text can be selected and copied,
		// but it cannot be altered by the user.  The text is left aligned.
		setupTextUI(text_Result, "Arial", 18, 350, Pos.BASELINE_LEFT, 10, 290, false);
		text_Result.setLayoutX(150);

		// Establish an error message for the Result operand just above it with, right aligned

		setupLabelUI(label_errResult, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 30,230);
		label_errResult.setTextFill(Color.RED);

		setupLabelUI(label_variable2, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 525, 300);


		// Label the result just above the result output field, left aligned
		//	setupLabelUI(label_ErrorResult, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 400, 200);

				// Establish the result output field.  It is not editable, so the text can be selected and copied,
				// but it cannot be altered by the user.  The text is left aligned.
				setupTextUI(text_ErrorResult, "Arial", 18, 250, Pos.BASELINE_LEFT, 550, 290, false);

				// Establish an error message for the Result operand just above it with, right aligned

				setupLabelUI(label_errErrorResult, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 400,220);
				label_errErrorResult.setTextFill(Color.RED);


		// Establish the ADD "+" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Add, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 0.5 * buttonSpace-BUTTON_OFFSET, 400);
		button_Add.setOnAction((event) -> { addOperands(); addOperands1(); });


		// Establish the SUB "-" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Sub, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 1.5 * buttonSpace-BUTTON_OFFSET, 400);
		button_Sub.setOnAction((event) -> { subOperands(); subOperands1(); });

		// Establish the MPY "x" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Mpy, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 2.5 * buttonSpace-BUTTON_OFFSET, 400);
		button_Mpy.setOnAction((event) -> { mpyOperands(); mpyOperands1(); });

		// Establish the DIV "/" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Div, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 3.5 * buttonSpace-BUTTON_OFFSET, 400);
		button_Div.setOnAction((event) -> { divOperands(); divOperands1(); });

		// Establish the SQRT "root" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Sqrt, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 4.5 * buttonSpace-BUTTON_OFFSET, 400);
		button_Sqrt.setOnAction((event) -> { sqrtOperands(); sqrtOperands1(); });

		//Combobox for units
		Units1.getItems().addAll("Kg","s","m","g","Km","days"," ");
		Units1.setLayoutX(910);
		Units1.setLayoutY(75);
		Units1.setPromptText("");
		Units2.getItems().addAll("Kg","s","m","g","Km","days"," ");
		Units2.setLayoutX(910);
		Units2.setLayoutY(185);
		Units2.setPromptText(""); 
		Units3.getItems().addAll("       ");
		Units3.setLayoutX(910);
		Units3.setLayoutY(295);
		Units3.setPromptText("");
		
		// Error Message for the Measured Value for operand 1
				operand1ErrPart1.setFill(Color.BLACK);
			    operand1ErrPart1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
			    operand1ErrPart2.setFill(Color.RED);
			    operand1ErrPart2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
			    
			    err1 = new TextFlow(operand1ErrPart1, operand1ErrPart2);
				err1.setMinWidth(Calculator.WINDOW_WIDTH-10);
				err1.setLayoutX(160);
				err1.setLayoutY(100);

		// Error Message for the Measured Value for operand 2
				operand2ErrPart1.setFill(Color.BLACK);
			    operand2ErrPart1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
			    operand2ErrPart2.setFill(Color.RED);
			    operand2ErrPart2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
			    err2 = new TextFlow(operand2ErrPart1, operand2ErrPart2);
				err2.setMinWidth(Calculator.WINDOW_WIDTH-10);
				err2.setLayoutX(160);
				err2.setLayoutY(210);

				// Error Message for the Measured Value for operand 3
				errorTerm1ErrPart1.setFill(Color.BLACK);
				errorTerm1ErrPart1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
				errorTerm1ErrPart2.setFill(Color.RED);
			    errorTerm1ErrPart2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
			    err3 = new TextFlow(errorTerm1ErrPart1, errorTerm1ErrPart2);
				err3.setMinWidth(Calculator.WINDOW_WIDTH-10);
				err3.setLayoutX(560);
				err3.setLayoutY(100);


				// Error Message for the Measured Value for operand 4
				errorTerm2ErrPart1.setFill(Color.BLACK);
				errorTerm2ErrPart1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
				errorTerm2ErrPart2.setFill(Color.RED);
				errorTerm2ErrPart2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
			    err4 = new TextFlow(errorTerm2ErrPart1, errorTerm2ErrPart2);
				err4.setMinWidth(Calculator.WINDOW_WIDTH-10);
				err4.setLayoutX(560);
				err4.setLayoutY(210);

		// Place all of the just-initialized GUI elements into the pane
		theRoot.getChildren().addAll(label_DoubleCalculator, label_variable, label_variable1, label_variable2, label_Operand1, text_Operand1, label_errOperand1,
			label_Operand2, text_Operand2, label_errOperand2,  text_ErrorTerm1, label_errErrorTerm1,  text_ErrorTerm2 , label_errErrorTerm2,
			label_Result,label_Units, text_Result, label_errResult, text_ErrorResult, label_errErrorResult, button_Add, button_Sub, button_Mpy, button_Div, button_Sqrt, err1, err2, err3, err4,  label_errOperand1My,
				label_errOperand2R, label_errErrorTerm3A, label_errErrorTerm4My,Units1,Units2,Units3);

	}

	/*******
	 * This public methods invokes the methods of Calculator class and generate a specific error
	 * message when the user enters the value of operand1
	 *
	 */
	public void pr() {
		String errMessage = CalculatorValue.checkMeasureValue(text_Operand1.getText());
		if (errMessage != "") {
			label_errOperand1.setText(CalculatorValue.measuredValueErrorMessage);
			if (CalculatorValue.measuredValueIndexofError <= -1) return;
			String input = CalculatorValue.measuredValueInput;
			operand1ErrPart1.setText(input.substring(0, CalculatorValue.measuredValueIndexofError));
			operand1ErrPart2.setText("\u21EB");
		}
	}

	/*******
	 * This public methods invokes the methods of Calculator class and generate a specific error
	 * message when the user enters the value of operand2
	 *
	 */
	public void at() {
		String errMessage = CalculatorValue.checkMeasureValue(text_Operand2.getText());
		if (errMessage != "") {
			label_errOperand2.setText(CalculatorValue.measuredValueErrorMessage);
			if (CalculatorValue.measuredValueIndexofError <= -1) return;
			String input = CalculatorValue.measuredValueInput;
			operand2ErrPart1.setText(input.substring(0, CalculatorValue.measuredValueIndexofError));
			operand2ErrPart2.setText("\u21EB");
		}
	}

	/*******
	 * This public methods invokes the methods of Calculator class and generate a specific error
	 * message when the user enters the value of operand2
	 *
	 */

	private void ik() {
		String errMessage = CalculatorValue.checkMeasureValue(text_ErrorTerm1.getText());
		if (errMessage != "") {
			
			label_errErrorTerm1.setText(CalculatorValue.measuredValueErrorMessage);
			if (CalculatorValue.measuredValueIndexofError <= -1) return;
			String input = CalculatorValue.measuredValueInput;
			errorTerm1ErrPart1.setText(input.substring(0, CalculatorValue.measuredValueIndexofError));
			errorTerm1ErrPart2.setText("\u21EB");
		}
		else {
			errMessage = CalculatorValue.checkErrorTerm(text_ErrorTerm1.getText());
			if (errMessage != "") {
			
				label_errErrorTerm1.setText(CalculatorValue.errorTermErrorMessage);
				String input = CalculatorValue.errorTermInput;
			if (CalculatorValue.errorTermIndexofError <= -1) return;
			errorTerm1ErrPart1.setText(input.substring(0, CalculatorValue.errorTermIndexofError));
			errorTerm1ErrPart2.setText("\u21EB");
			}}}

	/*******
	 * This public methods invokes the methods of Calculator class and generate a specific error
	 * message when the user enters the value of operand2
	 *
	 */


	private void si() {
		String errMessage = CalculatorValue.checkMeasureValue(text_ErrorTerm2 .getText());
		if (errMessage != "") {
		
			label_errErrorTerm2.setText(CalculatorValue.measuredValueErrorMessage);
			if (CalculatorValue.measuredValueIndexofError <= -1) return;
			String input = CalculatorValue.measuredValueInput;
			errorTerm2ErrPart1.setText(input.substring(0, CalculatorValue.measuredValueIndexofError));
			errorTerm2ErrPart2.setText("\u21EB");
		}
		else {
			errMessage = CalculatorValue.checkErrorTerm(text_ErrorTerm2 .getText());
			if (errMessage != "") {
			
				label_errErrorTerm2.setText(CalculatorValue.errorTermErrorMessage);
				String input = CalculatorValue.errorTermInput;
			if (CalculatorValue.errorTermIndexofError <= -1) return;
			errorTerm2ErrPart1.setText(input.substring(0, CalculatorValue.errorTermIndexofError));
				errorTerm2ErrPart2.setText("\u21EB");
			}}}

	/**********
	 * Private local method to initialize the standard fields for a label
	 */
	private void setupLabelUI(Label l, String ff, double f, double w, Pos p, double x, double y){
		l.setFont(Font.font(ff, f));
		l.setMinWidth(w);
		l.setAlignment(p);
		l.setLayoutX(x);
		l.setLayoutY(y);
	}

	/**********
	 * Private local method to initialize the standard fields for a text field
	 */
	private void setupTextUI(TextField t, String ff, double f, double w, Pos p, double x, double y, boolean e){
		t.setFont(Font.font(ff, f));
		t.setMinWidth(w);
		t.setMaxWidth(w);
		t.setAlignment(p);
		t.setLayoutX(x);
		t.setLayoutY(y);
		t.setEditable(e);
	}

	/**********
	 * Private local method to initialize the standard fields for a button
	 */
	private void setupButtonUI(Button b, String ff, double f, double w, Pos p, double x, double y){
		b.setFont(Font.font(ff, f));
		b.setMinWidth(w);
		b.setAlignment(p);
		b.setLayoutX(x);
		b.setLayoutY(y);
	}


	/**********************************************************************************************

	User Interface Actions

	**********************************************************************************************/

	/**********
	 * Private local method to set the value of the first operand given a text value. The method uses the
	 * business logic class to perform the work of checking the string to see it is a valid value and if
	 * so, saving that value internally for future computations. If there is an error when trying to convert
	 * the string into a value, the called business logic method returns false and actions are taken to
	 * display the error message appropriately.
	 */
	private void setOperand1() {
		text_Result.setText("");									// Any change of an operand probably invalidates
		label_Result.setText("Result");								// the result, so we clear the old result.
		label_errResult.setText("");
		if (perform.setOperand1(text_Operand1.getText())) {			// Set the operand and see if there was an error
			label_errOperand1.setText("");							// If no error, clear this operands error
			label_errOperand1My.setText("");
			operand1ErrPart1.setText("");                   		// Clear the first term of error part
			operand1ErrPart2.setText("");                   		// Clear the second term of error part
			if (text_Operand2.getText().length() == 0)				// If the other operand is empty, clear its error
				label_errOperand2.setText("");						// as well.
		}
		else 														// If there's a problem with the operand, display
			pr();
	}


	/**********
	 * Private local method to set the value of the second operand given a text value. The logic is exactly the
	 * same as used for the first operand, above.
	 */
	private void setOperand2() {
		text_Result.setText("");
		label_Result.setText("Result");
		label_errResult.setText("");
		if (perform.setOperand2(text_Operand2.getText())) {
			label_errOperand2.setText("");
			label_errOperand2R.setText("");
			operand2ErrPart1.setText("");                   		// Clear the first term of error part
			operand2ErrPart2.setText("");                  			// Clear the second term of error part
			if (text_Operand1.getText().length() == 0)
				label_errOperand1.setText("");
		}
		else
			at();
	}



	private void setOperand3() {
		label_errErrorTerm1.setText("");
		errorTerm1ErrPart1.setText("");
		errorTerm1ErrPart2.setText("");
		if (perform.setOperand3(text_ErrorTerm1.getText())) {
		text_ErrorResult.setText("");									// Any change of an operand probably invalidates
		label_ErrorResult.setText("Error Term");					   // the result, so we clear the old result.
		label_errErrorResult.setText("");		                      // Set the operand and see if there was an error
			label_errErrorTerm1.setText("");}						 // If no error, clear this operands error

			if (text_ErrorTerm2 .getText().length() == 0)				// If the other operand is empty, clear its error
				label_errErrorTerm2.setText("");						// as well.

		ik();
		}


	private void setOperand4() {
		label_errErrorTerm2.setText("");
		errorTerm2ErrPart1.setText("");
		errorTerm2ErrPart2.setText("");
		if (perform.setOperand4(text_ErrorTerm2 .getText())) {
		text_ErrorResult.setText("");									// Any change of an operand probably invalidates
		label_ErrorResult.setText("Error Term");								// the result, so we clear the old result.
		label_errErrorResult.setText("");		                      // Set the operand and see if there was an error
			label_errErrorTerm1.setText("");}							// If no error, clear this operands error

			if (text_ErrorTerm1.getText().length() == 0)				// If the other operand is empty, clear its error
				label_errErrorTerm1.setText("");						// as well.

		si();
		}


	/**********
	 * Private local method to set the value of the first operand given a text value. The method uses the
	 * business logic class to perform the work of checking the string to see it is a valid value and if
	 * so, saving that value internally for future computations. If there is an error when trying to convert
	 * the string into a value, the called business logic method returns false and actions are taken to
	 * display the error message appropriately.
	 */


	/**********
	 * Private local method to set the value of the first operand given a text value. The method uses the
	 * business logic class to perform the work of checking the string to see it is a valid value and if
	 * so, saving that value internally for future computations. If there is an error when trying to convert
	 * the string into a value, the called business logic method returns false and actions are taken to
	 * display the error message appropriately.
	 */

	/**********
	 * This method is called when an binary operation (expect square root) button has been pressed. It assesses if there are issues
	 * with either of the binary operands or they are not defined. If not return false (there are no issues)
	 *
	 * @return	True if there are any issues that should keep the calculator from doing its work.
	 */


	private boolean binaryOperandIssues() {
		String errorMessage1 = perform.getOperand1ErrorMessage();	// Fetch the error messages, if there are any
		String errorMessage2 = perform.getOperand2ErrorMessage();
		if (errorMessage1.length() > 0) {							// Check the first.  If the string is not empty
			label_errOperand1.setText(errorMessage1);				// there's an error message, so display it.
			if (errorMessage2.length() > 0) {						// Check the second and display it if there is
				label_errOperand2.setText(errorMessage2);			// and error with the second as well.

				return true;										// Return true when both operands have errors
			}
			else {
				return true;										// Return true when only the first has an error
			}
		}
		else if (errorMessage2.length() > 0) {						// No error with the first, so check the second
			label_errOperand2.setText(errorMessage2);				// operand. If non-empty string, display the error
			return true;											// message and return true... the second has an error
		}															// Signal there are issues

																	// If the code reaches here, neither the first nor the second has an error condition. The following code
																	// check to see if the operands are defined.
		if (!perform.getOperand1Defined()) {						// Check to see if the first operand is defined
			label_errOperand1.setText("No value found");			// If not, this is an issue for a binary operator
			if (!perform.getOperand2Defined()) {					// Now check the second operand. It is is also
				label_errOperand2.setText("No value found");		// not defined, then two messages should be displayed

				return true;										// Signal there are issues
			}
			return true;
		}


			else if (!perform.getOperand2Defined()) {					// If the first is defined, check the second. Both
			label_errOperand2.setText("No value found");			// operands must be defined for a binary operator.
			return true;											// Signal there are issues
		}

		return false;												// Signal there are no issues with the operands
		}


	/**********
	 * This method is called when square root button has been pressed. It assesses if there are issues
	 * with either of the binary operand1 or it is not defined. If not return false (there are no issues)
	 * As to perform square root, we only need operand1 thus any value added in the second field is
	 * automatically cleared when square root button is pressed
	 *
	 * @return	True if there are any issues that should keep the calculator from doing its work.
	 */


	private boolean unaryOperandIssues() {
		String errorMessage1 = perform.getOperand1ErrorMessage();
		String errorMessage2 = perform.getOperand2ErrorMessage();
		String errorMessage3 = perform.getErrorTerm1ErrorMessage();
		String errorMessage4 = perform.getErrorTerm2ErrorMessage();
		if (errorMessage1.length() == 0) {
			label_errOperand1.setText(errorMessage1);
			if (errorMessage2.length() > 0) {
				label_errOperand2.setText(errorMessage2);

				return true;
			}
			else {
				return true;
			}
		}
		else if (errorMessage2.length() > 0) {
			label_errOperand2.setText(errorMessage2);
			return true;
		}

		if (errorMessage3.length() == 0) {
			label_errErrorTerm1.setText(errorMessage3);
			if (errorMessage4.length() > 0) {
				label_errErrorTerm2.setText(errorMessage4);

				return true;
			}
			else {
				return true;
			}
		}
		else if (errorMessage4.length() > 0) {
			label_errErrorTerm2.setText(errorMessage4);
			return true;
		}

		if (!perform.getOperand1Defined()) {
			label_errOperand1.setText("");
			if (!perform.getOperand2Defined()) {
				label_errOperand2.setText("");
				if (!perform.getErrorTerm1Defined()) {
					label_errErrorTerm1.setText("");
					if (!perform.getErrorTerm2Defined()) {
						label_errErrorTerm2.setText("");

				return true;
			}
			return true;
		}
				return true;
			}
			return true;
		}


				else if (!perform.getOperand2Defined()) {
			label_errOperand2.setText("No Second Input Required For this Function.");
			return false;
				}


			else if (!perform.getErrorTerm2Defined()) {
				label_errErrorTerm2.setText("No Second Input Required For this Function.");
				return false;


		}
		return false;
			}

	/*******************************************************************************************************
	 * This portion of the class defines the actions that take place when the various calculator
	 * buttons (add, subtract, multiply, divide and square root) are pressed.
	 */

	/**********
	 * This is the add routine
	 *
	 */

	private void addOperands(){
		
		String a = text_Operand1.getText();                     // a store value in Operand 1 
		String b = text_Operand2.getText();						// b store value in Operand 2 
		double x = Double.parseDouble(a);						// x store a in form of double
		double y = Double.parseDouble(b);						// y store a in form of double
		String c;
		if(Units1.getValue().equals(" ") && Units2.getValue().equals(" ")) {
			Units3.setPromptText("");}
		else if(Units1.getValue().equals("Kg") && Units2.getValue().equals("Kg")) {
			Units3.setPromptText("Kg");	
		}
		else if(Units1.getValue().equals("g") && Units2.getValue().equals("g")) {
			Units3.setPromptText("g");	
		}
		else if(Units1.getValue().equals("s") && Units2.getValue().equals("s")) {
			Units3.setPromptText("s");	
		}
		else if(Units1.getValue().equals("km") && Units2.getValue().equals("Km")) {
			Units3.setPromptText("Km");	
		}
		else if(Units1.getValue().equals("m") && Units2.getValue().equals("m")) {
			Units3.setPromptText("m");	
		}
		else if(Units1.getValue().equals("days") && Units2.getValue().equals("days")) {
			Units3.setPromptText("days");	
		}
		else if(Units1.getValue().equals("Kg") && Units2.getValue().equals("g")) {
			x=x*1000;
			c = Double.toString(x);
			text_Operand1.setText(c);
			Units3.setPromptText("g");	
		}
		else if(Units1.getValue().equals("g") && Units2.getValue().equals("Kg")) {
			y=y*1000;
			c = Double.toString(y);
			text_Operand2.setText(c);
			Units3.setPromptText("g");	
		}
		else if(Units1.getValue().equals("Km") && Units2.getValue().equals("m")) {
			x=x*1000;
			c = Double.toString(x);
			text_Operand1.setText(c);
			Units3.setPromptText("m");	
		}
		else if(Units1.getValue().equals("m") && Units2.getValue().equals("Km")) {
			y=y*1000;
			c = Double.toString(y);
			text_Operand2.setText(c);
			Units3.setPromptText("m");	
		}
		else if(Units1.getValue().equals("days") && Units2.getValue().equals("s")) {
			x=x*86400;
			c = Double.toString(x);
			text_Operand1.setText(c);
			Units3.setPromptText("s");	
		}
		else if(Units1.getValue().equals("s") && Units2.getValue().equals("days")) {
			y=y*86400;
			c = Double.toString(y);
			text_Operand2.setText(c);
			Units3.setPromptText("s");	
		}
		
		
		if(text_Operand1.getText().isEmpty()) {
			text_Operand1.setText("");
		}
		if(text_Operand2.getText().isEmpty()) {
			text_Operand2.setText("");
		}
																		// Check to see if both operands are defined and valid
		if (binaryOperandIssues()) 										// If there are issues with the operands, return
			return;														// without doing the computation

																	// If the operands are defined and valid, request the business logic method to do the addition and return the
																		// result as a String. If there is a problem with the actual computation, an empty string is returned
		String theAnswer = perform.addition();							// Call the business logic add method
		label_errResult.setText("");									// Reset any result error messages from before
		if (theAnswer.length() > 0) {									// Check the returned String to see if it is okay
			

			text_Result.setText(theAnswer);								// If okay, display it in the result field and
			label_Result.setText("Sum");								// change the title of the field to "Sum"
		}
		else {															// Some error occurred while doing the addition.
			text_Result.setText("");									// Do not display a result if there is an error.
			label_Result.setText("Result");								// Reset the result label if there is an error.
			label_errResult.setText(perform.getResultErrorMessage());	// Display the error message.
		}
		if(Units1.getValue().equals("Kg") && Units2.getValue().equals("m")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("Kg") && Units2.getValue().equals("Km")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");
		}
		else if(Units1.getValue().equals("Kg") && Units2.getValue().equals("days")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");
		}
		else if(Units1.getValue().equals("Kg") && Units2.getValue().equals("s")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");
		}
		else if(Units1.getValue().equals("g") && Units2.getValue().equals("Km")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("g") && Units2.getValue().equals("m")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("g") && Units2.getValue().equals("days")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("g") && Units2.getValue().equals("s")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");
		}
		else if(Units1.getValue().equals("Km") && Units2.getValue().equals("g")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("Km") && Units2.getValue().equals("Kg")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("Km") && Units2.getValue().equals("days")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");
		}
		else if(Units1.getValue().equals("Km") && Units2.getValue().equals("s")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");
		}
		else if(Units1.getValue().equals("m") && Units2.getValue().equals("Kg")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("m") && Units2.getValue().equals("g")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");
		}
		else if(Units1.getValue().equals("m") && Units2.getValue().equals("days")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");
		}
		else if(Units1.getValue().equals("m") && Units2.getValue().equals("s")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("days") && Units2.getValue().equals("g")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("days") && Units2.getValue().equals("Kg")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("days") && Units2.getValue().equals("Km")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");
		}
		else if(Units1.getValue().equals("days") && Units2.getValue().equals("m")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");
		}
		else if(Units1.getValue().equals("s") && Units2.getValue().equals("Km")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("s") && Units2.getValue().equals("m")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("s") && Units2.getValue().equals("Kg")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("s") && Units2.getValue().equals("g")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");	
		}
		
		

	}
	
	

	//Implementation of Division for ErrorTerm
	
	private void addOperands1(){
		
		if(text_ErrorTerm1.getText().isEmpty()) {
			text_ErrorTerm1.setText("0.5");
		}
		if(text_ErrorTerm2 .getText().isEmpty()) {
			text_ErrorTerm2 .setText("0.5");
		}
	                                                          	// Check to see if both operands are defined and valid
if (binaryOperandIssues()) 										// If there are issues with the operands, return
return;

	String theAnswer1 = perform.addition1();							// Call the business logic add method
	label_errErrorResult.setText("");									// Reset any result error messages from before
	if (theAnswer1.length() > 0) {									// Check the returned String to see if it is okay

		
		text_ErrorResult.setText(theAnswer1);								// If okay, display it in the result field and
		label_ErrorResult.setText("Error Term");								// change the title of the field to "Sum"
	}
	else {															// Some error occurred while doing the addition.
		text_ErrorResult.setText("");									// Do not display a result if there is an error.
		label_ErrorResult.setText("Error Term");								// Reset the result label if there is an error.
		label_errErrorResult.setText(perform.getErrorResultErrorMessage());	// Display the error message.
	}
	if(Units1.getValue().equals("Kg") && Units2.getValue().equals("m")) {
		text_ErrorResult.setText("");
		Units3.setPromptText("");	
	}
	else if(Units1.getValue().equals("Kg") && Units2.getValue().equals("Km")) {
		text_ErrorResult.setText("");
		Units3.setPromptText("");
	}
	else if(Units1.getValue().equals("Kg") && Units2.getValue().equals("days")) {
		text_ErrorResult.setText("");
		Units3.setPromptText("");
	}
	else if(Units1.getValue().equals("Kg") && Units2.getValue().equals("s")) {
		text_ErrorResult.setText("");
		Units3.setPromptText("");
	}
	else if(Units1.getValue().equals("g") && Units2.getValue().equals("Km")) {
		text_ErrorResult.setText("");
		Units3.setPromptText("");	
	}
	else if(Units1.getValue().equals("g") && Units2.getValue().equals("m")) {
		text_ErrorResult.setText("");
		Units3.setPromptText("");	
	}
	else if(Units1.getValue().equals("g") && Units2.getValue().equals("days")) {
		text_ErrorResult.setText("");
		Units3.setPromptText("");	
	}
	else if(Units1.getValue().equals("g") && Units2.getValue().equals("s")) {
		text_ErrorResult.setText("");
		Units3.setPromptText("");
	}
	else if(Units1.getValue().equals("Km") && Units2.getValue().equals("g")) {
		text_ErrorResult.setText("");
		Units3.setPromptText("");	
	}
	else if(Units1.getValue().equals("Km") && Units2.getValue().equals("Kg")) {
		text_ErrorResult.setText("");
		Units3.setPromptText("");	
	}
	else if(Units1.getValue().equals("Km") && Units2.getValue().equals("days")) {
		text_ErrorResult.setText("");
		Units3.setPromptText("");
	}
	else if(Units1.getValue().equals("Km") && Units2.getValue().equals("s")) {
		text_ErrorResult.setText("");
		Units3.setPromptText("");
	}
	else if(Units1.getValue().equals("m") && Units2.getValue().equals("Kg")) {
		text_ErrorResult.setText("");
		Units3.setPromptText("");	
	}
	else if(Units1.getValue().equals("m") && Units2.getValue().equals("g")) {
		text_ErrorResult.setText("");
		Units3.setPromptText("");
	}
	else if(Units1.getValue().equals("m") && Units2.getValue().equals("days")) {
		text_ErrorResult.setText("");
		Units3.setPromptText("");
	}
	else if(Units1.getValue().equals("m") && Units2.getValue().equals("s")) {
		text_ErrorResult.setText("");
		Units3.setPromptText("");	
	}
	else if(Units1.getValue().equals("days") && Units2.getValue().equals("g")) {
		text_ErrorResult.setText("");
		Units3.setPromptText("");	
	}
	else if(Units1.getValue().equals("days") && Units2.getValue().equals("Kg")) {
		text_ErrorResult.setText("");
		Units3.setPromptText("");	
	}
	else if(Units1.getValue().equals("days") && Units2.getValue().equals("Km")) {
		text_ErrorResult.setText("");
		Units3.setPromptText("");
	}
	else if(Units1.getValue().equals("days") && Units2.getValue().equals("m")) {
		text_ErrorResult.setText("");
		Units3.setPromptText("");
	}
	else if(Units1.getValue().equals("s") && Units2.getValue().equals("Km")) {
		text_ErrorResult.setText("");
		Units3.setPromptText("");	
	}
	else if(Units1.getValue().equals("s") && Units2.getValue().equals("m")) {
		text_ErrorResult.setText("");
		Units3.setPromptText("");	
	}
	else if(Units1.getValue().equals("s") && Units2.getValue().equals("Kg")) {
		text_ErrorResult.setText("");
		Units3.setPromptText("");	
	}
	else if(Units1.getValue().equals("s") && Units2.getValue().equals("g")) {
		text_ErrorResult.setText("");
		Units3.setPromptText("");	
	}

	
}


	/**********
	 * This is the subtract routine
	 *
	 */
	private void subOperands(){
		String a = text_Operand1.getText();                     // a store value in Operand 1 
		String b = text_Operand2.getText();						// b store value in Operand 2 
		double x = Double.parseDouble(a);						// x store a in form of double
		double y = Double.parseDouble(b);						// y store a in form of double
		String c;
		if(Units1.getValue().equals(" ") && Units2.getValue().equals(" ")) {
			Units3.setPromptText(" ");}
		else if(Units1.getValue().equals("Kg") && Units2.getValue().equals("Kg")) {
			Units3.setPromptText("Kg");	
		}
		else if(Units1.getValue().equals("g") && Units2.getValue().equals("g")) {
			Units3.setPromptText("g");	
		}
		else if(Units1.getValue().equals("s") && Units2.getValue().equals("s")) {
			Units3.setPromptText("s");	
		}
		else if(Units1.getValue().equals("km") && Units2.getValue().equals("Km")) {
			Units3.setPromptText("Km");	
		}
		else if(Units1.getValue().equals("m") && Units2.getValue().equals("m")) {
			Units3.setPromptText("m");	
		}
		else if(Units1.getValue().equals("days") && Units2.getValue().equals("days")) {
			Units3.setPromptText("days");	
		}
		else if(Units1.getValue().equals("Kg") && Units2.getValue().equals("g")) {
			x=x*1000;
			c = Double.toString(x);
			text_Operand1.setText(c);
			Units3.setPromptText("g");	
		}
		else if(Units1.getValue().equals("g") && Units2.getValue().equals("Kg")) {
			y=y*1000;
			c = Double.toString(y);
			text_Operand2.setText(c);
			Units3.setPromptText("g");	
		}
		else if(Units1.getValue().equals("Km") && Units2.getValue().equals("m")) {
			x=x*1000;
			c = Double.toString(x);
			text_Operand1.setText(c);
			Units3.setPromptText("m");	
		}
		else if(Units1.getValue().equals("m") && Units2.getValue().equals("Km")) {
			y=y*1000;
			c = Double.toString(y);
			text_Operand2.setText(c);
			Units3.setPromptText("m");	
		}
		else if(Units1.getValue().equals("days") && Units2.getValue().equals("s")) {
			x=x*86400;
			c = Double.toString(x);
			text_Operand1.setText(c);
			Units3.setPromptText("s");	
		}
		else if(Units1.getValue().equals("s") && Units2.getValue().equals("days")) {
			y=y*86400;
			c = Double.toString(y);
			text_Operand2.setText(c);
			Units3.setPromptText("s");	
		}
		
		if(text_Operand1.getText().isEmpty()) {
			text_Operand1.setText("");
		}
		if(text_Operand2.getText().isEmpty()) {
			text_Operand2.setText("");
		}
		                                                                 // Check to see if both operands are defined and valid
		if (binaryOperandIssues()) 										// If there are issues with the operands, return
			return;														// without doing the computation

																		// If the operands are defined and valid, request the business logic method to do the Subtraction and return the
																		// result as a String. If there is a problem with the actual computation, an empty string is returned
		String theAnswer = perform.subtraction();						// Call the business logic Subtract method
		label_errResult.setText("");									// Reset any result error messages from before
		if (theAnswer.length() > 0) {									// Check the returned String to see if it is okay

		
			text_Result.setText(theAnswer);								// If okay, display it in the result field and
			label_Result.setText("Difference");							// change the title of the field to "Subtraction"
		}
		else {															// Some error occurred while doing the Subtraction.
			text_Result.setText("");									// Do not display a result if there is an error.
			label_Result.setText("Result");								// Reset the result label if there is an error.
			label_errResult.setText(perform.getResultErrorMessage());	// Display the error message.
		}
		if(Units1.getValue().equals("Kg") && Units2.getValue().equals("m")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("Kg") && Units2.getValue().equals("Km")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");
		}
		else if(Units1.getValue().equals("Kg") && Units2.getValue().equals("days")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");
		}
		else if(Units1.getValue().equals("Kg") && Units2.getValue().equals("s")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");
		}
		else if(Units1.getValue().equals("g") && Units2.getValue().equals("Km")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("g") && Units2.getValue().equals("m")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("g") && Units2.getValue().equals("days")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("g") && Units2.getValue().equals("s")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");
		}
		else if(Units1.getValue().equals("Km") && Units2.getValue().equals("g")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("Km") && Units2.getValue().equals("Kg")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("Km") && Units2.getValue().equals("days")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");
		}
		else if(Units1.getValue().equals("Km") && Units2.getValue().equals("s")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");
		}
		else if(Units1.getValue().equals("m") && Units2.getValue().equals("Kg")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("m") && Units2.getValue().equals("g")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");
		}
		else if(Units1.getValue().equals("m") && Units2.getValue().equals("days")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");
		}
		else if(Units1.getValue().equals("m") && Units2.getValue().equals("s")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("days") && Units2.getValue().equals("g")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("days") && Units2.getValue().equals("Kg")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("days") && Units2.getValue().equals("Km")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");
		}
		else if(Units1.getValue().equals("days") && Units2.getValue().equals("m")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");
		}
		else if(Units1.getValue().equals("s") && Units2.getValue().equals("Km")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("s") && Units2.getValue().equals("m")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("s") && Units2.getValue().equals("Kg")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("s") && Units2.getValue().equals("g")) {
			text_Result.setText("Error! Check your units");
			Units3.setPromptText("");	
		}
	}

	//Implementation of Subtraction for ErrorTerm
	
	private void subOperands1(){
		

		if(text_ErrorTerm1.getText().isEmpty()) {
			text_ErrorTerm1.setText("0.5");
		}
		if(text_ErrorTerm2 .getText().isEmpty()) {
			text_ErrorTerm2 .setText("0.5");
		}
		                                                                // Check to see if both operands are defined and valid
		if (binaryOperandIssues()) 										// If there are issues with the operands, return
			return;														// without doing the computation

																		// If the operands are defined and valid, request the business logic method to do the Subtraction and return the
																		// result as a String. If there is a problem with the actual computation, an empty string is returned
		String theAnswer1 = perform.subtractionE();						// Call the business logic Subtract method
		label_errErrorResult.setText("");									// Reset any result error messages from before
		if (theAnswer1.length() > 0) {									// Check the returned String to see if it is okay

			text_ErrorResult.setText(theAnswer1);								// If okay, display it in the result field and
			label_ErrorResult.setText("Error Term");							// change the title of the field to "Subtraction"
		}
		else {															// Some error occurred while doing the Subtraction.
			text_ErrorResult.setText("");									// Do not display a result if there is an error.
			label_ErrorResult.setText("Error Term");								// Reset the result label if there is an error.
			label_errErrorResult.setText(perform.getErrorResultErrorMessage());	// Display the error message.
		}
		if(Units1.getValue().equals("Kg") && Units2.getValue().equals("m")) {
			text_ErrorResult.setText("");
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("Kg") && Units2.getValue().equals("Km")) {
			text_ErrorResult.setText("");
			Units3.setPromptText("");
		}
		else if(Units1.getValue().equals("Kg") && Units2.getValue().equals("days")) {
			text_ErrorResult.setText("");
			Units3.setPromptText("");
		}
		else if(Units1.getValue().equals("Kg") && Units2.getValue().equals("s")) {
			text_ErrorResult.setText("");
			Units3.setPromptText("");
		}
		else if(Units1.getValue().equals("g") && Units2.getValue().equals("Km")) {
			text_ErrorResult.setText("");
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("g") && Units2.getValue().equals("m")) {
			text_ErrorResult.setText("");
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("g") && Units2.getValue().equals("days")) {
			text_ErrorResult.setText("");
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("g") && Units2.getValue().equals("s")) {
			text_ErrorResult.setText("");
			Units3.setPromptText("");
		}
		else if(Units1.getValue().equals("Km") && Units2.getValue().equals("g")) {
			text_ErrorResult.setText("");
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("Km") && Units2.getValue().equals("Kg")) {
			text_ErrorResult.setText("");
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("Km") && Units2.getValue().equals("days")) {
			text_ErrorResult.setText("");
			Units3.setPromptText("");
		}
		else if(Units1.getValue().equals("Km") && Units2.getValue().equals("s")) {
			text_ErrorResult.setText("");
			Units3.setPromptText("");
		}
		else if(Units1.getValue().equals("m") && Units2.getValue().equals("Kg")) {
			text_ErrorResult.setText("");
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("m") && Units2.getValue().equals("g")) {
			text_ErrorResult.setText("");
			Units3.setPromptText("");
		}
		else if(Units1.getValue().equals("m") && Units2.getValue().equals("days")) {
			text_ErrorResult.setText("");
			Units3.setPromptText("");
		}
		else if(Units1.getValue().equals("m") && Units2.getValue().equals("s")) {
			text_ErrorResult.setText("");
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("days") && Units2.getValue().equals("g")) {
			text_ErrorResult.setText("");
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("days") && Units2.getValue().equals("Kg")) {
			text_ErrorResult.setText("");
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("days") && Units2.getValue().equals("Km")) {
			text_ErrorResult.setText("");
			Units3.setPromptText("");
		}
		else if(Units1.getValue().equals("days") && Units2.getValue().equals("m")) {
			text_ErrorResult.setText("");
			Units3.setPromptText("");
		}
		else if(Units1.getValue().equals("s") && Units2.getValue().equals("Km")) {
			text_ErrorResult.setText("");
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("s") && Units2.getValue().equals("m")) {
			text_ErrorResult.setText("");
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("s") && Units2.getValue().equals("Kg")) {
			text_ErrorResult.setText("");
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("s") && Units2.getValue().equals("g")) {
			text_ErrorResult.setText("");
			Units3.setPromptText("");	
		}
	}

	/**********
	 * This is the multiply routine
	 *
	 */
	private void mpyOperands(){
		String a = text_Operand1.getText();                     // a store value in Operand 1 
		String b = text_Operand2.getText();						// b store value in Operand 2 
		double x = Double.parseDouble(a);						// x store a in form of double
		double y = Double.parseDouble(b);						// y store a in form of double
		String c,d;
		if(Units1.getValue().equals(" ") && Units2.getValue().equals(" ")) {
			Units3.setPromptText(" ");}
		else if(Units1.getValue().equals("Kg") && Units2.getValue().equals("Kg")) {
			Units3.setPromptText("Kg" + "\u00B2");	
		}
		else if(Units1.getValue().equals("g") && Units2.getValue().equals("g")) {
			Units3.setPromptText("g" + "\u00B2");	
		}
		else if(Units1.getValue().equals("s") && Units2.getValue().equals("s")) {
			Units3.setPromptText("s" + "\u00B2");	
		}
		else if(Units1.getValue().equals("km") && Units2.getValue().equals("Km")) {
			Units3.setPromptText("Km" + "\u00B2");	
		}
		else if(Units1.getValue().equals("m") && Units2.getValue().equals("m")) {
			Units3.setPromptText("m" + "\u00B2");	
		}
		else if(Units1.getValue().equals("days") && Units2.getValue().equals("days")) {
			Units3.setPromptText("days" + "\u00B2");	
		}
		else if(Units1.getValue().equals("Kg") && Units2.getValue().equals("g")) {
			x=x*1000;
			c = Double.toString(x);
			text_Operand1.setText(c);
			Units3.setPromptText("g" + "\u00B2");	
		}
		else if(Units1.getValue().equals("Kg") && Units2.getValue().equals("m")) {
			x=x*1000;
			c = Double.toString(x);
			text_Operand1.setText(c);
			Units3.setPromptText("g m");	
		}
		else if(Units1.getValue().equals("Kg") && Units2.getValue().equals("Km")) {
			x=x*1000;
			y=y*1000;
			c = Double.toString(x);
			d = Double.toString(y);
			text_Operand1.setText(c);
			text_Operand2.setText(d);
			Units3.setPromptText("g m");	
		}
		else if(Units1.getValue().equals("Kg") && Units2.getValue().equals("days")) {
			x=x*1000;
			y=y*86400;
			c = Double.toString(x);
			d = Double.toString(y);
			text_Operand1.setText(c);
			text_Operand2.setText(d);
			Units3.setPromptText("g s");
		}
		else if(Units1.getValue().equals("Kg") && Units2.getValue().equals("s")) {
			x=x*1000;
			c = Double.toString(x);
			text_Operand1.setText(c);
			Units3.setPromptText("g s");
		}
		else if(Units1.getValue().equals("g") && Units2.getValue().equals("Kg")) {
			y=y*1000;
			d = Double.toString(y);
			text_Operand2.setText(d);
			Units3.setPromptText("g" + "\u00B2");	
		}
		else if(Units1.getValue().equals("g") && Units2.getValue().equals("Km")) {
			y=y*1000;
			d = Double.toString(y);
			text_Operand2.setText(d);
			Units3.setPromptText("g m");	
		}
		else if(Units1.getValue().equals("g") && Units2.getValue().equals("m")) {
			Units3.setPromptText("g m");	
		}
		else if(Units1.getValue().equals("g") && Units2.getValue().equals("days")) {
			y=y*86400;
			d = Double.toString(y);
			text_Operand2.setText(d);
			Units3.setPromptText("g s");	
		}
		else if(Units1.getValue().equals("g") && Units2.getValue().equals("s")) {
			Units3.setPromptText("g s");	
		}
		else if(Units1.getValue().equals("Km") && Units2.getValue().equals("m")) {
			x=x*1000;
			c = Double.toString(x);
			text_Operand1.setText(c);
			Units3.setPromptText("m" + "\u00B2");	
		}
		else if(Units1.getValue().equals("Km") && Units2.getValue().equals("g")) {
			x=x*1000;
			c = Double.toString(x);
			text_Operand1.setText(c);
			Units3.setPromptText("m g");	
		}
		else if(Units1.getValue().equals("Km") && Units2.getValue().equals("Kg")) {
			x=x*1000;
			y=y*1000;
			c = Double.toString(x);
			d = Double.toString(y);
			text_Operand1.setText(c);
			text_Operand2.setText(d);
			Units3.setPromptText("m g");	
		}
		else if(Units1.getValue().equals("Km") && Units2.getValue().equals("days")) {
			x=x*1000;
			y=y*86400;
			c = Double.toString(x);
			d = Double.toString(y);
			text_Operand1.setText(c);
			text_Operand2.setText(d);
			Units3.setPromptText("g s");
		}
		else if(Units1.getValue().equals("Km") && Units2.getValue().equals("s")) {
			x=x*1000;
			c = Double.toString(x);
			text_Operand1.setText(c);
			Units3.setPromptText("g s");
		}
		else if(Units1.getValue().equals("m") && Units2.getValue().equals("Km")) {
			y=y*1000;
			d = Double.toString(y);
			text_Operand2.setText(d);
			Units3.setPromptText("m" + "\u00B2");	
		}
		else if(Units1.getValue().equals("m") && Units2.getValue().equals("Kg")) {
			y=y*1000;
			d = Double.toString(y);
			text_Operand2.setText(d);
			Units3.setPromptText("m g");	
		}
		else if(Units1.getValue().equals("m") && Units2.getValue().equals("g")) {
			Units3.setPromptText("m g");	
		}
		else if(Units1.getValue().equals("m") && Units2.getValue().equals("days")) {
			y=y*86400;
			d = Double.toString(y);
			text_Operand2.setText(d);
			Units3.setPromptText("m s");	
		}
		else if(Units1.getValue().equals("m") && Units2.getValue().equals("s")) {
			Units3.setPromptText("m s");	
		}
		else if(Units1.getValue().equals("days") && Units2.getValue().equals("s")) {
			x=x*86400;
			c = Double.toString(x);
			text_Operand1.setText(c);
			Units3.setPromptText("s" + "\u00B2");	
		}
		else if(Units1.getValue().equals("days") && Units2.getValue().equals("g")) {
			x=x*86400;
			c = Double.toString(x);
			text_Operand1.setText(c);
			Units3.setPromptText("s g");	
		}
		else if(Units1.getValue().equals("days") && Units2.getValue().equals("Kg")) {
			x=x*86400;
			y=y*1000;
			c = Double.toString(x);
			d = Double.toString(y);
			text_Operand1.setText(c);
			text_Operand1.setText(d);
			Units3.setPromptText("s g");	
		}
		else if(Units1.getValue().equals("days") && Units2.getValue().equals("Km")) {
			x=x*86400;
			y=y*1000;
			c = Double.toString(x);
			d = Double.toString(y);
			text_Operand1.setText(c);
			text_Operand1.setText(d);
			Units3.setPromptText("s m");
		}
		else if(Units1.getValue().equals("days") && Units2.getValue().equals("m")) {
			x=x*86400;
			c = Double.toString(x);
			text_Operand1.setText(c);
			Units3.setPromptText("s m");
		}
		else if(Units1.getValue().equals("s") && Units2.getValue().equals("days")) {
			y=y*86400;
			d = Double.toString(y);
			text_Operand2.setText(d);
			Units3.setPromptText("s" + "\u00B2");	
		}
		else if(Units1.getValue().equals("s") && Units2.getValue().equals("Km")) {
			y=y*1000;
			c = Double.toString(y);
			text_Operand1.setText(c);
			Units3.setPromptText("s m");	
		}
		else if(Units1.getValue().equals("s") && Units2.getValue().equals("m")) {
			Units3.setPromptText("s m");	
		}
		else if(Units1.getValue().equals("s") && Units2.getValue().equals("Kg")) {
			y=y*1000;
			d = Double.toString(y);
			text_Operand2.setText(d);
			Units3.setPromptText("s g");	
		}
		else if(Units1.getValue().equals("s") && Units2.getValue().equals("g")) {
			Units3.setPromptText("s g");	
		}
		
		
		if(text_Operand1.getText().isEmpty()) {
			text_Operand1.setText("");
		}
		if(text_Operand2.getText().isEmpty()) {
			text_Operand2.setText("");
		}
		// Check to see if both operands are
		if (binaryOperandIssues()) 											// If there are issues with the operands, return
			return;															// without doing the computation

																			// If the operands are defined and valid, request the business logic method to do the Multiplication and return the
																			// result as a String. If there is a problem with the actual computation, an empty string is returned
		String theAnswer = perform.multiplication();						// Call the business logic product method
		label_errResult.setText("");										// Reset any result error messages from before
		if (theAnswer.length() > 0) {										// Check the returned String to see if it is okay

			text_Result.setText(theAnswer);									// If okay, display it in the result field and
			label_Result.setText("Product");								// change the title of the field to "Multiplication"
		}
		else {																// Some error occurred while doing the Multiplication.
			text_Result.setText("");										// Do not display a result if there is an error.
			label_Result.setText("Result");									// Reset the result label if there is an error.
			label_errResult.setText(perform.getResultErrorMessage());		// Display the error message.
		}
		
		
	}
	
	
	//Implementation of Multiplication for ErrorTerm
	
	private void mpyOperands1(){
		if(text_ErrorTerm1.getText().isEmpty()) {
			text_ErrorTerm1.setText("0.5");
		}
		if(text_ErrorTerm2 .getText().isEmpty()) {
			text_ErrorTerm2 .setText("0.5");
		}
		// Check to see if both operands are
		if (binaryOperandIssues()) 											// If there are issues with the operands, return
			return;															// without doing the computation

																			// If the operands are defined and valid, request the business logic method to do the Multiplication and return the
																			// result as a String. If there is a problem with the actual computation, an empty string is returned
		String theAnswer1 = perform.multiplicationE();						// Call the business logic Product method
		label_errErrorResult.setText("");										// Reset any result error messages from before
		if (theAnswer1.length() > 0) {										// Check the returned String to see if it is okay

		
			text_ErrorResult.setText(theAnswer1);									// If okay, display it in the result field and
			label_ErrorResult.setText("Error Term");								// change the title of the field to "Multiplication"
		}
		else {																// Some error occurred while doing the Multiplication.
			text_ErrorResult.setText("");										// Do not display a result if there is an error.
			label_ErrorResult.setText("Error Term");									// Reset the result label if there is an error.
			label_errErrorResult.setText(perform.getErrorResultErrorMessage());		// Display the error message.
		}

	}


	/**********
	 * This is the divide routine.  If the divisor is zero, the divisor is declared to be invalid.
	 *
	 */
	private void divOperands(){
		
		String a = text_Operand1.getText();                     // a store value in Operand 1 
		String b = text_Operand2.getText();						// b store value in Operand 2 
		double u = Double.parseDouble(a);						// x store a in form of double
		double y = Double.parseDouble(b);						// y store a in form of double
		String c,d;
		 if(Units1.getValue().equals(" ") && Units2.getValue().equals(" ")) {
			Units3.setPromptText(" ");}
		else  if(Units1.getValue().equals("Kg") && Units2.getValue().equals("Kg")) {
			Units3.setPromptText(" ");	
		}
		else if(Units1.getValue().equals("g") && Units2.getValue().equals("g")) {
			Units3.setPromptText(" ");	
		}
		else if(Units1.getValue().equals("s") && Units2.getValue().equals("s")) {
			Units3.setPromptText(" ");	
		}
		else if(Units1.getValue().equals("km") && Units2.getValue().equals("Km")) {
			Units3.setPromptText(" ");	
		}
		else if(Units1.getValue().equals("m") && Units2.getValue().equals("m")) {
			Units3.setPromptText(" ");	
		}
		else if(Units1.getValue().equals("days") && Units2.getValue().equals("days")) {
			Units3.setPromptText(" ");	
		}
		else if(Units1.getValue().equals("Kg") && Units2.getValue().equals("g")) {
			u=u*1000;
			c = Double.toString(u);
			text_Operand1.setText(c);
			Units3.setPromptText(" ");	
		}
		else if(Units1.getValue().equals("Kg") && Units2.getValue().equals("m")) {
			u=u*1000;
			c = Double.toString(u);
			text_Operand1.setText(c);
			Units3.setPromptText("g/m");	
		}
		else if(Units1.getValue().equals("Kg") && Units2.getValue().equals("Km")) {
			u=u*1000;
			y=y*1000;
			c = Double.toString(u);
			d = Double.toString(y);
			text_Operand1.setText(c);
			text_Operand2.setText(d);
			Units3.setPromptText("g/m");	
		}
		else if(Units1.getValue().equals("Kg") && Units2.getValue().equals("days")) {
			u=u*1000;
			y=y*86400;
			c = Double.toString(u);
			d = Double.toString(y);
			text_Operand1.setText(c);
			text_Operand2.setText(d);
			Units3.setPromptText("g/s");
		}
		else if(Units1.getValue().equals("Kg") && Units2.getValue().equals("s")) {
			u=u*1000;
			c = Double.toString(u);
			text_Operand1.setText(c);
			Units3.setPromptText("g/s");
		}
		else if(Units1.getValue().equals("g") && Units2.getValue().equals("Kg")) {
			y=y*1000;
			d = Double.toString(y);
			text_Operand2.setText(d);
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("g") && Units2.getValue().equals("Km")) {
			y=y*1000;
			d = Double.toString(y);
			text_Operand2.setText(d);
			Units3.setPromptText("g/m");	
		}
		else if(Units1.getValue().equals("g") && Units2.getValue().equals("m")) {
			Units3.setPromptText("g/m");	
		}
		else if(Units1.getValue().equals("g") && Units2.getValue().equals("days")) {
			y=y*86400;
			d = Double.toString(y);
			text_Operand2.setText(d);
			Units3.setPromptText("g/s");	
		}
		else if(Units1.getValue().equals("g") && Units2.getValue().equals("s")) {
			Units3.setPromptText("g/s");	
		}
		else if(Units1.getValue().equals("Km") && Units2.getValue().equals("m")) {
			u=u*1000;
			c = Double.toString(u);
			text_Operand1.setText(c);
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("Km") && Units2.getValue().equals("g")) {
			u=u*1000;
			c = Double.toString(u);
			text_Operand1.setText(c);
			Units3.setPromptText("m/g");	
		}
		else if(Units1.getValue().equals("Km") && Units2.getValue().equals("Kg")) {
			u=u*1000;
			y=y*1000;
			c = Double.toString(u);
			d = Double.toString(y);
			text_Operand1.setText(c);
			text_Operand2.setText(d);
			Units3.setPromptText("m/g");	
		}
		else if(Units1.getValue().equals("Km") && Units2.getValue().equals("days")) {
			u=u*1000;
			y=y*86400;
			c = Double.toString(u);
			d = Double.toString(y);
			text_Operand1.setText(c);
			text_Operand2.setText(d);
			Units3.setPromptText("g/s");
		}
		else if(Units1.getValue().equals("Km") && Units2.getValue().equals("s")) {
			u=u*1000;
			c = Double.toString(u);
			text_Operand1.setText(c);
			Units3.setPromptText("g/s");
		}
		else if(Units1.getValue().equals("m") && Units2.getValue().equals("Km")) {
			y=y*1000;
			d = Double.toString(y);
			text_Operand2.setText(d);
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("m") && Units2.getValue().equals("Kg")) {
			y=y*1000;
			d = Double.toString(y);
			text_Operand2.setText(d);
			Units3.setPromptText("m/g");	
		}
		else if(Units1.getValue().equals("m") && Units2.getValue().equals("g")) {
			Units3.setPromptText("m/g");	
		}
		else if(Units1.getValue().equals("m") && Units2.getValue().equals("days")) {
			y=y*86400;
			d = Double.toString(y);
			text_Operand2.setText(d);
			Units3.setPromptText("m/s");	
		}
		else if(Units1.getValue().equals("m") && Units2.getValue().equals("s")) {
			Units3.setPromptText("m/s");	
		}
		else if(Units1.getValue().equals("days") && Units2.getValue().equals("s")) {
			u=u*86400;
			c = Double.toString(u);
			text_Operand1.setText(c);
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("days") && Units2.getValue().equals("g")) {
			u=u*86400;
			c = Double.toString(u);
			text_Operand1.setText(c);
			Units3.setPromptText("s/g");	
		}
		else if(Units1.getValue().equals("days") && Units2.getValue().equals("Kg")) {
			u=u*86400;
			y=y*1000;
			c = Double.toString(u);
			d = Double.toString(y);
			text_Operand1.setText(c);
			text_Operand2.setText(d);
			Units3.setPromptText("s/g");	
		}
		else if(Units1.getValue().equals("days") && Units2.getValue().equals("Km")) {
			u=u*86400;
			y=y*1000;
			c = Double.toString(u);
			d = Double.toString(y);
			text_Operand1.setText(c);
			text_Operand2.setText(d);
			Units3.setPromptText("s/m");
		}
		else if(Units1.getValue().equals("days") && Units2.getValue().equals("m")) {
			u=u*86400;
			c = Double.toString(u);
			text_Operand1.setText(c);
			Units3.setPromptText("s/m");
		}
		else if(Units1.getValue().equals("s") && Units2.getValue().equals("days")) {
			y=y*86400;
			d = Double.toString(y);
			text_Operand2.setText(d);
			Units3.setPromptText("");	
		}
		else if(Units1.getValue().equals("s") && Units2.getValue().equals("Km")) {
			y=y*1000;
			d = Double.toString(y);
			text_Operand2.setText(d);
			Units3.setPromptText("s/m");	
		}
		else if(Units1.getValue().equals("s") && Units2.getValue().equals("m")) {
			Units3.setPromptText("s/m");	
		}
		else if(Units1.getValue().equals("s") && Units2.getValue().equals("Kg")) {
			y=y*1000;
			d = Double.toString(y);
			text_Operand2.setText(d);
			Units3.setPromptText("s/g");	
		}
		else if(Units1.getValue().equals("s") && Units2.getValue().equals("g")) {
			Units3.setPromptText("s/g");	
		}
		
		if(text_Operand1.getText().isEmpty()) {
			text_Operand1.setText("");
		}
		if(text_Operand2.getText().isEmpty()) {
			text_Operand2.setText("");
		}

		if (binaryOperandIssues()) 										// If there are issues with the operands, return
			return;														// without doing the computation
		double x = Double.parseDouble(text_Operand2.getText());
		if (x==0f) {
			label_errResult.setText("Divide by zero is not allowed");
			text_Result.setText("");

		}
		else {String theAnswer = perform.division();					// Call the business logic Division method
		label_errResult.setText("");									// Reset any result error messages from before
		if (theAnswer.length() > 0) {									// Check the returned String to see if it is okay

			
			text_Result.setText(theAnswer);								// If okay, display it in the result field and
			label_Result.setText("Division");							// change the title of the field to "Divide"
		}
		else {															// Some error occurred while doing the division.
			text_Result.setText("");									// Do not display a result if there is an error.
			label_Result.setText("Result");								// Reset the result label if there is an error.
			label_errResult.setText(perform.getResultErrorMessage());	// Display the error message.
		}

		}
		
	}

        //Implementation of Division for ErrorTerm
	private void divOperands1(){
		if(text_ErrorTerm1.getText().isEmpty()) {
			text_ErrorTerm1.setText("0.5");
		}
		if(text_ErrorTerm2 .getText().isEmpty()) {
			text_ErrorTerm2 .setText("0.5");
		}

		if (binaryOperandIssues()) 										// If there are issues with the operands, return
			return;														// without doing the computation
		String theAnswer1 = perform.divisionE();					// Call the business logic Division method
		label_errErrorResult.setText("");									// Reset any result error messages from before
		if (theAnswer1.length() > 0) {									// Check the returned String to see if it is okay

		
			text_ErrorResult.setText(theAnswer1);								// If okay, display it in the result field and
			label_ErrorResult.setText("Error Term");							// change the title of the field to "Divide"
		}
		else {															// Some error occurred while doing the division.
			text_ErrorResult.setText("");									// Do not display a result if there is an error.
			label_ErrorResult.setText("Error Term");								// Reset the result label if there is an error.
			label_errErrorResult.setText(perform.getErrorResultErrorMessage());	// Display the error message.
		}

		}
	
	
	/**********
	 * This is the square root routine.
	 *
	 */
	private void sqrtOperands(){
		
		
		if(text_Operand1.getText().isEmpty()) {
			text_Operand1.setText("");
		}

					if(text_Operand1.getText().charAt(0)=='-') {
						text_Result.setText("Not Allowed...!");
						return;
					}
		if (unaryOperandIssues());										// If there are issues with the operands, return
														                // without doing the computation

		if (text_Operand2.getLength() != 0) {
			text_Operand2.setText("");
			label_errOperand2.setText("Second Input not Required.");
		}

		                                                                 // If the operands are defined and valid, request the business logic method to do the Square Root  and return th																// result as a String. If there is a problem with the actual computation, an empty string is returned
		String theAnswer = perform.squareroot();						// Call the business logic Root method
		label_errResult.setText("");									// Reset any result error messages from before
		if (theAnswer.length() > 0) {									// Check the returned String to see if it is okay
			text_Result.setText(theAnswer);								// If okay, display it in the result field and
			label_Result.setText("Square Root");						// change the title of the field to "Square Root"
			}
		else {															// Some error occurred while doing the Square Root.
			text_Result.setText("");									// Do not display a result if there is an error.
			label_Result.setText("Result");								// Reset the result label if there is an error.
			label_errResult.setText(perform.getResultErrorMessage());	// Display the error message.
		}
		if(Units1.getValue().equals(" ")) {
			Units3.setPromptText(" ");
			}
		else if(Units1.getValue().equals("Kg")) {
			Units2.setPromptText(" ");
			Units3.setPromptText("Kg");	
		}
		else if(Units1.getValue().equals("g")) {
			Units3.setPromptText("g");
			Units2.setPromptText(" ");
		}
		else if(Units1.getValue().equals("s")) {
			Units3.setPromptText("s");	
			Units2.setPromptText(" ");
		}
		else if(Units1.getValue().equals("km")) {
			Units3.setPromptText("Km");
			Units2.setPromptText(" ");
		}
		else if(Units1.getValue().equals("m")) {
			Units3.setPromptText("m");
			Units2.setPromptText(" ");
		}
		else if(Units1.getValue().equals("days")) {
			Units3.setPromptText("days");
			Units2.setPromptText(" ");
		}
	}

	//Implementation of Square root for Error Term

	private void sqrtOperands1(){
	if(text_ErrorTerm1.getText().isEmpty()) {
		text_ErrorTerm1.setText("0.5");
	}
	
	if(text_ErrorTerm1.getText().charAt(0)=='-') {
		text_ErrorResult.setText("Not Allowed...!");
		return;
	}
                                                           
	if (unaryOperandIssues());										// If there are issues with the operands, return
                                                                // without doing the computation

	if (text_ErrorTerm2 .getLength() != 0) {
		text_ErrorTerm2 .setText("");
		label_errErrorTerm2.setText("Second Input not Required.");
	}

                                                                // If the operands are defined and valid, request the business 
                                                                //logic method to do the Square Root and return the
	                                                            // result as a String. If there is a problem with the actual c
                                                               //omputation, an empty string is returned
	String theAnswer1 = perform.squarerootE();						// Call the business logic Square Root method
	label_errErrorResult.setText("");									// Reset any result error messages from before
	if (theAnswer1.length() > 0) {									// Check the returned String to see if it is okay
		text_ErrorResult.setText(theAnswer1);								
		label_ErrorResult.setText("Square Root");						//If the square root operator is then change the 'Result' into 'Result'
	}
	else {															// Some error occurred while doing the Square Root.
		text_ErrorResult.setText("");									// Do not display a result if there is an error.
		label_ErrorResult.setText("Result");								// Reset the result label if there is an error.
		label_errErrorResult.setText(perform.getErrorResultErrorMessage());	// Display the error message.
	}

	}

	}