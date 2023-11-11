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
@Table(name = "user_response")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String email;

    @Column( nullable = false, length = 100)
    private String topic;

    @Column ( nullable = false)
    private String content;

    public Contact( String name, String email, String topic, String content) {
        this.name = name;
        this.email = email;
        this.topic = topic;
        this.content = content;
    }
}
