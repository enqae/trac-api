package com.nineteendrops.tracdrops.client.api.ticket.ticket;

import java.util.ArrayList;

public class TicketKeys {

    public static final String FIELD_SUMMARY = "summary";
    public static final String FIELD_KEYWORDS = "keywords";
    public static final String FIELD_STATUS = "status";
    public static final String FIELD_BLOCKEDBY = "blockedby";
    public static final String FIELD_RESOLUTION = "resolution";
    public static final String FIELD_TYPE = "type";
    public static final String FIELD_VERSION = "version";
    public static final String FIELD_REPORTER = "reporter";
    public static final String FIELD_MILESTONE = "milestone";
    public static final String FIELD_BLOCKING = "blocking";
    public static final String FIELD_COMPONENT = "component";
    public static final String FIELD_DESCRIPTION = "description";
    public static final String FIELD_PRIORITY = "priority";
    public static final String FIELD_OWNER = "owner";
    public static final String FIELD_CC = "cc";

    public static final String FIELD_COMMENT = "comment";

    public static final ArrayList<String> FIELDS_ARRAY = new ArrayList<String>();

    static{
        FIELDS_ARRAY.add(FIELD_SUMMARY);
        FIELDS_ARRAY.add(FIELD_KEYWORDS);
        FIELDS_ARRAY.add(FIELD_STATUS);
        FIELDS_ARRAY.add(FIELD_BLOCKEDBY);
        FIELDS_ARRAY.add(FIELD_RESOLUTION);
        FIELDS_ARRAY.add(FIELD_TYPE);
        FIELDS_ARRAY.add(FIELD_VERSION);
        FIELDS_ARRAY.add(FIELD_REPORTER);
        FIELDS_ARRAY.add(FIELD_MILESTONE);
        FIELDS_ARRAY.add(FIELD_BLOCKING);
        FIELDS_ARRAY.add(FIELD_COMPONENT);
        FIELDS_ARRAY.add(FIELD_DESCRIPTION);
        FIELDS_ARRAY.add(FIELD_PRIORITY);
        FIELDS_ARRAY.add(FIELD_OWNER);
        FIELDS_ARRAY.add(FIELD_CC);
        FIELDS_ARRAY.add(FIELD_COMMENT);
    }

    public static final String OP_IS = "=";
    public static final String OP_IS_NOT = "!=";
    public static final String OP_CONTAINS = "~=";
    public static final String OP_DOES_NOT_CONTAIN = "!~=";
    public static final String OP_STARTS_WITH = "^=";
    public static final String OP_DOES_NOT_START_WITH = "!^=";
    public static final String OP_ENDS_WITH = "$=";
    public static final String OP_DOES_NOT_END_WITH = "!$=";

    public static final ArrayList<String> OPERATORS_ARRAY = new ArrayList<String>();

    static{
        OPERATORS_ARRAY.add(OP_IS);
        OPERATORS_ARRAY.add(OP_IS_NOT);
        OPERATORS_ARRAY.add(OP_CONTAINS);
        OPERATORS_ARRAY.add(OP_DOES_NOT_CONTAIN);
        OPERATORS_ARRAY.add(OP_STARTS_WITH);
        OPERATORS_ARRAY.add(OP_DOES_NOT_START_WITH);
        OPERATORS_ARRAY.add(OP_ENDS_WITH);
        OPERATORS_ARRAY.add(OP_DOES_NOT_END_WITH);
    }

}