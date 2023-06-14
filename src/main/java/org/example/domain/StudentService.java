package org.example.domain;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Objects;

@Component
public class StudentService {
    private ArrayList<Student_card> cardList;

    //Текущий год (нужно для отклонения)
    public int currentYear = 2023;
    public StudentService(){
        cardList = new ArrayList<Student_card>();
        JIn();}

    public String readFileAsString(String file)throws Exception {return new String(Files.readAllBytes(Paths.get(file)));}

    // Путь к файлу
    public String path = "C:\\Study\\json\\student_card.json";

    // Добавляем массив из объектов в JSON
    public void JOut() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.setVisibility(PropertyAccessor.CREATOR, JsonAutoDetect.Visibility.ANY);
        try {mapper.writeValue(new File(path), cardList);} catch (Exception e){System.out.println(e.getMessage());}}



    // Вывод массива объектов из файла
    public ArrayList<Student_card> JIn() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try
        {
            String json = readFileAsString(path);
            ArrayList<Student_card>  cL = mapper.readValue(json, new TypeReference<ArrayList<Student_card>>(){});
            cardList = cL;
            return cL;} catch (Exception e){System.out.println(e.getMessage());
        return null;}}

    // Удаление объекта из массива
    public void deleteStudent(int id) {
        try {
            ArrayList<Student_card> cL = new ArrayList<>();

            for (int i = 0; i< cardList.toArray().length; i++){
                if (cardList.get(i).getId() != id) {
                    cL.add(cardList.get(i));}}
            cardList = cL;
            JOut();} catch (Exception e) {System.out.println("Ошибка удаления");}}

    // Добавление объекта в массив
    public Student_card addStudent(Student_card student) {
        try {
            cardList.add(student);
            JOut();
            return student;}
        catch (Exception e){return null;}}

    // Найти студента по ID
    public Student_card getStudentById(int id){
        for (int i = 0; i< cardList.toArray().length; i++){
            if (cardList.get(i).getId() == id) {
                return cardList.get(i);
            }
        }
        return null;
    }

    // Найти всех студентов
    public ArrayList<Student_card> getAllStudents(){return cardList;}

    // Обноваить данные о студенте по ID
    public Student_card updateStudent(int id, Student_card student){
        ArrayList<Student_card> cL = new ArrayList<>();
        for (int i = 0; i< cardList.toArray().length; i++){
            if (cardList.get(i).getId() == id) {
                cL.add(student);}
            else{cL.add(cardList.get(i));}}
        cardList = cL;
        JOut();
        return student;}

    //Среднеквадратичное отклонение
    public double meanSquareDeviation(){
        double n = cardList.toArray().length;
        Double avrgAge = 0.;

        for (int i = 0; i< cardList.toArray().length; i++){avrgAge = avrgAge + (currentYear - cardList.get(i).getAge());}
        avrgAge = avrgAge / n;

        double sumAvrg = 0;
        for (int i = 0; i < cardList.toArray().length; i++){sumAvrg = sumAvrg + Math.pow(((currentYear - cardList.get(i).getAge()) - avrgAge), 2);}
        double dispersion = sumAvrg / n;
        dispersion = Math.pow(dispersion, 0.5);
        return dispersion;}}