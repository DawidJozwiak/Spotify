package advisor;

import java.util.*;

public class Spotify {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean isauthorized = false;
        int maximum = 20;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-page")) {
                maximum = Integer.parseInt(args[i + 1]);
            }
        }

        while (true) {
            String input = scanner.nextLine();
            String command = input.contains(" ") ? input.substring(0, input.indexOf(" ")) : input;
            if ("auth".equals(command)) {
                if (!authorized) {
                    isauthorized = JSON.authorize();
                    System.out.println(authorized ? "Success!" : "Failed");
                } else {
                    System.out.println("Already authorized");
                }
                continue;
            }
            if ("exit".equals(command)) {
                System.out.println("---GOODBYE!---");
                return;
            }
            if (authorized) {
                switch (command) {
                    case "new":
                        var newReleases = JSON.getNewReleases(limit);
                        Ui.print(newReleases);
                        break;
                    case "featured":
                        var featured = JSON.getFeatured(limit);
                        Ui.print(featured);
                        break;
                    case "categories":
                        var categories = JSON.getCategories(limit);
                        Ui.print(categories);
                        break;
                    case "playlists":
                        String categoryName = input.substring(input.indexOf(" ") + 1);
                        var playlists = JSON.getPlaylists(categoryName, limit);
                        Ui.print(playlists);
                        break;
                    case "next":
                        var nextPage = JSON.getPage("next");
                        if (nextPage == null) {
                            System.out.println("No more pages.");
                        } else {
                            View.print(nextPage);
                        }
                        break;
                    case "previous":
                        var previousPage = JSON.getPage("previous");
                        if (previousPage == null) {
                            System.out.println("No more pages.");
                        } else {
                            Ui.print(previousPage);
                        }
                        break;
                    default:
                        System.out.println("Unknown command");
                }
            } else {
                System.out.println("Provide access to the application");
            }
        }
    }
}
