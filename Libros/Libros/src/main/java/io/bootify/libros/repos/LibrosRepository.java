package io.bootify.libros.repos;

import io.bootify.libros.domain.Libros;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LibrosRepository extends JpaRepository<Libros, Long> {
}
