package algorithms;

import java.util.LinkedList;
import java.util.Stack;

public class SimplifyPath {
    public String simplifyPath(String path) {
        if(path == null || path.length() == 0) return path;

        String[] parts = path.split("/");
        Stack<String> optimized = new Stack<>();

        for(int idx = 0; idx < parts.length; idx++) {
            if(parts[idx].equals("..")) {
                if(!optimized.isEmpty()) {
                    optimized.pop();
                }
            } else if(parts[idx].equals(".") || parts[idx].length() == 0) {
                //ignore
            } else {
                optimized.push(parts[idx]);
            }
        }

        LinkedList<String> pathList = new LinkedList<>();
        while(!optimized.isEmpty()) {
            pathList.addFirst(optimized.pop());
        }

        StringBuilder result = new StringBuilder();
        for(String part : pathList) {
            result.append("/");
            result.append(part);
        }

        return result.length() > 0 ? result.toString() : "/";
    }

    public static void main(String[] args) {
        final String simplified = new SimplifyPath().simplifyPath("/home/.//////a/../..");

        System.out.println(simplified);
    }

}
