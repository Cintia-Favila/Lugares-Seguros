package org.switf.lugares.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.switf.lugares.models.CommentModel;

@Repository
public interface CommentJpaRepository extends JpaRepository<CommentModel, Integer> {
}

