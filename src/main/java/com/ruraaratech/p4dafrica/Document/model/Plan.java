package com.ruraaratech.p4dafrica.Document.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.net.URL;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema="files")
public class Plan {
    @Id
    @GeneratedValue
    private long id;
    private String title;
    private int year;
    @Column(length=1000)
    private String file;
    private long sector;
    private long district;
    private long country;
    @Column(length = 50000)
    private URL url;
    private long createdBy;
    private long updatedBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdated;
}
