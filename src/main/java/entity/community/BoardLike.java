package entity.community;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class BoardLike { //게시글의 좋아요 기록을 저장하는 Entity

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="like_id")
    private Long likeId; //좋아요 아이디

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user; //좋아요 누른 사용자 아이디

    @ManyToOne
    @JoinColumn(name="board_id")
    private Board board; //좋아요된 게시글 아이디
}
