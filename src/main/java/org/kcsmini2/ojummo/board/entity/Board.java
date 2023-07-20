package org.kcsmini2.ojummo.board.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.kcsmini2.ojummo.member.entity.Member;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Member author;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private LocalDateTime createdAt;

    @Builder
    public Board(Member author, String content, String title, LocalDateTime createdAt) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }
}
