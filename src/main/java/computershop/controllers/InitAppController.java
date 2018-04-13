package computershop.controllers;

import computershop.ShopApp;
import computershop.database.entity.OrderPosition;
import computershop.database.service.InfoAboutStationaryShopService;
import computershop.database.service.OrderPositionService;
import computershop.database.service.StationaryShopService;
import computershop.database.view.InfoAboutStationaryShop;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class InitAppController implements Initializable {
    private InfoAboutStationaryShopService infoAboutStationaryShopService = new InfoAboutStationaryShopService();
    private ObservableList<InfoAboutStationaryShop> infoAboutStationaryShopObservableList = FXCollections.observableArrayList();
    private StationaryShopService stationaryShopService = new StationaryShopService();
    private OrderPositionService orderPositionService;
    private List<OrderPosition> orderPositions;

    @FXML
    TableColumn<InfoAboutStationaryShop, String> nameColumn, streetColumn, cityColumn, postalCodeColumn, countryColumn;

    @FXML
    TableView<InfoAboutStationaryShop> tableViewInfoAboutStationaryShops;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTableView();
    }

    @FXML
    private void buttonRefreshTableView_onAction() {
        refreshTableView();
    }

    @FXML
    private void buttonLoadApplication_onAction() {
        try {
            InfoAboutStationaryShop infoAboutSelectedStationaryShop = tableViewInfoAboutStationaryShops.getSelectionModel().getSelectedItem();
            int idOfSelectedStationaryShop = infoAboutSelectedStationaryShop.getId();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/MainFrame.fxml"));
            loader.load();

            MainFrameController display = loader.getController();
            display.setShopWhereApplicationWasLaunched(stationaryShopService.getStationaryShop(idOfSelectedStationaryShop));
            display.setOrderPositions(orderPositions);
            display.setOrderPositionService(orderPositionService);

            Parent parent = loader.getRoot();
            Stage stage = ShopApp.getMainStage();
            stage.setScene(new Scene(parent));
        } catch (IOException ioEcx) {
            Logger.getLogger(InitAppController.class.getName()).log(Level.SEVERE, null, ioEcx);
        } catch (RuntimeException runtimeExc) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("images/app_icon.png"));
            alert.setTitle("Wykryto błąd");
            alert.setHeaderText("Błąd zaznaczenia.");
            alert.setContentText("Nie wybrano sklepu stacjonarnego.");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            });
        }
    }

    private void refreshTableView() {
        infoAboutStationaryShopObservableList.clear();
        infoAboutStationaryShopObservableList.addAll(infoAboutStationaryShopService.getInfoAboutStationaryShops());
        tableViewInfoAboutStationaryShops.setItems(infoAboutStationaryShopObservableList);
    }

    private void initTableView() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        streetColumn.setCellValueFactory(new PropertyValueFactory<>("street"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        postalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        refreshTableView();
    }

    public void setOrderPositionService(OrderPositionService orderPositionService) {
        this.orderPositionService = orderPositionService;
    }

    public void setOrderPositions(List<OrderPosition> orderPositions) {
        this.orderPositions = orderPositions;
    }
}
