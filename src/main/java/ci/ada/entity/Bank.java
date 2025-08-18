package ci.ada.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bank")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@NotBlank(message = "Name is required")
    private String nom;
    private Integer numbreClient;
    private String ville;
    private String pays;
    private String phone;
    private String email;
    private LocalDateTime createAt;
}
