package computershop.controllers;

import computershop.ShopApp;
import computershop.database.entity.*;
import computershop.database.service.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
public class PurchaseWithoutLoggingInFrameController implements Initializable {
    private StationaryShop shopWhereApplicationWasLaunched;
    private OrderPositionService orderPositionService;
    private List<OrderPosition> orderPositions;
    private Account loggedCustomerAccount;
    private AccountService accountService;
    private Discount discount;
    private Float promotionPrice;

    private AddressService addressService = new AddressService();
    private CustomerService customerService = new CustomerService();
    private OrderService orderService = new OrderService();

    private String simpleNamesPattern = "^[A-ZŻŹĆĄŚĘŁÓŃ][a-zżźćńółęąś]+$";

    @FXML
    private Label labelShopWhereApplicationWasLaunched, labelName, labelSurname, labelEmail, labelPhone, labelStreet, labelPostalCode,
            labelCity, labelCountry;

    @FXML
    private TextField textFieldName, textFieldSurname, textFieldEmail, textFieldPhoneNumber,
            textFieldStreet, textFieldPostalCode, textFieldCity, textFieldCountry;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void buttonBack_onAction() {
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

    @FXML
    void buttonConfirm_onAction() {
        String name = labelName.getText();
        String surname = labelSurname.getText();
        String email = labelEmail.getText();
        String phoneNumber = labelPhone.getText();

        String street = labelStreet.getText();
        String postalCode = labelPostalCode.getText();
        String city = labelCity.getText();
        String country = labelCountry.getText();

        if (name.isEmpty() && surname.isEmpty() && email.isEmpty() && phoneNumber.isEmpty() && street.isEmpty()
                && postalCode.isEmpty() && city.isEmpty() && country.isEmpty()) {
            Address address = new Address(textFieldStreet.getText(), textFieldCity.getText(),
                    textFieldPostalCode.getText(), textFieldCountry.getText());
            addressService.saveAddress(address);

            Customer customer = new Customer(textFieldName.getText(), textFieldSurname.getText(),
                    textFieldEmail.getText(), textFieldPhoneNumber.getText());
            customer.setAddress(address);
            customerService.saveCustomer(customer);

            Order order = new Order(0f, customer);
            order.setStationaryShop(shopWhereApplicationWasLaunched);

            orderService.saveOrder(order);

            for (OrderPosition orderPosition : orderPositions) {
                orderPosition.setOrder(order);
                orderPositionService.saveOrderPosition(orderPosition);
            }

            orderPositions.clear();

            showMessageBox(Alert.AlertType.INFORMATION, "Informacja",
                    "Zamówienie zostało sfinalizowane.",
                    "Zapraszamy do kasy w celu dokonania płatności i odbioru produktów.").showAndWait();
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
        } else {
            showMessageBox(Alert.AlertType.ERROR, "Błąd",
                    "Błąd danych.",
                    "Sprawdź poprawność wpisanych danych.").showAndWait();
        }
    }

    @FXML
    void textFieldCity_onAction() {
        if (textFieldCity.getText().isEmpty())
            labelCity.setText("Podaj miasto.");
        else if (!textFieldCity.getText().matches(simpleNamesPattern)) {
            labelCity.setText("Niepoprawny format.");
        } else if (textFieldCity.getText().length() > 30)
            labelCity.setText("Przekroczono limit znaków.");
        else
            labelCity.setText("");
    }

    @FXML
    void textFieldCountry_onAction() {
        if (textFieldCountry.getText().isEmpty())
            labelCountry.setText("Podaj kraj.");
        else if (!textFieldCountry.getText().matches(simpleNamesPattern)) {
            labelCountry.setText("Niepoprawny format.");
        } else if (textFieldCountry.getText().length() > 20)
            labelCountry.setText("Przekroczono limit znaków.");
        else
            labelCountry.setText("");
    }

    @FXML
    void textFieldEmail_onAction() {
        String emailPattern = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])";
        if (textFieldEmail.getText().isEmpty())
            labelEmail.setText("Podaj adres e-mail.");
        else if (!textFieldEmail.getText().matches(emailPattern)) {
            labelEmail.setText("Niepoprawny format.");
        } else if (textFieldEmail.getText().length() > 70)
            labelEmail.setText("Przekroczono limit znaków.");
        else
            labelEmail.setText("");
    }

    @FXML
    void textFieldName_onAction() {
        if (textFieldName.getText().isEmpty())
            labelName.setText("Podaj imię.");
        else if (!textFieldName.getText().matches(simpleNamesPattern)) {
            labelName.setText("Niepoprawny format.");
        } else if (textFieldName.getText().length() > 40)
            labelName.setText("Przekroczono limit znaków.");
        else
            labelName.setText("");
    }

    @FXML
    void textFieldPhoneNumber_onAction() {
        String phoneNumberPattern = "^[1-9][0-9]{8}$";
        if (textFieldPhoneNumber.getText().isEmpty())
            labelPhone.setText("Podaj nr telefonu.");
        else if (!textFieldPhoneNumber.getText().matches(phoneNumberPattern))
            labelPhone.setText("Niepoprawny format.");
        else
            labelPhone.setText("");
    }

    @FXML
    void textFieldPostalCode_onAction() {
        String postalCodePattern = "^[0-9]{2}-[0-9]{3}$";
        if (textFieldPostalCode.getText().isEmpty())
            labelPostalCode.setText("Podaj kod pocztowy.");
        else if (!textFieldPostalCode.getText().matches(postalCodePattern))
            labelPostalCode.setText("Niepoprawny format.");
        else
            labelPostalCode.setText("");
    }

    @FXML
    void textFieldStreet_onAction() {
        String streetPattern = "^[A-ZŻŹĆĄŚĘŁÓŃ][a-zżźćńółęąś]+\\s[1-9][0-9]*(/[1-9][0-9]*)?$";
        if (textFieldStreet.getText().isEmpty())
            labelStreet.setText("Podaj ulicę i nr domu.");
        else if (!textFieldStreet.getText().matches(streetPattern)) {
            labelStreet.setText("Niepoprawny format.");
        } else if (textFieldStreet.getText().length() > 40)
            labelStreet.setText("Przekroczono limit znaków.");
        else
            labelStreet.setText("");
    }

    @FXML
    void textFieldSurname_onAction() {
        if (textFieldSurname.getText().isEmpty())
            labelSurname.setText("Podaj nazwisko.");
        else if (!textFieldSurname.getText().matches(simpleNamesPattern)) {
            labelSurname.setText("Niepoprawny format.");
        } else if (textFieldSurname.getText().length() > 60)
            labelSurname.setText("Przekroczono limit znaków.");
        else
            labelSurname.setText("");
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

    public void setLoggedCustomerAccount(Account loggedCustomerAccount, AccountService accountService) {
        this.loggedCustomerAccount = loggedCustomerAccount;
        this.accountService = accountService;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public void setPromotionPrice(Float promotionPrice) {
        this.promotionPrice = promotionPrice;
    }
}
