import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Subscriptions {
    @EmbeddedId
    private IdStudentCourseKey id;

    @Column(name = "subscription_date")
    private Date subscriptionDate;
}
