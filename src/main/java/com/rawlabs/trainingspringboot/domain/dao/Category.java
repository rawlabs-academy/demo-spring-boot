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
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category")
@SQLDelete(sql = "update category set is_deleted = true where id = ?")
@Where(clause = "is_deleted = false")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            description = "Generated ID",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "1"
    )
    private Long id;

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

    @Column(name = "name", nullable = false)
    @Schema(
            description = "Category name",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "Programming"
    )
    private String name;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "category")
    private List<Book> books;

}
