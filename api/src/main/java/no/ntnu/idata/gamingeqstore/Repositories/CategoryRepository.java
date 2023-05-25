package no.ntnu.idata.gamingeqstore.Repositories;

import no.ntnu.idata.gamingeqstore.Entities.ProductCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<ProductCategory, Long> {

    @Override
    <S extends ProductCategory> S save(S entity);

    @Override
    <S extends ProductCategory> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    Optional<ProductCategory> findById(Long aLong);

    @Override
    Iterable<ProductCategory> findAll();

    @Override
    void delete(ProductCategory entity);

    @Override
    long count();


    Optional<ProductCategory> findByCategoryName(String categoryName);
}
