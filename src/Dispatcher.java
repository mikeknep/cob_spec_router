import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by mrk on 6/2/14.
 */
public class Dispatcher {
    public static ResponseBuilder setResponseBuilder(String rootDirectory, String method, String requestedResource) {
        Path path = Paths.get(rootDirectory + requestedResource);
        if (isBadRequest(method, requestedResource)) {
            return new BadRequestResponseBuilder();
        } else if (isNotAllowedMethod(method, requestedResource)) {
            return new MethodNotAllowedResponseBuilder();
        } else if (isOptionsRequest(method)) {
            return new OptionsResponseBuilder();
        } else if (isRedirect(requestedResource)) {
            return new RedirectResponseBuilder();
        } else if (isDirectory(path)) {
            return new DirectoryResponseBuilder(rootDirectory, requestedResource);
        } else if (isFile(path)) {
            return new FileResponseBuilder(rootDirectory, requestedResource);
        } else {
            return new MissingResourceResponseBuilder(rootDirectory);
        }
    }


    private static boolean isBadRequest(String method, String requestedResource) {
        return (method.equals("") && requestedResource.equals(""));
    }

    private static boolean isRedirect(String requestedResource) {
        return (requestedResource.equals("/redirect"));
    }

    private static boolean isDirectory(Path path) {
        return Files.isDirectory(path);
    }

    private static boolean isFile(Path path) {
        return Files.exists(path);
    }

    private static boolean isOptionsRequest(String method) {
        return (method.equals("OPTIONS"));
    }

    private static boolean isNotAllowedMethod(String method, String requestedResource) {
        ArrayList<String> validMethods = new ArrayList<String>();
        validMethods.add("GET");
        validMethods.add("HEAD");
        validMethods.add("OPTIONS");

        ArrayList<String> simpleResources = new ArrayList<String>();
        simpleResources.add("/file1");
        simpleResources.add("/text-file.txt");

        return (!validMethods.contains(method) && simpleResources.contains(requestedResource));
    }
}
