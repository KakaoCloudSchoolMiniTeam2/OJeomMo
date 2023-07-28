package org.kcsmini2.ojeommo.board.data.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.kcsmini2.ojeommo.board.data.dto.request.update.BoardUpdateRequestDTO;
import org.kcsmini2.ojeommo.comment.data.entity.Comment;
import org.kcsmini2.ojeommo.member.data.entity.Member;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member author;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "board", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Comment> comments;

    @Builder
    public Board(Member author, String content, String title, LocalDateTime createdAt) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public void update(BoardUpdateRequestDTO boardUpdateRequestDTO){
        this.title = boardUpdateRequestDTO.getTitle();
        this.content = boardUpdateRequestDTO.getContent();
    }
}
