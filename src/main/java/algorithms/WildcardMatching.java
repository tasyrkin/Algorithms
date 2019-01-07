package algorithms;

public class WildcardMatching {


    public static void main(String[] args) {
        final boolean doesMatch = new WildcardMatching().isMatch("adceb", "a*b****");

        System.out.println(doesMatch);
    }

    public boolean isMatch(String s, String p) {
        Boolean[][] dp = new Boolean[s.length() + 1][p.length() + 1];

        solve(s, 0, p, 0, dp);

        return dp[s.length()][p.length()] != null ? dp[s.length()][p.length()] : false;
    }

    Boolean solve(String s, int si, String p, int pi, Boolean[][] dp) {

        System.out.println(String.format("s[%s] = %s, p[%s] = %s", si, si < s.length() ? s.charAt(si) : "None", pi, pi < p.length() ? p.charAt(pi) : "None"));

        if (si >= s.length() && pi >= p.length()) {
            dp[si][pi] = true;
            return dp[si][pi];
        } else if (si >= s.length()) {
            boolean result = false;
            if (p.charAt(pi) == '*') {
                result |= solve(s, si, p, pi + 1, dp);
            }
            dp[si][pi] = (dp[si][pi] != null ? dp[si][pi] : false) || result;
            return dp[si][pi];
        } else if (pi >= p.length()) {
            dp[si][pi] = false;
            return dp[si][pi];
        }

        if(dp[si][pi] != null) return dp[si][pi];

        boolean result = false;
        if (p.charAt(pi) == '?') {
            result |= solve(s, si + 1, p, pi + 1, dp);
        } else if (p.charAt(pi) == '*') {
            result |= solve(s, si, p, pi + 1, dp);
            result |= solve(s, si + 1, p, pi, dp);
            result |= solve(s, si + 1, p, pi + 1, dp);
        } else if (s.charAt(si) == p.charAt(pi)) {
            result |= solve(s, si + 1, p, pi + 1, dp);
        }
        dp[si][pi] = (dp[si][pi] != null ? dp[si][pi] : false) || result;
        return dp[si][pi];
    }
}