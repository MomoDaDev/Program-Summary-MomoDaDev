package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.web.WebView;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.web.WebEngine;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    //start1 Test mit einer externen Seite
        /*
        @Override
        public void start(final Stage stage)
        {
            // Create the WebView
            WebView webView = new WebView();

            // Disable the context menu
            webView.setContextMenuEnabled(false);

            // Increase the text font size by 20%
            webView.setFontScale(1.20);

            // Set the Zoom 20%
            webView.setZoom(1.20);

            // Set font smoothing type to GRAY
            webView.setFontSmoothingType(FontSmoothingType.GRAY);

            // Create the WebEngine
            final WebEngine webEngine = webView.getEngine();
            // Load the StartPage
            webEngine.load("http://www.google.com");

            // Update the stage title when a new web page title is available
            webEngine.titleProperty().addListener((ov, oldvalue, newvalue) -> stage.setTitle(newvalue));

            // Create the VBox
            VBox root = new VBox();
            // Add the Children to the VBox
            root.getChildren().add(webView);

            // Set the Style-properties of the VBox
            root.setStyle("-fx-padding: 10;" +
                    "-fx-border-style: solid inside;" +
                    "-fx-border-width: 2;" +
                    "-fx-border-insets: 5;" +
                    "-fx-border-radius: 5;" +
                    "-fx-border-color: blue;");

            // Create the Scene
            Scene scene = new Scene(root);
            // Add  the Scene to the Stage
            stage.setScene(scene);
            // Display the Stage
            stage.show();
        }
    }
   */
//start2 Math Formel!!!
    @Override
    public void start(Stage primaryStage) {

        int start = 0, end = 99;

        // Optimized Method

        System.out.println("Calculating from range " + start + " -> " + end + " (Optimized)");
        AllResults allResults = Calculate.calculateRange(start, end);
        System.out.println(allResults.toString());

        String str = buildMathString(allResults);

        //str += "<math display=\"none\"><mrow><mi>x</mi><mo>=</mo><mfrac><mrow><mo>−</mo><mi>b</mi><mo>±</mo><msqrt><mrow><msup><mi>b</mi><mn>2</mn></msup><mo>−</mo><mn>4</mn><mi>a</mi><mi>c</mi></mrow></msqrt></mrow><mrow><mn>2</mn><mi>a</mi></mrow></mfrac></mrow></math>";
        WebView webView = new WebView();
        webView.getEngine().loadContent(
                "<!DOCTYPE html>\n" +
                        "<html lang=\"de\">\n" +
                        "  <head>\n" +
                        "    <meta charset=\"utf-8\">\n" +
                        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                        "    <title>Titel</title>\n" +
                        "  </head>\n" +
                        "  <body>\n" +
                        str + "\n" +
                        "<br>" +
                        "  </body>\n" +
                        "</html>"
        );
        primaryStage.setScene(new Scene(webView));
        primaryStage.show();
    }
    public String buildMathString(AllResults allResults) {
        String str = "";

        for (int i = 0; i < allResults.allresults.size(); i++) {
            str += "<math display=\"block\"><mrow><mi>" + allResults.allresults.get(i).getNumber() + "</mi>";
            for (int j = 0; j < allResults.allresults.get(i).getNumbers().size(); j++) {
                int[] x = allResults.allresults.get(i).getNumbers().get(j); // damit die Zeile darunter nicht zu lange wird
                str += "<mo>=</mo><msup><mi>" + (int)Math.sqrt(x[0]) + "</mi><mn>2</mn></msup><mo>+</mo><msup><mi>" + (int)Math.sqrt(x[1]) + "</mi><mn>2</mn></msup><mo>+</mo><msup><mi>"
                        + (int)Math.sqrt(x[2]) + "</mi><mn>2</mn></msup><mo>+</mo><msup><mi>" + (int)Math.sqrt(x[3]) + "</mi><mn>2</mn></msup>";
            }
            str += "<mi> </mi><mi> </mi><mi> </mi><mi> </mi><mi> </mi><mi> </mi><mi> </mi><mi> </mi></mrow></math>";
        }

        return str;
    }
}
