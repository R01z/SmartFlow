package com.smartflow.smartflow.specifications;

import org.springframework.data.jpa.domain.Specification;
import com.smartflow.smartflow.model.Information;

public class InformationSpecifications {

    public static Specification<Information> nameContains(String keyword) {
        return (root, query, builder) -> builder.like(root.get("name"), "%" + keyword + "%");
    }

    public static Specification<Information> descriptionContains(String keyword) {
        return (root, query, builder) -> builder.like(root.get("description"), "%" + keyword + "%");
    }

}
