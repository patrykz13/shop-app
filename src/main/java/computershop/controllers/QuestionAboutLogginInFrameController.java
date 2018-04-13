package computershop.controllers;

import computershop.ShopApp;
import computershop.database.entity.*;
import computershop.database.service.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class QuestionAboutLogginInFrameController implements Initializable {
    private StationaryShop shopWhereApplicationWasLaunched;
    private OrderPositionService orderPositionService;
    private List<OrderPosition> orderPositions;
    private Account loggedCustomerAccount;
    private AccountService accountService;
    private OrderService orderService = new OrderService();
    private Discount discount;
    private Float promotionPrice;
    private DiscountService discountService = new DiscountService();
    private ProductCategoryService productCategoryService = new ProductCategoryService();

    @FXML
    private Label labelShopWhereApplicationWasLaunched;

    @FXML
    private TextField textFieldLogin, textFieldPassword;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void buttonContinueWithoutLogin_onAction() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/PurchaseWithoutLoggingInFrame.fxml"));
            loader.load();

            PurchaseWithoutLoggingInFrameController display = loader.getController();
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
    void buttonLogin_onAction() {
        String login = textFieldLogin.getText();
        String password = textFieldPassword.getText();

        if (!login.isEmpty() && !password.isEmpty()) {
            for (Account account : accountService.getAccounts()) {
                if (account.getLogin().equals(login)) {
                    if (account.getPassword().equals(password)) {
                        loggedCustomerAccount = account;
                        textFieldLogin.setText("");
                        textFieldPassword.setText("");
                        showMessageBox(Alert.AlertType.CONFIRMATION, "Operacja wymaga potwierdzenia",
                                "Pomyślnie zalogowano jako: " +
                                        loggedCustomerAccount.getCustomer().getFirstName() + " " +
                                        loggedCustomerAccount.getCustomer().getLastName() + " (" +
                                        loggedCustomerAccount.getLogin() + ")\n" +
                                        "Jesteś pewny, że chcesz dokonać zakupu wybranych produktów?",
                                "Jeśli tak - potwierdź naciskająć OK, a następnie przejdź do kasy\n" +
                                        "w celu dokonania płatności i odbioru produktów.").showAndWait()
                                .ifPresent(rs -> {
                                    if (rs == ButtonType.OK) {
                                        Order order = new Order(0f, loggedCustomerAccount.getCustomer());
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
                        showMessageBox(Alert.AlertType.WARNING, "Informacja",
                                "Logowanie nie powiodło się.",
                                "Niepoprawne hasło.").showAndWait();
                    return;
                }
            }
            showMessageBox(Alert.AlertType.WARNING, "Informacja",
                    "Logowanie nie powiodło się.",
                    "Niepoprawne dane logowania.").showAndWait();
            textFieldLogin.setText("");
            textFieldPassword.setText("");
        } else {
            showMessageBox(Alert.AlertType.INFORMATION, "Informacja",
                    "Nie uzupełniono wszystkich pól.",
                    "W celu zalogowania podaj poprawny login i hasło.").showAndWait();
        }
    }

    @FXML
    void buttonRegister_onAction() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/PurchaseWithCreateAccountFrame.fxml"));
            loader.load();

            PurchaseWithCreateAccountFrameController display = loader.getController();
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
    void buttonBack_onAction() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/ShoppingBasketFrame.fxml"));
            loader.load();

            ShoppingBasketFrameController display = loader.getController();
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

    public void setOrderPositionService(OrderPositionService orderPositionService) {
        this.orderPositionService = orderPositionService;
    }

    public void setOrderPositions(List<OrderPosition> orderPositions) {
        this.orderPositions = orderPositions;
    }

    public void setShopWhereApplicationWasLaunched(StationaryShop stationaryShop) {
        this.shopWhereApplicationWasLaunched = stationaryShop;
        Address shopAddress = shopWhereApplicationWasLaunched.getAddress();
        labelShopWhereApplicationWasLaunched.setText(shopWhereApplicationWasLaunched.getName() + ": ul. "
                + shopAddress.getStreet() + ", " + shopAddress.getPostalCode() + " " + shopAddress.getCity()
                + ", " + shopAddress.getCountry());
    }

    public void setLoggedCustomerAccount(Account loggedCustomerAccount, AccountService accountService) {
        this.loggedCustomerAccount = loggedCustomerAccount;
        this.accountService = accountService;
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

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public void setPromotionPrice(Float promotionPrice) {
        this.promotionPrice = promotionPrice;
    }
}
