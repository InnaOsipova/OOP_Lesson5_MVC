package personal.model;

import java.util.List;
import java.util.Queue;

public interface Repository {
    List<User> getAllUsers();
    String CreateUser(User user);
    void updateUser(User user);
    void deleteUser(String userID);
}
