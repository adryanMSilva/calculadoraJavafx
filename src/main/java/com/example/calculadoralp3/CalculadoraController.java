package com.example.calculadoralp3;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.net.URL;
import java.util.ResourceBundle;

public class CalculadoraController implements Initializable {

    @FXML
    Button btnNum9, btnNum8, btnNum7, btnNum6, btnNum5, btnNum4, btnNum3, btnNum2, btnNum1, btnNum0;

    @FXML
    Button btnOpDiv, btnOpMulti, btnOpSub, btnOpSoma;

    @FXML
    Button btnIgual, btnApagar, btnLimpar;

    @FXML
    TextField textFieldVisor;

    @FXML
    Label labelErro;

    @FXML
    AnchorPane pane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pane.setBackground(Background.fill(Color.rgb(190,190,190)));
    }

    @FXML
    public void atualizaVisor(ActionEvent event){
        Button clickedBtn = (Button) event.getSource();

        if(textFieldVisor.getText().length() == 0){
            if(isNumericBtn(clickedBtn) || clickedBtn.equals(btnOpSub)) {
                textFieldVisor.setText(clickedBtn.getText());
            }
        } else {
            if(!isFunctionButton(clickedBtn)){
                textFieldVisor.setText(textFieldVisor.getText() + clickedBtn.getText());
            } else {
                if(clickedBtn.equals(btnLimpar)){
                    textFieldVisor.setText("");
                } else if(clickedBtn.equals(btnApagar)){
                    StringBuilder buffer = new StringBuilder(textFieldVisor.getText());
                    textFieldVisor.setText(buffer.deleteCharAt(buffer.length() - 1).toString());
                } else {
                    try {
                        Expression e = new ExpressionBuilder(textFieldVisor.getText())
                                .build();
                        textFieldVisor.setText(String.valueOf(e.evaluate()));
                    } catch (Exception e){
                        labelErro.setOpacity(1);
                        PauseTransition pause = new PauseTransition(Duration.seconds(2));
                        pause.setOnFinished(p -> {
                            labelErro.setOpacity(0);
                            textFieldVisor.setText("");
                        });
                        pause.play();
                    }
                }
            }
        }
    }

    private boolean isNumericBtn(Button clickedBtn) {
        return clickedBtn.equals(btnNum0) || clickedBtn.equals(btnNum1) || clickedBtn.equals(btnNum2) ||
                clickedBtn.equals(btnNum3) || clickedBtn.equals(btnNum4) || clickedBtn.equals(btnNum5) ||
                clickedBtn.equals(btnNum6) || clickedBtn.equals(btnNum7) || clickedBtn.equals(btnNum8) ||
                clickedBtn.equals(btnNum9);
    }

    private boolean isFunctionButton(Button clickedButton){
        return (clickedButton.equals(btnApagar) || clickedButton.equals(btnLimpar) || clickedButton.equals(btnIgual));
    }

}
