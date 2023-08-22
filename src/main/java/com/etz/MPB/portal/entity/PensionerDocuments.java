package com.etz.MPB.portal.entity;

import com.etz.MPB.portal.enums.DocumentType;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "pensioner_documents")
public class PensionerDocuments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long pensionerId;
    @Enumerated
    private DocumentType documentType;
    private String blobReference;
    private String url;
    private String extension;
    private boolean valid;
    private Date createdOn;
    private Date updatedOn;
}
