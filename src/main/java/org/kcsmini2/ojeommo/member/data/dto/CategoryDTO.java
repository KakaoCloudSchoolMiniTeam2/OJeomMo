package org.kcsmini2.ojeommo.member.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {

    private String memberId;
    private Long categoryId;

    @Builder
    public CategoryDTO(String memberId, Long categoryId) {
        this.memberId = memberId;
        this.categoryId = categoryId;
    }

}
