package ru.vsu.noidle_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.vsu.noidle_server.model.SecurityRole;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/login", "/setup", "/teams/short**", "/teams/short/**",
                        "/img/**", "/login**", "/webjars/**", "/error**", "/callback**", "/statistics/**",
                        "/users/**", "/admin/setup",
                        "/notifications**",
                        "/js/**", "/css/**")
                .permitAll()

                //prefix 'ROLE_' is added automatically
                .antMatchers("/admin/users/**").hasRole(SecurityRole.ROLE_ADMIN.substring(SecurityRole.PREFIX.length()))
                .antMatchers("/about").authenticated()
                .anyRequest().hasRole(SecurityRole.ROLE_USER.substring(SecurityRole.PREFIX.length()))
                .and().logout().logoutSuccessUrl("/").permitAll();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
