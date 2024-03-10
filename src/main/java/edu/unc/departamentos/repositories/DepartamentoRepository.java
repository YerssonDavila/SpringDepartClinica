package edu.unc.departamentos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unc.departamentos.domain.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

}
