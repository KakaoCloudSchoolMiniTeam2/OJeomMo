package org.kcsmini2.ojeommo.board.data.dto.response.detail;

import lombok.Data;
import org.kcsmini2.ojeommo.category.entity.Category;

import java.util.List;

@Data
public class CategoryResponseDto {

    List<String> categoryNames;

    private CategoryResponseDto(List<String> categoryNames) {
        this.categoryNames = categoryNames;
    }

    public static CategoryResponseDto from(List<Category> categories) {
        List<String> categoryNames = categories.stream()
                .map(Category::getCategoryName)
                .toList();
        return new CategoryResponseDto(categoryNames);
    }
}
