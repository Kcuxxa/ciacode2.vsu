package ru.student;
import java.lang.reflect.Proxy;

public class ProxyFactory {
    public static IAdminService create(IAdminService realService,  User user, AuditLog auditLog) {
        return (IAdminService) Proxy.newProxyInstance(IAdminService.class.getClassLoader(),new Class<?>[] { IAdminService.class }, new AdminProxyHandler(realService, user, auditLog));

    }
}
