package com.example.ecommerce;

import javafx.application.Platform;
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
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.sql.ResultSet;

public class UserInterface {
    GridPane loginPage,signUpPage,adminLoginPage,addProductPage;
    HBox headerBar;
    HBox footerBar;
    Button signInButton,registerButton;
    Label welcomeLabel;
    Customer loggedInCustomer;
    Admin loggedInAdmin;
    ProductList productList=new ProductList();
    VBox productPage;
    VBox body;
    boolean loginSuccess=false,adminLoginSuccess,isFilled,diffPassword;

    Button cartButton,orderButton,buyNowButton,addToCartButton,placeOrderButton,backButton,logout,signUpButton,adminSignInButton,adminLoginButton,addProductButton,addInDbButton,removeProdInDbButton;

    ObservableList<Product> itemsInCart=FXCollections.observableArrayList();
    String name,email,phone,signUpPassword,conPassword,address;

    UserInterface()
    {
        createLoginPage();
        createSignUpPage();
        createHeader();
        createFooter();
        createAdminPage();
    }

    BorderPane createContent()
    {
        BorderPane root=new BorderPane();
        root.setPrefSize(1555,800);
        root.setTop(headerBar);
//        root.setCenter(loginPage);
        body=new VBox();
        Image bodyBg=new Image("C:\\Users\\Sumit\\IdeaProjects\\E-Commerce\\src\\main\\3d-illustration-smartphone-with-products-coming-out-screen-online-shopping-e-commerce-concept.jpg");
        BackgroundImage bodyImage=new BackgroundImage(bodyBg,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        body.setBackground(new Background(bodyImage));
        body.setAlignment(Pos.CENTER);
        body.setPadding(new Insets(10));
        root.setCenter(body);
        productPage=productList.getAllProducts();
//        productPage.setSpacing(10);
//        root.setCenter(productPage);
//        productPage.setPadding(new Insets(50));
        body.getChildren().add(productPage);
        root.setBottom(footerBar);

        return root;
    }

    public void createLoginPage()
    {
        Text userName=new Text("User Name");
        userName.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 15));
        userName.setFill(Color.RED); // setting color of the text to blue
        userName.setStroke(Color.BLACK); // setting the stroke for the text
        userName.setStrokeWidth(0.5); // setting stroke width to 2

