package nk.todomanagement.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig
{

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests((authorize)->
        {
            authorize.requestMatchers(HttpMethod.POST,"/api/todo").hasRole("ADMIN");
            authorize.requestMatchers(HttpMethod.PUT,"/api/todo/{id}").hasRole("ADMIN");
            authorize.requestMatchers(HttpMethod.DELETE,"/api/**").hasRole("ADMIN");
            authorize.anyRequest().authenticated();}
        ).httpBasic(Customizer.withDefaults());
        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails naresh = User.builder().
                username("naresh").
                password(passwordEncoder().encode("pwd")).
                roles("USER").
                build();

        UserDetails admin = User.builder().
                username("admin").
                password(passwordEncoder().encode("admn")).
                roles("ADMIN").
                build();

        return new InMemoryUserDetailsManager(naresh,admin);
    }

}
