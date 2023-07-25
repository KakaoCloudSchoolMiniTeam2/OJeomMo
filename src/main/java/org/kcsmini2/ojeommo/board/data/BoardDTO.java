package org.kcsmini2.ojeommo.board.data;

import lombok.Getter;
import lombok.ToString;
import org.kcsmini2.ojeommo.board.data.entity.Board;
import org.kcsmini2.ojeommo.member.data.entity.Member;

import java.time.LocalDateTime;

@Getter
@ToString(onlyExplicitlyIncluded = true)
public class BoardDTO {

    @ToString.Include
    private Long id;
    private Member author;
    private String title;
    private String content;
    private LocalDateTime createdAt;


    private BoardDTO(Board board){
        this.id = board.getId();
        this.author = board.getAuthor();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.createdAt = board.getCreatedAt();
    }

    public static BoardDTO from(Board board){
        return new BoardDTO(board);
    }


}
