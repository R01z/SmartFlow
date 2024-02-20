package br.com.smart_flow.api.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.smart_flow.api.modelo.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findByByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
