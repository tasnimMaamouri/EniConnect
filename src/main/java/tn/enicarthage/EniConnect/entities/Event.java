package tn.enicarthage.EniConnect.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eventId", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @Column(name = "title")
    private String title;

    @Column(name = "date")
    private Date date;

    @Column(name = "location")
    private String location;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING) // Specify that the status is stored as a string
    private Status status = Status.DRAFT; // Default status set to DRAFT

//    @Column(name = "to_archive_on")
//    private Date toArchiveOn;
//
//    @Column(name = "archiving_date")
//    private Date archivingDate;

    @PrePersist
    protected void onCreate() {
        creationDate = new Date();
    }
}
