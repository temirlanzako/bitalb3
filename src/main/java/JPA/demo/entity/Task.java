package JPA.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "task")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    @Column(name = "id")
    private int id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "commentary")
    private String commentary;
    @Column(name = "phone")
    private String phone;
    @Column(name = "handled")
    private boolean handled;

    @ManyToOne
    private Course course;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Operators> operators;
}
