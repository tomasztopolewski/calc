package calc;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import static java.lang.Math.pow;

public class FXMLDocCalcController implements Initializable {

    private String charAddition = "+", charSubtraction = "-", charMultiplication = "*", charDivision = "/", charExponentiation = "^", period = ".";
    private String digit0 = "0", digit1 = "1", digit2 = "2", digit3 = "3", digit4 = "4", digit5 = "5", digit6 = "6", digit7 = "7", digit8 = "8", digit9 = "9"; 

    @FXML
    private AnchorPane CalcPane;

    @FXML
    private TextField workSpace;

    @FXML
    private Button button0;
    @FXML
    public void handleButton0Action(ActionEvent actionEvent) {
        if (workSpace.getText().indexOf("Nie") == -1) {
            if (!workSpace.getText().startsWith(digit0, 0) || (workSpace.getText().startsWith(digit0 + period) ||
                    workSpace.getText().endsWith(period) || workSpace.getText().endsWith(digit1) || workSpace.getText().endsWith(digit2) ||
                    workSpace.getText().endsWith(digit3) || workSpace.getText().endsWith(digit4) || workSpace.getText().endsWith(digit5) ||
                    workSpace.getText().endsWith(digit6) || workSpace.getText().endsWith(digit7) || workSpace.getText().endsWith(digit8) ||
                    workSpace.getText().endsWith(digit9)))
                workSpace.setText(workSpace.getText() + digit0);
        }
    }

    @FXML
    private Button button1;
    public void handleButton1Action(ActionEvent actionEvent) {
        if (workSpace.getText().indexOf("Nie") == -1) workSpace.setText(workSpace.getText() + digit1);
    }

    @FXML
    private Button button2;
    public void handleButton2Action(ActionEvent actionEvent) {
        if (workSpace.getText().indexOf("Nie") == -1) workSpace.setText(workSpace.getText() + digit2);

    }

    @FXML
    private Button button3;
    public void handleButton3Action(ActionEvent actionEvent) {
        if (workSpace.getText().indexOf("Nie") == -1) workSpace.setText(workSpace.getText() + digit3);
    }

    @FXML
    private Button button4;
    public void handleButton4Action(ActionEvent actionEvent) {
        if (workSpace.getText().indexOf("Nie") == -1) workSpace.setText(workSpace.getText() + digit4);
    }

    @FXML
    private Button button5;
    public void handleButton5Action(ActionEvent actionEvent) {
        if (workSpace.getText().indexOf("Nie") == -1) workSpace.setText(workSpace.getText() + digit5);
    }

    @FXML
    private Button button6;
    public void handleButton6Action(ActionEvent actionEvent) {
        if (workSpace.getText().indexOf("Nie") == -1) workSpace.setText(workSpace.getText() + digit6);
    }

    @FXML
    private Button button7;
    public void handleButton7Action(ActionEvent actionEvent) {
        if (workSpace.getText().indexOf("Nie") == -1) workSpace.setText(workSpace.getText() + digit7);
    }

    @FXML
    private Button button8;
    public void handleButton8Action(ActionEvent actionEvent) {
        if (workSpace.getText().indexOf("Nie") == -1) workSpace.setText(workSpace.getText() + digit8);
    }

    @FXML
    private Button button9;
    public void handleButton9Action(ActionEvent actionEvent) {
        if (workSpace.getText().indexOf(".") == -1) workSpace.setText(workSpace.getText() + digit9);
    }

