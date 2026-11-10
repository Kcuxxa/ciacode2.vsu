package ru.student;

public interface IAdminService {
    void banUser(String userId);

    void deleteProject (String projectId);

    String readAuditLog();
}
