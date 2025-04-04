package ee.ivkhkdev.Clothing_StoreJavaFX.controller.order;

import ee.ivkhkdev.Clothing_StoreJavaFX.tools.FormLoader;
import interfaces.OrderService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class RevenueFormController implements Initializable {

    // Радиокнопки для выбора типа периода
    @FXML private RadioButton rbDay;
    @FXML private RadioButton rbMonth;
    @FXML private RadioButton rbYear;

    // Контейнеры, в которых будут находиться ComboBox для конкретного периода
    @FXML private StackPane spPeriodContainer;
    @FXML private HBox hbDay;
    @FXML private HBox hbMonth;
    @FXML private HBox hbYear;

    // ComboBox для выбора дня, месяца, года
    @FXML private ComboBox<String> cbDay;
    @FXML private ComboBox<String> cbMonth;
    @FXML private ComboBox<String> cbMonthYear;
    @FXML private ComboBox<String> cbYear;

    // Метка для отображения результата
    @FXML private Label lblRevenue;

    // Группа для радиокнопок
    private ToggleGroup tgPeriod;

    private final OrderService orderService;
    private final FormLoader formLoader;

    public RevenueFormController(OrderService orderService, FormLoader formLoader) {
        this.orderService = orderService;
        this.formLoader = formLoader;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Создаем ToggleGroup и объединяем радиокнопки
        tgPeriod = new ToggleGroup();
        rbDay.setToggleGroup(tgPeriod);
        rbMonth.setToggleGroup(tgPeriod);
        rbYear.setToggleGroup(tgPeriod);

        // По умолчанию выбрана опция "За день"
        rbDay.setSelected(true);

        // Инициализируем списки для ComboBox
        initComboBoxes();

        // По умолчанию показываем блок для выбора дня, скрываем остальные
        hbDay.setVisible(true);
        hbMonth.setVisible(false);
        hbYear.setVisible(false);

        // При смене выбранной радиокнопки переключаем видимые блоки
        tgPeriod.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == rbDay) {
                hbDay.setVisible(true);
                hbMonth.setVisible(false);
                hbYear.setVisible(false);
            } else if (newVal == rbMonth) {
                hbDay.setVisible(false);
                hbMonth.setVisible(true);
                hbYear.setVisible(false);
            } else if (newVal == rbYear) {
                hbDay.setVisible(false);
                hbMonth.setVisible(false);
                hbYear.setVisible(true);
            }
        });
    }

    /**
     * Инициализация ComboBox-ов для дней, месяцев и годов.
     */
    private void initComboBoxes() {
        // Список дней от 1 до 31
        List<String> days = new ArrayList<>();
        for (int i = 1; i <= 31; i++) {
            days.add(String.valueOf(i));
        }
        cbDay.setItems(FXCollections.observableArrayList(days));

        // Список месяцев от 1 до 12
        List<String> months = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            months.add(String.valueOf(i));
        }
        cbMonth.setItems(FXCollections.observableArrayList(months));

        // Список годов (например, 2023-2025)
        List<String> years = new ArrayList<>();
        for (int y = 2023; y <= 2025; y++) {
            years.add(String.valueOf(y));
        }
        cbMonthYear.setItems(FXCollections.observableArrayList(years));
        cbYear.setItems(FXCollections.observableArrayList(years));
    }

    /**
     * Рассчитывает доход магазина на основе выбранного периода.
     */
    @FXML
    private void calculateRevenue() {
        RadioButton selected = (RadioButton) tgPeriod.getSelectedToggle();
        if (selected == null) {
            lblRevenue.setText("Выберите период.");
            return;
        }

        double revenue = 0.0;
        try {
            if (selected == rbDay) {
                // Если выбрано "За день"
                String dayStr = cbDay.getSelectionModel().getSelectedItem();
                if (dayStr == null) {
                    lblRevenue.setText("Выберите день.");
                    return;
                }
                int day = Integer.parseInt(dayStr);
                // Используем текущий год и месяц для примера
                LocalDate date = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), day);
                Double result = orderService.calculateRevenueForDay(date);
                if (result == null) {
                    lblRevenue.setText("За " + date + " не было покупок.");
                } else {
                    revenue = result;
                    lblRevenue.setText("Доход за " + date + ": " + revenue);
                }
            } else if (selected == rbMonth) {
                // Если выбрано "За месяц"
                String monthStr = cbMonth.getSelectionModel().getSelectedItem();
                String yearStr = cbMonthYear.getSelectionModel().getSelectedItem();
                if (monthStr == null || yearStr == null) {
                    lblRevenue.setText("Выберите месяц и год.");
                    return;
                }
                int month = Integer.parseInt(monthStr);
                int year = Integer.parseInt(yearStr);
                Double result = orderService.calculateRevenueForMonth(year, month);
                if (result == null) {
                    lblRevenue.setText("За " + month + "." + year + " не было покупок.");
                } else {
                    revenue = result;
                    lblRevenue.setText("Доход за " + month + "." + year + ": " + revenue);
                }
            } else if (selected == rbYear) {
                // Если выбрано "За год"
                String yearStr = cbYear.getSelectionModel().getSelectedItem();
                if (yearStr == null) {
                    lblRevenue.setText("Выберите год.");
                    return;
                }
                int year = Integer.parseInt(yearStr);
                Double result = orderService.calculateRevenueForYear(year);
                if (result == null) {
                    lblRevenue.setText("За " + year + " год не было покупок.");
                } else {
                    revenue = result;
                    lblRevenue.setText("Доход за " + year + " год: " + revenue);
                }
            }
        } catch (Exception e) {
            lblRevenue.setText("Ошибка при расчёте: " + e.getMessage());
        }
    }


    @FXML
    private void cancel() {
        formLoader.loadMainForm();
    }


}
