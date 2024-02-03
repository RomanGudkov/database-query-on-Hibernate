import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@EqualsAndHashCode
@Embeddable
public class IdStudentCourseKey implements Serializable {

    @Column(name = "student_id")
    private int studentId;

    @Column(name = "course_id")
    private int courseId;

    public IdStudentCourseKey() {
    }

    public IdStudentCourseKey(int studentId, int courseId) {
        super();
        this.studentId = studentId;
        this.courseId = courseId;
    }
}
