package leandrodev.creditapplicationsystem.entity;

import leandrodev.creditapplicationsystem.entity.abstracts.BaseEntity;
import leandrodev.creditapplicationsystem.enums.CreditApplicationStatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

import static leandrodev.creditapplicationsystem.enums.CreditApplicationStatusEnum.*;


@Table(name = "credit_applications")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreditApplication extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Enumerated(EnumType.STRING)
    private CreditApplicationStatusEnum status = PENDING;

    @OneToOne(mappedBy = "creditApplication")
    private Credit credit;

    private Double collateral;

}
