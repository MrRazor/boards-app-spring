package cz.uhk.boardsappspring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource, PasswordEncoder passwordEncoder) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
        jdbcUserDetailsManager.setDataSource(dataSource);

        //jdbcUserDetailsManager.createUser(user);
        //jdbcUserDetailsManager.createUser(admin);

        return jdbcUserDetailsManager;
        /*
        UserDetails user =
                User.withUsername("user")
                        .password(passwordEncoder.encode("user"))
                        .roles("USER")
                        .build();
        UserDetails admin =
                User.withUsername("admin")
                        .password(passwordEncoder.encode("admin"))
                        .roles("USER", "ADMIN")
                        .build();

        return new InMemoryUserDetailsManager(user, admin);
        */
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                //.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(HttpMethod.GET, "/private").hasRole(Role.ADMIN.name())
                .requestMatchers(HttpMethod.GET, "/public").permitAll())
                .httpBasic(withDefaults())
                .build();
    }

    @Bean
    @Primary
    public PasswordEncoder passwordEncoder() {
        /*
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return rawPassword.toString().equals(encodedPassword);
            }
        };
        */
        return new BCryptPasswordEncoder();
    }
}