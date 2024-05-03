package org.switf.lugares.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.switf.lugares.models.PlaceModel;

@Repository
public interface PlaceJpaRepository extends JpaRepository<PlaceModel, Integer> {
}

