package algorithms;

class InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        if(isEmpty(s3)) {
            return isEmpty(s1) && isEmpty(s2);
        }
        if(s1.length() + s2.length() != s3.length()) return false;
        Boolean[][]SL = new Boolean[s1.length()+1][s2.length()+1];
        solve(s1, s1.length(), s2, s2.length(), s3, SL);

        return SL[s1.length()][s2.length()];
    }

    boolean solve(String s1, int l1, String s2, int l2, String s3, Boolean[][]SL) {
        //if(SL[l1][l2] != null) return SL[l1][l2];
        if(l1 == 0 && l2 == 0) {
            SL[l1][l2] = true;
            return SL[l1][l2];
        }
        int l3 = l1 + l2;
        boolean m1 = false;
        if(l1 > 0) {
            m1 = s1.charAt(l1 - 1) == s3.charAt(l3 - 1);
            if(m1) {
                m1 = solve(s1, l1 - 1, s2, l2, s3, SL);
            }
        }
        boolean m2 = false;
        if(l2 > 0) {
            m2 = s2.charAt(l2 - 1) == s3.charAt(l3 - 1);
            if(m2) {
                m2 = solve(s1, l1, s2, l2 - 1, s3, SL);
            }
        }
        SL[l1][l2] = m1 || m2;

        return SL[l1][l2];
    }

    boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }
}