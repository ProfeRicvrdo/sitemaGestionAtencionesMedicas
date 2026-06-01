package cl.duoc.atencionMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.atencionMS.model.TipoAtencion;

@Repository
public interface TipoAtencionRepository extends JpaRepository<TipoAtencion, Integer> {

}