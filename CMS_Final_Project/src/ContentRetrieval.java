
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author brie okeefe
 * @suthor richney chin-chap
 */

public class ContentRetrieval {

    public static void main(String[] args) {

        DBConnecter db = new DBConnecter("okeefebl", "1908035");
        Scanner input = new Scanner(System.in);

        ArrayList<ArticleInfo> articleList = new ArrayList<>();



        /**
         // Program Menu
         // 1: search by title, author's last name, or ID
         // 2: display all
         // 3: more options includes: 1: check status 2: set status 3: check post time 3: format an article
         */

        db.getArticles();
        System.out.println("------ MAIN MENU ------"+"\n");
        System.out.println("Please make a selection: " + "\n" + "\n" + "1: Display all articles " + "\n" + "2: More options (Article ID required)");
        int userChoice = input.nextInt();
        if (userChoice == 1) {
            db.displayInfo();
        }
         else if (userChoice ==2){
            System.out.println("Please enter the ID of the Article you would like to continue with.");
            int idArticle = input.nextInt();
            ArticleInfo newArticle = db.getArticleInfo(idArticle);
            Publishing newPublish = new Publishing(db);
            newPublish.publisher(db, newArticle);
        } else {
            System.out.println("Invalid number. Please enter a number from 1-3:");
        }

        //loopBack();


//        int numTestHeadersParas = 10;
//        String[] testHeaders = new String[numTestHeadersParas];
//        String[] testParagraphs = new String[numTestHeadersParas];
//        for (int i = 0; i < numTestHeadersParas; i++)
//        {
//            testHeaders[i] = "header" + i;
//            testParagraphs[i] = "paragraph" + i;
//        }
//
//        TestArticleInfoDao testArticle = new TestArticleInfoDao(articleList);
//        ArticleInfo article1 = new ArticleInfo();
//        article1.setId(1017);
//        article1.setTitle("Coronavirus cases in the US");
//        article1.setAFirst("NewYork");
//        article1.setALast("Times");
//        article1.setPostAt(Timestamp.valueOf(String.valueOf("2020-04-10 12:00:00")));
//        article1.setStatus(1);
//        article1.setParagraphs(testParagraphs);
//        article1.setHeadings(testHeaders);
//
//        testArticle.add(article1);

//        System.out.println("Display object contents initialized with a single Book - no database calls");
//        for (ArticleInfo article :testArticle.getArticles()) {
//            System.out.println(article.toString());
//        }
    }

    /**
     * @author richney c
     */
    private static void loopBack(){
        System.out.println("Return to Main Menu? yes or no");
        Scanner input = new Scanner(System.in);
        boolean response = false;
        while (!response){
            String userResponse = input.nextLine();
            if (userResponse.equals("yes")) {
                main(null);
            }
            else{
                System.exit(0);
            }
        }
    }

}
