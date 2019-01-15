package ru.vsu.noidle_server.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import ru.vsu.noidle_server.Constants;
import ru.vsu.noidle_server.model.SecurityRole;
import ru.vsu.noidle_server.model.UpdateRole;

@Configuration
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/login", "/setup", "/teams/short**", "/teams/short/**",
                        "/img/**", "/login**", "/webjars/**", "/error**", "/callback**", "/statistics/**",
                        "/users/**","/admin/setup",
                        "/notifications**",
                        "/js/**", "/css/**")
                .permitAll()

                //prefix is added automatically
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
