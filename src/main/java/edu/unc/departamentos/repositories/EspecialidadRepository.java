package edu.unc.departamentos.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import edu.unc.departamentos.domain.Especialidad;

public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {
 
}
