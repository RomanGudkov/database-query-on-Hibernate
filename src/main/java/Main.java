import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Session session = CreateSessionFactory().openSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        Transaction transaction = session.beginTransaction();

        CriteriaQuery<PurchaseList> queryPurchaseList = purchaseListCriteriaQuery(criteriaBuilder);
        CriteriaQuery<Student> queryStudents = StudentsCriteriaQuery(criteriaBuilder);

        List<PurchaseList> purchaseListList = session.createQuery(queryPurchaseList)
                .getResultList();
        List<Student> studentList = session.createQuery(queryStudents)
                .getResultList();

        for (PurchaseList purchaseList : purchaseListList
        ) {
            String studentName = purchaseList.getId()
                    .getStudentsName();
            String courseName = purchaseList.getId()
                    .getCourseName();
            for (Student student : studentList
            ) {
                if (student.getName()
                        .equals(studentName)) {
                    int studentId = student.getId();

                    for (Course course : student.getCourseList()
                    ) {
                        if (course.getName()
                                .equals(courseName)) {
                            int courseId = course.getId();
                            System.out.println("student_id - " + student.getId() + " -> " + "course_id - " + course.getId());

                            LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList();
                            linkedPurchaseList.setId(new IdStudentCourseKey(studentId, courseId));
                            session.save(linkedPurchaseList);
                        }
                    }
                }
            }
        }
        transaction.commit();
        CreateSessionFactory().close();
    }

    static private SessionFactory CreateSessionFactory() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
                .build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder()
                .build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
                .build();
        return sessionFactory;
    }

    static private CriteriaQuery<PurchaseList> purchaseListCriteriaQuery(CriteriaBuilder criteriaBuilder) {
        CriteriaQuery<PurchaseList> query = criteriaBuilder.createQuery(PurchaseList.class);
        Root<PurchaseList> root = query.from(PurchaseList.class);
        return query.select(root);
    }

    static private CriteriaQuery<Student> StudentsCriteriaQuery(CriteriaBuilder criteriaBuilder) {
        CriteriaQuery<Student> query = criteriaBuilder.createQuery(Student.class);
        Root<Student> root = query.from(Student.class);
        return query.select(root);
    }
}