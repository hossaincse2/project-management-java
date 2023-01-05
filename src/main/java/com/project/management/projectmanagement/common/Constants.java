package com.project.management.projectmanagement.common;

import java.util.ArrayList;
import java.util.List;

public class Constants {

	public static final String ROLE_ADMIN = "Admin";
	public static final String ROLE_MANAGER = "Manager";
	public static final String ROLE_EMPLOYEE = "Employee";

	public static final List<String> USER_ROLES = new ArrayList<String>() {{
		add(ROLE_ADMIN);
		add(ROLE_MANAGER);
		add(ROLE_EMPLOYEE);
	}};

	public static final String PROJECT_PRE = "PRE";
	public static final String PROJECT_START = "START";
	public static final String PROJECT_END = "END";

	public static final List<String> PROJECT_STATUS = new ArrayList<String>() {{
		add(PROJECT_PRE);
		add(PROJECT_START);
		add(PROJECT_END);
	}};

	public static final Integer MAXIMUM_EMPLOYEE_ASSIGN = 5;
}