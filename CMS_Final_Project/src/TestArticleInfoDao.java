import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestArticleInfoDao implements ArticleInfoModelDao{

    Map<Integer, ArticleInfo> articles = new HashMap<Integer, ArticleInfo>();

    public TestArticleInfoDao(ArrayList<ArticleInfo> articleList) {

        // Load DB from the outside.
        for (ArticleInfo article : articleList) {
            this.articles.put(article.getID(), article);
        }
    }

    public void add(ArticleInfo article) {
        articles.put(article.getID(), article);
    }

    public void save(String[] cols) {
        ArticleInfo article = new ArticleInfo();
        article.setId(Integer.parseInt(cols[0]));
        article.setTitle(cols[1]);
        article.setAFirst(cols[2]);
        article.setALast(cols[3]);
        article.setPostAt(Timestamp.valueOf(String.valueOf(cols[4])));
        article.setStatus(Integer.parseInt(cols[6]));
        this.articles.put(article.getID(), article);
    }

    public ArrayList<ArticleInfo> getArticles() {
        ArrayList articleList = new ArrayList<ArticleInfo>();
        for (Map.Entry<Integer, ArticleInfo> entry : this.articles.entrySet()) {
            articleList.add(entry.getValue());
        }
        return articleList;
    }
}