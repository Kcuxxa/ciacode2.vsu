package ru.student;

public class Main {

    public static void main(String[] args) {
        IAdminService realService = new AdminService();
        AuditLog auditLog = new AuditLog();

        User admin = new User("Алиса", Role.ADMIN);
        User moderator = new User("Боб", Role.MODERATOR);
        User viewer = new User("Вася", Role.VIEWER);

        System.out.println("Админ вызывает всё");
        IAdminService adminProxy = ProxyFactory.create(realService, admin, auditLog);
        adminProxy.banUser("user42");
        adminProxy.deleteProject("proj1");
        System.out.println("Журнал: " + adminProxy.readAuditLog());

        System.out.println("Модератор не может удалять проекты ");
        IAdminService moderatorProxy = ProxyFactory.create(realService, moderator, auditLog);
        moderatorProxy.banUser("user99");
        try {
            moderatorProxy.deleteProject("proj2");
        } catch (PermissionError e) {
            System.out.println("Ожидаемо отказано: " + e.getMessage());
        }

        System.out.println("Стажёр не может ничего, кроме чтения");
        IAdminService viewerProxy = ProxyFactory.create(realService, viewer, auditLog);
        try {
            viewerProxy.banUser("user1");
        } catch (PermissionError e) {
            System.out.println("Ожидаемо отказано: " + e.getMessage());
        }

        System.out.println("Журнал вызовов");
        for (String entry : auditLog.getEntries()) {
            System.out.println("  " + entry);
        }
    }
}
