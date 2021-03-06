package com.epam.lab.page;

import com.epam.lab.control.Button;
import com.epam.lab.control.CheckBox;
import com.epam.lab.control.Label;
import com.epam.lab.control.TableRow;
import com.epam.lab.wait.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class GmailInboxPage extends PageObject {
    private static final Logger LOG = LoggerFactory.getLogger(GmailInboxPage.class);
    private static final String CHECKBOX_XPATH = ".//div[@role='checkbox']";
    private static final String UNDO_LINK_XPATH = "//span[@id='link_undo']";
    private static final String DELETE_BUTTON_XPATH = ".//div[@class ='asa']";
    private static final String UNDO_DELETE_BUTTON_XPATH = "//span[@id='link_undo']";
    private static final String INBOX_MESSAGE_LIST_XPATH = ".//tr[contains(@class, 'zA')]";
    private static final String RESTORED_MESSAGE_XPATH = "//span[@class = 'bAq']";

    @FindBy(xpath = CHECKBOX_XPATH)
    private List<CheckBox> checkbox;

    @FindBy(xpath = DELETE_BUTTON_XPATH)
    private Button deleteButton;

    @FindBy(xpath = UNDO_LINK_XPATH)
    private Button undoButtron;

    @FindBy(xpath = UNDO_DELETE_BUTTON_XPATH)
    private Button undoDelete;

    @FindBy(xpath = INBOX_MESSAGE_LIST_XPATH)
    private List<TableRow> inboxMessages;

    @FindBy(xpath = RESTORED_MESSAGE_XPATH)
    private Label restoredMessage;


    public GmailInboxPage() {
    }

    public GmailInboxPage(WebDriver driver) {
        super(driver);
    }


    public void selectMessage(int amount) {
        LOG.info("Click message checkBox on {} ", this.getClass().getSimpleName());
        int i = 0;
        List<TableRow> msg = new ArrayList<>(inboxMessages);
        for (TableRow message : msg) {
            CheckBox checkBox = new CheckBox(message.findElement(By.xpath(CHECKBOX_XPATH)));
            if (!checkBox.isSelected() && i < amount) {
                checkBox.click();
                i++;
            }
        }
    }

    public void deleteSelectedMessage() {
        LOG.info("Click delete button on {}", this.getClass().getSimpleName());
        Wait.waitForElementDisplayed(driver, DELETE_BUTTON_XPATH);
        deleteButton.click();

    }

    public void undoDeleteOperation() {
        LOG.info("Restore deleted messages ... ");
        Wait.waitForElementDisplayed(driver, UNDO_LINK_XPATH);
        undoButtron.click();
    }

    public boolean isMessagesDeleted() {
        return undoDelete.isDisplayed();
    }

    public boolean isMessageRestored() {
        return restoredMessage.isDisplayed();
    }

    public String getMessage() {
        return undoDelete.getText();
    }


}