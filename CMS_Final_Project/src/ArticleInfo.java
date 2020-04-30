import java.sql.Timestamp;

/**
 * @author brie okeefe
 * @suthor richney chin-chap
 */

public class ArticleInfo {
    private int id;
    private String title;
    private String aFirst;
    private String aLast;
    private String photo;
    private Timestamp postAt;
    private int posted;

    private String[] headings;
    private String[] paragraphs;
    public static final int maxNumHeaders = 10;
    public static final int maxNumParagraphs = 10;


    /**
     * @author brie okeefe
     * @suthor richney chin-chap
     */


    public ArticleInfo(int id, String title, String aFirst, String aLast, String photo, Timestamp postAt, int status, String[] para, String[] header)
    {
        this.id = id;
        this.title = title;
        this.aFirst = aFirst;
        this.aLast = aLast;
        this.photo = photo;
        this.postAt = postAt;
        this.posted = status;
        this.paragraphs = para;
        this.headings = header;
    }

    public ArticleInfo()
    {
        headings = new String[maxNumHeaders];
        paragraphs = new String[maxNumParagraphs];
    }

    /**
     * @author brie okeefe
     */
    public int getID(){
        return id;
    }

    /**
     * @author richney chin-chap
     */

    public void setId(int id) {
        this.id = id;
    }

    /**
     * @author brie okeefe
     */
    public String getIDString(){
        return Integer.toString(id);
    }

    /**
     * @author brie okeefe
     */
    public String getTitle(){
        return title;
    }
    /**
     * @author richney chin-chap
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @author brie okeefe
     */
    public String getAFirst(){
        return aFirst;
    }

    /**
     * @author richney chin-chap
     */

    public void setAFirst(String aFirst) {
        this.aFirst = aFirst;
    }

    /**
     * @author brie okeefe
     */
    public String getALast(){
        return aLast;
    }

    /**
     * @author richney chin-chap
     */
    public void setALast(String aLast) {
        this.aLast = aLast;
    }

    /**
     * @author brie okeefe
     */
    public String getPhoto(){
        return photo;
    }

    /**
     * @author richney chin-chap
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * @author brie okeefe
     */
    public String[] getHeadings(){
        return headings;
    }

    /**
     * @author richney chin-chap
     */
    public void setHeadings(String[] newHeadings)
    {
        if (newHeadings.length > maxNumHeaders)
        {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < newHeadings.length; i++)
        {
            headings[i] = newHeadings[i];
        }
    }

    /**
     * @author brie okeefe
     */
    public String[] getParagraphs(){
        return paragraphs;
    }

    /**
     * @author richney chin-chap
     */
    public void setParagraphs(String[] newParagraphs)
    {
        if (newParagraphs.length > maxNumParagraphs)
        {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < newParagraphs.length; i++)
        {
            paragraphs[i] = newParagraphs[i];
        }
    }

    /**
     * @author kaya m
     */
    public Timestamp getPostAt(){
        return postAt;
    }

    /**
     * @author kaya m
     */
    //0000-00-00 00:00:00 time Stamp format
    // In progress
    public void setPostAt(Timestamp newPostTime){
        postAt = newPostTime;

    }

    /**
     * @author kaya m
     * @author brie okeefe
     */
    //Status refers to if the article has been posted or not
    //0: not posted
    //1: posted
    public int getStatus(){ return posted; }

    public void setStatus(int i){
        posted = i;

    }

    /**
     * @author brie okeefe
     */
    /*
    //May use this later/ for final
    //public void printArticleInfo(){
    //    System.out.println("ID: " + getID());
    //    System.out.println("Title: "+getTitle());
    //    System.out.println("Name: " + getAFirst() + getALast());
    // }
    */

    /**
     * @author brie okeefe
     * @author kaya m
     */
    public String getBrowserAddress(){
        return "file:///Y:/%20" + this.getIDString() + ".html";
    }

    /**
     * @author richney chin-chap
     */
    @Override
    public String toString(){
        return "Id: " + getIDString() + ", Title: " + getTitle() + ", Author: " + getAFirst() +  " " + getALast() +
                ", Post time: " + getPostAt() + ", Status: " + getStatus();
    }

}

