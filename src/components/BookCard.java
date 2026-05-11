// package components;

// import services.WishlistService;
// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Cursor;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.*;
// import models.Book;
// import javafx.stage.Stage;
// import pages.BookDetailsPage;

// public class BookCard {

//     public static VBox create(Book book, Stage stage) {

//         VBox card = new VBox();

//         card.setPrefWidth(220);
//         card.setPrefHeight(360);

//         card.setCursor(Cursor.HAND);

//         card.setStyle(
//                 "-fx-background-color: #1A1A2E;" +
//                         "-fx-background-radius: 20;");

//         // =================================================
//         // IMAGE SECTION
//         // =================================================

//         StackPane imageSection = new StackPane();

//         imageSection.setPrefHeight(240);

//         imageSection.setStyle(
//                 "-fx-background-color: #262640;" +
//                         "-fx-background-radius: 20 20 0 0;");

//         ImageView imageView = new ImageView();

//         try {

//             Image image = new Image(
//                     book.getImage(),
//                     true);

//             imageView.setImage(image);

//         } catch (Exception e) {

//             System.out.println("Image failed");
//         }

//         imageView.setFitWidth(170);
//         imageView.setFitHeight(210);

//         imageView.setPreserveRatio(true);

//         imageSection.getChildren().add(imageView);

//         // =================================================
//         // DETAILS
//         // =================================================

//         VBox details = new VBox(8);

//         details.setPadding(new Insets(16));

//         Label title = new Label(
//                 book.getTitle());

//         title.setStyle(
//                 "-fx-text-fill: white;" +
//                         "-fx-font-size: 18px;" +
//                         "-fx-font-weight: bold;");

//         Label author = new Label(
//                 book.getAuthor());

//         author.setStyle(
//                 "-fx-text-fill: #9CA3AF;" +
//                         "-fx-font-size: 13px;");

//         // =================================================
//         // BOTTOM ROW
//         // =================================================

//         HBox bottom = new HBox();

//         bottom.setAlignment(Pos.CENTER_LEFT);

//         Label price = new Label(
//                 book.getPrice());

//         price.setStyle(
//                 "-fx-text-fill: #10B981;" +
//                         "-fx-font-size: 20px;" +
//                         "-fx-font-weight: bold;");

//         Button wishlistBtn = new Button("❤");
//         wishlistBtn.setOnAction(e -> {

//             WishlistService.addBook(book);

//             System.out.println(
//                     book.getTitle() +
//                             " added to wishlist");
//         });

//         wishlistBtn.setCursor(Cursor.HAND);

//         wishlistBtn.setStyle(
//                 "-fx-background-color: transparent;" +
//                         "-fx-text-fill: #EF4444;" +
//                         "-fx-font-size: 18px;");

//         Region spacer = new Region();

//         HBox.setHgrow(
//                 spacer,
//                 Priority.ALWAYS);

//         bottom.getChildren().addAll(
//                 price,
//                 spacer,
//                 wishlistBtn);

//         details.getChildren().addAll(
//                 title,
//                 author,
//                 bottom);

//         // =================================================
//         // HOVER EFFECT
//         // =================================================

//         card.setOnMouseEntered(e -> {

//             card.setScaleX(1.03);
//             card.setScaleY(1.03);

//             card.setStyle(
//                     "-fx-background-color: #24243A;" +
//                             "-fx-background-radius: 20;");
//         });

//         card.setOnMouseExited(e -> {

//             card.setScaleX(1);
//             card.setScaleY(1);

//             card.setStyle(
//                     "-fx-background-color: #1A1A2E;" +
//                             "-fx-background-radius: 20;");
//         });

//         // =================================================
//         // FINAL ADD
//         // =================================================

//         card.getChildren().addAll(
//                 imageSection,
//                 details);

//         card.setOnMouseClicked(e -> {

//             BookDetailsPage.show(
//                     stage,
//                     book);
//         });
        
//         return card;
//     }
// }