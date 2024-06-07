package com.marketplace.DealerService.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
public class UserInfo {

	private int userId;

	private String userType;

	private String userName;

	private String password;

	private long phoneNo;

	private String bankName;

	private long accountNo;

	public UserInfo(String userType, String userName, String password, long phoneNo, String bankName, long accountNo){
		this.userType = userType;
		this.userName = userName;
		this.password = password;
		this.phoneNo = phoneNo;
		this.bankName = bankName;
		this.accountNo = accountNo;
	}

}
