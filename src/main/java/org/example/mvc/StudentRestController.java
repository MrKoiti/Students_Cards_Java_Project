package org.example.mvc;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.ObjectUtils;
import org.example.domain.Student_card;
import org.example.domain.StudentService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@org.springframework.stereotype.Controller
@RestController
@RequestMapping("/students")
@AllArgsConstructor
public class StudentRestController {
    private final StudentService studentService;

    @GetMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<Student_card> JIn() throws FileNotFoundException {
        return studentService.JIn();}

    @GetMapping("/getAllStudents")
    public ResponseEntity<List<Student_card>> getAllStudents() {
        List<Student_card> students = studentService.getAllStudents();
        if (students != null){
            return new ResponseEntity<>(students, HttpStatus.FOUND);}
        else{return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);}}

    @GetMapping("/getStudentById/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Student_card getStudentById(@PathVariable("id") int id) {
        Student_card student = studentService.getStudentById(id);
        return studentService.getStudentById(id);}

    @PostMapping("/addStudent")
    @ResponseStatus(HttpStatus.CREATED)
    public Student_card addStudent(@RequestBody Student_card student) {
        Student_card newStudent = studentService.addStudent(student);
        return newStudent;}

    @PutMapping("/updateStudent/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Student_card updateStudent(@PathVariable("id") int id, @RequestBody Student_card student) {
        Student_card updatedStudent = studentService.updateStudent(id, student);
        return updatedStudent;}

    @DeleteMapping("/deleteStudent/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteStudent(@PathVariable("id") int id) {
        studentService.deleteStudent(id);
        return "Student deleted";}

    @GetMapping("/meanSquareDeviation")
    @ResponseStatus(HttpStatus.OK)
    public double meanSquareDeviation() {
        double s = studentService.meanSquareDeviation();
        return s;}

    @GetMapping("/infoProg")
    @ResponseStatus(HttpStatus.OK)
    public LinkedHashMap<String, Object> info(jakarta.servlet.http.HttpServletRequest request) throws FileNotFoundException {
        LinkedHashMap<String, Object> info = new LinkedHashMap<>();

        String Author = request.getHeader("Author");
        String AppType = request.getHeader("ProgramType");

        info.put("Автор", Author);
        info.put("Тип приложения", AppType);
        info.put("Текст", "Программу написал студент группы УВА-212 Тазуркаев Максим Бекханович");
        info.put("Тема", "Карточки студентов");
        return info;}
}