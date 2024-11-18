package seg3x02.tempconverterapi.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun configureSecurity(http: HttpSecurity): SecurityFilterChain {
        http.csrf().disable()
            .authorizeHttpRequests { requests ->
                requests.anyRequest().authenticated()
            }
            .httpBasic()
        return http.build()
    }

    @Bean
    fun inMemoryUserDetailsService(): UserDetailsService {
        val userOne: UserDetails = User.builder()
            .username("user1")
            .password(encoder().encode("pass1"))
            .roles("USER")
            .build()

        val userTwo: UserDetails = User.builder()
            .username("user2")
            .password(encoder().encode("pass2"))
            .roles("USER")
            .build()

        return InMemoryUserDetailsManager(userOne, userTwo)
    }

    @Bean
    fun encoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
