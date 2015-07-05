public class Main {

    public String concatenateWithPlus(final String s1, final String s2) {
        return "concatenated=" + s1 + s2;
    }

    public String concatenateWithStringBuilder(final String s1, final String s2) {
        final StringBuilder result = new StringBuilder();
        result.append("concatenated=");
        result.append(s1);
        result.append(s2);
        return result.toString();
    }
}
