package edu.esiea.coursDevOps.config;

import java.io.IOException;


public class JWTTokenFilter /*extends OncePerRequestFilter*/ {
	
//	private JWTTokenProvider provider;
//	
//	public JWTTokenFilter(JWTTokenProvider p) {
//		this.provider = p;
//	}
//	
//	@Override
//	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filter) throws ServletException, IOException{
//		String token = provider.resolveToken(req);	
//		try {
//				if(token != null&& provider.validatToken(token)) {
//					Authentication auth = provider.getAuthentication(token);
//					SecurityContextHolder.getContext().setAuthentication(auth);
//				}
//			} catch (Exception e) {
//				SecurityContextHolder.clearContext();
//				res.sendError(403, "bad token");
//			}
//		filter.doFilter(req, res);
//	}

}
