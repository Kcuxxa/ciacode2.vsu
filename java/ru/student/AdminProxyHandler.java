package ru.student;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.nio.file.AccessDeniedException;

public class AdminProxyHandler implements InvocationHandler {
    private final IAdminService realService;
    private final User user;
    private final AuditLog auditLog;

    public AdminProxyHandler(IAdminService realService, User user, AuditLog auditLog) {
        this.realService = realService;
        this.user = user;
        this.auditLog = auditLog;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        boolean allowed = Permissions.isAllowed(user.getRole(), methodName);
        String verdict = allowed ? "Разрешено" : "Запрещено";
        String logEntry = user.getName() + " - " + methodName + " - " + verdict;
        auditLog.record(logEntry);
        if(!allowed){
            throw new PermissionError("Роль " + user.getRole() + " не имеет права вызывать " + methodName);
        }
        return method.invoke(realService, args);
    }
}
