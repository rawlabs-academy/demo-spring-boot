package com.rawlabs.trainingspringboot.domain.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
@SQLDelete(sql = "update book set is_deleted = true where id = ?")
@Where(clause = "is_deleted = false")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            description = "Generated ID",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "1"
    )
    private Long id;

    @ManyToOne
    @Schema(
            description = "Book's Author'",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Author author;

    @ManyToOne
    @Schema(
            description = "Book's category",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Category category;

    @Column(name = "created_date")
    @Schema(
            description = "Created date",
            requiredMode = Schema.RequiredMode.REQUIRED,
            pattern = "yyyy-MM-ddTHH:mm:ss.XXXZ"
    )
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    @Schema(
            description = "Modified date",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            pattern = "yyyy-MM-ddTHH:mm:ss.XXXZ"
    )
    private LocalDateTime modifiedDate;

    @Column(name = "is_deleted")
    @Schema(
            description = "Is Deleted",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "false"
    )
    private Boolean isDeleted;

    @Column(name = "title", nullable = false)
    @Schema(
            description = "Book title",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "Mastering Spring Boot"
    )
    private String title;

    @Column(name = "price", nullable = false)
    @Schema(
            description = "Book price",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "1500000"
    )
    private Integer price;

    @Column(name = "stock", nullable = false)
    @Schema(
            description = "Stock value",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "100"
    )
    private Integer stock;

}
