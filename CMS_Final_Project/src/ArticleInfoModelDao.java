import java.util.ArrayList;

/**
 * @author richney chin-chap
 */

public interface ArticleInfoModelDao {

    void add(ArticleInfo article);

    void save(String[] cols);

    ArrayList<ArticleInfo> getArticles();
}
