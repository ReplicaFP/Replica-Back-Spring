package com.salesianas.dam.replica.utils.constant;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RestConstantsUtils {

    //APPLICATION
    public static final String APPLICATION_NAME = "/replica";
    public static final String API_VERSION_1 = "/v1";

    public static final String RESOURCE_ID = "/{id}";

    public static final String RESOURCE_USERNAME = "/{username}";

    public static final String RESOURCE_USERNAMES = "/username";

    //USERS
    public static final String RESOURCE_USERS = "/users";

    //STUDENTS
    public static final String RESOURCE_STUDENTS = "/students";

    //TEACHERS

    public static final String RESOURCE_TEACHERS = "/teachers";

    //EMPLOYEES

    public static final String RESOURCE_EMPLOYEES = "/employees";

    //INTERNSHIPS

    public static final String RESOURCE_INTERNSHIPS = "/internships";

    //FINALPROJECTS

    public static final String RESOURCE_FINAL_PROJECTS = "/final_projects";

    // ========= ROLE =========
    public static final String RESOURCE_ROLES = "/roles";

    public static final String RESOURCE_AUTH = "/auth";
    public static final String RESOURCE_SIGNIN = "/signin";
    public static final String RESOURCE_SIGNUP = "/signup";
    public static final String RESOURCE_DUPLICATE_USER_ERROR="Error: Username ya existente!";
    public static final String RESOURCE_DUPLICATE_EMAIL_ERROR="Error: Email ya existente!";
    public static final String RESOURCE_ROLE_NOT_FOUND_ERROR="Error: Role no encontrado.";

    public static final String USER_REGISTER_SUCCESS="Usuario registrado correctamente!";


}
