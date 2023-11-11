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
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false, length = 1000, name = "image")
    private String image;
    
    @Column(length = 200, nullable = false)
    private String name;
    
    @Column(nullable = false, name = "price")
    private Double price;
    
    @Column(nullable = false, name = "description")
    private String description;

}
