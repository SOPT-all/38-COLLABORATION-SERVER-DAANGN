package org.sopt.daangn.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Configuration
public class QuerydslConfig {
	@PersistenceContext
	private final EntityManager entityManager;

	public QuerydslConfig(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Bean
	public JPAQueryFactory jpaQueryFactory() {
		return new JPAQueryFactory(entityManager);
	}
}