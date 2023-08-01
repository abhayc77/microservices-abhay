package com.insightsystems.discoveryserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig
{
	@Value("${eureka.username}")
	private String username;

	@Value("${eureka.password}")
	private String password;

	@Bean
	InMemoryUserDetailsManager userDetailsService() {
		/*User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();
		UserDetails userDetails = userBuilder.username(username)
									  .password(password)
									  .authorities("USER")
									  .build();*/

		UserDetails userDetails = User.withUsername(username)
											 .password("{noop}"+password)
											 .authorities("USER")
											 .build();

		return new InMemoryUserDetailsManager(userDetails);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests((authz) -> authz.anyRequest().authenticated())
			.httpBasic(Customizer.withDefaults());
		return http.build();
	}
}
