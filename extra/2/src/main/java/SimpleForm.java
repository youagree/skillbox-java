import javax.swing.*;
import java.awt.event.ActionListener;

public class SimpleForm extends JPanel{
    private JPanel rootPanel;
    private JLabel labelFio;
    private JTextField textFIO;
    private JButton button;

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        rootPanel=this;
    }

    public Person getPerson(){
        String fio[]=textFIO.getText().trim().split("\\s++");
        return Person.builder()
                .name(fio.length > 0 ? fio[0] : "")
                .surname(fio.length > 1 ? fio[1] : "")
                .fathername(fio.length > 2 ? fio[2] : "")
                .build();
        }

    public void setPerson(Person person){
        textFIO.setText(person.toString());
    }
    public void addApplyPerson(ActionListener listener){
        button.addActionListener(listener);
    }
    public void removeApplyPerson(ActionListener listener){
        button.removeActionListener(listener);
    }
}
