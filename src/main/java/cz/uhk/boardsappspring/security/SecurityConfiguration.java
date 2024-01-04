package cz.uhk.boardsappspring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
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
                        .roles(Role.USER.name())
                        .build();
        UserDetails admin =
                User.withUsername("admin")
                        .password(passwordEncoder.encode("admin"))
                        .roles(Role.USER.name(), Role.ADMIN.name())
                        .build();

        return new InMemoryUserDetailsManager(user, admin);
        */
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/user/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/user/change-password").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/user/disable-user").hasRole(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.GET, "/api/user/current-user").authenticated()

                        .requestMatchers(HttpMethod.POST, "/api/posts/new").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/posts/update/*").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/posts/remove/*").hasRole(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.GET, "/api/posts/post/*").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/posts/post-comments/*").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/posts/all").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/posts/all-paged*").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/posts/all-comments").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/posts/all-comments-paged*").authenticated()

                        .requestMatchers(HttpMethod.POST, "/api/comments/new/*").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/comments/update/*").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/comments/remove/*").hasRole(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.GET, "/api/comments/comment/*").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/comments/all/*").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/comments/all-paged/*").authenticated()
                )
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