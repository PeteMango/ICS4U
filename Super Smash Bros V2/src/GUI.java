import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.ArrayList;


public class GUI extends Application{

    private int WINDOW_HEIGHT = 800;
    private int WINDOW_WIDTH = 1400;
    private int NUM_OF_MAPS = 4;
    private int NUM_OF_OPTIONS = 3; // play, instructions, change controls
    private int NUM_OF_CHARACTERS = 3;

    private BackgroundImage main_background = new BackgroundImage(new Image("file:images/main_background.jpg", WINDOW_WIDTH + 20, WINDOW_HEIGHT + 20, false, true),
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

    private BackgroundImage logo = new BackgroundImage(new Image("file:images/logo.png", 600, 400, true, true),
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

    private BackgroundImage mapImages[] = new BackgroundImage[NUM_OF_MAPS];
    private Label mapImageLabels[] = new Label[NUM_OF_MAPS];
    private GridPane[] maps = new GridPane[NUM_OF_MAPS];
    private Button [] mapButtons = new Button[NUM_OF_MAPS];
    private Button[] options = new Button[NUM_OF_OPTIONS];


    /**
     * 4 movements + 4 movements + 2 attack + 2 attack = 12 total keys to change
     *
     */
    private TextField controlKeyText[] = new TextField[12];
    private Label controlKeyLabel[] = new Label[12];
    private ArrayList<KeyCode> keyCodes;

    public GUI() {
        init();
    }
    public void init() {
        keyCodes = new ArrayList<>();
        mapImages[0] = new BackgroundImage(new Image("file:images/map1.jpg", 500, 280, true, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        mapImages[1] = new BackgroundImage(new Image("file:images/map2.jpg", 500, 280, true, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        mapImages[2] = new BackgroundImage(new Image("file:images/map3.png", 500, 280, true, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        mapImages[3] = new BackgroundImage(new Image("file:images/map4.jpg", 500, 280, true, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        mapImageLabels[0] = new Label("Map 1");
        mapImageLabels[1] = new Label("Map 2");
        mapImageLabels[2] = new Label("Map 3");
        mapImageLabels[3] = new Label("Map 4");
        for(int i=0; i<NUM_OF_MAPS; i++) {
            mapImageLabels[i].setFont(Font.loadFont("file:earth-orbiter/earthorbiter.ttf", 36));
            mapImageLabels[i].setStyle("-fx-text-fill: white;");
            mapButtons[i] = new Button();
            mapButtons[i].setPrefSize(500, 280);
            mapButtons[i].setBackground(new Background(mapImages[i]));
            maps[i] = new GridPane();
            maps[i].setVgap(20);
            maps[i].add(mapImageLabels[i], 0, 0);
            maps[i].add(mapButtons[i],0, 1);
        }
        for(int i=0; i<NUM_OF_OPTIONS; i++) {
            options[i] = new Button();
            options[i].setFont(Font.loadFont("file:earth-orbiter/earthorbiter.ttf", 24));
            options[i].setAlignment(Pos.CENTER);
            options[i].setPrefSize(300, 20);
        }
        keyCodes.add(KeyCode.W); keyCodes.add(KeyCode.A); keyCodes.add(KeyCode.S); keyCodes.add(KeyCode.D); keyCodes.add(KeyCode.C); keyCodes.add(KeyCode.V);
        keyCodes.add(KeyCode.UP); keyCodes.add(KeyCode.LEFT); keyCodes.add(KeyCode.DOWN); keyCodes.add(KeyCode.RIGHT); keyCodes.add(KeyCode.N); keyCodes.add(KeyCode.M);
    }
    public void start(Stage primaryStage) {
        createTitleScreen();
    }
    private void createMainScreen() {
        Stage gameScreen = new Stage();
        BorderPane mainScreenPane = new BorderPane();
        Scene mainScreenScene = new Scene(mainScreenPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        gameScreen.setTitle("Menu");

        VBox logoBox = new VBox();
        logoBox.setPrefSize(600, 400);
        logoBox.setBackground(new Background(logo));
        logoBox.setAlignment(Pos.CENTER);

        GridPane optionsPane = new GridPane();
        optionsPane.setVgap(50);
        optionsPane.setAlignment(Pos.CENTER);
        options[0].setText("Play");
        options[1].setText("Instructions");
        options[2].setText("Change Controls");
        for(int i=0; i<NUM_OF_OPTIONS; i++) {
            optionsPane.add(options[i], 0, i);
        }

        options[0].setOnMouseClicked(event -> {
           // createMapScreen();
            createInstructionsPage1(false);
            gameScreen.close();
        });

        options[1].setOnMouseClicked(event -> {
            createInstructionsPage1(true);
            gameScreen.close();
        });

        options[2].setOnMouseClicked(event -> {
            createControlChangeScreen();
            gameScreen.close();
        });
        mainScreenPane.setTop(logoBox);
        mainScreenPane.setCenter(optionsPane);
        gameScreen.setScene(mainScreenScene);
        mainScreenPane.setBackground(new Background(main_background));
        gameScreen.show();
    }
    private void createMapScreen() {
        Stage mapScreen = new Stage();
        BorderPane mainScreenPane = new BorderPane();
        Scene mainScreenScene = new Scene(mainScreenPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        mainScreenPane.setBackground(new Background(main_background));

        mapScreen.setResizable(false);
        mapScreen.setTitle("Choose A Map");

        Label message = new Label("Choose A Map To Continue");
        message.setFont(Font.loadFont("file:earth-orbiter/earthorbiter.ttf", 48));
        message.setStyle("-fx-text-fill: white;");
        message.setAlignment(Pos.CENTER);
        GridPane mapPane = new GridPane();
        mapPane.setVgap(25); mapPane.setHgap(50);
        mapPane.setAlignment(Pos.CENTER);
        mapPane.setPadding(new Insets(30));
        mapPane.add(message, 0, 0);
        mapPane.add(maps[0], 0, 1);
        mapPane.add(maps[1], 1, 1);
        mapPane.add(maps[2], 0, 2);
        mapPane.add(maps[3], 1, 2);

        mapButtons[0].setOnMouseClicked(event -> {
            createFightScreen(0);
            mapScreen.close();
        });
        mapButtons[1].setOnMouseClicked(event -> {
            createFightScreen(1);
            mapScreen.close();
        });
        mapButtons[2].setOnMouseClicked(event -> {
            createFightScreen(2);
            mapScreen.close();
        });
        mapButtons[3].setOnMouseClicked(event -> {
            createFightScreen(3);
            mapScreen.close();
        });
        mainScreenPane.setCenter(mapPane);
        mapScreen.setScene(mainScreenScene);
        mapScreen.show();
    }
    private void createInstructionsPage1(boolean fromMenu) { // finished
        Stage instructionStage = new Stage();
        instructionStage.setTitle("Instructions");
        instructionStage.setResizable(false);
        BorderPane instructionPane = new BorderPane();
        instructionPane.setBackground(new Background(main_background));
        Scene instructionScene = new Scene(instructionPane, WINDOW_WIDTH, WINDOW_HEIGHT);

        GridPane instructionContent = new GridPane();
        instructionContent.setPadding(new Insets(30));

        Label messages[] = new Label[10];
        for(int i=0; i<10; i++) {
            messages[i] = new Label();
            messages[i].setStyle("-fx-text-fill: white;");
        }

        messages[0].setText("INSTRUCTIONS");
        messages[0].setFont(Font.loadFont("file:earth-orbiter/earthorbiter.ttf", 72));
        messages[0].setAlignment(Pos.CENTER);
        messages[0].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        messages[1].setText("How To Play");
        messages[1].setFont(Font.loadFont("file:earth-orbiter/earthorbiter.ttf", 36));

        messages[2].setText("Blah Blah Blah");
        messages[2].setFont(Font.loadFont("file:earth-orbiter/earthorbiter.ttf", 24));

        messages[3].setText("Press Enter To Continue");
        messages[3].setFont(Font.loadFont("file:earth-orbiter/earthorbiter.ttf", 30));

        instructionContent.setVgap(20);
        instructionContent.add(messages[1], 0, 0);
        instructionContent.add(messages[2], 0, 1);

        instructionPane.setTop(messages[0]);
        instructionPane.setCenter(instructionContent);
        instructionPane.setBottom(messages[3]);

        instructionStage.setScene(instructionScene);
        instructionPane.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                createInstructionsPage2(fromMenu);
                instructionStage.close();
            }
        });
        instructionPane.requestFocus();
        instructionStage.show();
    } // TODO finish rule page
    private void createInstructionsPage2(boolean fromMenu) {
        Stage instructionStage = new Stage();
        instructionStage.setResizable(false);
        BorderPane instructionPane = new BorderPane();
        Scene instructionScene = new Scene(instructionPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        instructionPane.setBackground(new Background(main_background));
        Label messages[] = new Label[12];

        GridPane instructions = new GridPane();

        BackgroundImage keyBackground = new BackgroundImage(new Image("file:images/gray_box.jpg", 100, 100, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

        GridPane movementKeys1 = new GridPane();
        GridPane movementKeys2 = new GridPane();
        GridPane fightKeys1 = new GridPane();
        GridPane fightKeys2 = new GridPane();

        Button[] movementButtons = new Button[8];
        Button[] fightButtons = new Button[4];

        for(int i=0; i<8; i++) {
            movementButtons[i] = new Button();
            movementButtons[i].setBackground(new Background(keyBackground));
            //movementButtons[i].setStyle("-fx-background-color: black;");
            movementButtons[i].setStyle("-fx-border-color: black;" +
                    "-fx-border-radius: 5;");
            movementButtons[i].setPrefSize(100, 100);
            if(i < 4) {
                movementButtons[i].setFont(Font.loadFont("file:earth-orbiter/earthorbiter.ttf", 42));
            }
            else {
                if(i == 5 || i == 7) {  // we want the right and left arrows to be bigger;
                    movementButtons[i].setStyle("-fx-border-color: black;" +
                            "-fx-border-radius: 5;" +
                            "-fx-font-size: 48");
                }
                else {
                    movementButtons[i].setStyle("-fx-border-color: black;" +
                            "-fx-border-radius: 5;" +
                            "-fx-font-size: 42");
                }
            }
        }

        for(int i=0; i<12; i++) {
            messages[i] = new Label();
            messages[i].setStyle("-fx-text-fill: white;");
        }

        for(int i=0; i<4; i++) {
            fightButtons[i] = new Button();
            fightButtons[i].setBackground(new Background(keyBackground));
            fightButtons[i].setStyle("-fx-border-color: black;" +
                    "-fx-border-radius: 5;");
            fightButtons[i].setPrefSize(100, 100);
            fightButtons[i].setFont(Font.loadFont("file:earth-orbiter/earthorbiter.ttf", 42));
        }

        movementButtons[0].setText("W");
        movementButtons[1].setText("A");
        movementButtons[2].setText("S");
        movementButtons[3].setText("D");
        movementButtons[4].setText("\u2BC5");
        movementButtons[5].setText("\u25C0");
        movementButtons[6].setText("\u2BC6");
        movementButtons[7].setText("\u25B6");

        fightButtons[0].setText("C");
        fightButtons[1].setText("V");
        fightButtons[2].setText("N");
        fightButtons[3].setText("M");

        messages[0].setText("INSTRUCTIONS");
        messages[0].setFont(Font.loadFont("file:earth-orbiter/earthorbiter.ttf", 72));
        messages[0].setAlignment(Pos.CENTER);
        messages[0].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        messages[1].setText("Player One:");
        messages[1].setFont(Font.loadFont("file:earth-orbiter/earthorbiter.ttf", 36));

        messages[2].setText("Movement");
        messages[2].setFont(Font.loadFont("file:earth-orbiter/earthorbiter.ttf", 24));
        messages[2].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        messages[2].setAlignment(Pos.CENTER);

        messages[3].setText("Basic Attack");
        messages[3].setFont(Font.loadFont("file:earth-orbiter/earthorbiter.ttf", 24));

        messages[4].setText("Special Attack");
        messages[4].setFont(Font.loadFont("file:earth-orbiter/earthorbiter.ttf", 24));

        messages[5].setText("Player Two:");
        messages[5].setFont(Font.loadFont("file:earth-orbiter/earthorbiter.ttf", 36));

        messages[6].setText("Movement");
        messages[6].setFont(Font.loadFont("file:earth-orbiter/earthorbiter.ttf", 24));
        messages[6].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        messages[6].setAlignment(Pos.CENTER);

        messages[7].setText("Basic Attack");
        messages[7].setFont(Font.loadFont("file:earth-orbiter/earthorbiter.ttf", 24));

        messages[8].setText("Special Attack");
        messages[8].setFont(Font.loadFont("file:earth-orbiter/earthorbiter.ttf", 24));

        messages[9].setText("Controls");
        messages[9].setFont(Font.loadFont("file:earth-orbiter/earthorbiter.ttf", 36));

        messages[10].setText("Press Enter To Continue");
        messages[10].setFont(Font.loadFont("file:earth-orbiter/earthorbiter.ttf", 30));
        if(fromMenu) {
            messages[10].setText("Press Enter To Go Back To Menu");
        } // if the page was from the menu page


        movementKeys1.add(movementButtons[0], 1, 0);
        movementKeys1.add(movementButtons[1], 0, 1);
        movementKeys1.add(movementButtons[2], 1, 1);
        movementKeys1.add(movementButtons[3], 2, 1);
        movementKeys1.add(messages[2], 0, 3, 3, 1);

        movementKeys2.add(movementButtons[4], 1, 0);
        movementKeys2.add(movementButtons[5], 0, 1);
        movementKeys2.add(movementButtons[6], 1, 1);
        movementKeys2.add(movementButtons[7], 2, 1);
        movementKeys2.add(messages[6], 0, 3, 3, 1);

        fightKeys1.add(fightButtons[0], 0, 0);
        fightKeys1.add(fightButtons[1], 1, 0);
        fightKeys1.add(messages[3], 0, 1);
        fightKeys1.add(messages[4], 1, 1);

        fightKeys2.add(fightButtons[2], 0, 0);
        fightKeys2.add(fightButtons[3], 1, 0);
        fightKeys2.add(messages[7], 0, 1);
        fightKeys2.add(messages[8], 1, 1);

        movementKeys1.setVgap(10);
        movementKeys1.setPadding(new Insets(0,0,0,50));

        fightKeys1.setVgap(10); fightKeys1.setHgap(50);
        fightKeys1.setPadding(new Insets(110,0,0,0));

        movementKeys2.setVgap(10);
        movementKeys2.setPadding(new Insets(0, 0,0, 50));

        fightKeys2.setVgap(10); fightKeys2.setHgap(50);
        fightKeys2.setPadding(new Insets(110, 0, 0, 0));

        instructions.setVgap(10); instructions.setHgap(300);
        instructions.setPadding(new Insets(0,0,0, 50));

        instructions.add(messages[9], 0, 1); // CONTROLS
        instructions.add(messages[1], 0, 2); // PLAYER ONE
        instructions.add(movementKeys1, 0, 3);
        instructions.add(fightKeys1, 1, 3); // FIGHT
        instructions.add(messages[5], 0, 5); // PLAYER TWO
        instructions.add(movementKeys2, 0, 6);
        instructions.add(fightKeys2, 1, 6); // FIGHT

        instructionPane.setTop(messages[0]); // Title
        instructionPane.setCenter(instructions);
        instructionPane.setBottom(messages[10]); // prompt
        instructionStage.setScene(instructionScene);
        instructionPane.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                if(fromMenu) {
                    createMainScreen();
                }
                else {
                    createCharacterSelectScreen();
                }
                instructionStage.close();
            }
        });
        instructionStage.show();
    } // finished
    private void createTitleScreen() {
        Stage titleScreen = new Stage();
        titleScreen.setTitle("Super Smash Bros (Lakshy Edition)");
        titleScreen.setResizable(false);
        BackgroundImage image = new BackgroundImage(new Image("file:images/title.jpg", WINDOW_WIDTH - 180, WINDOW_HEIGHT + 20, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background background = new Background(image);
        Label message = new Label("Press Any Key To Start");
        message.setFont(Font.loadFont("file:earth-orbiter/earthorbiter.ttf", 48));
        message.setStyle("-fx-text-fill: white;");
        message.setAlignment(Pos.CENTER);
        message.setPadding(new Insets(600, 0, 0, 0));
        VBox vbox = new VBox(message);
        vbox.setAlignment(Pos.CENTER);
        vbox.setBackground(background);
        Scene titleScene = new Scene(vbox, WINDOW_WIDTH - 200, WINDOW_HEIGHT);
        titleScene.setOnMouseClicked(event -> {
            createMainScreen();
            titleScreen.close();
        });
        titleScene.setOnKeyPressed(event -> {
            createMainScreen();
            titleScreen.close();
        });
        titleScreen.setScene(titleScene);
        titleScreen.show();
    }
    private void createFightScreen(int mapNum) { // TODO wait for lakshy to merge code
    }
    private void createControlChangeScreen() {
        Stage controlChangeStage = new Stage();
        controlChangeStage.setResizable(false);
        controlChangeStage.setTitle("Change Controls");
        BorderPane controlChangePane = new BorderPane();

        Label title = new Label("Change Controls");
        title.setFont(Font.loadFont("file:earth-orbiter/earthorbiter.ttf", 72));
        title.setStyle("-fx-text-fill: white;");
        title.setAlignment(Pos.CENTER);
        title.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        Label playerOneHeader = new Label("Player One Controls:");
        playerOneHeader.setFont(Font.loadFont("file:earth-orbiter/earthorbiter.ttf", 36));
        playerOneHeader.setStyle("-fx-text-fill: white;");

        Label playerTwoHeader = new Label("Player Two Controls:");
        playerTwoHeader.setFont(Font.loadFont("file:earth-orbiter/earthorbiter.ttf", 36));
        playerTwoHeader.setStyle("-fx-text-fill: white;");

        BackgroundImage keyChangeBackground = new BackgroundImage(new Image("file:images/keychangebackground.png", WINDOW_WIDTH + 20, WINDOW_HEIGHT + 20, false, false),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        controlChangePane.setBackground(new Background(keyChangeBackground));
        Scene controlChangeScene = new Scene(controlChangePane, WINDOW_WIDTH, WINDOW_HEIGHT);

        GridPane controlKeyPane = new GridPane();
        controlKeyPane.setVgap(20); controlKeyPane.setHgap(100);
        controlKeyPane.setAlignment(Pos.CENTER);

        String actions[] = {"MOVE UP", "MOVE LEFT", "MOVE DOWN", "MOVE RIGHT", "BASIC ATK", "SPECIAL ATK"};
        String defaultKeyCodes[] = {"W", "A", "S", "D", "C", "V", "UP", "LEFT", "DOWN", "RIGHT", "N", "M"};

        Button changebtn = new Button("Go Back To Menu"); // TODO change text to GO BACK TO MENU after settings are changed
        changebtn.setFont(Font.loadFont("file:earth-orbiter/earthorbiter.ttf", 30));
        HBox changebtnBox = new HBox(changebtn); changebtnBox.setPadding(new Insets(20));

        for(int i=0; i<12; i++) {
            controlKeyText[i] = new TextField(defaultKeyCodes[i]);
            controlKeyText[i].setEditable(false);
            final int j=i;
            controlKeyText[i].setOnMouseClicked(event -> {
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                Label message = new Label("Please Enter A New Key For This Action");
                message.setAlignment(Pos.CENTER);
                Button cancel = new Button("Cancel");
                HBox hbox = new HBox(message); hbox.setSpacing(30);
                hbox.getChildren().add(cancel); hbox.setAlignment(Pos.CENTER);

                hbox.setOnKeyPressed(event1 -> {
                    if(keyCodes.contains(event1.getCode())) {
                        createPopUpWindow("Key Taken. Please Enter Another Key", "Attention");
                        return;
                    }
                    controlChangePane.requestFocus();
                    controlKeyText[j].setText(event1.getCode().toString());
                    keyCodes.set(j, event1.getCode());
                    stage.close();
                });
                cancel.setOnMouseClicked(event1 -> {
                    controlChangePane.requestFocus();
                    stage.close();
                });
                Scene scene = new Scene(hbox, 400, 100);
                hbox.requestFocus();
                stage.setScene(scene);
                stage.show();
            });
            controlKeyLabel[i] = new Label(actions[i%6]);
            controlKeyLabel[i].setPrefSize(200, 20);
            controlKeyLabel[i].setFont(Font.loadFont("file:earth-orbiter/earthorbiter.ttf", 24));
            controlKeyLabel[i].setStyle("-fx-text-fill: white;");
        }

        controlKeyPane.add(playerOneHeader, 0, 0);
        controlKeyPane.add(playerTwoHeader, 1, 0);

        for(int i=0; i<12; i++) {
            HBox hbox = new HBox(); hbox.setSpacing(30);
            hbox.getChildren().addAll(controlKeyLabel[i], controlKeyText[i]);
            if(i < 6) {
                controlKeyPane.add(hbox, 0, i%6+1);
            }
            else {
                controlKeyPane.add(hbox, 1, i%6+1);
            }
        }

        changebtn.setOnMouseClicked(event -> {
            createMainScreen();
            controlChangeStage.close();
        });

        controlChangePane.requestFocus();
        controlChangePane.setTop(title);
        controlChangePane.setCenter(controlKeyPane);
        controlChangePane.setBottom(changebtnBox);
        controlChangeStage.setScene(controlChangeScene);
        controlChangeStage.show();
    }
    private void createCharacterSelectScreen() {
        Stage characterSelectStage = new Stage();
        characterSelectStage.setResizable(false);
        BorderPane characterSelectPane = new BorderPane();
        characterSelectPane.setBackground(new Background(main_background));
        Scene characterSelectScene = new Scene(characterSelectPane, WINDOW_WIDTH, WINDOW_HEIGHT);

        GridPane characterPane = new GridPane(); characterPane.setHgap(20);
        characterPane.setPadding(new Insets(30));

        GridPane characterChoiceBoxes = new GridPane();
        characterChoiceBoxes.setHgap(30); characterChoiceBoxes.setVgap(50);
        characterChoiceBoxes.setPadding(new Insets(40));
        ComboBox playerOneBox = new ComboBox();
        ComboBox playerTwoBox = new ComboBox();

        Button ready = new Button("Ready!");
        ready.setFont(Font.loadFont("file:earth-orbiter/earthorbiter.ttf", 30));
        ready.setStyle("-fx-border-color: black;" +
                "-fx-border-radius: 5;");

        Label messages[] = new Label[6];
        for(int i=0; i<6; i++) {
            messages[i] = new Label();
            messages[i].setFont(Font.loadFont("file:earth-orbiter/earthorbiter.ttf", 30));
            messages[i].setStyle("-fx-text-fill: white;");
        }
        messages[0].setText("Choose A Character");
        messages[0].setFont(Font.loadFont("file:earth-orbiter/earthorbiter.ttf", 24));
        messages[1].setText("Player One:");
        messages[2].setText("Character:");
        messages[2].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        messages[3].setText("Choose A Character");
        messages[3].setFont(Font.loadFont("file:earth-orbiter/earthorbiter.ttf", 24));
        messages[4].setText("Player Two:");
        messages[5].setText("Character:");
        messages[5].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);


        Label title = new Label("Select A Character");
        title.setFont(Font.loadFont("file:earth-orbiter/earthorbiter.ttf", 72));
        title.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        title.setAlignment(Pos.CENTER);
        title.setStyle("-fx-text-fill: white;");

        HBox bottomBox = new HBox();
        bottomBox.setSpacing(100); bottomBox.setPadding(new Insets(20));

        Label footer = new Label("Press Ready To Continue");
        footer.setFont(Font.loadFont("file:earth-orbiter/earthorbiter.ttf", 30));
        footer.setStyle("-fx-text-fill: white;");

        bottomBox.getChildren().addAll(footer, ready);

        BorderPane characters[] = new BorderPane[NUM_OF_CHARACTERS];
        Label characterNames[] = new Label[NUM_OF_CHARACTERS];
        BackgroundImage characterImages[] = new BackgroundImage[NUM_OF_CHARACTERS];
        for(int i=0; i<NUM_OF_CHARACTERS; i++) {
            characters[i] = new BorderPane();
            characterNames[i] = new Label();
            characterNames[i].setAlignment(Pos.CENTER);
            characterNames[i].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            characterNames[i].setStyle("-fx-text-fill: white;");
            characterNames[i].setFont(Font.loadFont("file:earth-orbiter/earthorbiter.ttf", 48));
            characterNames[i].setBackground(new Background(new BackgroundImage(new Image("file:images/black_background.png", 300, 50, false, true),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
            characters[i].setPrefSize(300, 500);
            characters[i].setStyle("-fx-border-color: white;" +
                    "-fx-border-radius: 5;");

            String filepath = "file:images/character"+(i+1);
            String suffix = (i !=  2? ".jpg" : ".png"); // TODO although they should all be the same type for easier code
            filepath += suffix;
            characterImages[i] = new BackgroundImage(new Image(filepath, 300, 500, false, true),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            characters[i].setBackground(new Background(characterImages[i]));
            characters[i].setBottom(characterNames[i]);
            characterPane.add(characters[i], i, 0);
        }

        characterNames[0].setText("Naruto");
        characterNames[1].setText("Sasuke");
        characterNames[2].setText("Sakura");

        for(int i=0; i<NUM_OF_CHARACTERS; i++) {
            playerOneBox.getItems().add(characterNames[i].getText());
            playerTwoBox.getItems().add(characterNames[i].getText());
        }


        playerOneBox.setOnAction(event-> {
            if(!playerOneBox.getSelectionModel().isEmpty()) {
                messages[2].setText("Character: " + playerOneBox.getValue());
            }
        });

        playerTwoBox.setOnAction(event -> {
            if(!playerTwoBox.getSelectionModel().isEmpty()) {
                messages[5].setText("Character: " + playerTwoBox.getValue());
            }
        });

        ready.setOnMouseClicked(event -> {
            if(playerOneBox.getSelectionModel().isEmpty() || playerTwoBox.getSelectionModel().isEmpty()) {
                event.consume();
                return;
            }
            System.out.println("Player 1 chose: " + playerOneBox.getValue());
            System.out.println("Player 2 chose: " + playerTwoBox.getValue());

            // TODO update and store what the people chose
            createMapScreen();
            characterSelectStage.close();
        });

        characterChoiceBoxes.add(messages[1], 0, 0);
        characterChoiceBoxes.add(messages[0], 0, 1);
        characterChoiceBoxes.add(playerOneBox, 1, 1);
        characterChoiceBoxes.add(messages[2], 0, 2, 2, 1);

        characterChoiceBoxes.add(messages[4], 0, 4);
        characterChoiceBoxes.add(messages[3], 0, 5);
        characterChoiceBoxes.add(playerTwoBox, 1, 5);
        characterChoiceBoxes.add(messages[5], 0, 6, 2, 1);


        characterSelectPane.setTop(title);
        characterSelectPane.setCenter(characterPane);
        characterSelectPane.setBottom(bottomBox);
        characterSelectPane.setRight(characterChoiceBoxes);

        characterSelectStage.setScene(characterSelectScene);
        characterSelectStage.show();
    } // TODO things are not finished
    private void createPopUpWindow(String messageContent, String windowTitle) {
        Stage popUpStage = new Stage();
        popUpStage.setTitle(windowTitle); // set title
        popUpStage.initModality(Modality.APPLICATION_MODAL); // so the user has to interact with this window first

        Label message = new Label(messageContent); // set the message
        message.setWrapText(true);
        message.setAlignment(Pos.TOP_LEFT); // wrapping text and setting the message to the center of the screen
        message.setStyle("-fx-font-family: Lato;" +
                "-fx-font-size: 14;");
        TextField tf = new TextField(messageContent);
        tf.setAlignment(Pos.CENTER);
        tf.setStyle("-fx-font-family: Lato;" +
                "-fx-font-size: 14;");
        Scene popUpScene = new Scene(tf, 400, 100);
        popUpStage.setScene(popUpScene);
        popUpStage.showAndWait();
    }
}
