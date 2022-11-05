package net.suppressio.core.login.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.suppressio.core.login.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(final String email);
	
	User findByUsername(final String fullname);

}
