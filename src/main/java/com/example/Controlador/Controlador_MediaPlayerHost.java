package com.example.Controlador;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

public class Controlador_MediaPlayerHost implements Initializable {
    /** declararcion de las variables
     * @author Dereck Rojas, Emilio Valverde, Karen Porras
     */
    private Stage stage;
    private Scene scene;
    private Parent root;



    @FXML
    private VBox vboxParent;
    @FXML
    private MediaView mvVideo;
    private MediaPlayer mpVideo;
    private Media mediaVideo;
    @FXML
    private HBox hBoxControls;
    @FXML
    private HBox hboxVolume;
    @FXML
    private Button buttonPPR;
    @FXML
    private Button buttonUpload;
    @FXML
    private Button buttonLike;
    @FXML
    private Label labelCurrentTime;
    @FXML
    private Label labelTotalTime;
    @FXML
    private Label labelFullScreen;
    @FXML
    private Label labelSpeed;
    @FXML
    private Label labelVolume;
    @FXML
    private Slider sliderVolume;
    @FXML
    private Slider sliderTime;
    private boolean atEndOfVideo = false;
    private boolean isPlaying = true;
    private boolean isMuted = true;
    private ImageView ivPlay;
    private ImageView ivPause;
    private ImageView ivRestart;
    private ImageView ivVolume;
    private ImageView ivFullScreen;
    private ImageView ivMute;
    private ImageView ivExit;
    private ImageView ivLike;
    private String path;

    /**
     * Se encarga del cambio de escena
     * @author Dereck Rojas, Emilio Valverde, Karen Porras
     * @param event recibe como parametro la accion realizada por usuario
     * @throws IOException Captura las excepciones que se hayan podido reproducir en el codigo
     */

