package interfaces;

import ee.ivkhkdev.Clothing_StoreJavaFX.model.Order;
import javafx.collections.ObservableList;
import java.time.LocalDate;
import java.util.List;

public interface OrderService extends AppService<Order> {
    // Получение списка всех заказов
    ObservableList<Order> getAllOrders();

    // Расчёт дохода за указанный день
    Double calculateRevenueForDay(LocalDate day);

    // Расчёт дохода за указанный месяц (по году и месяцу)
    Double calculateRevenueForMonth(int year, int month);

    // Расчёт дохода за указанный год
    Double calculateRevenueForYear(int year);

    List<Object[]> getSalesRatingAllTime();
    List<Object[]> getSalesRatingByYear(int year);
    List<Object[]> getSalesRatingByMonth(int year, int month);
    List<Object[]> getSalesRatingByWeek(int year, int week);
}
