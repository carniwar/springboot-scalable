package com.github.carniwar.springboot.scalable.rest.security;

import com.github.carniwar.springboot.scalable.api.util.Roles;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Simple authentication and authorization for system access. Create memory users and roles to grant access.
 * <table width="100%">
 *     <tr>
 *         <th width="25%" style="text-align:left;">Login</th>
 *         <th width="25%" style="text-align:left;">Password</th>
 *         <th width="50%" style="text-align:left;">Roles</th>
 *     </tr>
 *     <tr>
 *         <td>user</td>
 *         <td>user</td>
 *         <td>USER</td>
 *     </tr>
 *     <tr>
 *         <td>developer</td>
 *         <td>developer</td>
 *         <td>DEVELOPER</td>
 *     </tr>
 *     <tr>
 *         <td>admin</td>
 *         <td>admin</td>
 *         <td>DEVELOPER,USER,ADMIN</td>
 *     </tr>
 * </table>
 */
@Configuration
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class WebSecurityAdapter extends WebSecurityConfigurerAdapter {

    /**
     * Basic in memory database for users, passwords and roles.
     *
     * @param auth
     * @throws Exception
     */
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance())
                .withUser("user").password("user").roles(Roles.USER)
                .and().withUser("admin").password("admin").roles(Roles.DEVELOPER, Roles.USER, Roles.ADMIN)
                .and().withUser("developer").password("developer").roles(Roles.DEVELOPER);
    }

    /**
     * Bootstrap security authentication and authorization rules. Controllers access specific rules should be used on declaring class or methods with <code>@RolesAllowed</code>.
     *
     * @param http
     * @throws Exception
     */
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and().authorizeRequests()
                .antMatchers("/swagger-ui*", "/webjars/**", "/swagger-resources/**","/v2/api-docs*").hasRole(Roles.DEVELOPER)
                .antMatchers("/actuator*").hasRole(Roles.ADMIN)
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .headers().frameOptions().disable();
    }

}