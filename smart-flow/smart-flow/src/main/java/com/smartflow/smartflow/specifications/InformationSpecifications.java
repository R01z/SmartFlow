package com.smartflow.smartflow.specifications;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import com.smartflow.smartflow.model.Information;

public class InformationSpecifications {

    public static Specification<Information> nameContains(String keyword) {
        return (root, query, builder) -> builder.like(root.get("name"), "%" + keyword + "%");
    }

    public static Specification<Information> descriptionContains(String keyword) {
        return (root, query, builder) -> builder.like(root.get("description"), "%" + keyword + "%");
    }

    public static Specification<Information> uploadDateBetween(Timestamp startDate, Timestamp endDate) {
        return (root, query, builder) -> builder.between(root.get("uploadDate"), startDate, endDate);
    }

    public static Specification<Information> teamIdEquals(Integer teamId) {
        return (root, query, builder) -> builder.equal(root.get("team").get("teamId"), teamId);
    }

    public static Specification<Information> tagsContain(List<String> tags) {
        return (root, query, builder) -> {
            if (tags == null || tags.isEmpty()) {
                return null;
            }
            return root.join("tags").in(tags);
        };
    }

    public static Specification<Information> teamIdIn(List<Integer> teamIds) {
        return (root, query, builder) -> root.get("team").get("teamId").in(teamIds);
    }

    public static Specification<Information> typeIdIn(List<Integer> typeId) {
        return (root, query, builder) -> root.get("typeInformation").get("typeInformationId").in(typeId);
    }

}
