package com.company.proautomation;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author vsanap
 * @created on 21-Sep-22
 * @project pro-automation
 */

@Component
@Data
public class Student {
    private String name;
    private int rollNo;

    public void sayHello(){
        System.out.println("Hi this is student");
        System.out.println(name+rollNo);
    }
}
