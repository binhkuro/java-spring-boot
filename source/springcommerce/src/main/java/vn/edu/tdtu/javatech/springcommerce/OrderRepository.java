package vn.edu.tdtu.javatech.springcommerce;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {
    @Query(value = "select  * from orders where id = ?1", nativeQuery = true)
    public Order updateStatus(Integer id);

}
