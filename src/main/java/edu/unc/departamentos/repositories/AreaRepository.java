package edu.unc.departamentos.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import edu.unc.departamentos.domain.Area;

public interface AreaRepository extends JpaRepository<Area, Long> {
 
}
