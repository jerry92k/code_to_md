import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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

        String filename=path.getFileName().toString();
        String fileExtension=filename.split("\\.")[1];
        String subjectTitle=getSubjectTitle(filename);
        String preContent=makePreContent(createdDate,subjectTitle);

        List<String> fileContent= new ArrayList<>();
        fileContent.add(preContent);
        fileContent.addAll(readSourceCode(path,fileExtension));

        String mdFilename = makeMdFileName(subjectTitle,createdDate);
        writeToMd(fileContent,mdFilename);
    }

    private String getSubjectTitle(String filename) {
        String[] strs=filename.split("_");
        StringBuilder sb=new StringBuilder();
        // medium 단어 생략
        String subjectTitle = Arrays.stream(strs)
                .filter(str->!str.equals("medium"))
                .map(str-> Character.toUpperCase(str.charAt(0))+str.substring(1))
                .collect(Collectors.joining(" "));
        return subjectTitle;
    }

    private String makePreContent(String createdDate, String subjectTitle) {
        return "---\n" +
                "layout: post\n" +
                "title: LeetCode - "+subjectTitle+"\n" +
                "date: "+createdDate+" 00:00:00\n" +
                "categories: leetcode\n" +
                "---\n";

    }

    private String makeMdFileName(String filename, String createdDate){
        return "mds/"+createdDate+"-[medium] "+filename.split("\\.")[0]+".md";
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

    public List<String> readSourceCode(Path path, String fileExtension){
        List<String> lines=new ArrayList<>();
        try(Reader reader = new FileReader(path.toString())) {
            try(BufferedReader bufferedReader = new BufferedReader(reader)){
                lines.add("```"+fileExtension);

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
