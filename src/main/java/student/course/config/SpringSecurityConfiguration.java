package student.course.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import student.course.model.UserAuthority;

@Slf4j
@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(expressionInterceptUrlRegistry ->
                        expressionInterceptUrlRegistry
                                .requestMatchers("/registration", "/login").permitAll() // Доступно всем
                                .requestMatchers(HttpMethod.POST, "/armors/**", "/bosses/**", "/locations/**", "/magics/**", "/npc/**",
                                        "/souls/**", "/units/**", "/weapons/**").hasAuthority(UserAuthority.CREATOR.getAuthority())
                                .requestMatchers(HttpMethod.GET, "/armors/**", "/bosses/**", "/locations/**", "/magics/**", "/npc/**",
                                        "/souls/**", "/units/**", "/weapons/**").authenticated() // Любой кто залогинился
                                .requestMatchers(HttpMethod.DELETE, "/armors/**","/bosses/**", "/locations/**", "/magics/**", "/npc/**",
                                        "/souls/**", "/units/**", "/weapons/**").hasAuthority(UserAuthority.CREATOR.getAuthority())
                                .requestMatchers(HttpMethod.PUT, "/armors/**","/bosses/**", "/locations/**", "/magics/**", "/npc/**",
                                        "/souls/**", "/units/**", "/weapons/**").hasAuthority(UserAuthority.CREATOR.getAuthority())
                                .anyRequest().hasAuthority(UserAuthority.ADMIN.getAuthority()))
                .formLogin(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
