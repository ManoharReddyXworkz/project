package com.xworkz.login.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import io.smallrye.common.constraint.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "logindetails")

@NamedQueries({
		@NamedQuery(name = "FindByEmail", query = "SELECT login from LoginDTO login where login.userEmail=:email"),
		@NamedQuery(name = "findByNameAndMobileNumber", query = "select login from LoginDTO login where login.userEmail=:Email And login.contactInfo =:info"),
		@NamedQuery(name = "findByEmailAndSecurity", query = "select login from LoginDTO login where login.userEmail=:emails And login.security =:secu"),
		@NamedQuery(name = "updateStatusByEmail", query = "update LoginDTO login set login.status=:statuss where login.userEmail=:mails"),
		@NamedQuery(name = "updateCountByEmail", query = "update LoginDTO login set login.count=:counts where login.userEmail=:mails"),
		@NamedQuery(name = "resetPasswordByOtpAndEmail", query = "update LoginDTO login set login.security=:passs,login.status=:sts where login.userEmail=:mass"),
		@NamedQuery(name = "updateOtpDateAndTimeByEmail", query = "update LoginDTO login set login.otp=:ot,login.date=:da,login.time=:tm where login.userEmail=:gmail"),
		@NamedQuery(name = "UpdateUserDetailsByEmail", query = "update LoginDTO login set login.userName=name,login. contactInfo=:info,login.fileName=:file where login.userEmail=:mail"),
		@NamedQuery(name = "findAll", query = "Select login from LoginDTO login") })

public class LoginDTO implements Serializable {

	@Id
	@GenericGenerator(name = "save", strategy = "increment")
	@GeneratedValue(generator = "save")
	@Min(value = 1)
	private Integer uId;
	@NotNull
	@NotEmpty
	@Length(min = 3)
	@Length(max = 50)
	private String userName;
	@NotNull
	@NotEmpty
	@Length(min = 3)
	@Length(max = 50)
	@Email
	private String userEmail;
	@NotNull
	@NotEmpty
	@Length(min = 10)
	@Length(max = 13)
	private String contactInfo;

	private String security;
	private String status;
	private Integer count;
	private Integer otp;
	private LocalDate date;
	private LocalTime time;
	private String fileName;
}
