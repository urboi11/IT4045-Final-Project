package entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="usercomments")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserComments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userCommentId;

    private int userId;
    private int courseId;
    private String comment;
}
