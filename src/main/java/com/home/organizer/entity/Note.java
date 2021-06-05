package com.home.organizer.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="note")
@Getter @Setter
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name="date_created")
    @CreationTimestamp
    private Date dateCreated;

    @Column(name="last_updated")
    @UpdateTimestamp
    private Date lastUpdated;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}
