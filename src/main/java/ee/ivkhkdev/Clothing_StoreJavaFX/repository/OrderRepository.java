package ee.ivkhkdev.Clothing_StoreJavaFX.repository;

import ee.ivkhkdev.Clothing_StoreJavaFX.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // Подсчёт суммы totalPrice за период
    @Query("SELECT SUM(o.totalPrice) FROM Order o WHERE o.orderDate BETWEEN :startDate AND :endDate")
    Double calculateRevenueBetween(@Param("startDate") LocalDateTime startDate,
                                   @Param("endDate") LocalDateTime endDate);

    // Для всего времени
    @Query("SELECT o.clothing, SUM(o.quantity) AS totalSold FROM Order o GROUP BY o.clothing ORDER BY totalSold DESC")
    List<Object[]> findProductSalesRatingAllTime();

    @Query("SELECT o.clothing, SUM(o.quantity) AS totalSold FROM Order o " +
            "WHERE FUNCTION('YEAR', o.orderDate) = :year " +
            "GROUP BY o.clothing ORDER BY totalSold DESC")
    List<Object[]> findProductSalesRatingByYear(@Param("year") int year);

    @Query("SELECT o.clothing, SUM(o.quantity) AS totalSold FROM Order o " +
            "WHERE FUNCTION('MONTH', o.orderDate) = :month " +
            "GROUP BY o.clothing ORDER BY totalSold DESC")
    List<Object[]> findProductSalesRatingByMonth(@Param("month") int month);

    @Query("SELECT o.clothing, SUM(o.quantity) AS totalSold FROM Order o " +
            "WHERE FUNCTION('WEEK', o.orderDate) = :week " +
            "GROUP BY o.clothing ORDER BY totalSold DESC")
    List<Object[]> findProductSalesRatingByWeek(@Param("week") int week);

}
