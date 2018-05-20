package tools;

import com.ecbd.Entity.User;

import java.util.Base64;

public class Encoder {
    public static String encodeUser(User user) {
        String userString = user.getId() + ":::" + user.getName();
        return Base64.getEncoder().encodeToString(userString.getBytes());
    }

    public static User decodeUser(String encodedString) {
        String decoded = new String(Base64.getDecoder().decode(encodedString));
        System.out.println(decoded);
        String[] split = decoded.split(":::");
        User returnUser = new User();
        returnUser.setId(split[0]);
        returnUser.setName(split[1]);
        return returnUser;
    }
}
