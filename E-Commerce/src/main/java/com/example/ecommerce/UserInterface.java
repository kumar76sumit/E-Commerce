package com.example.ecommerce;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class UserInterface {
    GridPane loginPage;
    HBox headerBar;
    HBox footerBar;
    Button signInButton;
    Label welcomeLabel;
    Customer loggedInCustomer;
    ProductList productList=new ProductList();
    VBox productPage;
    VBox body;
    Button placeOrderButton=new Button("Place Order");
    ObservableList<Product> itemsInCart= FXCollections.observableArrayList();

    UserInterface()
    {
        createLoginPage();
        createHeader();
        createFooter();
    }

    BorderPane createContent()
    {
        BorderPane root=new BorderPane();
        root.setPrefSize(800,600);
//        root.getChildren().add(loginPage);
        root.setTop(headerBar);
//        root.setCenter(loginPage);
        body=new VBox();
        body.setAlignment(Pos.CENTER);
        body.setPadding(new Insets(10));
        root.setCenter(body);
        productPage=productList.getAllProducts();
//        root.setCenter(productPage);
        body.getChildren().add(productPage);
        root.setBottom(footerBar);
        return root;
    }

    public void createLoginPage()
    {
        Text userName=new Text("User Name");
        Text password=new Text("Password");

        TextField userNameField=new TextField("kumar76sumit@gmail.com");
        userNameField.setPromptText("Enter Your UserName");
        PasswordField passwordField=new PasswordField();
        passwordField.setText("Kumar76sum@");
        passwordField.setPromptText("Enter Your Password");
        Label messageLabel=new Label("Hi");

        Button loginButton=new Button("Login");
        welcomeLabel=new Label();

        loginPage=new GridPane();
        loginPage.setAlignment(Pos.CENTER);
        loginPage.setVgap(10);
        loginPage.setHgap(10);
        loginPage.add(userName,0,0);
        loginPage.add(userNameField,1,0);
        loginPage.add(password,0,1);
        loginPage.add(passwordField,1,1);
        loginPage.add(loginButton,1,2);
        loginPage.add(messageLabel,0,2);

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String name=userNameField.getText();
                String pass=passwordField.getText();
                Login login=new Login();
                loggedInCustomer=login.customerLogin(name,pass);
                if(loggedInCustomer!=null)
                {
                    messageLabel.setText("Welcome : " + loggedInCustomer.getName());
                    welcomeLabel.setText("Welcome " + loggedInCustomer.getName());
                    headerBar.getChildren().add(welcomeLabel);
                    body.getChildren().clear();
                    body.getChildren().add(productPage);
                }
                else {
                    messageLabel.setText("Please provide correct credentials");
                }
            }
        });
    }

    public void createHeader()
    {
        Button homeButton=new Button();
        Image image=new Image("C:\\Users\\Sumit\\IdeaProjects\\E-Commerce\\src\\main\\ECom.jpg");
        ImageView imageView=new ImageView();
        imageView.setImage(image);
        imageView.setFitHeight(30);
        imageView.setFitWidth(90);
        homeButton.setGraphic(imageView);

        TextField searchBar=new TextField();
        Button searchButton=new Button("Search");
        Button cartButton=new Button("Cart");
        Button orderButton=new Button("Orders");

        signInButton=new Button("SignIn");
        headerBar=new HBox();
        headerBar.setSpacing(10);
        headerBar.setPadding(new Insets(10));
        headerBar.setAlignment(Pos.CENTER);
        headerBar.setStyle("-fx-background-color: gray");
        headerBar.getChildren().addAll(homeButton,searchBar,searchButton,signInButton,cartButton,orderButton);

        signInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                body.getChildren().add(loginPage);
                headerBar.getChildren().remove(signInButton);
            }
        });

        cartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                VBox prodPage=productList.getProductInCart(itemsInCart);
                prodPage.setAlignment(Pos.CENTER);
                prodPage.setSpacing(10);
                prodPage.getChildren().add(placeOrderButton);
                body.getChildren().add(prodPage);
                footerBar.setVisible(false);
            }
        });

        placeOrderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product=productList.getSelectedProduct();
                if(itemsInCart==null)
                {
                    showDialog("Please Add Items in Cart");
                    return;
                }

                if(loggedInCustomer==null)
                {
                    showDialog("Please Login First!");
                    return;
                }

                int count=Order.placeMultipleOrder(loggedInCustomer,itemsInCart);
                if(count!=0)
                {
                    showDialog(""+count+" Order Placed Successfully!");
                }
                else {
                    showDialog("Order Failed!");
                }
            }
        });

        homeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                body.getChildren().add(productPage);
                footerBar.setVisible(true);
                if(loggedInCustomer==null && headerBar.getChildren().indexOf(signInButton)==-1)
                {
                    headerBar.getChildren().add(signInButton);
                }
            }
        });
    }

    public void createFooter()
    {
        Button buyNowButton=new Button("Buy Now");
        Button addToCartButton=new Button("Add to Cart");

        footerBar=new HBox();
        footerBar.setPadding(new Insets(10));
        footerBar.setSpacing(10);
        footerBar.setAlignment(Pos.CENTER);
        footerBar.getChildren().addAll(buyNowButton,addToCartButton);

        buyNowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product=productList.getSelectedProduct();
                if(product==null)
                {
                    showDialog("Please Select Any Item!");
                    return;
                }

                if(loggedInCustomer==null)
                {
                    showDialog("Please Login First!");
                    return;
                }

                boolean status=Order.placeOrder(loggedInCustomer,product);
                if(status)
                {
                    showDialog("Order Placed Successfully!");
                }
                else {
                    showDialog("Order Failed!");
                }
            }
        });

        addToCartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product=productList.getSelectedProduct();
                if(product==null)
                {
                    showDialog("Please Select Any Item!");
                    return;
                }
                itemsInCart.add(product);
                showDialog("Added to Cart");
            }
        });
    }
    private void showDialog(String message)
    {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mesage");
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
