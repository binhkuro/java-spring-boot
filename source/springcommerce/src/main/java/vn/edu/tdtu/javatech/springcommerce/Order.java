package vn.edu.tdtu.javatech.springcommerce;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Integer ido;

    @Column(nullable = false, length = 15)
    private String phone;

    @Column( nullable = false, length = 200)
    private String location;

    @Column (name = "price", nullable = false, length = 20)
    private String price;

    @Column(nullable = false, length = 20)
    private String status;

    public Order(Integer idOrder, String phone, String location, String price, String status) {
        this.ido = idOrder;
        this.phone = phone;
        this.location = location;
        this.price = price;
        this.status = status;
    }
}
