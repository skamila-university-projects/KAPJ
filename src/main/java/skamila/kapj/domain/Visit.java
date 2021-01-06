package skamila.kapj.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "doctor_id")
    long doctorId;

    @Column(name = "patient_id")
    long patientId;

    private LocalDateTime time;

    private int lengthOfVisit;

    private int price;

    private boolean confirmed;

    private boolean canceled;

}
