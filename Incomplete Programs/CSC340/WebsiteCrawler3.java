import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebsiteCrawler3 {
    private Set<String> visitedUrls = new HashSet<>();
    private List<String> discoveredEmails = new ArrayList<>();
    private FileWriter fileWriter;
    private int maxDepth;
    private Pattern emailPattern;

    public void crawlWebsites(String startingUrl, String outputFile, int maxDepth) {
        try {
            fileWriter = new FileWriter(outputFile);
            this.maxDepth = maxDepth;
            emailPattern = Pattern.compile("\\b[A-Za-z0-9._%+-]+@ksu.edu.sa\\b");
            crawlWebsiteBFS(startingUrl);
            fileWriter.close();
            System.out.println("Crawling completed. Visited websites saved to: " + outputFile);
        } catch (IOException e) {
            System.out.println("Error writing to output file: " + outputFile);
            e.printStackTrace();
        }
    }

    private void crawlWebsiteBFS(String startingUrl) {
        Queue<WebsiteNode> queue = new LinkedList<>();
        queue.add(new WebsiteNode(startingUrl, 0));

        while (!queue.isEmpty()) {
            WebsiteNode currentNode = queue.poll();
            String url = currentNode.getUrl();
            int depth = currentNode.getDepth();

            if (depth > maxDepth) {
                continue;
            }

            try {
                visitedUrls.add(url);
                Document doc = Jsoup.connect(url).get();
                discoverEmails(doc.html());

                Elements links = doc.select("a[href]");
                for (Element link : links) {
                    String href = link.attr("abs:href");
                    if (href.contains("ksu.edu.sa") && !visitedUrls.contains(href)) {
                        queue.add(new WebsiteNode(href, depth + 1));
                    }
                }
            } catch (IOException e) {
                System.out.println("Error accessing website: " + url);
                //e.printStackTrace();
            }
        }
    }

    private void discoverEmails(String html) {
        Matcher matcher = emailPattern.matcher(html);
        while (matcher.find()) {
            String email = matcher.group();
            if (!discoveredEmails.contains(email)) {
                discoveredEmails.add(email);
                writeEmailToFile(email);
            }
        }
    }

    private void writeEmailToFile(String email) {
        try {
            fileWriter.write(email + "\n");
        } catch (IOException e) {
            System.out.println("Error writing email to file.");
            //e.printStackTrace();
        }
    }

    private static class WebsiteNode {
        private final String url;
        private final int depth;

        public WebsiteNode(String url, int depth) {
            this.url = url;
            this.depth = depth;
        }

        public String getUrl() {
            return url;
        }

        public int getDepth() {
            return depth;
        }
    }

    public static void main(String[] args) {
        WebsiteCrawler3 websiteCrawler = new WebsiteCrawler3();
        String startingUrl = "https://ccis.ksu.edu.sa/en";
        String outputFile = "emails.txt";
        int maxDepth = 2;
        websiteCrawler.crawlWebsites(startingUrl, outputFile, maxDepth);
    }
}