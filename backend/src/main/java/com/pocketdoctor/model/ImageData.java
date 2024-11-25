package com.pocketdoctor.model;

import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Id; // Correct import for JPA


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Getter
    private String userType;
    private String type;

    @Lob
    @Column(name = "image_data", columnDefinition = "LONGBLOB", nullable = false)
    private byte[] imageData;

    public void setUserType(String userType) {
        this.userType = userType.toLowerCase();
    }
}

