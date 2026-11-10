package ru.student;

public class AdminService implements IAdminService{
    private int banUserCount =0;
    private int deleteProjectCount =0;

    @Override
    public void banUser(String userId) {
        banUserCount++;
        System.out.println("Забанен пользователь:" + userId);

    }
     @Override
    public void deleteProject(String projectId) {
        deleteProjectCount++;
        System.out.println("Проект удален:" + projectId);

    }
    @Override
    public String readAuditLog() {
        System.out.println("[AdminService] Чтение журнала действий");
        return "журнал действий (заглушка)";
    }
    public int getBanUserCount() {
        return banUserCount;
    }

    public int getDeleteProjectCount() {
        return deleteProjectCount;
    }

}
