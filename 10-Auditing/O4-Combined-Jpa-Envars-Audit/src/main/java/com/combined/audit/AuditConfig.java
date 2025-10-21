package com.combined.audit;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@EnableJpaRepositories(basePackages = "com.combined.repository", repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
public class AuditConfig {

	@Bean
	AuditorAware<String> auditorProvider() {
		return () -> Optional.of(getCurrentAuditor());
	}

	private String getCurrentAuditor() {
		return "registered by meta-data";
	}

}
