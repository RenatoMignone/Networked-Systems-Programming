package it.unisannio.eshop.eshop.Security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@Import(User_Auth.class)
public class Security_Config {
    private final User_Auth userDetailService;

    @Autowired
    public Security_Config(User_Auth userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Bean // questo metodo serve per effettuare un encoding sulla nostra password per andarla a crittare nel database
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception{
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                response.sendRedirect("/customer.html");
            }
        };
    }

    @Bean
    //Vado a istanziare una catena di filtri attraverso la quale le nostre chiamate http verranno autorizzate ad accedere al server
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable);


        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/eShop/Customer/**").permitAll());
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/eShop/login/**").permitAll());

        //Customer Section
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/customer.html").permitAll());
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/CSS/customer.css").permitAll());
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/JS/customer_script.js").permitAll());

        //Login Section
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/login.html").permitAll());
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/CSS/login.css").permitAll());
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/JS/login_script.js").permitAll());

        //Admin Section
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/admin.html").hasAuthority("ADMIN"));
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/CSS/admin.css").hasAuthority("ADMIN"));
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/JS/admin_script.js").hasAuthority("ADMIN"));

        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/favicon.ico").permitAll());

//        http.formLogin(Customizer.withDefaults()).formLogin(login -> login.successHandler(myAuthenticationSuccessHandler()));


        http.authorizeHttpRequests((requests) ->requests.requestMatchers("/eShop/Admin/**").hasAuthority("ADMIN")).httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
