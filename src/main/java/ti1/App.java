package ti1;

import static spark.Spark.after;
import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFiles;
import static spark.debug.DebugScreen.enableDebugScreen;

import ti1.financial.FinancialController;
import ti1.index.IndexController;
import ti1.utils.Filters;
import ti1.utils.Path;
import ti1.utils.ViewUtil;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {

        // Configure Spark
        port(4567);
        staticFiles.location("/public");
        staticFiles.expireTime(600L);
        enableDebugScreen();

        // Set up before-filters (called before each get/post)
        before("*", Filters.addTrailingSlashes);
        // before("*", Filters.handleLocaleChange);

        // Set up routes
        get(Path.Web.INDEX, IndexController.serveIndexPage);
        get(Path.Web.FINANCIAL, FinancialController.serveFinancialPage);
        get("*", ViewUtil.notFound);

        // Set up after-filters (called after each get/post)
        after("*", Filters.addGzipHeader);

    }
}
