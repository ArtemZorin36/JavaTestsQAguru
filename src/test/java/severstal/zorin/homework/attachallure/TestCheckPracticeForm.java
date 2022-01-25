package severstal.zorin.homework.attachallure;

import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import severstal.zorin.homework.attachallure.pages.PracticeFormPage;
import severstal.zorin.homework.attachallure.pages.StudentModel;

import java.util.List;

import static severstal.zorin.homework.pageobject.TestData.*;

public class TestCheckPracticeForm extends TestBase {

        PracticeFormPage practiceFormPage = new PracticeFormPage();
        StudentModel studentModel = new StudentModel();

        @Test
        @Owner("ArtemZorin36")
        @DisplayName("Тест заполнения формы регистрации студента")
        @Severity(SeverityLevel.BLOCKER)
        @Link(value = "Форма регистрации", url = "https://demoqa.com/automation-practice-form")
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
}
