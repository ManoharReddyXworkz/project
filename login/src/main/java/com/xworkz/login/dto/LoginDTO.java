package com.xworkz.login.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import io.smallrye.common.constraint.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="logindetails")

@NamedQueries({@NamedQuery(name="FindByEmail",query="SELECT login from LoginDTO login where login.userEmail=:email"),
	@NamedQuery(name="findByNameAndMobileNumber",query="select login from LoginDTO login where login.userEmail=:mail And login.contactInfo =:info")})
public class LoginDTO {
	
public LoginDTO() {
	System.out.println("Calling Default Constructorof:"+ this.getClass().getSimpleName());
}
@Id
@GenericGenerator(name="save",strategy="increment")
@GeneratedValue(generator="save")
@Min(value=1)
private Integer uId;
@NotNull @NotEmpty @Length(min = 3)@Length(max = 50)
private String userName;
@NotNull @NotEmpty @Length(min = 3)@Length(max = 50)
private String userEmail;
@NotNull @NotEmpty @Length(min = 10)@Length(max = 13)
private String contactInfo;


}
