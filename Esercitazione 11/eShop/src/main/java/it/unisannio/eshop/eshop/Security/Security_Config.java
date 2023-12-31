package it.unisannio.eshop.eshop.Security;

import jakarta.servlet.http.HttpServletRequest;
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
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import static org.springframework.security.config.Customizer.withDefaults;

@Configuration //File di configurazione
@EnableWebSecurity //dove metteremo le nostre configurazioni di sicurezza
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
    //Vado a istanziare una catena di filtri attraverso la quale le nostre chiamate http verranno autorizzate ad accedere al server
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable);

//        http.formLogin(login -> login.);

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

        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/?continue/").permitAll());

        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/favicon.ico").permitAll());

        http.formLogin(Customizer.withDefaults());

        http.authorizeHttpRequests((requests) ->requests.requestMatchers("/eShop/Admin/**").hasAuthority("ADMIN")).httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
