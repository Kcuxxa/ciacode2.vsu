package ru.student;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Permissions {
    private static final Map<Role, Set<String>> ALLOWED = new HashMap<>();
    static  {
        ALLOWED.put(Role.ADMIN, new HashSet<>(java.util.Arrays.asList("banUser", "deleteProject", "readAuditLog")));
        ALLOWED.put(Role.MODERATOR, new HashSet<>(java.util.Arrays.asList("banUser", "readAuditLog")));
        ALLOWED.put(Role.VIEWER, new HashSet<>(java.util.Arrays.asList("readAuditLog")));
    }
    public static boolean isAllowed(Role role, String methodName) {
        Set<String> allowedForRole = ALLOWED.get(role);
        if (allowedForRole == null) {
            return false;
        }
        return allowedForRole.contains(methodName);
    }
}
