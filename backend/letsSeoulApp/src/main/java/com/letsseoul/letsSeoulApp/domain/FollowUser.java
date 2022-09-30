package com.letsseoul.letsSeoulApp.domain;

import javax.persistence.*;

import com.letsseoul.letsSeoulApp.config.audit.Auditable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowUser extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_user_id" ,referencedColumnName = "id")
    private User fromUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_user_id",referencedColumnName = "id")
    private User toUserId;

    @Builder
    public FollowUser(User fromUserId, User toUserId) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
    }
}
