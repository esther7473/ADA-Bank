package ci.ada.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String prenom;
    private String clientNumber;
    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;
    private LocalDateTime createdAt;
}