        Text password=new Text("Password");
        password.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 15));
        password.setFill(Color.RED); // setting color of the text to blue
        password.setStroke(Color.BLACK); // setting the stroke for the text
        password.setStrokeWidth(0.5); // setting stroke width to 2

        TextField userNameField=new TextField("kumar76sumit@gmail.com");
        userNameField.setPromptText("Enter Your UserName");
        PasswordField passwordField=new PasswordField();
        passwordField.setText("Kumar76sum@");
        passwordField.setPromptText("Enter Your Password");

        Button loginButton=new Button("Login");
        loginButton.setStyle("-fx-background-color: #0AFFFF");
        loginButton.setTextFill(Color.rgb(0,0,0));
        loginButton.setTranslateX(8);
        loginButton.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 15));

        backButton=new Button("  <  ");
        backButton.setStyle("-fx-background-color: #0AFFFF");
        backButton.setTextFill(Color.rgb(0,0,0));
        backButton.setTranslateX(-74);
        backButton.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 15));

        logout=new Button("Logout");
        logout.setStyle("-fx-background-color: green");
        logout.setTextFill(Color.rgb(0,0,0));
        logout.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 15));
        logout.setVisible(false);

        signUpButton=new Button("New to XYZ? Create an account");
        signUpButton.setStyle("-fx-background-color: #CCCCFF");
        signUpButton.setTextFill(Color.RED);
        signUpButton.setFont(Font.font("Verdana", FontWeight.NORMAL, FontPosture.REGULAR, 12));

        welcomeLabel=new Label();
        registerButton=new Button("Register");
        registerButton.setStyle("-fx-background-color: #0AFFFF");
        registerButton.setTextFill(Color.rgb(0,0,0));
        registerButton.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 15));

        loginPage=new GridPane();
        loginPage.setPrefHeight(500);
        loginPage.setMaxWidth(450);
        loginPage.setBorder(new Border(new BorderStroke(Color.WHITE,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,new BorderWidths(1,1,0,1))));
        loginPage.setAlignment(Pos.CENTER);
        loginPage.setVgap(10);
        loginPage.setHgap(10);
        loginPage.add(userName,0,1);
        loginPage.add(userNameField,1,1);
        loginPage.add(password,0,2);
        loginPage.add(passwordField,1,2);
        loginPage.add(loginButton,1,3);
        loginPage.add(backButton,2,3);

        Image loginBg=new Image("C:\\Users\\Sumit\\IdeaProjects\\E-Commerce\\src\\main\\img_8.png");
        BackgroundImage bgImage=new BackgroundImage(loginBg,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        loginPage.setBackground(new Background(bgImage));

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String name=userNameField.getText();
                String pass=passwordField.getText();
                Login login=new Login();
                loggedInCustomer=login.customerLogin(name,pass);
                if(loggedInCustomer!=null)
                {
                    welcomeLabel.setText("Welcome " + loggedInCustomer.getName());
                    welcomeLabel.setTranslateX(180);
                    welcomeLabel.setTextFill(Color.WHITE);
                    welcomeLabel.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 15));
                    loginSuccess=true;
                    headerBar.getChildren().removeAll(signInButton, adminSignInButton);
                    cartButton.setVisible(true);
                    orderButton.setVisible(true);
                    buyNowButton.setVisible(true);
                    addToCartButton.setVisible(true);
                    headerBar.getChildren().add(welcomeLabel);
                    body.getChildren().clear();
                    body.getChildren().addAll(productPage);
                    logout.setVisible(true);
                }
                else {
                    showDialog("Please provide correct credentials!");
                }
            }
        });

        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                body.getChildren().add(productPage);
            }
        });

        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                restartApplication();
            }
        });
    }
    public void createSignUpPage()
    {
        signUpPage=new GridPane();
        signUpPage.setPrefHeight(500);
        signUpPage.setMaxWidth(450);
        signUpPage.setAlignment(Pos.CENTER);
        signUpPage.setVgap(10);
        signUpPage.setHgap(10);
        signUpPage.setBorder(new Border(new BorderStroke(Color.WHITE,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,new BorderWidths(2,2,0,2))));
        Image signUpImg=new Image("C:\\Users\\Sumit\\IdeaProjects\\E-Commerce\\src\\main\\img_9.png");
        BackgroundImage signUpBg=new BackgroundImage(signUpImg,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        signUpPage.setBackground(new Background(signUpBg));

        Text addName=new Text("Name");
        addName.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 15));
        addName.setFill(Color.SKYBLUE); // setting color of the text to blue
        addName.setStroke(Color.BLACK); // setting the stroke for the text
        addName.setStrokeWidth(0.5); // setting stroke width to 2

        Text addEmail=new Text("Email");
        addEmail.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 15));
        addEmail.setFill(Color.SKYBLUE); // setting color of the text to blue
        addEmail.setStroke(Color.BLACK); // setting the stroke for the text
        addEmail.setStrokeWidth(0.5); // setting stroke width to 2

        Text addPhone=new Text("Phone");
        addPhone.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 15));
        addPhone.setFill(Color.SKYBLUE); // setting color of the text to blue
        addPhone.setStroke(Color.BLACK); // setting the stroke for the text
        addPhone.setStrokeWidth(0.5); // setting stroke width to 2

        Text addPassword=new Text("Password");
        addPassword.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 15));
        addPassword.setFill(Color.SKYBLUE); // setting color of the text to blue
        addPassword.setStroke(Color.BLACK); // setting the stroke for the text
        addPassword.setStrokeWidth(0.5); // setting stroke width to 2

        Text confirmPassword=new Text("Confirm Password");
        confirmPassword.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 15));
        confirmPassword.setFill(Color.SKYBLUE); // setting color of the text to blue
        confirmPassword.setStroke(Color.BLACK); // setting the stroke for the text
        confirmPassword.setStrokeWidth(0.5); // setting stroke width to 2

        Text addAddress=new Text("Address");
        addAddress.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 15));
        addAddress.setFill(Color.SKYBLUE); // setting color of the text to blue
        addAddress.setStroke(Color.BLACK); // setting the stroke for the text
        addAddress.setStrokeWidth(0.5); // setting stroke width to 2

        TextField nameField=new TextField("abc");
        nameField.setPromptText("Enter your Name");
        TextField emailField=new TextField();
        emailField.setPromptText("Enter your Email");
        TextField phoneField=new TextField("7903035412");
        phoneField.setPromptText("Enter your Phone");
        PasswordField passField=new PasswordField();
        passField.setText("111");
        passField.setPromptText("Create a new Password");
        PasswordField conPassField=new PasswordField();
        conPassField.setText("111");
        conPassField.setPromptText("Confirm your Password");
        TextField addField=new TextField("xyz");
        addField.setPromptText("Enter your Address");

        signUpPage.add(addName,0,0);
        signUpPage.add(nameField,1,0);
        signUpPage.add(addEmail,0,1);
        signUpPage.add(emailField,1,1);
        signUpPage.add(addPhone,0,2);
        signUpPage.add(phoneField,1,2);
        signUpPage.add(addPassword,0,3);
        signUpPage.add(passField,1,3);
        signUpPage.add(confirmPassword,0,4);
        signUpPage.add(conPassField,1,4);
        signUpPage.add(addAddress,0,5);
        signUpPage.add(addField,1,5);
        signUpPage.add(registerButton,1,6);

        registerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                name=nameField.getText();
                email=emailField.getText();
                phone=phoneField.getText();
                signUpPassword=passField.getText();
                conPassword=conPassField.getText();
                address=addField.getText();

                if(signUpPassword!="" && conPassword!="")
                {
                    if(!signUpPassword.equals(conPassword))
                    {
                        diffPassword=true;
                    }
                }
                if(diffPassword)
                {
                    showDialog("Password doesn't match!");
                    return;
                }
                if(!name.equals("") && !email.equals("") && !phone.equals("") && !signUpPassword.equals("") && !address.equals(""))
                {
                    isFilled =true;
                }
                if(isFilled==false)
                {
                    showDialog("Fill all the details!");
                    return;
                }
                if(isFilled && !diffPassword)
                {
                    String query="select * from customer where Email='"+email+"'";
                    DbConnection dbConnection=new DbConnection();
                    try
                    {
                        ResultSet rs=dbConnection.getQueryTable(query);
                        if(rs.next())
                        {
//                            if(rs.getString("Email").equals(email))
                            showDialog("Email is already Registered!");
                            return;
                        }
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }

                    query="select * from customer where Mobile='"+phone+"'";
                    try
                    {
                        ResultSet rs=dbConnection.getQueryTable(query);
                        if(rs.next())
                        {
//                            if(rs.getString("Mobile").equals(phone))
                            showDialog("Phone is already Registered!");
                            return;
                        }
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                    query="insert into customer(Name,Email,Mobile,Password,Address) values('"+name+"','"+email+"','"+phone+"','"+signUpPassword+"','"+address+"')";
                    dbConnection.updateDatabase(query);
                    body.getChildren().clear();
                    welcomeLabel.setText("Welcome " + name);
                    welcomeLabel.setTranslateX(180);
                    welcomeLabel.setTextFill(Color.WHITE);
                    welcomeLabel.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 15));
                    loginSuccess=true;
                    headerBar.getChildren().remove(signInButton);
                    cartButton.setVisible(true);
                    orderButton.setVisible(true);
                    buyNowButton.setVisible(true);
                    addToCartButton.setVisible(true);
                    headerBar.getChildren().add(welcomeLabel);
                    body.getChildren().clear();
                    body.getChildren().addAll(productPage);
                    logout.setVisible(true);
                }
            }
        });
    }

    public void createAdminPage()
    {
        adminLoginPage=new GridPane();
        adminLoginPage.setPrefHeight(500);
        adminLoginPage.setMaxWidth(450);
        Image adminPageImg=new Image("C:\\Users\\Sumit\\IdeaProjects\\E-Commerce\\src\\main\\img_4.png");
        BackgroundImage adminBg=new BackgroundImage(adminPageImg,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
        adminLoginPage.setBorder(new Border(new BorderStroke(Color.GRAY,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,new BorderWidths(1,1,1,1))));
        adminLoginPage.setBackground(new Background(adminBg));
        adminLoginButton=new Button("Login");
        adminLoginButton.setStyle("-fx-background-color: #0AFFFF");
        adminLoginButton.setTextFill(Color.rgb(0,0,0));
        adminLoginButton.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 15));

        Text adminUsername=new Text("Admin Username");
        adminUsername.setFont(Font.font("Verdana",FontWeight.BOLD,FontPosture.ITALIC,15));
        adminUsername.setFill(Color.RED);
        adminUsername.setStroke(Color.BLACK);
        adminUsername.setStrokeWidth(0.5);

        Text adminPassword=new Text("Admin Password");
        adminPassword.setFont(Font.font("Verdana",FontWeight.BOLD,FontPosture.ITALIC,15));
        adminPassword.setFill(Color.RED);
        adminPassword.setStroke(Color.BLACK);
        adminPassword.setStrokeWidth(0.5);

        TextField adminUserNameTextField=new TextField("kumar76sumit@gmail.com");
        adminUserNameTextField.setPromptText("Enter your Username");
        PasswordField adminPasswordPassField=new PasswordField();
        adminPasswordPassField.setText("Kumar76sum@");
        adminPasswordPassField.setPromptText("Enter your Password");

        adminLoginPage.setAlignment(Pos.CENTER);
        adminLoginPage.setHgap(10);
        adminLoginPage.setVgap(10);
        adminLoginPage.add(adminUsername,0,1);
        adminLoginPage.add(adminUserNameTextField,1,1);
        adminLoginPage.add(adminPassword,0,2);
        adminLoginPage.add(adminPasswordPassField,1,2);
        adminLoginPage.add(adminLoginButton,1,3);

        addProductPage=new GridPane();
        Image addProdImg=new Image("C:\\Users\\Sumit\\IdeaProjects\\E-Commerce\\src\\main\\img_6.png");
        BackgroundImage addProdBg=new BackgroundImage(addProdImg,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
        addProductPage.setBackground(new Background(addProdBg));

        addInDbButton=new Button("ADD");
        addInDbButton.setStyle("-fx-background-color: #0AFFFF");
        addInDbButton.setTextFill(Color.rgb(0,0,0));
        addInDbButton.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 15));

        removeProdInDbButton=new Button("REMOVE");
        removeProdInDbButton.setStyle("-fx-background-color: #0AFFFF");
        removeProdInDbButton.setTextFill(Color.rgb(0,0,0));
        removeProdInDbButton.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 15));

        addProductPage.setPrefHeight(350);
        addProductPage.setMaxWidth(625);
        addProductPage.setVgap(10);
        addProductPage.setHgap(10);
        addProductPage.setAlignment(Pos.CENTER);

        Text productName=new Text("Item Name");
        productName.setFont(Font.font("Verdana",FontWeight.BOLD,FontPosture.ITALIC,15));
        productName.setFill(Color.LIGHTGREEN);
        productName.setStroke(Color.DARKGREEN);
        productName.setStrokeWidth(0.7);

        Text productPrice=new Text("MRP");
        productPrice.setFont(Font.font("Verdana",FontWeight.BOLD,FontPosture.ITALIC,15));
        productPrice.setFill(Color.LIGHTGREEN);
        productPrice.setStroke(Color.DARKGREEN);
        productPrice.setStrokeWidth(0.7);

        Text productQuantity=new Text("Quantity");
        productQuantity.setFont(Font.font("Verdana",FontWeight.BOLD,FontPosture.ITALIC,15));
        productQuantity.setFill(Color.LIGHTGREEN);
        productQuantity.setStroke(Color.DARKGREEN);
        productQuantity.setStrokeWidth(0.7);

        TextField prodName=new TextField();
        prodName.setPromptText("Enter Item Name");
        TextField prodPrice=new TextField();
        prodPrice.setPromptText("Enter MRP");
        TextField prodQuantity=new TextField();
        prodQuantity.setPromptText("Enter Item Quantity");

        addProductPage.add(productName,0,0);
        addProductPage.add(productPrice,1,0);
        addProductPage.add(productQuantity,2,0);
        addProductPage.add(prodName,0,1);
        addProductPage.add(prodPrice,1,1);
        addProductPage.add(prodQuantity,2,1);
        addProductPage.add(addInDbButton,3,1);

        Text productId=new Text("Item Id");
        productId.setFont(Font.font("Verdana",FontWeight.BOLD,FontPosture.ITALIC,15));
        productId.setFill(Color.LIGHTGREEN);
        productId.setStroke(Color.DARKGREEN);
        productId.setStrokeWidth(0.7);

        TextField prodId=new TextField();
        prodId.setPromptText("Enter ItemId");

        addProductPage.add(productId,0,8);
        addProductPage.add(prodId,0,9);
        addProductPage.add(removeProdInDbButton,1,9);

        adminLoginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String adminUserName=adminUserNameTextField.getText();
                String adminPass=adminPasswordPassField.getText();
                AdminLogin adminlogin=new AdminLogin();
                loggedInAdmin=adminlogin.adminLogin(adminUserName,adminPass);
                if(loggedInAdmin!=null)
                {
                    welcomeLabel.setText("Welcome " + loggedInAdmin.getName());
                    welcomeLabel.setTranslateX(180);
                    welcomeLabel.setTextFill(Color.RED);
                    welcomeLabel.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 15));
                    adminLoginSuccess=true;
                    headerBar.getChildren().removeAll(signInButton,adminSignInButton);
                    headerBar.getChildren().add(welcomeLabel);
                    body.getChildren().clear();
                    body.getChildren().addAll(productPage);
                    logout.setTranslateX(-100);
                    logout.setVisible(true);
                    addProductButton.setVisible(true);
                }
                else {
                    showDialog("Please provide correct credentials!");
                }
            }
        });

        addProductButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                body.getChildren().add(addProductPage);
                addProductButton.setVisible(false);
            }
        });

        addInDbButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String prodNameToAdd=prodName.getText();
                String prodPriceToAdd=prodPrice.getText();
                String prodQtyToAdd=prodQuantity.getText();

                if(!prodNameToAdd.equals("") && !prodPriceToAdd.equals("") && !prodQtyToAdd.equals(""))
                {
                    String query="Insert into product(Name,Price,Quantity) values('"+prodNameToAdd+"','"+prodPriceToAdd+"','"+prodQtyToAdd+"')";
                    DbConnection dbConnection=new DbConnection();
                    dbConnection.updateDatabase(query);
                    showDialog("Added Successfully!");
                    prodName.clear();
                    prodPrice.clear();
                    prodQuantity.clear();
                }
                else
                {
                    showDialog("Fill all the Fields!");
                }
            }
        });

        removeProdInDbButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String prodIdToRemove=prodId.getText();
                boolean isProdAvailable=false;
                if(!prodIdToRemove.equals(""))
                {
                    String query="select * from product where id='"+prodIdToRemove+"'";
                    DbConnection dbConnection=new DbConnection();
                    try
                    {
                        ResultSet rs=dbConnection.getQueryTable(query);
                        if(rs.next())
                        {
                            isProdAvailable=true;
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    if(isProdAvailable)
                    {
                        query="delete from product where id='"+prodIdToRemove+"'";
                        dbConnection.updateDatabase(query);
                        showDialog("Removed Successfully!");
                        prodId.clear();
                    }
                    else {
                        showDialog("Item not Available!");
                        prodId.clear();
                    }
                }
                else {
                    showDialog("Fill all the Fields!");
                }
            }
        });
    }
    public void createHeader()
    {
        Button homeButton=new Button();
        homeButton.setStyle("-fx-background-color: #6a2b84");
        Image image=new Image("C:\\Users\\Sumit\\IdeaProjects\\E-Commerce\\src\\main\\img_10.png");
        ImageView imageView=new ImageView();
        imageView.setPreserveRatio(true);
        imageView.setImage(image);
        imageView.setFitHeight(30);
        imageView.setFitWidth(90);
        homeButton.setGraphic(imageView);

        TextField searchBar=new TextField();
        searchBar.setPromptText("Search for products,brands and more");
        searchBar.setPrefSize(500,35);
        Image searchImage=new Image("C:\\Users\\Sumit\\IdeaProjects\\E-Commerce\\src\\main\\201-2016989_search-icon-blue-circle.png");
        ImageView searchImg=new ImageView();
        Button searchButton=new Button();
        searchButton.setTranslateX(-2);
        searchButton.setTranslateY(-1);
        searchImg.setImage(searchImage);
        searchImg.setFitHeight(26);
        searchImg.setFitWidth(30);
        searchButton.setGraphic(searchImg);

        cartButton=new Button("Cart");
        cartButton.setStyle("-fx-background-color: #FFFF00");
        cartButton.setVisible(false);

        orderButton=new Button("Orders");
        orderButton.setStyle("-fx-background-color: #FFFF00");
        orderButton.setVisible(false);

        placeOrderButton=new Button("Place Order");
        placeOrderButton.setStyle("-fx-background-color: pink");
        placeOrderButton.setTextFill(Color.rgb(0,0,0));
        placeOrderButton.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 15));

        signInButton=new Button("SignIn");
        signInButton.setStyle("-fx-background-color: #FFFF00");
        signInButton.setPrefSize(125,25);
        signInButton.setTextFill(Color.rgb(0,0,0));
        signInButton.setTranslateX(30);
        signInButton.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 15));

        cartButton.setPrefSize(125,25);
        cartButton.setTextFill(Color.rgb(0,0,0));
        cartButton.setTranslateX(50);
        cartButton.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 15));

        orderButton.setPrefSize(125,25);
        orderButton.setTextFill(Color.rgb(0,0,0));
        orderButton.setTranslateX(70);
        orderButton.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 15));

        adminSignInButton =new Button("Admin");
        adminSignInButton.setStyle("-fx-background-color: azure");
        adminSignInButton.setPrefSize(125,25);
        adminSignInButton.setTextFill(Color.rgb(0,0,0));
        adminSignInButton.setTranslateX(-195);
        adminSignInButton.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 15));

        addProductButton=new Button("Add/Remove");
        addProductButton.setStyle("-fx-background-color: yellow");
        addProductButton.setPrefSize(125,25);
        addProductButton.setTextFill(Color.rgb(0,0,0));
        addProductButton.setTranslateX(-230);
        addProductButton.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 15));
        addProductButton.setVisible(false);


        headerBar=new HBox();
        headerBar.setPadding(new Insets(10));
        headerBar.setAlignment(Pos.CENTER);
