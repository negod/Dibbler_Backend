/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.constants;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public class DibblerNamedQueries {

    public static final String LANGUAGETEXT_FINDBY_LANGUAGE_EXTID = "a";
    public static final String LANGUAGETEXT_FINDBY_TEXT_TYPE = "b";
    public static final String LANGUAGETEXT_FINDBY_CATEGORY = "c";

    public static final String CATEGORY_FINDBY_LANGUAGE_EXTID = "findCategoryByLanguageExtId";
    public static final String EVENTTYPE_FINDBY_LANGUAGE_EXTID = "findEventTypeByLanguageExtId";
    public static final String EVENTTEXT_FINDBY_LANGUAGE_EXTID = "findEventTextsByLanguageExtId";

    public static final String USERS_FINDBY_FACEBOOK_ID = "findUsersByFacebookId";
    public static final String USERS_FINDBY_GOOGLE_ID = "findUsersByGoogleId";
    public static final String USERS_AUTHENTICATE = "authenticateUser";

    public static final String PUBLISHED_EVENT_FINDBY_EXPIRED_DATE = "findEventByExpiredDate";
    public static final String PUBLISHED_EVENT_FINDBY_COMPANY = "findEventByCompany";

    public static final String COMPANY_GET_BY_ORGNO = "findByVatNo";

}