    public void switchToSceneMegusta(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Megusta.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,236,150);
        stage.setScene(scene);
        stage.show();
    }






    /**
     * Se encarga de darle estilo y funcionamiento a los botones y labels
     * @author Dereck Rojas, Emilio Valverde, Karen Porras
     * @param url recibe como parametro la accion realizada por usuario
     * @param resourceBundle recibe como parametro la accion realizada por usuario
     */


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {





        mediaVideo = new Media(new File(Controlador_Buscar.data).toURI().toString());
        mpVideo = new MediaPlayer(mediaVideo);
        mvVideo.setMediaPlayer(mpVideo);



        Image imagePlay = new Image(new File("src/Recursos/play.png").toURI().toString());
        ivPlay = new ImageView(imagePlay);
        ivPlay.setFitWidth(35);
        ivPlay.setFitHeight(35);

        Image imageLike = new Image(new File("src/Recursos/corazon.png").toURI().toString());
        ivLike = new ImageView(imageLike);
        ivLike.setFitWidth(35);
        ivLike.setFitHeight(35);

        Image imageStop = new Image(new File("src/Recursos/pause.png").toURI().toString());
        ivPause = new ImageView(imageStop);
        ivPause.setFitHeight(35);
        ivPause.setFitWidth(35);

        Image imageRestart = new Image(new File("src/Recursos/restart.png").toURI().toString());
        ivRestart = new ImageView(imageRestart);
        ivRestart.setFitWidth(35);
        ivRestart.setFitHeight(35);

        Image imageVol = new Image(new File("src/Recursos/volume.png").toURI().toString());
        ivVolume = new ImageView(imageVol);
        ivVolume.setFitWidth(35);
        ivVolume.setFitHeight(35);

        Image imageFull = new Image(new File("src/Recursos/fullscreen.png").toURI().toString());
        ivFullScreen = new ImageView(imageFull);
        ivFullScreen.setFitHeight(35);
        ivFullScreen.setFitWidth(35);

        Image imageMute = new Image(new File("src/Recursos/mute.png").toURI().toString());
        ivMute = new ImageView(imageMute);
        ivMute.setFitWidth(35);
        ivMute.setFitHeight(35);

        Image imageExit = new Image(new File("src/Recursos/exit.png").toURI().toString());
        ivExit = new ImageView(imageExit);
        ivExit.setFitHeight(35);
        ivExit.setFitWidth(35);



        buttonPPR.setGraphic(ivPause);

        labelVolume.setGraphic(ivMute);

        buttonLike.setGraphic(ivLike);

        labelSpeed.setText("1X");

        labelFullScreen.setGraphic(ivFullScreen);

        hboxVolume.getChildren().remove(sliderVolume);

        /** acciones de los videos
         * @author Dereck Rojas, Emilio Valverde, Karen Porras
         * @param actionEvent recibe como parametro la accion realizada por usuario
         */

        buttonPPR.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Button buttonPlay = (Button) actionEvent.getSource();
                bindCurrentTimeLabel();
                if (atEndOfVideo) {
                    sliderTime.setValue(0);
                    atEndOfVideo = false;
                    isPlaying = false;
                }
                if (isPlaying) {
                    try {
                        ServerSocket serverSocket = new ServerSocket(12345);
                        Socket s = serverSocket.accept();
                        DataOutputStream data = new DataOutputStream(s.getOutputStream());
                        data.writeUTF("pausar");
                        data.flush();
                        data.close();
                        s.close();
                        serverSocket.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    buttonPlay.setGraphic(ivPlay);
                    mpVideo.pause();
                    isPlaying = false;
                } else {
                    try {
                        ServerSocket serverSocket = new ServerSocket(12345);
                        Socket s = serverSocket.accept();
                        DataOutputStream data = new DataOutputStream(s.getOutputStream());
                        data.writeUTF("empezar");
                        data.flush();
                        data.close();
                        s.close();
                        serverSocket.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    buttonPlay.setGraphic(ivPause);
                    mpVideo.play();
                    isPlaying = true;
                }

            }
        });



        /** volumen video
         * @author Dereck Rojas, Emilio Valverde, Karen Porras
         */


        mpVideo.volumeProperty().bindBidirectional(sliderVolume.valueProperty());

        sliderVolume.valueProperty().addListener(new InvalidationListener() {

            /**
             * volumen video
             * @author Dereck Rojas, Emilio Valverde, Karen Porras
             * @param observable Recibe como parametro la accion realizada por usuario
             */
            @Override
            public void invalidated(Observable observable) {

                mpVideo.setVolume(sliderVolume.getValue());

                if (mpVideo.getVolume() != 0.0) {
                    labelVolume.setGraphic(ivVolume);
                    isMuted = false;
                } else {

                    labelVolume.setGraphic(ivMute);
                    isMuted = true;
                }
            }
        });


        mpVideo.play();


        labelSpeed.setOnMouseClicked(new EventHandler<MouseEvent>() {
            /** velocidad video
             * @author Dereck Rojas, Emilio Valverde, Karen Porras
             * @param mouseEvent Recibe como parametro la accion realizada por usuario
             */
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (labelSpeed.getText().equals("1X")) {
                    labelSpeed.setText("2X");
                    mpVideo.setRate(2.0);
                } else {
                    labelSpeed.setText("1X");
                    mpVideo.setRate(1.0);
                }
            }
        });


        labelVolume.setOnMouseClicked(new EventHandler<MouseEvent>() {
            /**
             * Recibe como parametro la accion realizada por usuario
             * @param mouseEvent Recibe como parametro la accion realizada por usuario
             */
            @Override
            public void handle(MouseEvent mouseEvent) {

                if (isMuted) {
                    labelVolume.setGraphic(ivVolume);
                    sliderVolume.setValue(0.2);
                    isMuted = false;
                }
                else {

                    labelVolume.setGraphic(ivMute);
                    sliderVolume.setValue(0);
                    isMuted = true;
                }
            }
        });


        labelVolume.setOnMouseEntered(new EventHandler<MouseEvent>() {
            /**
             * @author Dereck Rojas, Emilio Valverde, Karen Porras
             * @param mouseEvent  Recibe como parametro la accion realizada por usuario
             */
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (hboxVolume.lookup("#sliderVolume") == null) {
                    hboxVolume.getChildren().add(sliderVolume);
                    sliderVolume.setValue(mpVideo.getVolume());
                }
            }
        });

        hboxVolume.setOnMouseExited(new EventHandler<MouseEvent>() {
            /**
             * @author Dereck Rojas, Emilio Valverde, Karen Porras
             * @param mouseEvent Recibe como parametro la accion realizada por usuario
             */
            @Override
            public void handle(MouseEvent mouseEvent) {
                hboxVolume.getChildren().remove(sliderVolume);
            }
        });



        vboxParent.sceneProperty().addListener(new ChangeListener<Scene>() {
            /**
             * @author Dereck Rojas, Emilio Valverde, Karen Porras
             * @param observableValue Recibe como parametro la accion realizada por usuario
             * @param scene Recibe como parametro la accion realizada por usuario
             * @param newScene Recibe como parametro la accion realizada por usuario
             */
            @Override
            public void changed(ObservableValue<? extends Scene> observableValue, Scene scene, Scene newScene) {
                if (scene == null && newScene != null) {

                    mvVideo.fitHeightProperty().bind(newScene.heightProperty().subtract(hBoxControls.heightProperty().add(20)));
                }
            }
        });


        labelFullScreen.setOnMouseClicked(new EventHandler<MouseEvent>() {
            /**
             * @author Dereck Rojas, Emilio Valverde, Karen Porras
             * @param mouseEvent Recibe como parametro la accion realizada por usuario
             */
            @Override
            public void handle(MouseEvent mouseEvent) {
                Label label = (Label) mouseEvent.getSource();
                Stage stage = (Stage) label.getScene().getWindow();

                if (stage.isFullScreen()) {
                    stage.setFullScreen(false);
                    labelFullScreen.setGraphic(ivFullScreen);
                } else {
                    stage.setFullScreen(true);
                    labelFullScreen.setGraphic(ivExit);
                    stage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
                        @Override
                        public void handle(KeyEvent keyEvent) {
                            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                                labelFullScreen.setGraphic(ivFullScreen);
                            }
                        }
                    });
                }
            }
        });


        /**
         * Recibe como parametro la accion realizada por usuario
         * totalDurationProperty() - the total amount of play time if allowed to play until finished.
         */

        mpVideo.totalDurationProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observableValue, Duration oldDuration, Duration newDuration) {
                sliderTime.setMax(newDuration.toSeconds());
                labelTotalTime.setText(getTime(newDuration));

            }
        });


        sliderTime.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            /**
             *@author Dereck Rojas, Emilio Valverde, Karen Porras
             * @param observableValue Recibe como parametro la accion realizada por usuario
             * @param wasChanging Recibe como parametro la accion realizada por usuario
             * @param isChanging Recibe como parametro la accion realizada por usuario
             */
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean wasChanging, Boolean isChanging) {
                bindCurrentTimeLabel();

                if (!isChanging) {
                    mpVideo.seek(Duration.seconds(sliderTime.getValue()));
                }
            }
        });

        sliderTime.valueProperty().addListener(new ChangeListener<Number>() {
            /**
             * @author Dereck Rojas, Emilio Valverde, Karen Porras
             * @param observableValue Recibe como parametro la accion realizada por usuario
             * @param oldValue Recibe como parametro la accion realizada por usuario
             * @param newValue Recibe como parametro la accion realizada por usuario
             */
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                bindCurrentTimeLabel();
                double currentTime = mpVideo.getCurrentTime().toSeconds();
                if (Math.abs(currentTime - newValue.doubleValue()) > 0.5) {
                    mpVideo.seek(Duration.seconds(newValue.doubleValue()));
                }
                labelsMatchEndVideo(labelCurrentTime.getText(), labelTotalTime.getText());
            }
        });

        mpVideo.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            /**
             * @author Dereck Rojas, Emilio Valverde, Karen Porras
             * @param observableValue Recibe como parametro la accion realizada por usuario
             * @param oldTime Recibe como parametro la accion realizada por usuario
             * @param newTime Recibe como parametro la accion realizada por usuario
             */
            @Override
            public void changed(ObservableValue<? extends Duration> observableValue, Duration oldTime, Duration newTime) {
                bindCurrentTimeLabel();
                if (!sliderTime.isValueChanging()) {
                    sliderTime.setValue(newTime.toSeconds());
                }
                labelsMatchEndVideo(labelCurrentTime.getText(), labelTotalTime.getText());
            }
        });


        mpVideo.setOnEndOfMedia(new Runnable() {
            @Override
            /**
             * @author Dereck Rojas, Emilio Valverde, Karen Porras
             */
            public void run() {
                buttonPPR.setGraphic(ivRestart);
                atEndOfVideo = true;

                if (!labelCurrentTime.textProperty().equals(labelTotalTime.textProperty())) {
                    labelCurrentTime.textProperty().unbind();
                    labelCurrentTime.setText(getTime(mpVideo.getTotalDuration()) + " / ");
                }
            }
        });


    }

    /**
     * @author Dereck Rojas, Emilio Valverde, Karen Porras
     * La funcion toma el tiempo del video y calcula las horas minutos y segundos
     * @param time - The time of the video.
     * @return Corrected seconds, minutes, and hours.
     */
    public String getTime(Duration time) {

        int hours = (int) time.toHours();
        int minutes = (int) time.toMinutes();
        int seconds = (int) time.toSeconds();


        if (seconds > 59) seconds = seconds % 60;
        if (minutes > 59) minutes = minutes % 60;
        if (hours > 59) hours = hours % 60;


        if (hours > 0) return String.format("%d:%02d:%02d",
                hours,
                minutes,
                seconds);
        else return String.format("%02d:%02d",
                minutes,
                seconds);
    }



    /**
     * @author Dereck Rojas, Emilio Valverde, Karen Porras
     * @param labelTime Recibe como parametro la accion realizada por usuario
     * @param labelTotalTime  Recibe como parametro la accion realizada por usuario
     */
    public void labelsMatchEndVideo(String labelTime, String labelTotalTime) {
        for (int i = 0; i < labelTotalTime.length(); i++) {
            if (labelTime.charAt(i) != labelTotalTime.charAt(i)) {
                atEndOfVideo = false;
                if (isPlaying) buttonPPR.setGraphic(ivPause);
                else buttonPPR.setGraphic(ivPlay);
                break;
            } else {
                atEndOfVideo = true;
                buttonPPR.setGraphic(ivRestart);
            }
        }
    }

    /**
     * @author Dereck Rojas, Emilio Valverde, Karen Porras
     */
    public void bindCurrentTimeLabel() {

        labelCurrentTime.textProperty().bind(Bindings.createStringBinding(new Callable<String>() {
            @Override
            public String call() throws Exception {

                return getTime(mpVideo.getCurrentTime()) + " / ";
            }
        }, mpVideo.currentTimeProperty()));
    }
}
