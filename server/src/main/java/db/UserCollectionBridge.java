package db;

import com.ecbd.Entity.User;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.mongodb.client.model.Filters.eq;

public class UserCollectionBridge {
    private static String salt = "ecbdMongoProiect";
    public static boolean addUser(User user) {
        MongoCollection<Document> userCollection = DbSingleton.getInstance().getUserCollection();
        FindIterable<Document> found = userCollection.find(eq("name", user.getName()));
        Document first = found.first();
        if (first != null) {
            return false;
        }
        else {
            Document userDoc = makeUserDocument(user);
            userCollection.insertOne(userDoc);
            return true;
        }

    }

    public static Document getUser(String name) {
        MongoCollection<Document> userCollection = DbSingleton.getInstance().getUserCollection();
        FindIterable<Document> found = userCollection.find(eq("name", name));
        Document first = found.first();
        return first;
    }

    public static Document makeUserDocument(User user) {
        Document userDoc = new Document();
        userDoc.put("name", user.getName());
        String unhashed = user.getPassword();
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(salt.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest(unhashed.getBytes());
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        userDoc.put("password", generatedPassword);
        return userDoc;
    }
}
