package com.lichi.increaselimit.user.controller;

import java.io.UnsupportedEncodingException;
import java.security.SignatureException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lichi.increaselimit.community.dao.CircleDao;
import com.lichi.increaselimit.community.entity.Circle;
import com.lichi.increaselimit.security.properties.SecurityProperties;
import com.lichi.increaselimit.user.dao.UserDao;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 测试controller
 * 
 * @author majie
 *
 */
@Api(description = "用户信息")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserDao userDao;

	@Autowired
	private CircleDao circleDao;
	
	@Autowired
	private SecurityProperties systemProperties;

	/**
	 * 解析jwt的token
	 */
	@GetMapping("/me")
	public Object getCurrentUser(Authentication authentication, HttpServletRequest request)
			throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException,
			IllegalArgumentException, UnsupportedEncodingException {
		String header = request.getHeader("Authorization");
		String token = StringUtils.substringAfter(header, "bearer ");

		Claims claims = Jwts.parser()
				.setSigningKey(systemProperties.getOauth2Properties().getJwtSigningKey().getBytes("UTF-8"))
				.parseClaimsJws(token).getBody();
		
		//获取附加信息
		String company = (String) claims.get("company");

		return claims;
	}

	@ApiOperation(value = "获取用户信息")
	@GetMapping
	// Specification<User> spec = new Specification<User>() {
	//
	// @Override
	// public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query,
	// CriteriaBuilder cb) {
	// Predicate p1 = cb.equal(root.get("id").as(Integer.class), 1);
	// // 设置sql链接
	// Join<User, Circle> fuJoin =
	// root.join(root.getModel().getSingularAttribute("circles", Circle.class),
	// JoinType.INNER);
	// Predicate p2 = cb.equal(fuJoin.get("userId").as(Long.class), 1);
	// query.where(cb.and(p1, p2));
	// // 添加排序的功能
	//// query.orderBy(cb.desc(root.get("id").as(Long.class)));
	// return query.getRestriction();
	// }
	//
	// };
	// List<User> all = userDao.findAll();
	// return all;

	public Page<Circle> query(final String tname, final String sex, final String degree, final String orgname) {
		// TODO Auto-generated method stub
		return circleDao.findAll(new Specification<Circle>() {
			@Override
			public Predicate toPredicate(Root<Circle> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				// Join<Circle, User> join = root.join("users",JoinType.LEFT);
				//
				// List<Predicate> list = new ArrayList<Predicate>();
				return cb.equal(root.get("name").as(String.class), "1");
				// if (tname != null && !"".equals(tname)) {
				// list.add(cb.like(namepath, "%" + tname + "%"));
				// }
				// if (sex != null && !"".equals(sex)) {
				// list.add(cb.like(sexpath, "%" + sex + "%"));
				// }
				// if (degree != null && !"".equals(degree)) {
				// list.add(cb.like(degreepath, "%" + degree + "%"));
				// }
				// if (orgname != null && !"".equals(orgname)) {
				// Path<String> orgnamepath = root.get("org").get("orgname");
				// list.add(cb.like(orgnamepath, "%" + orgname + "%"));
				// }
			}
		}, new PageRequest(0, 2));
	}

	// @GetMapping("/add")
	// public Object add() {
	// for(int j=0;j<10;j++) {
	// Set<Circle> circles = new HashSet<>();
	// for(int i=0;i<10000;i++) {
	// Circle c = new Circle();
	// c.setCreateTime(new Date());
	// c.setName(i+"");
	// c.setUrl(i+"");
	// Circle save = circleDao.save(c);
	// circles.add(save);
	// }
	// User user = new User();
	// user.setCircles(circles);
	// user.setMobilephone(j+"");
	// userDao.save(user);
	// }
	// return null;
	// }
}
