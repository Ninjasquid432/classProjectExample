package controller;

import model.NavigableDocModel;
import view.NavDocView;

import javax.swing.*;

public class NavDocController {

    //fields
    private NavDocView view;
    private NavigableDocModel model;

    //constructor
    public NavDocController(NavDocView v, NavigableDocModel mdl){
        this.view = v;
        this.model = mdl;

        //ADD ACTION BUTTON LISTENER
        view.form.getAddButton().addActionListener(e -> {

            String addText = view.form.getAddTextField().getText();

            //1. validation check
            if(addText.isEmpty()){
                JOptionPane.showMessageDialog(view,
                        "Error: text field is empty!");
                return;
            }

            //2. add valid input
            model.insertNewItemRt(addText); //todo: check

            //3. update UI
            view.form.getNavDocsContent().setText(model.toString());
            view.form.getAddTextField().setText("");

        });

        //RESET BUTTON LISTENER
        view.form.getResetButton().addActionListener(e -> {
            //1: rest the list
            model.reset();

            //2: update view
            view.form.getNavDocsContent().setText(model.toString());
        });

        //FORWARD BUTTON LISTENER
        view.form.getForwardButton().addActionListener(e -> {

            //1: validation check
            if(model.lenAfterBar() == 0){
                JOptionPane.showMessageDialog(view,
                        "Error: cant move forward! Nothing on right");
                return;
            }
            //2: move cursor forward
            model.forward();

            //3: update view
            view.form.getNavDocsContent().setText(model.toString());

        });

        //CONTAINS BUTTON LISTENER
        view.form.getContainsButton().addActionListener(e -> {

            String addText = view.form.getAddTextField().getText();

            //1. validation check
            if(addText.isEmpty()){
                JOptionPane.showMessageDialog(view,
                        "Error: text field is empty!");
                return;
            }


            //3. update form
            JOptionPane.showMessageDialog(view,
                    model.contains(addText));
           // view.form.getNavDocsContent().setText(model.toString());
        });
    }
}
