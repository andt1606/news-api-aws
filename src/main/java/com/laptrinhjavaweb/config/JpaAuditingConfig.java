package com.laptrinhjavaweb.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

//java config để sử dụng tính chất
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")                             //bật tính nằng jpa auditing
public class JpaAuditingConfig {
    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }

    //lấy username người đăng nhập vào
    public static class AuditorAwareImpl implements AuditorAware<String> {
        @Override
        public String getCurrentAuditor() {
            //authentication này dùng spring security cung cấp , ko dùng của tomcat --> vô pom
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !authentication.isAuthenticated()) { // biến cờ khác null và đã đăng nhập rồi
                return null;
            }
            return authentication.getName();
        }
    }
}
