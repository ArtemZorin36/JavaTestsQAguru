package severstal.zorin.homework.attachallure.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static severstal.zorin.homework.pageobject.pages.Utils.convertResponseToModel;
import static severstal.zorin.homework.pageobject.pages.Utils.getBrowserResponseExecutingJs;

public class PracticeFormPage {

    private final String practiceFormPage = "/automation-practice-form";
    private StudentModel studentModel;

    private final SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            email = $("#userEmail"),
            phoneNumber = $("#userNumber"),
            studentGender = $("#genterWrapper"),
            studentSubjects = $("#subjectsInput"),
            studentHobbies = $("#hobbiesWrapper"),
            uploadPicture = $("#uploadPicture"),
            studentAddress = $("#currentAddress"),
            studentState = $("#state"),
            studentCity = $("#city"),
            submit = $("#submit"),
            acceptingModalWindow = $("#example-modal-sizes-title-lg");


    private final CalendarComponent calendarComponent = new CalendarComponent();

    @Step("Открытие страницы с формой")
    public PracticeFormPage openPracticeFormPage() {
        open(practiceFormPage);
        return this;
    }

    @Step("Вводим имя")
    public PracticeFormPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    @Step("Вводим фамилию")
    public PracticeFormPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    @Step("Вводим емейл")
    public PracticeFormPage setEmail(String studentEmail) {
        email.setValue(studentEmail);
        return this;
    }

    @Step("Вводим номер")
    public PracticeFormPage setPhoneNumber(String studentPhoneNumber) {
        phoneNumber.setValue(studentPhoneNumber);
        return this;
    }

    @Step("Вводим пол")
    public PracticeFormPage selectGender(String gender) {
        studentGender.$(byText(gender)).click();
        return this;
    }

    @Step("Выбираем день рождения")
    public PracticeFormPage setBirthDate(BirthDate birthDate) {
        calendarComponent.setDate(birthDate.getBirthDay(), birthDate.getBirthMonth(), birthDate.getBirthYear());
        return this;
    }

    @Step("ЗАполняем поля Subject")
    public PracticeFormPage setStudentSubjects(List<String> subject) {
        subject.forEach(s -> studentSubjects.setValue(s).pressEnter());
        return this;
    }

    @Step("Выбираем хобби")
    public PracticeFormPage selectHobbies(List<String> hobbies) {
        hobbies.forEach(h -> studentHobbies.$(byText(h)).click());
        return this;
    }

    @Step("Загрузка фотографии")
    public PracticeFormPage uploadPicture(String fileName) {
        uploadPicture.uploadFromClasspath(fileName);
        return this;
    }

    @Step("Выбираем адрес")
    public PracticeFormPage setStudentAddress(String address) {
        studentAddress.setValue(address);
        return this;
    }

    @Step("Выбираем штат")
    public PracticeFormPage setStudentState(String state) {
        studentState.scrollTo().click();
        studentState.$(byText(state)).click();
        return this;
    }

    @Step("Выбираем город")
    public PracticeFormPage setStudentCity(String city) {
        studentCity.click();
        studentCity.$(byText(city)).scrollTo().click();
        return this;
    }

    @Step("Нажимаем кнопку Отправить")
    public void submit() {
        submit.click();
    }

    @Step("Проверка данных")
    public PracticeFormPage validateSubmittingFormTitle() {
        acceptingModalWindow.shouldHave(text("Thanks for submitting the form"));
        return this;
    }

    public PracticeFormPage setStudentModel(StudentModel studentModel) {
        this.studentModel = studentModel;
        this.setFirstName(studentModel.getFirstName())
                .setLastName(studentModel.getLastName())
                .setEmail(studentModel.getEmail())
                .selectGender(studentModel.getGender())
                .setPhoneNumber(studentModel.getMobilePhone())
                .setBirthDate(studentModel.getBirthDate())
                .setStudentSubjects(studentModel.getSubjects())
                .selectHobbies(studentModel.getHobbies())
                .uploadPicture(studentModel.getPicture())
                .setStudentAddress(studentModel.getCurrentAddress())
                .setStudentState(studentModel.getState())
                .setStudentCity(studentModel.getCity());
        return this;
    }

    @Step("Проверка данных")
    public PracticeFormPage validateSubmittingFormFields() {
        String browserResponse = getBrowserResponseExecutingJs("get_table_data.js");
        SubmitFormModel submitFormModel = convertResponseToModel(browserResponse, SubmitFormModel.class);
        checksubmitForm(submitFormModel);
        return this;
    }

    @Step("Проверка данных")
    private void checksubmitForm(SubmitFormModel submitFormModel) {
        String regex = "[\\[\\]]";
        assertEquals(studentModel.getFirstName() + " " + studentModel.getLastName(), submitFormModel.getStudentName());
        assertEquals(studentModel.getEmail(), submitFormModel.getStudentEmail());
        assertEquals(studentModel.getGender(), submitFormModel.getGender());
        assertEquals(studentModel.getMobilePhone(), submitFormModel.getMobile());

        BirthDate birthDate = studentModel.getBirthDate();
        assertEquals(
                birthDate.getBirthDay()
                        + " "
                        + birthDate.getBirthMonth()
                        + ","
                        + birthDate.getBirthYear(),
                submitFormModel.getDateOfBirth()
        );

        assertEquals(studentModel.getSubjects().toString().replaceAll(regex, ""), submitFormModel.getSubjects());
        assertEquals(studentModel.getHobbies().toString().replaceAll(regex, ""), submitFormModel.getHobbies());
        assertEquals(studentModel.getPicture(), submitFormModel.getPicture());
        assertEquals(studentModel.getCurrentAddress(), submitFormModel.getAddress());
        assertEquals(studentModel.getState() + " " + studentModel.getCity(), submitFormModel.getStateAndCity());
    }
}