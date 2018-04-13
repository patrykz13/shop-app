package computershop;

import computershop.controllers.InitAppController;
import computershop.database.entity.OrderPosition;
import computershop.database.service.OrderPositionService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShopApp extends Application {
    private static Stage mainStage;
    private OrderPositionService orderPositionService = new OrderPositionService();
    private List<OrderPosition> orderPositions = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        try {
            setMainStage(primaryStage);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/InitApp.fxml"));
            loader.load();

            InitAppController display = loader.getController();
            display.setOrderPositions(orderPositions);
            display.setOrderPositionService(orderPositionService);

            Parent root = loader.getRoot();
            primaryStage.setTitle("Computer Shop Application");
            primaryStage.getIcons().add(new Image("/images/app_icon.png"));
            primaryStage.setScene(new Scene(root, 1600, 900));
            primaryStage.show();
        } catch (IOException ioEcx) {
            Logger.getLogger(ShopApp.class.getName()).log(Level.SEVERE, null, ioEcx);
        }
    }

    @Override
    public void stop() {
        if (orderPositions.size()>0)
            for (OrderPosition orderPosition:orderPositions)
                orderPositionService.deleteOrderPosition(orderPosition.getId());
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getMainStage() {
        return mainStage;
    }

    public static void setMainStage(Stage mainStage) {
        ShopApp.mainStage = mainStage;
    }

    public OrderPositionService getOrderPositionService() {
        return orderPositionService;
    }

    public void setOrderPositionService(OrderPositionService orderPositionService) {
        this.orderPositionService = orderPositionService;
    }

    public List<OrderPosition> getOrderPositions() {
        return orderPositions;
    }

    public void setOrderPositions(List<OrderPosition> orderPositions) {
        this.orderPositions = orderPositions;
    }
}