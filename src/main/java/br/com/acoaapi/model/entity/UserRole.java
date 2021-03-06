package br.com.acoaapi.model.entity;

import javax.persistence.*;
import java.io.Serializable;

//@Entity
public class UserRole {

    @Embeddable
    public static class Id implements Serializable {
        private static final long serialVersionUID = 1322120000551624359L;

        @Column(name = "app_user_id")
        protected Long userId;

        @Enumerated(EnumType.STRING)
        @Column(name = "role")
        protected Role role;

        public Id() {
        }

        public Id(Long userId, Role role) {
            this.userId = userId;
            this.role = role;
        }
    }

    @EmbeddedId
    Id id = new Id();

    @Enumerated(EnumType.STRING)
    @Column(name = "role", insertable = false, updatable = false)
    protected Role role;

    public Role getRole() {
        return role;
    }
}
