package vn.edu.tdtu.javatech.springcommerce;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "select * from products p where p.name like '%oxford%' or p.description like '%oxford%'" , nativeQuery = true)
    public List<Product> findByOxford();
    
    @Query(value = "select * from products p where p.name like '%derby%' or p.description like '%derby%'" , nativeQuery = true)
    public List<Product> findByDerby();
    
    @Query(value = "select * from products p where p.name like '%loafer%' or p.description like '%loafer%'" , nativeQuery = true)
    public List<Product> findByLoafer();
    
    @Query(value = "select * from products p where p.name like '%boots%' or p.description like '%boots%'" , nativeQuery = true)
    public List<Product> findByBoots();
    
    @Query(value = "select * from products p where p.name like '%sneaker%' or p.description like '%sneaker%'" , nativeQuery = true)
    public List<Product> findBySneaker();
    
    @Query(value = "select * from products p where p.name like '%monkstrap%' or p.description like '%monkstraps%'" , nativeQuery = true)
    public List<Product> findByMonkstrap();
    
    @Query(value = "select * from products p where p.name like '%wallet%'" , nativeQuery = true)
    public List<Product> findByWallet();
    
    @Query(value = "select * from products p where p.name like '%holder%'" , nativeQuery = true)
    public List<Product> findByHolder();
    
    @Query(value = "select * from products p where p.name like '%pouch%'" , nativeQuery = true)
    public List<Product> findByBag();
    
    @Query(value = "select * from products p where p.name like '%belt%'" , nativeQuery = true)
    public List<Product> findByBelt();
    
    @Query(value = "select * from products p where p.name like '%sock%' or p.description like '%tất mang giày%' or p.description like '%vớ mang giày%'" , nativeQuery = true)
    public List<Product> findBySock();
    
    @Query(value = "select * from products p where p.name like '%newgen%' or p.description like '%NEW GEN%'" , nativeQuery = true)
    public List<Product> findByNewGen();
    
    @Query(value = "select * from products p where p.name like '%classic%' or p.description like '%classic%'" , nativeQuery = true)
    public List<Product> findByClassic();
    
    @Query(value = "select * from products p where p.name like '%brogues%' or p.description like '%brogues%'" , nativeQuery = true)
    public List<Product> findByBrogues();
    
    @Query(value = "select * from products p where p.name like '%james%' or p.description like '%james%'" , nativeQuery = true)
    public List<Product> findByJames();

    @Query(value = "select * from products p where p.name like '%horsehair brush%' or p.name like '%shoes cream%' or p.name like '%shoes horn%'" , nativeQuery = true)
    public List<Product> findByCare();
    
    @Query(value = "select * from products p where p.name like '%gift card%'" , nativeQuery = true)
    public List<Product> findByGift();
    
    @Query(value = "select * from products p where id = ?1" , nativeQuery = true)
    public List<Product> findAddById(Integer id);
    
    @Query(value = "select * from users  where name = ?1" , nativeQuery = true)
    public Product findIdByName(String name);
    
    @Query(value = "select id from users  where name = ?1" , nativeQuery = true)
    public Integer findIdUs(String name);
}