    @FXML
    private Button buttonPeriod;
    public void handleButtonCommaAction(ActionEvent actionEvent) {
        if (workSpace.getText().indexOf(period) == -1) {
            // nie została użyta kropka
            if (ifEndsWithDigit(workSpace.getText())) workSpace.setText(workSpace.getText() + period);
        } else if (workSpace.getText().indexOf(period) > 0) {
            // została użyta co najmniej jedna kropka
            int indexFirst = workSpace.getText().indexOf(period);
            indexFirst++;

            if (workSpace.getText().indexOf(period, indexFirst) == -1) {
                // została użyta jedna kropka
                if (ifEndsWithDigit(workSpace.getText()) && ifItIsCharOfOperation(workSpace.getText()) && !ifItIsCharOfOperation(workSpace.getText(), indexFirst-1)) workSpace.setText(workSpace.getText() + period);
            } /*else if (workSpace.getText().indexOf(period, indexFirst) > indexFirst) { //zostały użyte co najmniej dwa przecinki }*/
        }
    }


    @FXML
    private Button buttonClear;
    public void handleButtonClearAction(ActionEvent actionEvent) {
        workSpace.setText("");
    }

    @FXML
    private Button buttonClearTheEndChar;
    public void handleButtonClearTheEndCharAction(ActionEvent actionEvent) {
        if (workSpace.getText().length() > 0) workSpace.setText(workSpace.getText().substring(0, workSpace.getText().length()-1).trim());
    }



    @FXML
    private Button buttonCalc;
    public void handleButtonCalcAction(ActionEvent actionEvent) {
        if (ifItIsCharOfOperation(workSpace.getText()) && ifEndsWithDigit(workSpace.getText())) {
            String[] x = operationToTable(workSpace.getText());

            double numberFirts = Double.valueOf(x[0]);
            double numberSecond = Double.valueOf(x[1]);

            double result = 0;
            String resultToView = "";
            if (x[2].equals(charAddition)) result = numberFirts + numberSecond;
            else if (x[2].equals(charSubtraction)) result = numberFirts - numberSecond;
            else if (x[2].equals(charMultiplication)) result = numberFirts * numberSecond;
            else if (x[2].equals(charDivision) && numberSecond != 0) result = numberFirts / numberSecond;
            else if (x[2].equals(charDivision) && numberSecond == 0) resultToView = "Nie dziel przez zero.";
            else if (x[2].equals(charExponentiation)) result = pow(numberFirts, numberSecond);

            if (resultToView.equals("")) {
                resultToView = String.valueOf(result);
                if (resultToView.endsWith(".0")) resultToView = resultToView.substring(0, resultToView.length()-2);
                workSpace.setText(resultToView);
            } else workSpace.setText(resultToView);

        }
    }



    @FXML
    private Button buttonAddition;
    public void handleButtonAdditionAction(ActionEvent actionEvent) {
        if (ifEndsWithDigit(workSpace.getText()) &&!ifItIsCharOfOperation(workSpace.getText())) workSpace.setText(workSpace.getText() + " " + charAddition + " ");
    }

    @FXML
    private Button buttonSubtraction;
    public void handleButtonSubtractionAction(ActionEvent actionEvent) {
        if (ifEndsWithDigit(workSpace.getText()) && !ifItIsCharOfOperation(workSpace.getText())) workSpace.setText(workSpace.getText() + " " + charSubtraction + " ");
    }

    @FXML
    private Button buttonMultiplication;
    public void handleButtonMultiplicationAction(ActionEvent actionEvent) {
        if (ifEndsWithDigit(workSpace.getText()) && !ifItIsCharOfOperation(workSpace.getText())) workSpace.setText(workSpace.getText() + " " + charMultiplication + " ");
    }

    @FXML
    private Button buttonDivision;
    public void handleButtonDivisionAction(ActionEvent actionEvent) {
        if (ifEndsWithDigit(workSpace.getText()) && !ifItIsCharOfOperation(workSpace.getText())) workSpace.setText(workSpace.getText() + " " + charDivision + " ");
    }

