import java.sql.*;
import java.util.ArrayList;

public class DBConnecter implements ArticleInfoModelDao{

    /**
     * credit for creation and manipulation of the database
     * @author brie okeefe
     */

    private Connection connection;
    private ArrayList<ArticleInfo> articles;

    boolean useDb = true;

    /**
     * @author bob z
     * @author brie okeefe
     */
    DBConnecter(String user, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://dany.simmons.edu:3306/33501sp20_okeefebl?useUnicode=yes&characterEncoding=UTF-8",
                    user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructor that accepts a list of ArticleInfo
     * @param articles
     * @author Bob Z
     * @author Richney Chin-Chap
     */
    DBConnecter(ArrayList<ArticleInfo> articles){
        this.useDb = false;
        this.articles = articles;
    }

    DBConnecter(){
        this.useDb = false;
        this.articles = new ArrayList<ArticleInfo>();
    }
    /**
     * @author richney chin-chap
     */
    public void add(ArticleInfo article) {
        articles.add(article);
    }

    /**
     * @author richney chin-chap
     */
    public ArticleInfo getArticleInfo(int id){
       for (ArticleInfo ai : articles)
       {
           if (ai.getID() == id)
           {
               return ai;
           }

       }

        return null;
    }

    /**
     * @author richney chin-chap
     */
    public void save(String[] cols) {
        try {
            Statement insertBook = connection.createStatement();
            insertBook.execute(
                    "INSERT INTO POSTINGS3 " +
                            "(ID, TITLE, AUTHOR_FIRST, AUTHOR_LAST, PHOTO, POST_AT, POSTED, H1, P1, H2, P2, H3, P3, H4, P4, H5, P5, H6, P6, H7, P7, H8, P8, H9, P9, H10, P10)" +
                            " VALUES ('" + Integer.parseInt(cols[0]) + "', '" + cols[1] + "', '" + cols[2] + "', '" + cols[3] + "', '" +
                            cols[4] + "', '" + Timestamp.valueOf(String.valueOf(cols[5])) + "', '" +
                            Integer.parseInt(cols[6]) + "', '" + cols[7] + "','" + cols[8] + "','" + cols[9] + "','" +
                            cols[10] + "', '" + cols[11] + "', '" + cols[12]+ "', '" + cols[13]+ "', '" + cols[14]+ "', '" + cols[15] +
                            cols[16]+ "', '" + cols[17]+ "', '" + cols[18]+ "', '" + cols[19]+ "', '" + cols[20]+ "', '" + cols[21] +
                            cols[22]+ "', '" + cols[23]+ "', '" + cols[24]+ "', '" + cols[25]+ "', '" + cols[26] + "')");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @author richney chin-chap
     */
    public void updatePostAt(ArticleInfo ai)
    {
        String setPostAtQuery = "UPDATE POSTINGS3 SET POST_AT = '" + ai.getPostAt() + "' WHERE ID = " + ai.getIDString() + ";";
        //String setPostAtQuery = "UPDATE POSTINGS3 SET POST_AT = '" + ts + "';";
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(setPostAtQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @author richney chin-chap
     */
    public void updateStatus(ArticleInfo ai)
    {
        String setStatusQuery = "UPDATE POSTINGS3 SET POSTED = " + ai.getStatus() + " WHERE ID = " + ai.getIDString() + ";";

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(setStatusQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * @author richney chin-chap
     */
    public ArrayList<ArticleInfo> getArticles() {
        if (useDb) {
            articles = new ArrayList<ArticleInfo>();
            try {

                Statement selectArticle = connection.createStatement();
                ResultSet rs = selectArticle.executeQuery("SELECT ID, TITLE, AUTHOR_FIRST, AUTHOR_LAST," +
                        " PHOTO, POST_AT, POSTED, H1, P1, H2, P2, H3, P3, H4, P4, H5, P5, H6, P6, H7, P7," +
                        " H8, P8, H9, P9, H10, P10 FROM POSTINGS3; ");
                // Iterate over result set and print each book description.
                while (rs.next()) {
                    ArticleInfo article = new ArticleInfo();
                    article.setId(rs.getInt(1)); // ID
                    article.setTitle(rs.getString(2)); // Title
                    article.setAFirst(rs.getString(3)); //AuthorFirst
                    article.setALast(rs.getString(4)); // AuthorLast
                    article.setPhoto(rs.getString(5)); // Photo
                    article.setPostAt(rs.getTimestamp(6)); //Timestamp to post article
                    article.setStatus(rs.getInt(7)); //Published or not

                    // Fill headers
                    int headerColumnBase = 8;
                    String[] newHeadings = new String[ArticleInfo.maxNumHeaders];
                    for (int i = 0; i < newHeadings.length; i++)
                    {
                        newHeadings[i] = rs.getString(headerColumnBase + i*2);
                    }
                    article.setHeadings(newHeadings);

                    // Fill paragraphs
                    int paragraphsColumnBase = 9;
                    String test = rs.getString(9);
                    test = "";
                    String[] newParagraphs = new String[ArticleInfo.maxNumParagraphs];
                    for (int j = 0; j < newParagraphs.length; j++)
                    {
                        newParagraphs[j] = rs.getString(paragraphsColumnBase + j*2);
                    }
                    article.setParagraphs(newParagraphs);

                    articles.add(article);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this.articles;
    }

    /**
     * @author brie okeefe
     */
    public void displayInfo(){
        try {
            Statement selectArticle = connection.createStatement();
            ResultSet rs = selectArticle.executeQuery("SELECT TITLE, AUTHOR_FIRST, AUTHOR_LAST, ID," +
                    " POSTED FROM POSTINGS3");
            while (rs.next()) {
                System.out.println("ID: " + rs.getString(4)); // ID
                System.out.println("Title: " + rs.getString(1)); // Title
                System.out.println("Author: " + rs.getString(2) + " " + rs.getString(3)); // Author
                System.out.println("Published: " + rs.getString(5));
                System.out.println("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection(){
        return connection;
    }

}
