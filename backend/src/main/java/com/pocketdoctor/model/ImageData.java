package com.pocketdoctor.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    private String type;

    @Lob
    @Column(name = "image_data", columnDefinition = "BLOB", nullable = false)
    private byte[] imageData;
}

