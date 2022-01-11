package severstal.zorin.homework.pageobject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import severstal.zorin.homework.pageobject.pages.PracticeFormPage;
import severstal.zorin.homework.pageobject.pages.StudentModel;

import java.util.List;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static severstal.zorin.homework.pageobject.TestData.*;

public class TestCheckPracticeForm extends TestBase {

        PracticeFormPage practiceFormPage = new PracticeFormPage();
        StudentModel studentModel = new StudentModel();

        @Test
        void registrationFormTest() {
            studentModel
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setEmail(studentEmail)
                    .setGender("Male")
                    .setMobilePhone(studentPhoneNumber)
                    .setBirthDate("28", "June", "1976")
                    .setSubjects(List.of("Computer Science"))
                    .setHobbies(List.of("Music", "Sports"))
                    .setPicture("FirstJob.jpg")
                    .setCurrentAddress(address)
                    .setState("NCR")
                    .setCity("Gurgaon");

            practiceFormPage
                    .openPracticeFormPage()
                    .setStudentModel(studentModel)
                    .submit();

            practiceFormPage
                    .validateSubmittingFormTitle()
                    .validateSubmittingFormFields();
        }

    @AfterEach
    void after(){
        closeWebDriver();
    }

}
