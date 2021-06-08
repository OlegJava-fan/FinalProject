package com.my.finalProject;

public final class Fields {

    public static final String ENTITY__ID = "id";

    public static final String ACCOUNT_LOGIN = "login";
    public static final String ACCOUNT_PASSWORD = "password";
    public static final String ACCOUNT__FIRST_NAME = "firstName";
    public static final String ACCOUNT__LAST_NAME = "lastName";
    public static final String ACCOUNT__MIDDLE_NAME = "middleName";
    public static final String ACCOUNT_EMAIL = "email";
    public static final String ACCOUNT__CITY = "city";
    public static final String ACCOUNT__REGION = "region";
    public static final String ACCOUNT__ROLE_ID = "roles_id";
    public static final String ACCOUNT__CERTIFICATE_ID = "certificate_id";
    public static final String ACCOUNT__STUDY_FORM_ID = "studyForm_id";
    public static final String ACCOUNT__FACULTIES_NAME = "faculties_name";

    public static final String FACULTIES_NAME = "name";
    public static final String FACULTIES_PASSING_SCORE_FREE_FORM = "passingScoreFreeForm";
    public static final String FACULTIES_PASSING_SCORE_PAY_FORM = "passingScorePayForm";
    public static final String FACULTIES_ALL_PLACE = "allPlaces";
    public static final String FACULTIES_FREE_FORM_PLACES = "freeFormPlaces";
    public static final String FACULTIES_PAY_FORM_PLACES = "payFormPlaces";

    public static final String CERTIFICATE_MATH = "mathematics";
    public static final String CERTIFICATE_CHEMISTRY = "chemistry";
    public static final String CERTIFICATE_PHYSICS = "physics";
    public static final String CERTIFICATE_ENGLISH = "english";
    public static final String CERTIFICATE_UKRAINIAN = "ukrainian";
    public static final String CERTIFICATE_INFORMATICS = "informatics";
    public static final String CERTIFICATE_GEOGRAPHY = "geography";
    public static final String CERTIFICATE_BIOLOGY = "biology";
    public static final String CERTIFICATE_HISTORY = "history";
    public static final String CERTIFICATE_GYM = "gym";
    public static final String CERTIFICATE_AVERAGE_SCORE = "averageScore";

    public static final String ORDER_ACCOUNT_ID = "account_id";
    public static final String ORDER_STATUS_ID = "status_id";
    public static final String ORDER_FACULTIES_ID = "faculties_id";

    public static final String ACT_ACCOUNT_ID = "account_id";
    public static final String ACT_FACULTIES_ID = "faculties_id";
    public static final String ACT_STATUS = "status";
    public static final String ACT_FACULTIES_NAME = "faculties_name";

    public static final String NOT_VALID_FIELD = "notValid";
    public static final String NOT_VALID_ISNUMBER = "number";
    public static final String NOT_VALID_EMPTY_STRING = "empty";
    public static final Long NOT_VALID_NEGATIVE_NUMBER = -1L;
    public static final Long NOT_VALID_IS_LETTER = -2L;
    public static final Long NOT_VALID_IS_EMPTY = 0L;

    public static final int PARAMETER_INDEX = 1;
    public static final int DEFAULT_ROLE_USER_ID = 2;
    public static final long DEFAULT_STUDY_FORM_ID = 1;
    public static final String DEFAULT_FACULTIES_NAME = "matriculate";
    public static final long DEFAULT_ORDER_STATUS=1;
    public static final long PAY_FORM_ORDER_STATUS=2;
    public static final long FREE_FORM_ORDER_STATUS=3;

    public static final Long  PAY_FORM =2L;
    public static final Long FREE_FORM =3L;
    public static final String FREE_FORM_STUDY ="FREE FORM";
    public static final String PAY_FORM_STUDY ="PAY FORM";
}