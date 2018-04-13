package computershop.controllers;

import computershop.ShopApp;
import computershop.database.entity.*;
import computershop.database.service.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class ShoppingBasketFrameController implements Initializable {
    private StationaryShop shopWhereApplicationWasLaunched;
    private OrderPositionService orderPositionService;
    private List<OrderPosition> orderPositions;
    private ObservableList<OrderPosition> orderPositionObservableList = FXCollections.observableArrayList();
    private Account loggedCustomerAccount;
    private AccountService accountService;
    private OrderService orderService = new OrderService();
    private Discount discount;
    private Float promotionPrice;
    private DecimalFormat decimalFormat = new DecimalFormat();
    private DiscountService discountService = new DiscountService();
    private ProductCategoryService productCategoryService = new ProductCategoryService();


    @FXML
    private TableView<OrderPosition> tableViewOrderProducts;

    @FXML
    private TableColumn<OrderPosition, String> nameColumn, barcodeColumn;

    @FXML
    private TableColumn<OrderPosition, Float> priceNettoColumn, vatColumn, priceBruttoColumn;

    @FXML
    private Label labelDiscountCode, labelShoppingBaskecPrice, labelShopWhereApplicationWasLaunched;

    @FXML
    private TextField textFieldDiscountCode;

    @FXML
    private Button buttonDiscountCode, buttonRemoveDiscountCode;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        decimalFormat.setMaximumFractionDigits(2);
    }

    @FXML
    void buttonBack_onAction() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/MainFrame.fxml"));
            loader.load();

            MainFrameController display = loader.getController();
            display.setShopWhereApplicationWasLaunched(shopWhereApplicationWasLaunched);
            display.setOrderPositions(this.orderPositions);
            display.setOrderPositionService(this.orderPositionService);
            display.setLoggedCustomerAccount(this.loggedCustomerAccount, this.accountService);
            display.setDiscount(this.discount);
            display.setPromotionPrice(this.promotionPrice);

            Parent parent = loader.getRoot();
            Stage stage = ShopApp.getMainStage();
            stage.setScene(new Scene(parent));
        } catch (IOException ioEcx) {
            Logger.getLogger(InitAppController.class.getName()).log(Level.SEVERE, null, ioEcx);
        }
    }

    @FXML
    void buttonDeleteProduct_onAction() {
        try {
            OrderPosition orderPosition = tableViewOrderProducts.getSelectionModel().getSelectedItem();
            int orderPositionTableId = tableViewOrderProducts.getSelectionModel().getSelectedIndex();
            orderPositionService.deleteOrderPosition(orderPosition.getId());
            orderPositions.remove(orderPositionTableId);
            refreshProductTableView(orderPositions);
            if (discount != null) {
                Float sum = 0f;
                for (OrderPosition orderPos : orderPositions) {
                    if (this.discount.getProductCategory().getName().equals(orderPos.getProduct().getProductCategory().getName())) {
                        sum += (orderPos.getPurchasePriceBrutto());
                    }
                }
                sum = sum * (this.discount.getDiscountPercentage());
                sum = sum / 100;
                promotionPrice = sum;
                refreshOrderPositionsSummaryInfo(true);
                buttonRemoveDiscountCode.setVisible(true);
            } else {
                refreshOrderPositionsSummaryInfo(false);
                buttonRemoveDiscountCode.setVisible(false);
            }
        } catch (NullPointerException nullExc) {
            showMessageBox(Alert.AlertType.INFORMATION, "Informacja",
                    "Próba usunięcia egzemplarza produktu z koszyka nieudana.",
                    "Nie wybrano żadnego produktu.").showAndWait();
        }
    }

    @FXML
    void buttonDiscountCode_onAction() {
        String discountCode = textFieldDiscountCode.getText();

        if (discountCode.isEmpty()) {
            showMessageBox(Alert.AlertType.INFORMATION, "Informacja",
                    "Promocja nie zostanie uwzględniona.",
                    "Nie wprowadzono kodu rabatowego.").showAndWait();
        } else {
            try {
                labelDiscountCode.setUnderline(true);
                labelDiscountCode.setCursor(Cursor.HAND);
                textFieldDiscountCode.setVisible(false);
                textFieldDiscountCode.setMinWidth(0);
                textFieldDiscountCode.setMinHeight(0);
                textFieldDiscountCode.setPrefWidth(0);
                textFieldDiscountCode.setPrefHeight(0);
                textFieldDiscountCode.setMaxWidth(0);
                textFieldDiscountCode.setMaxHeight(0);
                buttonDiscountCode.setVisible(false);
                buttonDiscountCode.setMinWidth(0);
                buttonDiscountCode.setMinHeight(0);
                buttonDiscountCode.setPrefWidth(0);
                buttonDiscountCode.setPrefHeight(0);
                buttonDiscountCode.setMaxWidth(0);
                buttonDiscountCode.setMaxHeight(0);

                List<Discount> discounts = loggedCustomerAccount.getDiscounts();
                for (Discount discount : discounts) {
                    if (discountCode.equals(discount.getDiscountCode()) && !discount.getUsed()) {
                        this.discount = discount;
                        Float sum = 0f;
                        for (OrderPosition orderPosition : orderPositions) {
                            if (this.discount.getProductCategory().getName().equals(orderPosition.getProduct().getProductCategory().getName())) {
                                sum += (orderPosition.getPurchasePriceBrutto());
                            }
                        }
                        sum = sum * (this.discount.getDiscountPercentage());
                        sum = sum / 100;
                        promotionPrice = sum;
                        refreshOrderPositionsSummaryInfo(true);
                        buttonRemoveDiscountCode.setVisible(true);
                        return;
                    }
                }

                showMessageBox(Alert.AlertType.INFORMATION, "Informacja",
                        "Promocja nie zostanie uwzględniona.",
                        "Kod rabatowy jest nieprawidłowy lub został już wykorzystany.").showAndWait();
            } catch (NullPointerException exc) {
                showMessageBox(Alert.AlertType.INFORMATION, "Informacja",
                        "Promocja nie zostanie uwzględniona.",
                        "Użytkownik nie posiada kodów rabatowych.").showAndWait();
            }
        }
    }

    @FXML
    void buttonRemoveDiscountCode_onAction() {
        this.discount = null;
        this.promotionPrice = null;
        buttonRemoveDiscountCode.setVisible(false);
        refreshOrderPositionsSummaryInfo(false);
    }

    @FXML
    void buttonNext_onAction() {
        if (orderPositions.size() == 0) {
            showMessageBox(Alert.AlertType.ERROR, "Błąd",
                    "Błąd ilości produktów.",
                    "Na Twojej liście produktów nie ma żadnych pozycji.").showAndWait();
        } else {
            if (loggedCustomerAccount != null) {
                showMessageBox(Alert.AlertType.CONFIRMATION, "Operacja wymaga potwierdzenia",
                        "Jesteś pewny, że chcesz dokonać zakupu wybranych produktów?",
                        "Jeśli tak - potwierdź naciskająć OK, a następnie przejdź do kasy\n" +
                                "w celu dokonania płatności i odbioru produktów.").showAndWait()
                        .ifPresent(rs -> {
                            if (rs == ButtonType.OK) {


                                Order order = new Order(0f, loggedCustomerAccount.getCustomer());
                                if (this.discount != null) {
                                    this.discount.setUsed(true);
                                    order.setDiscount(this.discount);
                                    order.setPromotion_price(this.promotionPrice);
                                    discountService.saveDiscount(this.discount);
                                    this.discount = null;
                                    this.promotionPrice = null;
                                }
                                order.setStationaryShop(shopWhereApplicationWasLaunched);

                                orderService.saveOrder(order);

                                Boolean newDiscountCode = false;
                                Map<Integer, Integer> numberOfProductsOfEachCategory = new HashMap<Integer, Integer>();
                                List<ProductCategory> productCategories = productCategoryService.getProductCategories();
                                List<Integer> productCategoriesId = new ArrayList<>();
                                for (ProductCategory pCategory : productCategories)
                                    productCategoriesId.add(pCategory.getId());

                                for (Integer id : productCategoriesId)
                                    numberOfProductsOfEachCategory.put(id, 0);

                                for (OrderPosition orderPosition : orderPositions) {
                                    numberOfProductsOfEachCategory.put(orderPosition.getProduct().getProductCategory().getId(),
                                            numberOfProductsOfEachCategory.get(orderPosition.getProduct().getProductCategory().getId()) + 1);
                                    orderPosition.setOrder(order);
                                    orderPositionService.saveOrderPosition(orderPosition);
                                }

                                List<Discount> loggedCustomerDiscounts = loggedCustomerAccount.getDiscounts();
                                Discount alreadyOwnedDiscount;
                                for (Map.Entry<Integer, Integer> entry : numberOfProductsOfEachCategory.entrySet()) {
                                    Integer keyCategoryId = entry.getKey();
                                    Integer valueAmountOfProducts = entry.getValue();
                                    alreadyOwnedDiscount = null;

                                    if (valueAmountOfProducts >= 3) {
                                        if (loggedCustomerDiscounts != null) {
                                            for (Discount dis : loggedCustomerDiscounts) {
                                                if ((dis.getProductCategory().getId() == keyCategoryId) && dis.getUsed() == false) {
                                                    alreadyOwnedDiscount = dis;
                                                    break;
                                                }
                                            }
                                        }
                                        if (alreadyOwnedDiscount != null) {
                                            if (alreadyOwnedDiscount.getDiscountPercentage() < 50) {
                                                alreadyOwnedDiscount.setDiscountPercentage(alreadyOwnedDiscount.getDiscountPercentage() + 10);
                                                discountService.saveDiscount(alreadyOwnedDiscount);
                                                newDiscountCode = true;
                                            }
                                        } else {
                                            Discount discount = new Discount();
                                            discount.setAccount(this.loggedCustomerAccount);
                                            discount.setProductCategory(productCategoryService.getProductCategory(keyCategoryId));
                                            discount.setDiscountPercentage(10);
                                            discount.setUsed(false);
                                            discountService.saveDiscount(discount);
                                            newDiscountCode = true;
                                        }
                                    }
                                }

                                if (newDiscountCode == true)
                                    showMessageBox(Alert.AlertType.INFORMATION, "Informacja",
                                            "Otrzymałeś nowe kody rabatowe!",
                                            "Sprawdź swoją listę kodów rabatowych w zakładce KONTO.").showAndWait();

                                orderPositions.clear();

                                try {
                                    FXMLLoader loader = new FXMLLoader();
                                    loader.setLocation(getClass().getResource("/fxml/MainFrame.fxml"));
                                    loader.load();

                                    MainFrameController display = loader.getController();
                                    display.setShopWhereApplicationWasLaunched(shopWhereApplicationWasLaunched);
                                    display.setOrderPositions(this.orderPositions);
                                    display.setOrderPositionService(this.orderPositionService);
                                    display.setLoggedCustomerAccount(this.loggedCustomerAccount, this.accountService);
                                    display.setDiscount(this.discount);
                                    display.setPromotionPrice(this.promotionPrice);

                                    Parent parent = loader.getRoot();
                                    Stage stage = ShopApp.getMainStage();
                                    stage.setScene(new Scene(parent));
                                } catch (IOException ioEcx) {
                                    Logger.getLogger(InitAppController.class.getName()).log(Level.SEVERE, null, ioEcx);
                                }
                            }
                        });
            } else
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/fxml/QuestionAboutLogginInFrame.fxml"));
                    loader.load();

                    QuestionAboutLogginInFrameController display = loader.getController();
                    display.setShopWhereApplicationWasLaunched(shopWhereApplicationWasLaunched);
                    display.setOrderPositions(this.orderPositions);
                    display.setOrderPositionService(this.orderPositionService);
                    display.setLoggedCustomerAccount(this.loggedCustomerAccount, this.accountService);
                    display.setDiscount(this.discount);
                    display.setPromotionPrice(this.promotionPrice);

                    Parent parent = loader.getRoot();
                    Stage stage = ShopApp.getMainStage();
                    stage.setScene(new Scene(parent));
                } catch (IOException ioEcx) {
                    Logger.getLogger(InitAppController.class.getName()).log(Level.SEVERE, null, ioEcx);
                }
        }
    }

    @FXML
    void labelDiscountCode_onMouseClicked() {
        if (loggedCustomerAccount == null) {
            showMessageBox(Alert.AlertType.INFORMATION, "Informacja",
                    "Możliwość wprowadzenia kodu rabatowego niedostępna.",
                    "Funkcjonalność dostępna tylko dla zalogowanych klientów.").showAndWait();
        } else {
            labelDiscountCode.setUnderline(false);
            labelDiscountCode.setCursor(Cursor.DEFAULT);
            textFieldDiscountCode.setVisible(true);
            textFieldDiscountCode.setMinWidth(Control.USE_COMPUTED_SIZE);
            textFieldDiscountCode.setMinHeight(Control.USE_COMPUTED_SIZE);
            textFieldDiscountCode.setPrefWidth(181);
            textFieldDiscountCode.setPrefHeight(25);
            textFieldDiscountCode.setMaxWidth(Control.USE_COMPUTED_SIZE);
            textFieldDiscountCode.setMaxHeight(Control.USE_COMPUTED_SIZE);
            buttonDiscountCode.setVisible(true);
            buttonDiscountCode.setMinWidth(Control.USE_COMPUTED_SIZE);
            buttonDiscountCode.setMinHeight(Control.USE_COMPUTED_SIZE);
            buttonDiscountCode.setPrefWidth(135);
            buttonDiscountCode.setPrefHeight(25);
            buttonDiscountCode.setMaxWidth(Control.USE_COMPUTED_SIZE);
            buttonDiscountCode.setMaxHeight(Control.USE_COMPUTED_SIZE);
        }
    }

    private void refreshProductTableView(List<OrderPosition> orderPositions) {
        orderPositionObservableList.clear();
        orderPositionObservableList.addAll(orderPositions);
        tableViewOrderProducts.setItems(orderPositionObservableList);
    }

    private void initProductTableView() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        barcodeColumn.setCellValueFactory(new PropertyValueFactory<>("barcode"));
        priceBruttoColumn.setCellValueFactory(new PropertyValueFactory<>("purchasePriceBrutto"));
        priceNettoColumn.setCellValueFactory(new PropertyValueFactory<>("purchasePriceNetto"));
        vatColumn.setCellValueFactory(new PropertyValueFactory<>("vatRate"));
        refreshProductTableView(orderPositions);
    }

    private void refreshOrderPositionsSummaryInfo(Boolean promotion) {
        if (!promotion) {
            float sumOfPrices = 0;
            for (OrderPosition orderPosition : orderPositions)
                sumOfPrices += orderPosition.getPurchasePriceBrutto();
            labelShoppingBaskecPrice.setText("Wartość zakupów: " + decimalFormat.format(sumOfPrices) + " zł");
        } else {
            float sumOfPrices = 0;
            for (OrderPosition orderPosition : orderPositions)
                sumOfPrices += orderPosition.getPurchasePriceBrutto();
            labelShoppingBaskecPrice.setText("Wartość zakupów: " + decimalFormat.format(sumOfPrices) +
                    " - " + decimalFormat.format(promotionPrice) + " = " + decimalFormat.format((sumOfPrices - promotionPrice)) + " zł");
        }
    }

    private Alert showMessageBox(Alert.AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("images/app_icon.png"));
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        return alert;
    }

    public void setShopWhereApplicationWasLaunched(StationaryShop stationaryShop) {
        this.shopWhereApplicationWasLaunched = stationaryShop;
        Address shopAddress = shopWhereApplicationWasLaunched.getAddress();
        labelShopWhereApplicationWasLaunched.setText(shopWhereApplicationWasLaunched.getName() + ": ul. "
                + shopAddress.getStreet() + ", " + shopAddress.getPostalCode() + " " + shopAddress.getCity()
                + ", " + shopAddress.getCountry());
    }

    public void setOrderPositionService(OrderPositionService orderPositionService) {
        this.orderPositionService = orderPositionService;
    }

    public void setOrderPositions(List<OrderPosition> orderPositions) {
        this.orderPositions = orderPositions;
        initProductTableView();
        refreshOrderPositionsSummaryInfo(false);
    }

    public void setLoggedCustomerAccount(Account loggedCustomerAccount, AccountService accountService) {
        this.loggedCustomerAccount = loggedCustomerAccount;
        this.accountService = accountService;
        String actualText = labelShopWhereApplicationWasLaunched.getText();
        if (loggedCustomerAccount != null)
            labelShopWhereApplicationWasLaunched.setText(actualText + " | zalogowano jako: " +
                    loggedCustomerAccount.getCustomer().getFirstName() + " " +
                    loggedCustomerAccount.getCustomer().getLastName() + " (" + loggedCustomerAccount.getLogin() + ")");
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;

        if (discount != null) {
            Float sum = 0f;
            for (OrderPosition orderPosition : orderPositions) {
                if (this.discount.getProductCategory().getName().equals(orderPosition.getProduct().getProductCategory().getName())) {
                    sum += (orderPosition.getPurchasePriceBrutto());
                }
            }
            sum = sum * (this.discount.getDiscountPercentage());
            sum = sum / 100;
            promotionPrice = sum;
            refreshOrderPositionsSummaryInfo(true);

            buttonRemoveDiscountCode.setVisible(true);
        } else
            buttonRemoveDiscountCode.setVisible(false);

        labelDiscountCode.setUnderline(true);
        labelDiscountCode.setCursor(Cursor.HAND);
        textFieldDiscountCode.setVisible(false);
        textFieldDiscountCode.setMinWidth(0);
        textFieldDiscountCode.setMinHeight(0);
        textFieldDiscountCode.setPrefWidth(0);
        textFieldDiscountCode.setPrefHeight(0);
        textFieldDiscountCode.setMaxWidth(0);
        textFieldDiscountCode.setMaxHeight(0);
        buttonDiscountCode.setVisible(false);
        buttonDiscountCode.setMinWidth(0);
        buttonDiscountCode.setMinHeight(0);
        buttonDiscountCode.setPrefWidth(0);
        buttonDiscountCode.setPrefHeight(0);
        buttonDiscountCode.setMaxWidth(0);
        buttonDiscountCode.setMaxHeight(0);
    }

    public void setPromotionPrice(Float promotionPrice) {
        this.promotionPrice = promotionPrice;
    }
}
