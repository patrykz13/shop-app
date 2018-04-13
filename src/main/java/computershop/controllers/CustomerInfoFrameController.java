package computershop.controllers;

import computershop.ShopApp;
import computershop.database.entity.*;
import computershop.database.service.*;
import computershop.database.view.InfoAboutDiscounts;
import computershop.database.view.InfoAboutOrder;
import computershop.database.view.InfoAboutOrderPositions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class CustomerInfoFrameController implements Initializable {
    private StationaryShop shopWhereApplicationWasLaunched;
    private OrderPositionService orderPositionService;
    private InfoAboutDiscountService infoAboutDiscountsService = new InfoAboutDiscountService();
    private DecimalFormat decimalFormat = new DecimalFormat();
    private ObservableList<InfoAboutDiscounts> infoAboutDiscountsObservableList = FXCollections.observableArrayList();
    private List<OrderPosition> orderPositions;

    private InfoAboutOrderService infoAboutOrderService = new InfoAboutOrderService();
    private ObservableList<InfoAboutOrder> infoAboutOrderObservableList = FXCollections.observableArrayList();

    private InfoAboutOrderPositionsService infoAboutOrderPositionsService = new InfoAboutOrderPositionsService();
    private ObservableList<InfoAboutOrderPositions> infoAboutOrderPositionsObservableList = FXCollections.observableArrayList();

    private Account loggedCustomerAccount;
    private Discount discount;
    private Float promotionPrice;

    private AddressService addressService = new AddressService();
    private CustomerService customerService = new CustomerService();
    private OrderService orderService = new OrderService();
    private AccountService accountService = new AccountService();

    private String simpleNamesPattern = "^[A-ZŻŹĆĄŚĘŁÓŃ][a-zżźćńółęąś]+$";
    private String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,30}$";

    @FXML
    private Label labelShopWhereApplicationWasLaunched, labelName, labelSurname, labelEmail, labelPhone, labelStreet, labelPostalCode,
            labelCity, labelCountry, labelLogin, labelPassword, labelConfirmPassword;

    @FXML
    private TextField textFieldName, textFieldSurname, textFieldEmail, textFieldPhoneNumber,
            textFieldStreet, textFieldPostalCode, textFieldCity, textFieldCountry, textFieldLogin;

    @FXML
    private PasswordField passwordFieldPassword, passwordFieldConfirmPassword;

    @FXML
    private RadioButton radioButtonAccountInfo, radioButtonOrders, radioButtonDiscounts;

    @FXML
    private VBox vBoxAccountInfo, vBoxOrders, vBoxDiscounts;

    @FXML
    private TableView<InfoAboutDiscounts> tableViewDiscount;

    @FXML
    private TableView<InfoAboutOrder> tableViewOrders;

    @FXML
    private TableView<InfoAboutOrderPositions> tableViewOrderProducts;

    @FXML
    private TableColumn<InfoAboutOrder, Date> tableViewOrdersDataColumn;

    @FXML
    TableColumn<InfoAboutOrder, String> tableViewOrdersShopColumn, tableViewOrdersDiscountColumn;

    @FXML
    TableColumn<InfoAboutOrder, Float> tableViewOrdersPriceColumn, tableViewOrdersPromotionPriceColumn;

    @FXML
    private TableColumn<InfoAboutOrderPositions, String> tableViewOrderProductsNameColumn, tableViewOrderProductsBarcodeColumn;

    @FXML
    private TableColumn<InfoAboutOrderPositions, Float> tableViewOrderProductsPriceNettoColumn, tableViewOrderProductsPriceBruttoColumn,
            tableViewOrderProductsVatColumn;

    @FXML
    private TableColumn<InfoAboutDiscounts, String> tableViewCodeColumn, tableViewCategoryColumn, tableViewUsedColumn;

    @FXML
    private TableColumn<InfoAboutDiscounts, Integer> tableViewPercentageColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initRadioButtons();
        decimalFormat.setMaximumFractionDigits(2);
    }

    private void initAccountInfo() {
        textFieldName.setText(loggedCustomerAccount.getCustomer().getFirstName());
        textFieldSurname.setText(loggedCustomerAccount.getCustomer().getLastName());
        textFieldEmail.setText(loggedCustomerAccount.getCustomer().getEmail());
        textFieldPhoneNumber.setText(loggedCustomerAccount.getCustomer().getPhoneNumber());
        textFieldStreet.setText(loggedCustomerAccount.getCustomer().getAddress().getStreet());
        textFieldPostalCode.setText(loggedCustomerAccount.getCustomer().getAddress().getPostalCode());
        textFieldCity.setText(loggedCustomerAccount.getCustomer().getAddress().getCity());
        textFieldCountry.setText(loggedCustomerAccount.getCustomer().getAddress().getCountry());
        textFieldLogin.setText(loggedCustomerAccount.getLogin());
        passwordFieldPassword.setText(loggedCustomerAccount.getPassword());
        passwordFieldConfirmPassword.setText(loggedCustomerAccount.getPassword());

        labelName.setText("");
        labelSurname.setText("");
        labelEmail.setText("");
        labelPhone.setText("");
        labelStreet.setText("");
        labelPostalCode.setText("");
        labelCity.setText("");
        labelCountry.setText("");
        labelPassword.setText("");
        labelConfirmPassword.setText("");
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
    void buttonUpdate_onAction() {
        String name = labelName.getText();
        String surname = labelSurname.getText();
        String email = labelEmail.getText();
        String phoneNumber = labelPhone.getText();

        String street = labelStreet.getText();
        String postalCode = labelPostalCode.getText();
        String city = labelCity.getText();
        String country = labelCountry.getText();
        String password = labelPassword.getText();
        String confirmedPassword = labelConfirmPassword.getText();

        if (name.isEmpty() && surname.isEmpty() && email.isEmpty() && phoneNumber.isEmpty() && street.isEmpty()
                && postalCode.isEmpty() && city.isEmpty() && country.isEmpty() && password.isEmpty()
                && confirmedPassword.isEmpty()) {
            showMessageBox(Alert.AlertType.CONFIRMATION, "Operacja wymaga potwierdzenia",
                    "Próba aktualizacji danych użytkownika.",
                    "Jesteś pewny, że chcesz zaaktualizować swoje dane?").showAndWait()
                    .ifPresent(rs -> {
                        if (rs == ButtonType.OK) {
                            loggedCustomerAccount.getCustomer().getAddress().setStreet(textFieldStreet.getText());
                            loggedCustomerAccount.getCustomer().getAddress().setCity(textFieldCity.getText());
                            loggedCustomerAccount.getCustomer().getAddress().setPostalCode(textFieldPostalCode.getText());
                            loggedCustomerAccount.getCustomer().getAddress().setCountry(textFieldCountry.getText());
                            addressService.saveAddress(loggedCustomerAccount.getCustomer().getAddress());

                            loggedCustomerAccount.getCustomer().setFirstName(textFieldName.getText());
                            loggedCustomerAccount.getCustomer().setLastName(textFieldSurname.getText());
                            loggedCustomerAccount.getCustomer().setEmail(textFieldEmail.getText());
                            loggedCustomerAccount.getCustomer().setPhoneNumber(textFieldPhoneNumber.getText());
                            customerService.saveCustomer(loggedCustomerAccount.getCustomer());

                            loggedCustomerAccount.setPassword(passwordFieldPassword.getText());
                            accountService.saveAccount(loggedCustomerAccount);

                            loggedCustomerAccount = accountService.getAccount(loggedCustomerAccount.getId());

                            setShopWhereApplicationWasLaunched(this.shopWhereApplicationWasLaunched);
                            setLoggedCustomerAccount(this.loggedCustomerAccount, this.accountService);
                        }
                    });
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
        String emailPattern = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\" +
                "x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(" +
                "?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]" +
                "|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01" +
                "-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])";
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

    @FXML
    void passwordFieldPassword_onAction() {
        if (passwordFieldPassword.getText().isEmpty())
            labelPassword.setText("Podaj hasło.");
        else if (!passwordFieldPassword.getText().matches(passwordPattern))
            labelPassword.setText("Niepoprawny format.");
        else if (labelConfirmPassword.getText().isEmpty() && !(passwordFieldConfirmPassword.getText().equals(passwordFieldPassword.getText())))
            labelConfirmPassword.setText("Hasła nie zgadzają się.");
        else if (labelConfirmPassword.getText().equals("Hasła nie zgadzają się.") && (passwordFieldConfirmPassword.getText().equals(passwordFieldPassword.getText())))
            labelConfirmPassword.setText("");
        else
            labelPassword.setText("");
    }

    @FXML
    void passwordFieldConfirmPassword_onAction() {
        if (passwordFieldConfirmPassword.getText().isEmpty())
            labelConfirmPassword.setText("Ponownie wprowadź hasło.");
        else if (!passwordFieldConfirmPassword.getText().matches(passwordPattern))
            labelConfirmPassword.setText("Niepoprawny format.");
        else if (labelPassword.getText().isEmpty() && !(passwordFieldConfirmPassword.getText().equals(passwordFieldPassword.getText())))
            labelConfirmPassword.setText("Hasła nie zgadzają się.");
        else if (labelConfirmPassword.getText().equals("Hasła nie zgadzają się.") && (passwordFieldConfirmPassword.getText().equals(passwordFieldPassword.getText())))
            labelConfirmPassword.setText("");
        else
            labelConfirmPassword.setText("");
    }

    @FXML
    void radioButtonAccountInfo_onAction() {
        vBoxAccountInfo.setVisible(true);
        vBoxAccountInfo.setDisable(false);
        vBoxAccountInfo.setMinWidth(Control.USE_COMPUTED_SIZE);
        vBoxAccountInfo.setMinHeight(Control.USE_COMPUTED_SIZE);
        vBoxAccountInfo.setPrefWidth(985);
        vBoxAccountInfo.setPrefHeight(569);
        vBoxAccountInfo.setMaxWidth(Control.USE_COMPUTED_SIZE);
        vBoxAccountInfo.setMaxHeight(Control.USE_COMPUTED_SIZE);

        vBoxOrders.setVisible(false);
        vBoxOrders.setDisable(true);
        vBoxOrders.setMinWidth(0);
        vBoxOrders.setMinHeight(0);
        vBoxOrders.setPrefWidth(0);
        vBoxOrders.setPrefHeight(0);
        vBoxOrders.setMaxWidth(0);
        vBoxOrders.setMaxHeight(0);

        vBoxDiscounts.setVisible(false);
        vBoxDiscounts.setDisable(true);
        vBoxDiscounts.setMinWidth(0);
        vBoxDiscounts.setMinHeight(0);
        vBoxDiscounts.setPrefWidth(0);
        vBoxDiscounts.setPrefHeight(0);
        vBoxDiscounts.setMaxWidth(0);
        vBoxDiscounts.setMaxHeight(0);

        initAccountInfo();
    }

    @FXML
    void radioButtonDiscounts_onAction() {
        vBoxAccountInfo.setVisible(false);
        vBoxAccountInfo.setDisable(true);
        vBoxAccountInfo.setMinWidth(0);
        vBoxAccountInfo.setMinHeight(0);
        vBoxAccountInfo.setPrefWidth(0);
        vBoxAccountInfo.setPrefHeight(0);
        vBoxAccountInfo.setMaxWidth(0);
        vBoxAccountInfo.setMaxHeight(0);

        vBoxOrders.setVisible(false);
        vBoxOrders.setDisable(true);
        vBoxOrders.setMinWidth(0);
        vBoxOrders.setMinHeight(0);
        vBoxOrders.setPrefWidth(0);
        vBoxOrders.setPrefHeight(0);
        vBoxOrders.setMaxWidth(0);
        vBoxOrders.setMaxHeight(0);

        vBoxDiscounts.setVisible(true);
        vBoxDiscounts.setDisable(false);
        vBoxDiscounts.setMinWidth(Control.USE_COMPUTED_SIZE);
        vBoxDiscounts.setMinHeight(Control.USE_COMPUTED_SIZE);
        vBoxDiscounts.setPrefWidth(862);
        vBoxDiscounts.setPrefHeight(649);
        vBoxDiscounts.setMaxWidth(Control.USE_COMPUTED_SIZE);
        vBoxDiscounts.setMaxHeight(Control.USE_COMPUTED_SIZE);

        initDiscountTableView();
    }

    @FXML
    void radioButtonOrders_onAction() {
        vBoxAccountInfo.setVisible(false);
        vBoxAccountInfo.setDisable(true);
        vBoxAccountInfo.setMinWidth(0);
        vBoxAccountInfo.setMinHeight(0);
        vBoxAccountInfo.setPrefWidth(0);
        vBoxAccountInfo.setPrefHeight(0);
        vBoxAccountInfo.setMaxWidth(0);
        vBoxAccountInfo.setMaxHeight(0);

        vBoxOrders.setVisible(true);
        vBoxOrders.setDisable(false);
        vBoxOrders.setMinWidth(Control.USE_COMPUTED_SIZE);
        vBoxOrders.setMinHeight(Control.USE_COMPUTED_SIZE);
        vBoxOrders.setPrefWidth(985);
        vBoxOrders.setPrefHeight(569);
        vBoxOrders.setMaxWidth(Control.USE_COMPUTED_SIZE);
        vBoxOrders.setMaxHeight(Control.USE_COMPUTED_SIZE);

        vBoxDiscounts.setVisible(false);
        vBoxDiscounts.setDisable(true);
        vBoxDiscounts.setMinWidth(0);
        vBoxDiscounts.setMinHeight(0);
        vBoxDiscounts.setPrefWidth(0);
        vBoxDiscounts.setPrefHeight(0);
        vBoxDiscounts.setMaxWidth(0);
        vBoxDiscounts.setMaxHeight(0);

        initOrderTableView();
    }

    @FXML
    void tableViewOrders_onMouseClicked() {
        try {
            initOrderPositionsTableView(tableViewOrders.getSelectionModel().getSelectedItem().getId());
        } catch (NullPointerException | IndexOutOfBoundsException ignored) {

        }
    }

    private void initRadioButtons() {
        ToggleGroup toggleGroupViewMode = new ToggleGroup();
        radioButtonAccountInfo.setToggleGroup(toggleGroupViewMode);
        radioButtonOrders.setToggleGroup(toggleGroupViewMode);
        radioButtonDiscounts.setToggleGroup(toggleGroupViewMode);
    }

    private void refreshDiscountTableView(List<InfoAboutDiscounts> infoAboutDiscounts) {
        infoAboutDiscountsObservableList.clear();
        infoAboutDiscountsObservableList.addAll(infoAboutDiscounts);
        tableViewDiscount.setItems(infoAboutDiscountsObservableList);
    }

    private void initDiscountTableView() {
        tableViewCodeColumn.setCellValueFactory(new PropertyValueFactory<>("discountCode"));
        tableViewCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("productCategory"));
        tableViewPercentageColumn.setCellValueFactory(new PropertyValueFactory<>("discountPercentage"));
        tableViewUsedColumn.setCellValueFactory(new PropertyValueFactory<>("isUsed"));
        refreshDiscountTableView(infoAboutDiscountsService.getDiscountsForAccount(loggedCustomerAccount.getId()));
    }

    private void refreshOrderTableView(List<InfoAboutOrder> infoAboutOrder) {
        infoAboutOrderObservableList.clear();
        infoAboutOrderObservableList.addAll(infoAboutOrder);
        tableViewOrders.setItems(infoAboutOrderObservableList);
    }

    private void initOrderTableView() {
        tableViewOrdersDataColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableViewOrdersDataColumn.setCellFactory(col ->
                new TableCell<InfoAboutOrder, Date>() {
                    @Override
                    public void updateItem(Date date, boolean empty) {
                        super.updateItem(date, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(new SimpleDateFormat("dd-MM-yyyy | HH:mm:ss").format(date));
                        }
                    }
                });
        tableViewOrdersShopColumn.setCellValueFactory(new PropertyValueFactory<>("stationaryShop"));
        tableViewOrdersPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableViewOrdersPriceColumn.setCellFactory(col ->
                new TableCell<InfoAboutOrder, Float>() {
                    @Override
                    public void updateItem(Float price, boolean empty) {
                        super.updateItem(price, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(String.format(decimalFormat.format(price)));
                        }
                    }
                });
        tableViewOrdersDiscountColumn.setCellValueFactory(new PropertyValueFactory<>("discountCode"));
        tableViewOrdersPromotionPriceColumn.setCellValueFactory(new PropertyValueFactory<>("promotionPrice"));
        tableViewOrdersPromotionPriceColumn.setCellFactory(col ->
                new TableCell<InfoAboutOrder, Float>() {
                    @Override
                    public void updateItem(Float price, boolean empty) {
                        super.updateItem(price, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(String.format(decimalFormat.format(price)));
                        }
                    }
                });
        refreshOrderTableView(infoAboutOrderService.getOrdersForCustomer(loggedCustomerAccount.getCustomer().getId()));
    }

    private void refreshOrderPositionsTableView(List<InfoAboutOrderPositions> infoAboutOrderPositions) {
        infoAboutOrderPositionsObservableList.clear();
        infoAboutOrderPositionsObservableList.addAll(infoAboutOrderPositions);
        tableViewOrderProducts.setItems(infoAboutOrderPositionsObservableList);
    }

    private void initOrderPositionsTableView(int orderId) {
        tableViewOrderProductsNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        tableViewOrderProductsBarcodeColumn.setCellValueFactory(new PropertyValueFactory<>("barcode"));
        tableViewOrderProductsPriceNettoColumn.setCellValueFactory(new PropertyValueFactory<>("priceNetto"));
        tableViewOrderProductsPriceNettoColumn.setCellFactory(col ->
                new TableCell<InfoAboutOrderPositions, Float>() {
                    @Override
                    public void updateItem(Float price, boolean empty) {
                        super.updateItem(price, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(String.format(decimalFormat.format(price)));
                        }
                    }
                });
        tableViewOrderProductsPriceBruttoColumn.setCellValueFactory(new PropertyValueFactory<>("priceBrutto"));
        tableViewOrderProductsPriceBruttoColumn.setCellFactory(col ->
                new TableCell<InfoAboutOrderPositions, Float>() {
                    @Override
                    public void updateItem(Float price, boolean empty) {
                        super.updateItem(price, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(String.format(decimalFormat.format(price)));
                        }
                    }
                });
        tableViewOrderProductsVatColumn.setCellValueFactory(new PropertyValueFactory<>("vatRate"));

        refreshOrderPositionsTableView(infoAboutOrderPositionsService.getOrderPositionsForOrder(orderId));
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

        String actualText = labelShopWhereApplicationWasLaunched.getText();
        if (loggedCustomerAccount != null)
            labelShopWhereApplicationWasLaunched.setText(actualText + " | zalogowano jako: " +
                    loggedCustomerAccount.getCustomer().getFirstName() + " " +
                    loggedCustomerAccount.getCustomer().getLastName() + " (" + loggedCustomerAccount.getLogin() + ")");

        initAccountInfo();
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public void setPromotionPrice(Float promotionPrice) {
        this.promotionPrice = promotionPrice;
    }
}
