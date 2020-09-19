package forum.messenger.configurations;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;

@EnableWebSecurity

public class WebSecConfig extends WebSecurityConfigurerAdapter {
//todo configure sec
    @SuppressWarnings("deprecation")
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    //todo enable csrf , implement security
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        http.formLogin()
                .and()
                .authorizeRequests()
                .antMatchers( "/")
                .permitAll() // all requests are allowed on that path
                .and()
                .csrf().disable();
    }
}

/*
 .formLogin()
                .loginPage("/login").defaultSuccessUrl("/", true).permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/")
                .and()
                .authorizeRequests()
                .antMatchers("/messages", "/registration", "/perform_registration", "/css/**", "/js/**", "/", "/admin/messages/deletedMessages/").permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/admin/**", "/admin/messages/deletedMessages/").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();

 */