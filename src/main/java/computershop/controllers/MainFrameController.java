package computershop.controllers;

import computershop.ShopApp;
import computershop.database.entity.*;
import computershop.database.service.*;
import computershop.database.view.InfoAboutSet;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class MainFrameController implements Initializable {
    private StationaryShop shopWhereApplicationWasLaunched;
    private ProductService productService = new ProductService();
    private ProductCategoryService productCategoryService = new ProductCategoryService();
    private ObservableList<Product> productsObservableList = FXCollections.observableArrayList();
    private ObservableList<InfoAboutSet> infoAboutSetsObservableList = FXCollections.observableArrayList();
    private List<ProductPhoto> selectedProductPhotos;
    private Integer photoOfSelectedProductId;
    private ObservableList<String> productCategoriesNamesObservableList = FXCollections.observableArrayList();
    private OrderPositionService orderPositionService;
    private List<OrderPosition> orderPositions;
    private InfoAboutSetService infoAboutSetService = new InfoAboutSetService();
    private ProductSetService productSetService = new ProductSetService();
    private int selectedSetId;
    private Account loggedCustomerAccount;
    private AccountService accountService = new AccountService();
    private Discount discount;
    private Float promotionPrice;

    @FXML
    Label labelShopWhereApplicationWasLaunched, labelProducer, labelModel, labelCategory, labelNetto, labelBrutto,
            labelAmount, labelVat, labelAmountOfProductsInShoppingBasket, labelTableName, labelProductsOfSet,
            labelProductInfoAndAdvancedSearchPanel, labelLoggedAccount;

    @FXML
    TextField textFieldLogin, textFieldPassword, textFieldSearch, textFieldAdvancedSearchProducer,
            textFieldAdvancedSearchModel, textFieldAdvancedSearchPriceFrom, textFieldAdvancedSearchPriceTo;

    @FXML
    TextArea textAreaDescription;

    @FXML
    ImageView buttonShoppingBasket, buttonProfile, buttonSearch, buttonPreviousPhoto, buttonNextPhoto,
            imageViewProductPhoto;

    @FXML
    Button buttonLogin, buttonRegister, buttonAddProductToShoppingBasket, buttonAdvancedSearch;

    @FXML
    ComboBox<String> comboBoxSearch, comboBoxAdvancedSearchProductCategories;

    @FXML
    TableView<Product> tableViewProducts;

    @FXML
    TableView<InfoAboutSet> tableViewSets;

    @FXML
    TableColumn<Product, String> productNameColumn, productPriceColumn;

    @FXML
    TableColumn<InfoAboutSet, String> setsNameColumn, setsPatronColumn;

    @FXML
    TableColumn<InfoAboutSet, Float> setsTotalPriceColumn;

    @FXML
    CheckBox checkBoxShowOnlyAvailableProducts, checkBoxAdvancedSearchShowOnlyAvailableProducts;

    @FXML
    RadioButton radioButtonSets, radioButtonProducts, radioButtonSearch, radioButtonAdvancedSearch;

    @FXML
    VBox vBoxProductsOfSet;

    @FXML
    HBox hBoxAdvancedSearchPanel, hBoxSearchMode, hBoxProductPanel, hBoxProductLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initProductTableView();
        setDefaultProductPhoto();
        setDefaultProductInformation();
        refreshComboBox();
        initRadioButtons();
        if (loggedCustomerAccount == null)
            labelLoggedAccount.setText("");
    }

    @FXML
    private void buttonShoppingBasket_onMouseClicked() {
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

    @FXML
    private void buttonProfile_onMouseClicked() {
        if (loggedCustomerAccount != null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/CustomerInfoFrame.fxml"));
                loader.load();

                CustomerInfoFrameController display = loader.getController();
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
    private void buttonAdvancedSearch_onAction() {
        String priceFrom = textFieldAdvancedSearchPriceFrom.getText();
        String priceTo = textFieldAdvancedSearchPriceTo.getText();
        if (priceFrom.isEmpty())
            priceFrom = "0";
        if (priceTo.isEmpty())
            priceTo = String.valueOf(Float.MAX_VALUE);
        try {
            searchProducts(comboBoxAdvancedSearchProductCategories.getSelectionModel()
                            .getSelectedItem(), "", textFieldAdvancedSearchProducer.getText(),
                    textFieldAdvancedSearchModel.getText(),
                    Float.parseFloat(priceFrom),
                    Float.parseFloat(priceTo),
                    checkBoxAdvancedSearchShowOnlyAvailableProducts.isSelected());
        } catch (NumberFormatException numExc) {
            showMessageBox(Alert.AlertType.ERROR, "Błąd",
                    "Wprowadzono niepoprawne dane.",
                    "Zły format zawartości pól odpowiedzialnych za cenę.").showAndWait();
        }
    }

    @FXML
    private void buttonSearch_onMouseClicked() {
        searchProducts(comboBoxSearch.getSelectionModel().getSelectedItem(),
                textFieldSearch.getText(), "", "", Float.parseFloat("0"),
                Float.MAX_VALUE, checkBoxShowOnlyAvailableProducts.isSelected());
    }

    @FXML
    private void buttonPreviousPhoto_onMouseClicked() {
        if (selectedProductPhotos != null && photoOfSelectedProductId > 0) {
            Image image = new Image(selectedProductPhotos.get(photoOfSelectedProductId - 1).getPhotoPath());
            imageViewProductPhoto.setImage(image);
            photoOfSelectedProductId--;
        }
    }

    @FXML
    private void buttonNextPhoto_onMouseClicked() {
        if (selectedProductPhotos != null && photoOfSelectedProductId < selectedProductPhotos.size() - 1) {
            Image image = new Image(selectedProductPhotos.get(photoOfSelectedProductId + 1).getPhotoPath());
            imageViewProductPhoto.setImage(image);
            photoOfSelectedProductId++;
        }
    }

    @FXML
    private void tableViewProducts_onMouseClicked() {
        try {
            tableViewSets.getSelectionModel().clearSelection();
            Product product = tableViewProducts.getSelectionModel().getSelectedItem();
            setProductInformation(product.getProducer(), product.getModel(), product.getProductCategory().getName(), product.getSellingPriceNetto().toString() + " PLN",
                    product.getSellingPriceBrutto().toString() + " PLN", product.getAmount().toString(),
                    product.getVatRate().toString() + " %", product.getDescription());
            selectedProductPhotos = product.getProductPhotos();
            photoOfSelectedProductId = 0;
            Image image = new Image(selectedProductPhotos.get(photoOfSelectedProductId).getPhotoPath());
            imageViewProductPhoto.setImage(image);
        } catch (NullPointerException nullExc) {
            setDefaultProductInformation();
        } catch (IndexOutOfBoundsException indExc) {
            setDefaultProductPhoto();
        }
    }

    @FXML
    private void tableViewSets_onMouseClicked() {
        try {
            tableViewProducts.getSelectionModel().clearSelection();
            setDefaultProductPhoto();
            setDefaultProductInformation();
            ProductSet productSet = productSetService.getProductSet(tableViewSets.getSelectionModel().getSelectedItem().getId());
            refreshProductTableView(productSet.getProducts());
            selectedSetId = productSet.getId();
        } catch (NullPointerException | IndexOutOfBoundsException nullExc) {
            System.out.println(nullExc.getMessage());
        }
    }

    @FXML
    private void buttonLogin_onAction() {
        String login = textFieldLogin.getText();
        String password = textFieldPassword.getText();

        if (!login.isEmpty() && !password.isEmpty()) {
            for (Account account : accountService.getAccounts()) {
                if (account.getLogin().equals(login)) {
                    if (account.getPassword().equals(password)) {
                        loggedCustomerAccount = account;
                        labelLoggedAccount.setText(loggedCustomerAccount.getCustomer().getFirstName() + " "
                                + loggedCustomerAccount.getCustomer().getLastName() + " (" +
                                loggedCustomerAccount.getLogin() + ")");
                        textFieldLogin.setText("");
                        textFieldPassword.setText("");
                        buttonLogin.setVisible(false);
                        buttonLogin.setMinWidth(0);
                        buttonLogin.setMinHeight(0);
                        buttonLogin.setPrefWidth(0);
                        buttonLogin.setPrefHeight(0);
                        buttonLogin.setMaxWidth(0);
                        buttonLogin.setMaxHeight(0);
                        buttonRegister.setText("Wyloguj");
                        showMessageBox(Alert.AlertType.INFORMATION, "Informacja",
                                "Logowanie powiodło się.",
                                "Możesz teraz korzystać z dodatkowych funkcjonalności systemu.").showAndWait();
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
    private void buttonRegister_onAction() {
        if (buttonRegister.getText().equals("Wyloguj")) {
            buttonLogin.setVisible(true);
            buttonRegister.setText("Zarejestruj");
            labelLoggedAccount.setText("");
            buttonLogin.setMinWidth(Control.USE_COMPUTED_SIZE);
            buttonLogin.setMinHeight(Control.USE_COMPUTED_SIZE);
            buttonLogin.setPrefWidth(70);
            buttonLogin.setPrefHeight(Control.USE_COMPUTED_SIZE);
            buttonLogin.setMaxWidth(Control.USE_COMPUTED_SIZE);
            buttonLogin.setMaxHeight(Control.USE_COMPUTED_SIZE);
            loggedCustomerAccount = null;
            if (discount != null) {
                discount = null;
                promotionPrice = null;
            }
        } else {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/RegisterAccountFrame.fxml"));
                loader.load();

                RegisterAccountFrameController display = loader.getController();
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
    private void buttonAddProductToShoppingBasket_onAction() {
        if ((tableViewProducts.getSelectionModel().getSelectedIndex() != -1)
                && (tableViewSets.getSelectionModel().getSelectedIndex() == -1)) {
            addProductToShoppingBasket();
        } else if ((tableViewProducts.getSelectionModel().getSelectedIndex() == -1)
                && (tableViewSets.getSelectionModel().getSelectedIndex() != -1)) {
            addProductsOfSetToShoppingBasket();
        } else {
            showMessageBox(Alert.AlertType.INFORMATION, "Informacja",
                    "Próba dodania egzemplarza produktu do koszyka nieudana.",
                    "Nie wybrano żadnego produktu.").showAndWait();
        }
    }

    @FXML
    private void radioButtonSets_onAction() {
        initSetTableView();
        productsObservableList.clear();
        tableViewProducts.setItems(productsObservableList);

        tableViewProducts.setMinWidth(400);
        tableViewProducts.setMinHeight(Control.USE_COMPUTED_SIZE);
        tableViewProducts.setPrefWidth(75);
        tableViewProducts.setPrefHeight(200);
        tableViewProducts.setMaxWidth(Control.USE_COMPUTED_SIZE);
        tableViewProducts.setMaxHeight(Control.USE_COMPUTED_SIZE);

        tableViewSets.setVisible(true);
        tableViewSets.setDisable(false);
        tableViewSets.setMinWidth(400);
        tableViewSets.setMinHeight(Control.USE_COMPUTED_SIZE);
        tableViewSets.setPrefWidth(75);
        tableViewSets.setPrefHeight(200);
        tableViewSets.setMaxWidth(Control.USE_COMPUTED_SIZE);
        tableViewSets.setMaxHeight(Control.USE_COMPUTED_SIZE);

        tableViewProducts.getSelectionModel().select(null);
        setDefaultProductInformation();
        setDefaultProductPhoto();

        labelTableName.setText("Zestawy");

        vBoxProductsOfSet.setVisible(true);
        vBoxProductsOfSet.setDisable(false);
        vBoxProductsOfSet.setMinWidth(Control.USE_COMPUTED_SIZE);
        vBoxProductsOfSet.setMinHeight(Control.USE_COMPUTED_SIZE);
        vBoxProductsOfSet.setPrefWidth(Control.USE_COMPUTED_SIZE);
        vBoxProductsOfSet.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBoxProductsOfSet.setMaxWidth(Control.USE_COMPUTED_SIZE);
        vBoxProductsOfSet.setMaxHeight(Control.USE_COMPUTED_SIZE);

        changeNormalSearchComponentsVisible(false);
        hBoxSearchMode.setVisible(false);
        radioButtonSearch.setSelected(true);
        prepareAdvancedSearchModeComponents(true);
        labelProductInfoAndAdvancedSearchPanel.setText("Opis wybranego produktu:");
        clearSearchFields();
        clearAdvancedSearchFields();
    }

    @FXML
    void radioButtonProducts_onAction() {
        initProductTableView();
        tableViewSets.setVisible(false);
        tableViewSets.setDisable(true);
        tableViewSets.setMinWidth(0);
        tableViewSets.setMinHeight(0);
        tableViewSets.setPrefWidth(0);
        tableViewSets.setPrefHeight(0);
        tableViewSets.setMaxWidth(0);
        tableViewSets.setMaxHeight(0);

        tableViewProducts.setVisible(true);
        tableViewProducts.setDisable(false);
        tableViewProducts.setMinWidth(400);
        tableViewProducts.setMinHeight(Control.USE_COMPUTED_SIZE);
        tableViewProducts.setPrefWidth(75);
        tableViewProducts.setPrefHeight(200);
        tableViewProducts.setMaxWidth(Control.USE_COMPUTED_SIZE);
        tableViewProducts.setMaxHeight(Control.USE_COMPUTED_SIZE);

        tableViewProducts.getSelectionModel().select(null);
        setDefaultProductInformation();
        setDefaultProductPhoto();

        labelTableName.setText("Produkty");

        vBoxProductsOfSet.setVisible(false);
        vBoxProductsOfSet.setDisable(true);
        vBoxProductsOfSet.setMinWidth(0);
        vBoxProductsOfSet.setMinHeight(0);
        vBoxProductsOfSet.setPrefWidth(0);
        vBoxProductsOfSet.setPrefHeight(0);
        vBoxProductsOfSet.setMaxWidth(0);
        vBoxProductsOfSet.setMaxHeight(0);

        changeNormalSearchComponentsVisible(true);
        hBoxSearchMode.setVisible(true);
        radioButtonSearch.setSelected(true);
        prepareAdvancedSearchModeComponents(true);
        labelProductInfoAndAdvancedSearchPanel.setText("Opis wybranego produktu:");
        refreshComboBox();
    }

    private void changeNormalSearchComponentsVisible(Boolean visible) {
        checkBoxShowOnlyAvailableProducts.setVisible(visible);
        textFieldSearch.setVisible(visible);
        comboBoxSearch.setVisible(visible);
        buttonSearch.setVisible(visible);
    }

    @FXML
    private void radioButtonSearch_onAction() {
        prepareAdvancedSearchModeComponents(true);
        changeNormalSearchComponentsVisible(true);
        refreshProductTableView(productService.getProducts());
        labelProductInfoAndAdvancedSearchPanel.setText("Opis wybranego produktu:");
        refreshComboBox();
        clearSearchFields();
    }

    @FXML
    private void radioButtonAdvancedSearch_onAction() {
        prepareAdvancedSearchModeComponents(false);
        changeNormalSearchComponentsVisible(false);
        refreshProductTableView(productService.getProducts());
        labelProductInfoAndAdvancedSearchPanel.setText("Opis wybranego produktu oraz panel zaawansowanego wyszukiwania:");
        refreshComboBox();
        clearAdvancedSearchFields();
    }

    private void prepareAdvancedSearchModeComponents(Boolean normalSearch) {
        if (normalSearch) {
            hBoxAdvancedSearchPanel.setVisible(false);
            hBoxAdvancedSearchPanel.setDisable(true);
            hBoxAdvancedSearchPanel.setMinWidth(0);
            hBoxAdvancedSearchPanel.setMinHeight(0);
            hBoxAdvancedSearchPanel.setPrefWidth(0);
            hBoxAdvancedSearchPanel.setPrefHeight(0);
            hBoxAdvancedSearchPanel.setMaxWidth(0);
            hBoxAdvancedSearchPanel.setMaxHeight(0);
        } else {
            hBoxAdvancedSearchPanel.setVisible(true);
            hBoxAdvancedSearchPanel.setDisable(false);
            hBoxAdvancedSearchPanel.setMinWidth(Control.USE_COMPUTED_SIZE);
            hBoxAdvancedSearchPanel.setMinHeight(Control.USE_COMPUTED_SIZE);
            hBoxAdvancedSearchPanel.setPrefWidth(642);
            hBoxAdvancedSearchPanel.setPrefHeight(329);
            hBoxAdvancedSearchPanel.setMaxWidth(Control.USE_COMPUTED_SIZE);
            hBoxAdvancedSearchPanel.setMaxHeight(Control.USE_COMPUTED_SIZE);
        }
    }

    private void initRadioButtons() {
        ToggleGroup toggleGroupViewMode = new ToggleGroup();
        radioButtonProducts.setToggleGroup(toggleGroupViewMode);
        radioButtonSets.setToggleGroup(toggleGroupViewMode);

        ToggleGroup toggleGroupSearchMode = new ToggleGroup();
        radioButtonAdvancedSearch.setToggleGroup(toggleGroupSearchMode);
        radioButtonSearch.setToggleGroup(toggleGroupSearchMode);
    }

    public void setShopWhereApplicationWasLaunched(StationaryShop stationaryShop) {
        this.shopWhereApplicationWasLaunched = stationaryShop;
        Address shopAddress = shopWhereApplicationWasLaunched.getAddress();
        labelShopWhereApplicationWasLaunched.setText(shopWhereApplicationWasLaunched.getName() + ": ul. "
                + shopAddress.getStreet() + ", " + shopAddress.getPostalCode() + " " + shopAddress.getCity()
                + ", " + shopAddress.getCountry());
    }

    private void refreshProductTableView(List<Product> products) {
        productsObservableList.clear();
        productsObservableList.addAll(products);
        tableViewProducts.setItems(productsObservableList);
    }

    private void initProductTableView() {
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("sellingPriceBrutto"));
        refreshProductTableView(productService.getProducts());
    }

    private void refreshSetTableView(List<InfoAboutSet> infoAboutSets) {
        infoAboutSetsObservableList.clear();
        infoAboutSetsObservableList.addAll(infoAboutSets);
        tableViewSets.setItems(infoAboutSetsObservableList);
    }

    private void initSetTableView() {
        setsNameColumn.setCellValueFactory(new PropertyValueFactory<>("setName"));
        setsPatronColumn.setCellValueFactory(new PropertyValueFactory<>("setPatron"));
        setsTotalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        refreshSetTableView(infoAboutSetService.getInfoAboutSets());
    }


    private void refreshComboBox() {
        productCategoriesNamesObservableList.clear();
        productCategoriesNamesObservableList.add("Wszystkie kategorie");
        List<ProductCategory> productCategories = productCategoryService.getProductCategories();
        for (ProductCategory product : productCategories)
            productCategoriesNamesObservableList.add(product.getName());
        comboBoxSearch.setItems(productCategoriesNamesObservableList);
        comboBoxSearch.getSelectionModel().select(0);

        comboBoxAdvancedSearchProductCategories.setItems(productCategoriesNamesObservableList);
        comboBoxAdvancedSearchProductCategories.getSelectionModel().select(0);
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

    private void searchProducts(String category, String name, String producer, String model, Float priceFrom, Float priceTo, Boolean amount) {
        refreshProductTableView(productService.searchProducts(category, name, producer, model, priceFrom, priceTo, !amount));
    }

    private void addProductToShoppingBasket() {
        int selectedProductId;
        Product selectedProduct;
        try {
            selectedProduct = tableViewProducts.getSelectionModel().getSelectedItem();
            selectedProductId = tableViewProducts.getSelectionModel().getSelectedIndex();
            if (radioButtonSets.isSelected()) {
                showMessageBox(Alert.AlertType.CONFIRMATION, "Operacja wymaga potwierdzenia",
                        "Wybrany produkt jest częścią zestawu.",
                        "Jesteś pewny, że nie chcesz zakupić całego zestawu?").showAndWait()
                        .ifPresent(rs -> {
                            if (rs == ButtonType.OK) {
                                OrderPosition orderPosition = new OrderPosition(selectedProduct);
                                orderPositionService.saveOrderPosition(orderPosition);
                                orderPositions.add(orderPositionService.getOrderPosition(orderPosition.getId()));
                                labelAmount.setText(String.valueOf((Integer.parseInt(labelAmount.getText()) - 1)));
                                labelAmountOfProductsInShoppingBasket.setText(String.valueOf(orderPositions.size()));
                                refreshProductTableView(productSetService.getProductSet(selectedSetId).getProducts());
                                tableViewProducts.getSelectionModel().clearAndSelect(selectedProductId);
                            }
                        });
            } else {
                OrderPosition orderPosition = new OrderPosition(selectedProduct);
                orderPositionService.saveOrderPosition(orderPosition);
                orderPositions.add(orderPositionService.getOrderPosition(orderPosition.getId()));
                labelAmount.setText(String.valueOf((Integer.parseInt(labelAmount.getText()) - 1)));
                labelAmountOfProductsInShoppingBasket.setText(String.valueOf(orderPositions.size()));
                if (radioButtonSearch.isSelected())
                    searchProducts(comboBoxSearch.getSelectionModel().getSelectedItem(),
                            textFieldSearch.getText(), "", "", Float.parseFloat("0"),
                            Float.MAX_VALUE, checkBoxShowOnlyAvailableProducts.isSelected());
                else {
                    String priceFrom = textFieldAdvancedSearchPriceFrom.getText();
                    String priceTo = textFieldAdvancedSearchPriceTo.getText();
                    if (priceFrom.isEmpty())
                        priceFrom = "0";
                    if (priceTo.isEmpty())
                        priceTo = String.valueOf(Float.MAX_VALUE);

                    searchProducts(comboBoxAdvancedSearchProductCategories.getSelectionModel()
                                    .getSelectedItem(), "", textFieldAdvancedSearchProducer.getText(),
                            textFieldAdvancedSearchModel.getText(),
                            Float.parseFloat(priceFrom),
                            Float.parseFloat(priceTo),
                            checkBoxAdvancedSearchShowOnlyAvailableProducts.isSelected());
                }

                tableViewProducts.getSelectionModel().clearAndSelect(selectedProductId);
            }
        } catch (GenericJDBCException jdbcExc) {
            showMessageBox(Alert.AlertType.INFORMATION, "Informacja",
                    "Próba dodania egzemplarza produktu do koszyka nieudana.",
                    jdbcExc.getSQLException().getMessage()).showAndWait();
        }
    }

    private void addProductsOfSetToShoppingBasket() {
        InfoAboutSet infoAboutSet = tableViewSets.getSelectionModel().getSelectedItem();
        ProductSet productSet = productSetService.getProductSet(infoAboutSet.getId());
        OrderPosition orderPosition;
        List<Product> missingProducts = new ArrayList<>();
        for (Product p : productSet.getProducts()) {
            if (p.getAmount() == 0) {
                missingProducts.add(p);
            } else {
                orderPosition = new OrderPosition(p);
                orderPositionService.saveOrderPosition(orderPosition);
                this.orderPositions.add(orderPositionService.getOrderPosition(orderPosition.getId()));
            }
        }

        tableViewProducts.getSelectionModel().clearSelection();
        productSet = productSetService.getProductSet(tableViewSets.getSelectionModel().getSelectedItem().getId());
        refreshProductTableView(productSet.getProducts());
        tableViewSets.getSelectionModel().select(infoAboutSet);
        labelAmountOfProductsInShoppingBasket.setText(String.valueOf(this.orderPositions.size()));

        if (missingProducts.size() > 0) {
            StringBuilder missingProductsInfo = new StringBuilder();
            for (int i = 0; i < missingProducts.size(); i++) {
                if (i == missingProducts.size() - 1)
                    missingProductsInfo.append(missingProducts.get(i).getName()).append(".");
                else
                    missingProductsInfo.append(missingProducts.get(i).getName()).append(",\n\t- ");
            }
            showMessageBox(Alert.AlertType.INFORMATION, "Informacja",
                    "Nie wszystkie produkty zestawu zostały dodane do koszyka.",
                    "Następujących produktów nie ma na stanie:\n\t- " + missingProductsInfo + "\n" +
                            "Przepraszamy za wszelkie niedogodności.").showAndWait();
        }
    }

    private void setDefaultProductPhoto() {
        Image image = new Image("images/no-thumb-innovation_ruby_350x200.jpg");
        imageViewProductPhoto.setImage(image);
    }

    private void setDefaultProductInformation() {
        setProductInformation("", "", "", "", "", "", "",
                "");
    }

    private void clearSearchFields() {
        textFieldSearch.setText("");
        comboBoxSearch.getSelectionModel().select(0);
        checkBoxShowOnlyAvailableProducts.setSelected(false);
    }

    private void clearAdvancedSearchFields() {
        comboBoxAdvancedSearchProductCategories.getSelectionModel().select(0);
        textFieldAdvancedSearchProducer.setText("");
        textFieldAdvancedSearchModel.setText("");
        textFieldAdvancedSearchPriceFrom.setText("");
        textFieldAdvancedSearchPriceTo.setText("");
        checkBoxAdvancedSearchShowOnlyAvailableProducts.setSelected(false);
    }

    private void setProductInformation(String producer, String model, String category, String netto, String brutto,
                                       String amount, String vat, String description) {
        labelProducer.setText(producer);
        labelModel.setText(model);
        labelCategory.setText(category);
        labelNetto.setText(netto);
        labelBrutto.setText(brutto);
        labelAmount.setText(amount);
        labelVat.setText(vat);
        textAreaDescription.setText(description);
    }

    public void setOrderPositionService(OrderPositionService orderPositionService) {
        this.orderPositionService = orderPositionService;
    }

    public void setOrderPositions(List<OrderPosition> orderPositions) {
        this.orderPositions = orderPositions;
        labelAmountOfProductsInShoppingBasket.setText(String.valueOf(orderPositions.size()));
    }

    public void setLoggedCustomerAccount(Account loggedCustomerAccount, AccountService accountService) {
        this.loggedCustomerAccount = loggedCustomerAccount;
        this.accountService = accountService;
        if (loggedCustomerAccount == null) {
            labelLoggedAccount.setText("");
            buttonLogin.setMinWidth(Control.USE_COMPUTED_SIZE);
            buttonLogin.setMinHeight(Control.USE_COMPUTED_SIZE);
            buttonLogin.setPrefWidth(70);
            buttonLogin.setPrefHeight(Control.USE_COMPUTED_SIZE);
            buttonLogin.setMaxWidth(Control.USE_COMPUTED_SIZE);
            buttonLogin.setMaxHeight(Control.USE_COMPUTED_SIZE);
        } else {
            labelLoggedAccount.setText(loggedCustomerAccount.getCustomer().getFirstName() + " "
                    + loggedCustomerAccount.getCustomer().getLastName() + " (" + loggedCustomerAccount.getLogin() + ")");
            textFieldLogin.setText("");
            textFieldPassword.setText("");
            buttonLogin.setVisible(false);
            buttonRegister.setText("Wyloguj");
            buttonLogin.setMinWidth(0);
            buttonLogin.setMinHeight(0);
            buttonLogin.setPrefWidth(0);
            buttonLogin.setPrefHeight(0);
            buttonLogin.setMaxWidth(0);
            buttonLogin.setMaxHeight(0);
        }
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public void setPromotionPrice(Float promotionPrice) {
        this.promotionPrice = promotionPrice;
    }
}
