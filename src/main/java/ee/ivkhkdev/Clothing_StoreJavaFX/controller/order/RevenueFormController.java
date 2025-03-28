package ee.ivkhkdev.Clothing_StoreJavaFX.controller.order;

import ee.ivkhkdev.Clothing_StoreJavaFX.tools.FormLoader;
import interfaces.OrderService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ResourceBundle;

@Component
public class RevenueFormController implements Initializable {

    @FXML private RadioButton rbDay;
    @FXML private RadioButton rbMonth;
    @FXML private RadioButton rbYear;
    @FXML private TextField tfPeriod;
    @FXML private Label lblRevenue;
    @FXML private ToggleGroup tgPeriod;

    private final OrderService orderService;
    private final FormLoader formLoader;

    public RevenueFormController(OrderService orderService, FormLoader formLoader) {
        this.orderService = orderService;
        this.formLoader = formLoader;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Можно установить значение по умолчанию, например, выбрать "За день"
        rbDay.setSelected(true);
    }

    @FXML
    private void calculateRevenue() {
        String input = tfPeriod.getText().trim();
        double revenue = 0.0;
        try {
            if (rbDay.isSelected()) {
                // Ожидается формат: YYYY-MM-DD
                LocalDate day = LocalDate.parse(input);
                revenue = orderService.calculateRevenueForDay(day);
                lblRevenue.setText("Доход за " + day + ": " + revenue);
            } else if (rbMonth.isSelected()) {
                // Ожидается формат: YYYY-MM
                String[] parts = input.split("-");
                if (parts.length != 2) {
                    lblRevenue.setText("Введите месяц в формате YYYY-MM");
                    return;
                }
                int year = Integer.parseInt(parts[0]);
                int month = Integer.parseInt(parts[1]);
                revenue = orderService.calculateRevenueForMonth(year, month);
                lblRevenue.setText("Доход за " + input + ": " + revenue);
            } else if (rbYear.isSelected()) {
                // Ожидается формат: YYYY
                int year = Integer.parseInt(input);
                revenue = orderService.calculateRevenueForYear(year);
                lblRevenue.setText("Доход за " + year + " год: " + revenue);
            }
        } catch (Exception e) {
            lblRevenue.setText("Ошибка при расчёте: " + e.getMessage());
        }
    }

    @FXML
    private void cancel() {
        formLoader.loadMainForm();

    }

    private void closeWindow() {
        Stage stage = (Stage) tfPeriod.getScene().getWindow();
        stage.close();
    }
}
