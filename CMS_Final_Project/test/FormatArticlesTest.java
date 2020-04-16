import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class FormatArticlesTest {

    private FormatArticles sampleArticle = new FormatArticles();
    private ArticleInfo article1;


    @BeforeEach
    void setUp() {
        //fills headers and paragraphs
        int numTestHeadersParas = 10;
        String[] testHeaders = new String[numTestHeadersParas];
        String[] testParagraphs = new String[numTestHeadersParas];
        for (int i = 0; i < numTestHeadersParas; i++) {
            testHeaders[i] = "header" + i;
            testParagraphs[i] = "paragraph" + i + "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
                    "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation " +
                    "ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in " +
                    "voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat " +
                    "non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
        }

        article1 = new ArticleInfo(1017, "Coronavirus cases in the US", "Tina", "Turner",
                "\thttps://www.oif.ala.org/oif/wp-content/uploads/2019/12/Chillicothe-Prison-Lib-wm.jpg ",
                Timestamp.valueOf(String.valueOf("2020-04-10 12:00:00")), 1, testParagraphs, testHeaders);
    }

    @Test
    void formatArticleAsHTML() {
        String htmlHead = "<!DOCTYPE html> <html lang='en'>";
        htmlHead += "<head>";
        htmlHead += "<title>" + article1.getTitle() + "</title>";
        htmlHead += "<link rel='stylesheet' href='styles.css' media='screen'> <link href=\"https://fonts.googleapis.com/css?family=Khand:700|Lora|Raleway&display=swap\" rel=\"stylesheet\">";
        htmlHead += "</head>";

        String htmlHeader = "<div class='container'>";
        htmlHeader += "<header> ";
        htmlHeader += "<h1> " + article1.getTitle() + " </h1>";
        htmlHeader += " <p class=\"submitted-by\">Submitted by " + article1.getAFirst() + " ";
        htmlHeader += article1.getALast() + " </p>";
        htmlHeader += " <img src='" + article1.getPhoto() + "' height = '300' width = '300'> ";

        String htmlBody;
        String [] headings = article1.getHeadings();
        String[] paragraphs = article1.getParagraphs();
        htmlBody = "<body> ";
        for (int i = 1; i <= 10; i++) {
            if (headings[i] != null) {
                htmlBody += "<h2> " + headings[i] + " </h2>";
            }
            if (paragraphs[i] != null) {
                htmlBody += "<p> " + paragraphs[i] + " </p>";
            }
            i++;
        }
        htmlBody += "</body></div></html>";

        String htmlArticle = htmlHead;
        htmlArticle += htmlHeader;
        htmlArticle += htmlBody;

        assertEquals(htmlArticle, sampleArticle.formatArticleAsHTML(article1));
    }

    @Test
    void formatHeadAsHTML() {
        String htmlHead = "<!DOCTYPE html> <html lang='en'>";
        htmlHead += "<head>";
        htmlHead += "<title>" + article1.getTitle() + "</title>";
        htmlHead += "<link rel='stylesheet' href='styles.css' media='screen'> <link href=\"https://fonts.googleapis.com/css?family=Khand:700|Lora|Raleway&display=swap\" rel=\"stylesheet\">";
        htmlHead += "</head>";

        assertEquals(htmlHead, sampleArticle.formatHeadAsHTML(article1));
    }

    @Test
    void formatHeaderAsHTML() {
        String htmlHeader = "<div class='container'>";
        htmlHeader += "<header> ";
        htmlHeader += "<h1> " + article1.getTitle() + " </h1>";
        htmlHeader += " <p class=\"submitted-by\">Submitted by " + article1.getAFirst() + " ";
        htmlHeader += article1.getALast() + " </p>";
        htmlHeader += " <img src='" + article1.getPhoto() + "' height = '300' width = '300'> ";

        assertEquals(htmlHeader, sampleArticle.formatHeaderAsHTML(article1));
    }

    @Test
    void formatBodyAsHTML() {
        String htmlBody;
        String [] headings = article1.getHeadings();
        String[] paragraphs = article1.getParagraphs();
        htmlBody = "<body> ";
        for (int i = 1; i <= 10; i++) {
            if (headings[i] != null) {
                htmlBody += "<h2> " + headings[i] + " </h2>";
            }
            if (paragraphs[i] != null) {
                htmlBody += "<p> " + paragraphs[i] + " </p>";
            }
            i++;
        }
        htmlBody += "</body></div></html>";

        assertEquals(htmlBody, sampleArticle.formatBodyAsHTML(article1));
    }
}