package ti1;

import static spark.Spark.after;
import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.delete;
import static spark.Spark.staticFiles;

import com.google.gson.Gson;

import dao.UserDAO;
import model.UserModel;

import services.JsonTransformer;
import spark.ResponseTransformer;
import spark.Route;
import ti1.calculator.Calculator;
import ti1.calendar.Calendar;
import ti1.financialRegistration.FinancialRegistrationController;
import ti1.index.IndexController;
import ti1.inflationChart.InflationChart;
import ti1.personalLogin.PersonalLogin;
import ti1.personalRegistration.PersonalRegistrationController;
import ti1.planningChart.PlanningChart;
import ti1.spendingChart.SpendingChart;
import ti1.utils.Filters;
import ti1.utils.Path;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();

        // Configure Spark
        port(4567);
        staticFiles.location("/public");
        staticFiles.expireTime(600L);

        // db.create_table(conn, "blog");
        userDAO.create_user_table();
        // db.insert_data(conn, "blog", "Mais linhas", "Landau", "Tudo sobre uma nova
        // linha");
        // db.read_all_data(conn, "blog");
        // db.update_data(conn, 2, "blog", "Linha atualizada", "Landau 2", "New
        // contnt");
        // db.search_by_id(conn, "blog", "3");
        // db.delete_data_by_author(conn, "blog", "Landau 2");

        // Set up before-filters (called before each get/post)
        before("*", Filters.addTrailingSlashes);
        // before("*", Filters.handleLocaleChange);

        // Set up routes
        get(Path.Web.INDEX, IndexController.serveIndexPage);
        get(Path.Web.HOME, IndexController.serveIndexPage);
        get(Path.Web.FINANCIAL_REGISTRATION, FinancialRegistrationController.serveFinancialPage);
        get(Path.Web.PERSONAL_REGISTRATION, PersonalRegistrationController.serveRegistrationPage);
        get(Path.Web.CALCULATOR, Calculator.serveCalculatorPage);
        get(Path.Web.CALENDAR, Calendar.serveCalendarPage);
        get(Path.Web.INFLATION_CHART, InflationChart.serveInflationChartPage);
        get(Path.Web.PLANNING_CHART, PlanningChart.servePlanningPage);
        get(Path.Web.SPENDING_CHART, SpendingChart.serveSpendingChartPage);
        get(Path.Web.LOGIN, PersonalLogin.servePersonalLoginPage);
        get(Path.Web.PERSONAL_LOGIN, PersonalLogin.servePersonalLoginPage);
        // get("*", ViewUtil.notFound);

        // Set up after-filters (called after each get/post)
        after("*", Filters.addGzipHeader);

        // Set up JSON transformer
        ResponseTransformer jsonTransformer = new JsonTransformer();

        // Set up API routes
        before("/*", (q, a) -> System.out.print("Received api call"));
        path("/api/", () -> {

            // Autenticação
            post("/users/login/", getUserRoute, jsonTransformer);
            post("/users/registration/", createUserRoute, jsonTransformer);
            put("/users/update/", updateUserRoute, jsonTransformer);
            delete("/users/delete/", deleteUserRoute, jsonTransformer);
            
            // // Calendário
            // post("/calendar/create/", createCalendarEventRoute, jsonTransformer);
            // post("/calendar/update/", updateCalendarEventRoute, jsonTransformer);
            // post("/calendar/delete/", deleteCalendarEventRoute, jsonTransformer);
            // post("/calendar/search/", searchCalendarEventRoute, jsonTransformer);
            // post("/calendar/search/by-date/", searchCalendarEventByDateRoute, jsonTransformer);

            // // Investimentos
            // post("/investments/create/", createInvestmentRoute, jsonTransformer);
            // post("/investments/update/", updateInvestmentRoute, jsonTransformer);
            // post("/investments/delete/", deleteInvestmentRoute, jsonTransformer);
            // post("/investments/search/", searchInvestmentRoute, jsonTransformer);
            // post("/investments/search/by-id/", searchInvestmentByIdRoute, jsonTransformer);
            // post("/investments/search/by-user/", searchInvestmentByUserRoute, jsonTransformer);

        
        });
    }

    private static Route deleteUserRoute = (request, response) -> {
        UserDAO userDAO = new UserDAO();
        String post = request.body();
        UserModel user = new Gson().fromJson(post, UserModel.class);
        userDAO.delete_user(user.getId());
        return "User Deleted";
    };

    private static Route updateUserRoute = (request, response) -> {
        UserDAO userDAO = new UserDAO();
        String post = request.body();
        UserModel user = new Gson().fromJson(post, UserModel.class);
        if (user.getFirstname().isEmpty() || user.getEmail().isEmpty() || user.getPassword().isEmpty()) {
            response.status(400);
            return "Error: name, email and password are required.";
        }

        userDAO.update_user(user.getId(), user.getFirstname(), user.getLastname(), user.getCpf(), user.getPassword(), user.getEmail(), user.isAccept());
        return "User Updated";
    };

    private static Route createUserRoute = (request, response) -> {
        UserDAO userDAO = new UserDAO();

        String post = request.body();
        UserModel user = new Gson().fromJson(post, UserModel.class);
        if (user.getFirstname().isEmpty() || user.getEmail().isEmpty() || user.getPassword().isEmpty()) {
            response.status(400);
            return "Error: name, email and password are required.";
        }
        ;
        userDAO.create_user(user.getFirstname(), user.getLastname(), user.getCpf(), user.getPassword(),
                user.getEmail(), user.isAccept());

        return "User Created";
    };

    private static Route getUserRoute = (request, response) -> {
        UserDAO userDAO = new UserDAO();
        String requestBody = request.body();
        UserModel userInput = new Gson().fromJson(requestBody, UserModel.class);

        if (userInput.getEmail().isEmpty() || userInput.getPassword().isEmpty()) {
            response.status(400);
            return "Error: email e senha são obrigarios.";
        }
        ;
        UserModel user = userDAO.authenticate_user(userInput.getEmail(), userInput.getPassword());
        response.type("application/json");
        System.out.println("Usuário autenticado");
        return user;
    };

}
