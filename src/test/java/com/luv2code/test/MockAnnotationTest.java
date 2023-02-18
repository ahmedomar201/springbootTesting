package com.luv2code.test;

import com.luv2code.component.MvcTestingExampleApplication;
import com.luv2code.component.dao.ApplicationDao;
import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;
import com.luv2code.component.service.ApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = MvcTestingExampleApplication.class)
public class MockAnnotationTest {

    @Autowired
    ApplicationContext context;

    @Autowired
    CollegeStudent studentOne;

    @Autowired
    StudentGrades studentGrades;

//    @Mock
    @MockBean
    private ApplicationDao applicationDao;

//    @InjectMocks
    @Autowired
    private ApplicationService applicationService;

    @BeforeEach
    public void beforeEach() {
        studentOne.setFirstname("Ahmed");
        studentOne.setLastname("Omar");
        studentOne.setEmailAddress("ahmedomar1997.aoo@gmail.com");
        studentOne.setStudentGrades(studentGrades);
    }
    //setup
    @DisplayName("when verify")
    @Test
    public void isGradeGreaterStudentGrades() {

        //execute  call method you want testing
        when(applicationDao.addGradeResultsForSingleClass(
           studentGrades.getMathGradeResults()
        )).thenReturn(100.0);

        //assert check result
        assertEquals(100,applicationService.addGradeResultsForSingleClass(
                studentOne.getStudentGrades().getMathGradeResults()));

        //verify
        verify(applicationDao).addGradeResultsForSingleClass(
                studentGrades.getMathGradeResults());

        verify(applicationDao,times(1)).addGradeResultsForSingleClass(
                studentGrades.getMathGradeResults());

    }

    @DisplayName("find gpa")
    @Test
    public void findGpa() {

        //execute  call method you want testing
        when(applicationDao.findGradePointAverage(
                studentGrades.getMathGradeResults()
        )).thenReturn(88.31);

        //assert check result
        assertEquals(88.31,applicationService.findGradePointAverage(
                studentOne.getStudentGrades().getMathGradeResults()));


    }

    @DisplayName("not null")
    @Test
    public void notNull() {
        //execute  call method you want testing
        when(applicationDao.checkNull(
                studentGrades.getMathGradeResults()
        )).thenReturn(true);

        //assert check result
        assertNotNull(applicationService.checkNull(
                studentOne.getStudentGrades().getMathGradeResults()),"object should not be null");
    }




}
