package com.pengsoft.support.commons.util;

/**
 * String utility methods.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static final String GLOBAL_SEPARATOR = "::";

    public static final String PACKAGE_SEPARATOR = ".";

    public static final String HYPHEN = "-";

    public static final String UNDERCROSS = "_";

    public static final String COMMA = ",";

    public static final String ESCAPES = "\\";

    /**
     * @see #equals(CharSequence, CharSequence)
     */
    public static boolean notEquals(final CharSequence cs1, final CharSequence cs2) {
        return !equals(cs1, cs2);
    }

    /**
     * Camel case to the other case.
     *
     * @param name        The camel case name.
     * @param separator   The separator.
     * @param isUpperCase Change to the other upper case name.
     * @return The other case name.
     */
    public static String camelCaseTo(final String name, final String separator, final boolean isUpperCase) {
        if (isBlank(name)) {
            return name;
        } else {
            final var sourceName = name;
            final var targetName = new StringBuilder();
            final var charArray = sourceName.toCharArray();
            targetName.append(charArray[0]);
            if (charArray.length > 1) {
                for (int i = 1; i < charArray.length; i++) {
                    final var c = charArray[i];
                    if (Character.getType(c) < 3 && c == Character.toUpperCase(c)
                            && !separator.equals(String.valueOf(c))) {
                        targetName.append(separator);
                    }
                    targetName.append(c);
                }
            }
            if (isUpperCase) {
                return targetName.toString().toUpperCase();
            } else {
                return targetName.toString().toLowerCase();
            }
        }
    }

    /**
     * Camel case to snake case.
     *
     * @param name        The camel case name.
     * @param isUpperCase Change to upper snake case name.
     * @return The snake case name.
     */
    public static String camelCaseToSnakeCase(final String name, final boolean isUpperCase) {
        return camelCaseTo(name, UNDERCROSS, isUpperCase);
    }


}
