package pt.feefo.jobtitleservice.config.commons.baseservice;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.feefo.jobtitleservice.config.commons.BasicEntity;
import pt.feefo.jobtitleservice.exception.repository.NotFoundException;

import java.time.Clock;
import java.time.Instant;
import java.util.List;

public abstract class BaseService<TEntity extends BasicEntity, TKey, TRepository extends JpaRepository<TEntity, TKey>> implements IService<TEntity, TKey, TRepository> {
    protected final TRepository repository;

    protected BaseService(TRepository repository) {
        this.repository = repository;
    }

    public TEntity findById(TKey id) {
        return (TEntity) this.repository.findById(id).orElseThrow(() -> {
            return new NotFoundException(String.format("Entity with Id %s was not found.", id));
        });
    }

    public List<TEntity> findAll() {
        return this.repository.findAll();
    }

    public void deleteById(TKey id) {
        TEntity entity = this.findById(id);
        entity.setDeleted(true);
        entity.setUpdatedAt(Instant.now(Clock.systemUTC()));
        this.repository.save(entity);
    }
}
