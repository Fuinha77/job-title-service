package pt.feefo.jobtitleservice.config.commons;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.Clock;
import java.time.Instant;

@MappedSuperclass
@JsonIgnoreProperties(
        value = {"createdBy", "updatedBy", "createdAt", "updatedAt", "deleted"},
        allowGetters = true
)
@EntityListeners({AuditingEntityListener.class})
public abstract class BasicEntity {
    @CreatedDate
    @Column(
            name = "created_at"
    )
    private Instant createdAt;
    @LastModifiedDate
    @Column(
            name = "updated_at"
    )
    private Instant updatedAt;
    @CreatedBy
    @Column(
            name = "created_by"
    )
    private Long createdBy;
    @LastModifiedBy
    @Column(
            name = "updated_by"
    )
    private Long updatedBy;
    @Column(
            name = "deleted"
    )
    private Boolean deleted;

    private static Instant $default$createdAt() {
        return Instant.now(Clock.systemUTC());
    }

    private static Long $default$createdBy() {
        return 0L;
    }

    private static Boolean $default$deleted() {
        return false;
    }

    protected BasicEntity(BasicEntityBuilder<?, ?> b) {
        if (b.createdAt$set) {
            this.createdAt = b.createdAt$value;
        } else {
            this.createdAt = $default$createdAt();
        }

        this.updatedAt = b.updatedAt;
        if (b.createdBy$set) {
            this.createdBy = b.createdBy$value;
        } else {
            this.createdBy = $default$createdBy();
        }

        this.updatedBy = b.updatedBy;
        if (b.deleted$set) {
            this.deleted = b.deleted$value;
        } else {
            this.deleted = $default$deleted();
        }

    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public Instant getUpdatedAt() {
        return this.updatedAt;
    }

    public Long getCreatedBy() {
        return this.createdBy;
    }

    public Long getUpdatedBy() {
        return this.updatedBy;
    }

    public Boolean getDeleted() {
        return this.deleted;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof BasicEntity)) {
            return false;
        } else {
            BasicEntity other = (BasicEntity)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label71: {
                    Object this$createdBy = this.getCreatedBy();
                    Object other$createdBy = other.getCreatedBy();
                    if (this$createdBy == null) {
                        if (other$createdBy == null) {
                            break label71;
                        }
                    } else if (this$createdBy.equals(other$createdBy)) {
                        break label71;
                    }

                    return false;
                }

                Object this$updatedBy = this.getUpdatedBy();
                Object other$updatedBy = other.getUpdatedBy();
                if (this$updatedBy == null) {
                    if (other$updatedBy != null) {
                        return false;
                    }
                } else if (!this$updatedBy.equals(other$updatedBy)) {
                    return false;
                }

                label57: {
                    Object this$deleted = this.getDeleted();
                    Object other$deleted = other.getDeleted();
                    if (this$deleted == null) {
                        if (other$deleted == null) {
                            break label57;
                        }
                    } else if (this$deleted.equals(other$deleted)) {
                        break label57;
                    }

                    return false;
                }

                Object this$createdAt = this.getCreatedAt();
                Object other$createdAt = other.getCreatedAt();
                if (this$createdAt == null) {
                    if (other$createdAt != null) {
                        return false;
                    }
                } else if (!this$createdAt.equals(other$createdAt)) {
                    return false;
                }

                Object this$updatedAt = this.getUpdatedAt();
                Object other$updatedAt = other.getUpdatedAt();
                if (this$updatedAt == null) {
                    if (other$updatedAt == null) {
                        return true;
                    }
                } else if (this$updatedAt.equals(other$updatedAt)) {
                    return true;
                }

                return false;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof BasicEntity;
    }

    public int hashCode() {
        var PRIME = true;
        var result = 1;
        Object $createdBy = this.getCreatedBy();
        result = result * 59 + ($createdBy == null ? 43 : $createdBy.hashCode());
        Object $updatedBy = this.getUpdatedBy();
        result = result * 59 + ($updatedBy == null ? 43 : $updatedBy.hashCode());
        Object $deleted = this.getDeleted();
        result = result * 59 + ($deleted == null ? 43 : $deleted.hashCode());
        Object $createdAt = this.getCreatedAt();
        result = result * 59 + ($createdAt == null ? 43 : $createdAt.hashCode());
        Object $updatedAt = this.getUpdatedAt();
        result = result * 59 + ($updatedAt == null ? 43 : $updatedAt.hashCode());
        return result;
    }

    public String toString() {
        Instant var10000 = this.getCreatedAt();
        return "BasicEntity(createdAt=" + var10000 + ", updatedAt=" + this.getUpdatedAt() + ", createdBy=" + this.getCreatedBy() + ", updatedBy=" + this.getUpdatedBy() + ", deleted=" + this.getDeleted() + ")";
    }

    public BasicEntity() {
        this.createdAt = $default$createdAt();
        this.createdBy = $default$createdBy();
        this.deleted = $default$deleted();
    }

    public abstract static class BasicEntityBuilder<C extends BasicEntity, B extends BasicEntityBuilder<C, B>> {
        private boolean createdAt$set;
        private Instant createdAt$value;
        private Instant updatedAt;
        private boolean createdBy$set;
        private Long createdBy$value;
        private Long updatedBy;
        private boolean deleted$set;
        private Boolean deleted$value;

        public BasicEntityBuilder() {
        }

        protected abstract B self();

        public abstract C build();

        public B createdAt(Instant createdAt) {
            this.createdAt$value = createdAt;
            this.createdAt$set = true;
            return this.self();
        }

        public B updatedAt(Instant updatedAt) {
            this.updatedAt = updatedAt;
            return this.self();
        }

        public B createdBy(Long createdBy) {
            this.createdBy$value = createdBy;
            this.createdBy$set = true;
            return this.self();
        }

        public B updatedBy(Long updatedBy) {
            this.updatedBy = updatedBy;
            return this.self();
        }

        public B deleted(Boolean deleted) {
            this.deleted$value = deleted;
            this.deleted$set = true;
            return this.self();
        }

        public String toString() {
            return "BasicEntity.BasicEntityBuilder(createdAt$value=" + this.createdAt$value + ", updatedAt=" + this.updatedAt + ", createdBy$value=" + this.createdBy$value + ", updatedBy=" + this.updatedBy + ", deleted$value=" + this.deleted$value + ")";
        }
    }
}
