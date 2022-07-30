package pt.feefo.jobtitleservice.config.commons.baseservice;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.feefo.jobtitleservice.config.commons.BasicEntity;

import java.util.List;

public interface IService<TEntity extends BasicEntity, TKey, TRepository extends JpaRepository<TEntity, TKey>> {
    TEntity findById(TKey var1);

    List<TEntity> findAll();

    void deleteById(TKey var1);
}
