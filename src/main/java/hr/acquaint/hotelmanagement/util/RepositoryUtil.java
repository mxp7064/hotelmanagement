package hr.acquaint.hotelmanagement.util;

/**
 * Utility class for repository operations
 */
public class RepositoryUtil {

    /**
     * Prepare string contains parameter to be used in LIKE matching
     */
    public static String prepareLikeContainsParameter(String parameter) {
        return "%" + sanitizeLikeParameter(parameter) + "%";
    }

    /**
     * Sanitize LIKE parameter by escaping wildcards
     */
    public static String sanitizeLikeParameter(String parameter) {
        return parameter
                .toLowerCase()
                .trim()
                .replace("%", "\\%")
                .replace("_", "\\_");
    }
}
