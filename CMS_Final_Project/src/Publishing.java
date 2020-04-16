import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.Scanner;

public class Publishing {

    private DBConnecter db;

    public Publishing(DBConnecter database)
    {
        db = database;
    }

    /**
     * @author brie okeefe
     */
    public void fileOutput(String htmlString, String id) {
        String fileID = id;
        try {
            FileOutputStream fileOut = new FileOutputStream("Y:\\ " + fileID + ".html");
            String s = htmlString;
            byte b[] = s.getBytes();//converting string into byte array
            fileOut.write(b);
            fileOut.close();
            System.out.println("success...");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * @author brie okeefe
     * @author kaya m
     */
    public void publisher(DBConnecter database, ArticleInfo ai) {
        FormatArticles fa = new FormatArticles();
        Scanner input = new Scanner(System.in);
        System.out.println("Please select what task you would like to accomplish with the selected article: ");
        System.out.println("1: Check status" + "\n" + "2: Set status" + "\n" + "3: Check post time" + "\n" + "4: Set post time" + "\n" + "5: Format article" + "\n");
        boolean userInputIsValid = false;
        while (!userInputIsValid) {
            String userInput = input.nextLine();
            if (userInput.equals("1")) {
                userInputIsValid = true;
                System.out.println(ai.getStatus());
            } else if (userInput.equals("2")) {
                System.out.println("Has the article been posted yet? " + "\n" + "0: No" + "\n" + "1: Yes");
                int status = input.nextInt();
                ai.setStatus(status);
                db.updateStatus(ai);
                userInputIsValid = true;
                System.out.println("The article status is now: " + ai.getStatus() + "\n");
            } else if (userInput.equals("3")) {
                userInputIsValid = true;
                System.out.println("The current post time is set for " + ai.getPostAt() + "\n");
            } else if (userInput.equals("4")) {
                userInputIsValid = true;
                System.out.println("Please enter the updated post time in this format: YYYY-MM-DD HH:MM:SS");
                String newPostTime = input.nextLine();
                Timestamp ts = Timestamp.valueOf(String.valueOf(newPostTime));
                ai.setPostAt(ts);
                db.updatePostAt(ai);
            }
            else if (userInput.equals("5")) {
                userInputIsValid = true;
                fileOutput(fa.formatArticleAsHTML(ai), ai.getIDString());
                System.out.println("The HTML file: " + ai.getIDString() + ".html should appear in your Y Drive");
                System.out.println("Or you can copy and paste: " + ai.getBrowserAddress() + " in your brower's URL bar\n");
            }
            //Make Option 4 if finished
            //System.out.println("When would you like this article posted?");
            //String time = input.nextLine();
            //ai.setPostTime(time, ai.getID());
            else {
                System.out.println("Please type in a valid response:");
            }
        }
    }
}