//        headerBar.setStyle("-fx-background-color:Brandeis Blue");
        headerBar.setBackground(Background.fill(Color.rgb(106, 43, 132)));
        headerBar.getChildren().addAll(homeButton,searchBar,searchButton,signInButton,cartButton,orderButton,adminSignInButton,addProductButton);

        signInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                backButton.setAlignment(Pos.TOP_LEFT);
                body.getChildren().addAll(loginPage,signUpButton);
            }
        });

        adminSignInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                body.getChildren().add(adminLoginPage);
            }
        });

        signUpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                body.getChildren().add(signUpPage);
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
                if(adminLoginSuccess)
                {
                    addProductButton.setVisible(true);
                }
                if(loggedInCustomer==null && headerBar.getChildren().indexOf(signInButton)==-1 && loginSuccess==false && adminLoginSuccess==false)
                {
                    headerBar.getChildren().add(signInButton);
                }
            }
        });
    }

    public void createFooter()
    {
        buyNowButton=new Button("Buy Now");
        buyNowButton.setStyle("-fx-background-color: red");
        buyNowButton.setTextFill(Color.rgb(0,0,0));
        buyNowButton.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 15));
        buyNowButton.setVisible(false);

        addToCartButton=new Button("Add to Cart");
        addToCartButton.setStyle("-fx-background-color: #FF6700");
        addToCartButton.setTextFill(Color.rgb(0,0,0));
        addToCartButton.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 15));
        addToCartButton.setVisible(false);

        footerBar=new HBox();
        footerBar.setPadding(new Insets(10));
        footerBar.setSpacing(10);
        footerBar.setAlignment(Pos.CENTER);
        footerBar.setBackground(Background.fill(Color.rgb(106, 43, 132)));
        footerBar.getChildren().addAll(buyNowButton,addToCartButton,logout);

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
                showDialog("Added to Cart!");
            }
        });
    }

    public void restartApplication()
    {
        try {
            // Restart the application
            logout.getScene().getWindow().hide();

            Platform.runLater(() -> {
                Ecommerce otherClass = new Ecommerce();
                Stage newPrimaryStage = new Stage();
                try {
                    otherClass.start(newPrimaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void showDialog(String message)
    {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        Image image=new Image("C:\\Users\\Sumit\\IdeaProjects\\E-Commerce\\src\\main\\img_10.png");
        ImageView imageView=new ImageView();
        imageView.setPreserveRatio(true);
        imageView.setImage(image);
        imageView.setFitHeight(30);
        imageView.setFitWidth(90);
        alert.setGraphic(imageView);
        alert.setTitle("Mesage");
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
