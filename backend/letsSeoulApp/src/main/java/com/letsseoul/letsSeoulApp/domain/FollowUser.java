package com.letsseoul.letsSeoulApp.domain;

import com.letsseoul.letsSeoulApp.config.audit.Auditable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowUser extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_user_id" ,referencedColumnName = "id")
    private User fromUserId;

    @ManyToOne
    @JoinColumn(name = "to_user_id",referencedColumnName = "id")
    private User toUserId;

    @Builder
    public FollowUser(User fromUserId, User toUserId) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
    }
}
