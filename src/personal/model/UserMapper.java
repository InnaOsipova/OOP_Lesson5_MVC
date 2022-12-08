package personal.model;

public class UserMapper {
//    JSONObject employeeDetails = new JSONObject();
//        employeeDetails.put("firstName", "Lokesh");
//        employeeDetails.put("lastName", "Gupta");

    public String map(User user) {
        //System.out.println(String.format("%s;%s;%s;%s", user.getId(), user.getFirstName(), user.getLastName(), user.getPhone()));
        return String.format("%s,%s,%s,%s", user.getId(), user.getFirstName(), user.getLastName(), user.getPhone());

    }

    public User map(String line) {
        String[] lines = line.split(",");
       // System.out.println(lines);
        return new User(lines[0], lines[1], lines[2], lines[3]);
    }

}
