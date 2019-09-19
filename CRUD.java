import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public final class CRUD {

  private static final String outputFilePath = System.getProperty("user.dir") + File.separator + "Index_Output.txt";

  /**
   * Initilaizes a directory with parameterized filepath.
   *
   * @Directory path to initialize
   * @return true if write was successful, false otherwise
   */
  public boolean initDirectory(String dirPath) {
    if (!new File(dirPath).exists()) {
      new File(dirPath).mkdirs();
      return true;
    }
    return false;
  }

  /**
   * Gets and returns a list of file names in the article directory.
   *
   * @param articlePath the source directory
   * @return a list of file names contained in the desired directory
   */
  static LinkedList<String> getFilesInDirectory(String articlePath) {
    File folder = new File(articlePath);
    File[] directoryContents = folder.listFiles();
    LinkedList<String> filesInDirectory = new LinkedList<>();
    assert directoryContents != null;
    for (File directoryContent : directoryContents) {
      if (directoryContent.isFile()) {
        filesInDirectory.add(directoryContent.getName());
      }
    }
    return filesInDirectory;
  }

  /**
   * Appends the given line to inverted index output file.
   *
   * @param path filepath to write-to
   * @param line contents to write
   * @return true if successfully written
   */
  static boolean appendToFileBuffered(String path, String line) {
    boolean exists = new File(path).exists();
    try (BufferedWriter bw = new BufferedWriter(
            new FileWriter(path, true))) {
      if (exists) { bw.newLine(); }
      bw.append(line);
      bw.flush();
      bw.close();
      return true;
    } catch (IOException e) {
      return false;
    }
  }

  /**
   * Appends the given line to inverted index output file.
   *
   * @param path filepath to write-to
   * @param line contents to write
   * @return true if successfully written
   */
  static boolean appendToFileRaw(String path, String line) {
    try (FileWriter fw = new FileWriter(path, true)) {
      fw.append(line);
      fw.flush();
      fw.close();
      return true;
    } catch (IOException e) {
      return false;
    }
  }

  /**
   * Reads file from AbsolutePath.
   *
   * @param path the path
   * @return the list
   */
  static List<String> readFile(String path) {
    try {
      //create list to hold lines
      List<String> list = Files.readAllLines(Paths.get(path));
      return list;
    } catch (IOException e) {
      e.getMessage();
    }
    return null;
  }
}
