package utils;

public class ValidationUtils {

    /**
     * Checks if any of the parameters are empty
     *
     * @param params the other parameters (String)
     * @return "" or a String containing an error message or
     */
    public static String validateEmptyParams(final String... params) {
        for (String str : params) {
            if (str.isEmpty()) {
                return "Please enter all fields!";
            }
        }
        return "";
    }

    /**
     * Checks if the id is negative
     *
     * @param id the object's id
     * @return "" or a String containing an error message or
     */
    public static String validatePosId(final int id) {
        if (id < 1) {
            return "Please enter a valid ID!";
        }
        return "";
    }

    /**
     * Just uses {@code validateEmptyParams} and {@code validatePosId} together
     *
     * @param id     the object's id
     * @param params the other parameters (String)
     * @return "" or a String containing an error message or
     */
    public static String validateEmptyParamsNPosId(final int id, final String... params) {
        String result = validateEmptyParams(params);
        if (result.isEmpty()) {
            result = validatePosId(id);
        }
        return result;
    }
}
