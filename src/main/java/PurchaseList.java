import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity
@ToString
public class PurchaseList {
    @EmbeddedId
    private PurchaseListId id;

    private int price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    @Setter
    @Getter
    @NoArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    static class PurchaseListId implements Serializable {

        @Column(name = "student_name")
        private String studentsName;

        @Column(name = "course_name")
        private String courseName;
        }
    }
