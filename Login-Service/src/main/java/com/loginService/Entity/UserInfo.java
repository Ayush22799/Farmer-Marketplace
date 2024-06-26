package com.loginService.Entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="User_Info")
public class UserInfo {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "user_id")
	private int userId;

	@Column(name = "user_type")
    private String userType;

	@Column(name = "username")
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "phone_number")
	private long phoneNo;

	@Column(name = "bank_name")
	private String bankName;

	@Column(name = "account_number")
	private long accountNo;

	@Column(name = "token")
	private String token;

	public UserInfo(String userType, String userName, String password, long phoneNo, String bankName, long accountNo){
		this.userType = userType;
		this.userName = userName;
		this.password = password;
		this.phoneNo = phoneNo;
		this.bankName = bankName;
		this.accountNo = accountNo;
	}

}
