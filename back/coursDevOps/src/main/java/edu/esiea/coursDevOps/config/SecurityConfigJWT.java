package edu.esiea.coursDevOps.config;



//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigJWT /*extends WebSecurityConfigurerAdapter*/ {
	
//	@Autowired
//	 private JWTTokenProvider jwtTokenProvider;
//
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception{
//		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//	
//		http.csrf()
//		.disable()
//		.authorizeRequests()
//		.antMatchers("/users/signin").permitAll()	// public, obligatoire à définir en premier
//        .antMatchers("/users/signup").permitAll()	// public, obligatoire à définir en premier
//		.antMatchers("/auth/**").authenticated() 	// si dans l'url j'ai /auth/ je dois être authentifié pour y accéder
//		.anyRequest().permitAll();								// si dans l'url je n'ai aps auth, c'est public
//		
//		http.apply(new JXTTokenFilterConfiguration(jwtTokenProvider));
//	}
//
//	  @Bean
//	  public PasswordEncoder passwordEncoder() {
//	    return new BCryptPasswordEncoder(12);
//	  }
//
//	  @Override
//	  @Bean
//	  public AuthenticationManager authenticationManagerBean() throws Exception {
//	    //return super.authenticationManagerBean();
//		  return new AppAuthProvider();
//	  }

}
