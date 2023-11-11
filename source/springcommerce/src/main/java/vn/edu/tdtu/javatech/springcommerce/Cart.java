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
@Table(name = "cart")
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private Integer userid;
	
	@Column
	private Integer productid;
	
	@Column(nullable = false, length = 1000, name = "image")
    private String image;
	
    @Column(length = 200, nullable = false)
    private String name;
    
    @Column(nullable = false, name = "quantity")
    private Integer quantity;
    
    @Column(nullable = false, name = "price")
    private Double price;

	public Cart(Integer userid, Integer productid, String image, String name, Integer quantity, Double price) {
		super();
		this.userid = userid;
		this.productid = productid;
		this.image = image;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}
}
