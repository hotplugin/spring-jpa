/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faisal.controllers;

import com.faisal.entities.Student;
import com.faisal.repositories.StudentDao;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import jxl.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import jxl.Workbook;
import com.faisal.entities.Ask;
import com.faisal.entities.CsvMap;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

/**
 *
 * @author hotplugin
 */
@Controller
public class HelloWorld {

    @Autowired
    StudentDao studentDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewHome() {
        System.out.println("-------------------ccccccccccccc----------------");
//        List<String> list = getList();
//        ModelAndView model = new ModelAndView("hello");
//        model.addObject("msg", "ni ssss");
//        model.addObject("lists", list);
//        model.addObject("student", new Student());
//        model.addObject("list",getStudentsList());
//        Student s = studentDao.findStudent("faisal");
//        System.out.println(s.getLastName());
//        model.addObject("name", s.getFirstName()+ " "+s.getLastName());
        return "hello";

    }

    @RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
    public ModelAndView uploadfile(@RequestParam("file") MultipartFile file) {
        System.out.println("Uploadfile called..");
        String msg = "";
        String successmg = "";
        if (!file.isEmpty()) {
            System.out.println("original: " + file.getOriginalFilename());
            System.out.println("size " + file.getSize());

            try {
//                ByteArrayInputStream bis = new ByteArrayInputStream(file.getBytes());
                ICsvBeanReader beanReader = null;
                try {
                    beanReader = new CsvBeanReader(new InputStreamReader(file.getInputStream()), CsvPreference.STANDARD_PREFERENCE);
//                    System.out.println("okokokoko");
                    // the header elements are used to map the values to the bean (names must match)
                    beanReader.getHeader(true);
                    final String[] header = new String[]{"Time", "Ask", null, null, null
                    };

                    final CellProcessor[] processors = getProcessors();
//                
                    CsvMap customer;

                    while ((customer = beanReader.read(CsvMap.class, header, processors)) != null) {
//                        System.out.println(String.format("lineNo=%s, rowNo=%s, customer=%s", beanReader.getLineNumber(),
//                                beanReader.getRowNumber(), customer));

                        Ask a = new Ask();
//
                        a.setTimeanddatestring(customer.getTime());
                        a.setAsk(customer.getAsk());
                        try {
                            studentDao.addRow(a);
                            successmg = " Success!";
                        } catch (Exception e) {
//                            System.out.println("duplicate..");
                            if ("".equals(msg)) {
//                                System.out.println("111");
                                msg = "Duplicates ignoring..";
                            }
//                            System.out.println("msggg "+msg);
                            msg = msg + ", " + customer.getTime();
                        }

                    }

                } finally {
                    if (beanReader != null) {
                        beanReader.close();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("no file uploaded");
            msg = "No file selected";
        }
        System.out.println("FINAL MSG: " + msg);
        ModelAndView model = new ModelAndView("hello");
        model.addObject("msg", msg);
        model.addObject("successmsg", successmg);
        return model;
//        return "hello";

    }

    public CellProcessor[] getProcessors() {
        CellProcessor[] processors = new CellProcessor[]{
            new NotNull(), // time
            new NotNull(), // time
            null, // time
            null,
            null // askpoints
        };
        return processors;
    }

    @RequestMapping(value = "/queryfile", method = RequestMethod.POST)
    public ModelAndView queryfile(@RequestParam("from") String from, @RequestParam("to") String to, @RequestParam("flocation") String flocation) throws IOException {
        System.out.println("queryfile called..." + from + "  " + to);
        List<Ask> found = studentDao.download(from, to);
        String msg = "";
        if (found != null) {
//            for (Ask a : found) {
//                System.out.println(a.getId());
//            }

            ICsvBeanWriter beanWriter = null;
            try {
                beanWriter = new CsvBeanWriter(new FileWriter(flocation),
                        CsvPreference.STANDARD_PREFERENCE);

                // the header elements are used to map the bean values to each column (names must match)
                final String[] header = new String[]{"timeanddatestring", "ask"
                };
                final CellProcessor[] processors = new CellProcessor[]{
                    new NotNull(), // time
                    new NotNull() // time
                };

                // write the header
                beanWriter.writeHeader(header);

                // write the beans
                for (final Ask customer : found) {
                    beanWriter.write(customer, header, processors);
                }
                msg="Success!";
            } catch (IOException ex) {
                msg = ex.getMessage();
            } finally {
                if (beanWriter != null) {
                    beanWriter.close();
                }
            }
        }else{
            msg="No Record Found";
        }
         ModelAndView model = new ModelAndView("hello");
        model.addObject("msgd", msg);
        return model;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView adduser(@ModelAttribute("student") Student st) {
        System.out.println("called d" + st.getFirstName());
        studentDao.addStudent(st);
        return new ModelAndView("hello", "list", getStudentsList());
//        return "hello";

    }

    List<Student> getStudentsList() {
        return studentDao.findAllStudent();
    }

    private List<String> getList() {

        List<String> list = new ArrayList<String>();
        list.add("List A");
        list.add("List B");
        list.add("List C");
        list.add("List D");
        list.add("List 1");
        list.add("List 2");
        list.add("List 3");

        return list;

    }

}
