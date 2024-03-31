package com.marketplace.DealerService.Entity;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserInfo {

	private int userId;

    private String userType;

	private String userName;

	private String password;

	private long phoneNo;

	private String bankName;

	private long accountNo;

}
