package ua.opnu.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "client")
@Data
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @SuppressWarnings("JpaAttributeTypeInspection")
    @OneToMany(mappedBy = "client")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Ticket> tickets = new ArrayList<>();

    public Client(String name) {
        this.name = name;
    }

}