    @FXML
    private Button buttonExponentiation;
    public void handleButtonExponentiationAction(ActionEvent actionEvent) {
        if (ifEndsWithDigit(workSpace.getText()) && !ifItIsCharOfOperation(workSpace.getText())) workSpace.setText(workSpace.getText() + " " + charExponentiation + " ");
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {}


    ////////////////////////////////////////
    //// METODY OPERACYJNE

    /**
     * Funkcja 'operationToTable' wywoływana przez funkcję odpowiedzialną za wywoływanie eventu dla button 'Calc'.
     * Funkcja dzieli zmienną 'operation', w której znajduje się polecenie do obliczenia, w zależności od
     * znaku działania w poleceniu na pierwszą część do znaku operacji i umieszcza ją w index = 0, a drugą część po
     * znaku operacji umieszcza ją w index = 1. W index = 2 funkcja dodaje znak operacji, wg którego nastąpił
     * podział polecenia.
     */
        private String[] operationToTable(String operation) {
            String[] x = null;
            String[] s = new String[3];
            String charOfOOperation = null;
            if (!(operation.indexOf(charAddition) == -1)) {
                x = operation.split(Pattern.quote(charAddition));
                charOfOOperation = charAddition;
            }
            else if (!(operation.indexOf(charSubtraction) == -1)) {
                x = operation.split(Pattern.quote(charSubtraction));
                charOfOOperation = charSubtraction;
            }
            else if (!(operation.indexOf(charMultiplication) == -1)) {
                x = operation.split(Pattern.quote(charMultiplication));
                charOfOOperation = charMultiplication;
            }
            else if (!(operation.indexOf(charDivision) == -1)) {
                x = operation.split(Pattern.quote(charDivision));
                charOfOOperation = charDivision;
            }
            else if (!(operation.indexOf(charExponentiation) == -1)) {
                x = operation.split(Pattern.quote(charExponentiation));
                charOfOOperation = charExponentiation;
            }
            for (int i = 0; i < s.length-1; i++) s[i] = x[i];
            s[s.length-1] = charOfOOperation;

            return clearOfTableFromWhiteChars(s);
        }

    /**
     * Funkcja 'clearOfTableFromWhiteChars' usuwa z każdego elementu tablicy znaki białe (spacje itp.).
     * Funkcja używa metody definiowanej dla String 'trim()'.
     */
        public static String[] clearOfTableFromWhiteChars(String[] s) {
            for (int i = 0; i < s.length; i++) s[i] = s[i].trim();
            return s;
        }


    ////////////////////////////////////////
    //// METODY LOGICZNE (PORÓWNAWCZE)

        private boolean ifEndsWithDigit(String string) {
            return (string.endsWith(digit0) || string.endsWith(digit1) || string.endsWith(digit2) ||
                    string.endsWith(digit3) || string.endsWith(digit4) || string.endsWith(digit5) ||
                    string.endsWith(digit6) || string.endsWith(digit7) || string.endsWith(digit8) ||
                    string.endsWith(digit9));
        }

        private boolean ifItIsCharOfOperation(String string) {
            return ((string.indexOf(charAddition) >= 0) || (string.indexOf(charSubtraction) >= 0) || (string.indexOf(charMultiplication) >= 0) ||
                    (string.indexOf(charDivision) >= 0) || (string.indexOf(charExponentiation) >= 0));
        }

    /**
     * Funkcja bada występowanie jakiegokolwiek znaku operacji po zadanym index'ie w Stringu.
     * Jeśli jakikolwiek znak operacji występuję po zadanym index'ie funkcja zwróci true,
     * a w przeciwnym razie zwróci false.
     */
        private boolean ifItIsCharOfOperation(String string, int index) {
            if (string.indexOf(charAddition) > 0) {
                if (string.indexOf(charAddition) < index)           return true;
            } else if (string.indexOf(charSubtraction) > 0) {
                if (string.indexOf(charSubtraction) < index)        return true;
            } else if (string.indexOf(charMultiplication) > 0) {
                if (string.indexOf(charMultiplication) < index)     return true;
            } else if (string.indexOf(charDivision) > 0) {
                if (string.indexOf(charDivision) < index)           return true;
            } else if (string.indexOf(charExponentiation) > 0) {
                if (string.indexOf(charExponentiation) < index)     return true;
            } return false;
        }

}
