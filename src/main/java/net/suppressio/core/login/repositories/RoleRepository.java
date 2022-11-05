package net.suppressio.core.login.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.suppressio.core.login.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findByRole(final String role);
}
