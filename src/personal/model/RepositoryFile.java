package personal.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RepositoryFile implements Repository {
    private UserMapper mapper = new UserMapper();
    private FileOperation fileOperation;

    public RepositoryFile(FileOperation fileOperation) {
        this.fileOperation = fileOperation;
    }

    @Override
    public List<User> getAllUsers() {
        List<String> lines = fileOperation.readAllLines();
        List<User> users = new ArrayList<>();
        for (String line : lines) {
            users.add(mapper.map(line));
        }
        return users;
    }

    @Override
    public String CreateUser(User user) {

        List<User> users = getAllUsers();
        int max = 0;
        for (User item : users) {
            int id = Integer.parseInt(item.getId());
            if (max < id){
                max = id;
            }
        }
        int newId = max + 1;
        String id = String.format("%d", newId);
        user.setId(id);
        saveUser(user, users);
        return id;
    }

    public void updateUser(User user){
        deleteUser(user.getId());
        List<User> users = getAllUsers();
        saveUser(user, users);
    }
    private void saveUser(User user, List<User> users) {
        users.add(user);
        saveUsers(users);
    }

    public void deleteUser(String userID){
        List<User> users = getAllUsers();
        users.remove(findUser(userID, users));
        saveUsers(users);

    }

    private User findUser(String userID, List<User> users) {
        for (User user : users) {
            if (user.getId().equals(userID)) {
                return user;
            }
        }
        throw new IllegalStateException("User not found!");
    }

    private void saveUsers(List<User> users){
        List<String> lines = new ArrayList<>();
        for (User item: users) {
            lines.add(mapper.map(item));
        }
        fileOperation.saveAllLines(lines);
    }
}
