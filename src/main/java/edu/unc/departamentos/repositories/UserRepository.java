package edu.unc.departamentos.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unc.departamentos.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUsername(String username);
}
