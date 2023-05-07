import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.*;
import java.util.HashMap;

public class MemoAppController {

    @FXML
    private ComboBox<Integer> dayC, monthC, yearC;

    @FXML
    private VBox vbox;

    @FXML
    private TextArea textArea;

    private HashMap<Date, String> hm;

    public void initialize() {
        initComboBox();
        readFromFile();
    }

    private void initComboBox() {
        final int DAYS = 31, MONTHS = 12, START_YEAR = 2020, END_YEAR = 2025;
        hm = new HashMap<>();

        for (int i = 1; i <= DAYS; i++)
            dayC.getItems().add(i);
        dayC.setValue(1);

        for (int i = 1; i <= MONTHS; i++)
            monthC.getItems().add(i);
        monthC.setValue(1);

        for (int i = START_YEAR; i <= END_YEAR; i++)
            yearC.getItems().add(i);
        yearC.setValue(START_YEAR);
    }


    @FXML
    void loadPressed(ActionEvent event) {
        Date d = new Date(dayC.getValue(), monthC.getValue(), yearC.getValue());
        textArea.setText(hm.get(d));
    }

    @FXML
    void savePressed(ActionEvent event) {
        Date d = new Date(dayC.getValue(), monthC.getValue(), yearC.getValue());
        hm.put(d, textArea.getText());

        addCloseEvent();
    }

    private void readFromFile() {
        File file = getFile();
        if (file != null) {
            try {
                FileInputStream fi = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fi);
                hm = (HashMap<Date, String>) ois.readObject();
                ois.close();
                fi.close();

            } catch (Exception e) {
                System.out.println("Error opening file");
            }
        }
    }

    private void writeToFile() {
        File file = getFile();
        try {
            FileOutputStream fo = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fo);
            out.writeObject(hm);
            out.close();
            fo.close();
        } catch (Exception e) {
            System.out.println("Error opening file");
        }
    }

    private File getFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select file");
        fileChooser.setInitialDirectory(new File("."));
        return fileChooser.showOpenDialog(null);
    }

    private void addCloseEvent() {
        Stage stage = (Stage) ((Node) vbox).getScene().getWindow();
        stage.getScene().getWindow().addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, event1 -> {
            writeToFile();
        });
    }

}
