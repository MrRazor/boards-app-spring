package cz.uhk.boardsappspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

@Component
public class Tester implements CommandLineRunner {

    @Autowired
    private UserDetailsManager userDetailsManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
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

        userDetailsManager.createUser(user);
        userDetailsManager.createUser(admin);
        */
    }
}
