import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CodeToMd {

    public static void main(String[] args){
        CodeToMd codeToMd=new CodeToMd();
        codeToMd.convertFiles();
    }

    public void convertFiles(){
        try {
            //files 경로의 파일 리스트를 읽어와서
            Path[] filePaths = Files.list(Paths.get("files")).toArray(Path[]::new);

            for(Path path : filePaths){
                convertFileToMd(path);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void convertFileToMd(Path path) throws IOException {
        BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);
        FileTime time = attrs.creationTime();

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String createdDate = simpleDateFormat.format(new Date(time.toMillis()));

        String preContent=makePreContent(createdDate);

        List<String> fileContent= new ArrayList<>();
        fileContent.add(preContent);
        fileContent.addAll(readSourceCode(path));

        String filename = makeMdFileName(path.getFileName().toString(),createdDate);
        writeToMd(fileContent,filename);
    }

    private String makePreContent(String createdDate) {
        return "---\n" +
                "layout: post\n" +
                "title: LeetCode - 33. Search in Rotated Sorted Array\n" +
                "date: "+createdDate+" 00:00:00\n" +
                "categories: leetcode\n" +
                "---\n";

    }

    private String makeMdFileName(String filename, String createdDate){
        filename= filename.substring(0,filename.length()-5)+".md";
        return "mds/"+createdDate+"-[medium] "+filename;
    }

    private void writeToMd(List<String> fileContent,String filename) {

        try(Writer fileWriter= new FileWriter(filename,false)){
            try(BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                for(String word : fileContent){
                    bufferedWriter.write(word);
                    bufferedWriter.newLine();
                }
                bufferedWriter.newLine();
            }
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> readSourceCode(Path path){
        List<String> lines=new ArrayList<>();
        try(Reader reader = new FileReader(path.toString())) {
            try(BufferedReader bufferedReader = new BufferedReader(reader)){
                lines.add("```java");

                String line="";
                while((line=bufferedReader.readLine())!=null){
                    lines.add(line);
                }
                lines.add("```");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
