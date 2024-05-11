package ti1.financial;

import java.util.HashMap;
import java.util.Map;

import spark.Request;
import spark.Response;
import spark.Route;
import ti1.utils.Path;
import ti1.utils.ViewUtil;

public class FinancialController {
    public static Route serveFinancialPage = (Request request, Response response) -> {
        Map<String, Object> model = new HashMap<>();
        return ViewUtil.render(request, model, Path.Template.FINANCIAL);
    };
}
