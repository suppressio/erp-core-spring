package net.suppressio.core.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.suppressio.core.demo.models.DemoContent;

public interface DemoContentRepository extends JpaRepository<DemoContent, Long>{

	DemoContent findByContent(final String content);
}
